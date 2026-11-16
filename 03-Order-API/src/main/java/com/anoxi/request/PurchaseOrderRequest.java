package com.anoxi.request;

import com.anoxi.dto.CustomerDto;
import com.anoxi.dto.OrderDto;
import com.anoxi.dto.OrderItemDto;
import com.anoxi.dto.ShippingAddressDto;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Data
public class PurchaseOrderRequest {

    private CustomerDto customer;
    private ShippingAddressDto address;  //always keep frontend request data name
    private OrderDto order;
    private List<OrderItemDto> orderItems = new ArrayList<>();
}