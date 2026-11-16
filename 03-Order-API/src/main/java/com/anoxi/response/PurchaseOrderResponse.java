package com.anoxi.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PurchaseOrderResponse {

    public String razorpayOrderId;
    public String orderStatus;
    public String orderTrackingNumber;

}
