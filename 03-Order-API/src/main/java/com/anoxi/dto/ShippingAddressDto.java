package com.anoxi.dto;

import lombok.Data;

@Data
public class ShippingAddressDto {

    private String hno;
    private String street;
    private String city;
    private String state;
    private String zipcode;
    private String country;
}
