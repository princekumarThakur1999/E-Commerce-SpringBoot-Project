package com.anoxi.restcontroller;

import com.anoxi.apiresponse.APIResponse;
import com.anoxi.dto.OrderDto;
import com.anoxi.request.PurchaseOrderRequest;
import com.anoxi.response.PurchaseOrderResponse;
import com.anoxi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class OrderRestController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/order")
    public ResponseEntity<APIResponse<PurchaseOrderResponse>> createOrder(
            @RequestBody PurchaseOrderRequest orderRequest
            ) throws Exception{

        PurchaseOrderResponse orderResDto = orderService.createOrder(orderRequest);

        APIResponse<PurchaseOrderResponse> response = new APIResponse<>();

        response.setStatus(201);
        response.setMsg("Order Placed Successfully");
        response.setData(orderResDto);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/order")
    public ResponseEntity<APIResponse<PurchaseOrderResponse>> updateOrder(
            @RequestBody OrderDto orderDto
    ) throws Exception{

        PurchaseOrderResponse orderResDto = orderService.updateOrder(orderDto);

        APIResponse<PurchaseOrderResponse> response = new APIResponse<>();

        response.setStatus(200);
        response.setMsg("Order Updated Successfully");
        response.setData(orderResDto);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/orders/{email}")
    public ResponseEntity<APIResponse<List<OrderDto>>> getOrders(@PathVariable("email") String email) throws Exception{

        List<OrderDto> orderList = orderService.getOrdersByEmail(email);

        APIResponse<List<OrderDto>> response = new APIResponse<>();

        response.setStatus(200);
        response.setMsg("Fetched Orders Successfully");
        response.setData(orderList);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
