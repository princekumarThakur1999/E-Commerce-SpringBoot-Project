package com.anoxi.dto;

import lombok.Data;

@Data
public class OrderItemDto {

    private String orderItemId;
    private String productName;   //future will see in the entity this value is not present
    private String imageUrl;
    private String unitPrice;
    private Integer quanity;
}
