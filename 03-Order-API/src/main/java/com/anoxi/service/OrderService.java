package com.anoxi.service;

import com.anoxi.dto.OrderDto;
import com.anoxi.dto.PaymentCallBackDto;
import com.anoxi.request.PurchaseOrderRequest;
import com.anoxi.response.PurchaseOrderResponse;

import java.util.List;

public interface OrderService {

    PurchaseOrderResponse createOrder(PurchaseOrderRequest orderRequest);

    List<OrderDto> getOrdersByEmail(String email);

    PurchaseOrderResponse updateOrder(OrderDto orderDto);



}
