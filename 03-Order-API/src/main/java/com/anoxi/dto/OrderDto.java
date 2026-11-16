package com.anoxi.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class OrderDto {

    private Integer orderId;  //no need to write
    private String orderTrackingNum;
    private Integer totalQuantity;
    private Double totalPrice;
    private String orderStatus;
    private String email;
    private LocalDate deliveryDate;
    private String paymentStatus;
    private String razorpayOrderId;
    private String razorpayPaymentId;
    private String invoiceUrl;
}
