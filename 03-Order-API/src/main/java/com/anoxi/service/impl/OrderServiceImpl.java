package com.anoxi.service.impl;

import com.anoxi.dto.*;
import com.anoxi.entities.*;
import com.anoxi.repo.CustomerRepo;
import com.anoxi.repo.OrderItemsRepo;
import com.anoxi.repo.OrderRepo;
import com.anoxi.repo.ShippingAddressRepo;
import com.anoxi.request.PurchaseOrderRequest;
import com.anoxi.response.PurchaseOrderResponse;
import com.anoxi.service.OrderService;
import com.anoxi.service.RazorpayService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private OrderItemsRepo orderItemsRepo;

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private ShippingAddressRepo addressRepo;

    @Autowired
    private RazorpayService razorpayService;

    @Override
    public PurchaseOrderResponse createOrder(PurchaseOrderRequest orderRequest) {

        //taking data from orderRequest and set into dtos and make db operation
        CustomerDto customerDto = orderRequest.getCustomer();
        ShippingAddressDto addressDto = orderRequest.getAddress();
        OrderDto orderDto = orderRequest.getOrder();
        List<OrderItemDto> orderItemList = orderRequest.getOrderItems();

        //Checking customer is present in DB or not. If not then crate a customer and save in DB.
        Optional<Customer> customerRecord = customerRepo.findByEmail(customerDto.getEmail());
        Customer c = new Customer();
        if (customerRecord.isEmpty()){
            //ToDo : Inter service Communication(Feign Client)
            BeanUtils.copyProperties(customerDto, c);
            c = customerRepo.save(c);

        }else {
            c = customerRecord.get();
        }

        //save address
        ShippingAddress address = new ShippingAddress();
        BeanUtils.copyProperties(addressDto, address);

        address.setCustomer(c);    //based on the customer address will be stored  : assosiation mapping
        addressRepo.save(address);

        //save order
        Order  newOrder = new Order();
        String orderTrackingNum = generateOrderTrackingNum();
        newOrder.setOrderTrackingNum(orderTrackingNum);

        //create razorpay order and get order details
        com.razorpay.Order paymentOrder = razorpayService.createPaymentOrder(orderDto.getTotalPrice(), customerDto.getEmail()); //customer email pass
        newOrder.setRazorpayOrderId(paymentOrder.get("id"));
       // newOrder.setOrderStatus(paymentOrder.get("status"));  //future will check this is order status or payemnt status
         newOrder.setPaymentStatus(paymentOrder.get("status")); // i changed orderstatus to payment status

         newOrder.setTotalPrice(orderDto.getTotalPrice());
        newOrder.setTotalQuantity(orderDto.getTotalQuantity());
        newOrder.setEmail(c.getEmail());

        newOrder.setCustomer(c); //association mapping
        newOrder.setShippingAddress(address);

        Order saveOrder = orderRepo.save(newOrder);


        //save Order Items
        for (OrderItemDto itemDto : orderItemList){
            OrderItems orderItem = new OrderItems();
            BeanUtils.copyProperties(itemDto, orderItem);

            orderItem.setOrder(saveOrder);

            orderItemsRepo.save(orderItem);
        }

        //Prepare and return Response
    return PurchaseOrderResponse.builder()
                .razorpayOrderId(paymentOrder.get("id"))
                .orderStatus(paymentOrder.get("status"))
                .orderTrackingNumber(orderTrackingNum)
                .build();
    }

    //Create orderTrackingNumber
    private String generateOrderTrackingNum() {
        //get the current timestamp
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String timestamp = simpleDateFormat.format(new Date());

        //Generate a random UUID for uniqueness
        String randomUUID = UUID.randomUUID().toString().substring(0,5).toUpperCase();

        //Combine timestamp and UUID to form the tracking ID
        return "ORD-" + timestamp + randomUUID;
    }

    @Override
    public List<OrderDto> getOrdersByEmail(String email) {

        List<OrderDto> orderList = new ArrayList<>();
        List<Order> entities = orderRepo.findByEmail(email);

        for(Order entity: entities){

            OrderDto orderDto = new OrderDto();
            BeanUtils.copyProperties(entity, orderDto);
            orderList.add(orderDto);
        }
        return orderList;
    }

    @Override
    public PurchaseOrderResponse updateOrder(OrderDto orderDto) {

        Optional<Order> byId = orderRepo.findById(Integer.valueOf(orderDto.getOrderId()));
        Order order = null;
        if(byId.isPresent()){

            order = byId.get();
            order.setOrderStatus(orderDto.getOrderStatus());
            orderRepo.save(order);
        }
        return PurchaseOrderResponse.builder()
                .razorpayOrderId(order.getRazorpayOrderId())
                .orderStatus(order.getOrderStatus().toString())
                .orderTrackingNumber(order.getOrderTrackingNum())
                .build();
    }
}
