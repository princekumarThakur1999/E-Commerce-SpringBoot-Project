package com.anoxi.service.impl;

import com.anoxi.service.RazorpayService;
import com.razorpay.RazorpayClient;
import com.razorpay.Order;
import lombok.SneakyThrows;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RazorpayServiceImpl implements RazorpayService {

    @Value("${razorpay.key.id}")
    private String keyId;

    @Value("${razorpay.secret.key}")
    private String keySecret;

    private RazorpayClient razorpayClient;

    @Override
    @SneakyThrows
    public Order createPaymentOrder(double amount, String email) {

        this.razorpayClient = new RazorpayClient(keyId, keySecret);

        JSONObject orderRequest = new JSONObject();
        orderRequest.put("amount", amount*100);  //amount is paisa
        orderRequest.put("currency", "INR");
        orderRequest.put("payment_capture", 1);
        orderRequest.put("receipt", email);

        return razorpayClient.orders.create(orderRequest);
    }
}
