package com.anoxi.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Table(name = "order_tbl")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer orderId;

    @Column(unique = true, updatable = false)
    private String orderTrackingNum;

    @Column
    private Integer totalQuantity;

    @Column
    private Double totalPrice;

    @Column
    private String orderStatus;

    @CreationTimestamp
    private LocalDateTime createdDate;

    @CreationTimestamp
    private LocalDateTime lastUpdated;

    @Column
    private LocalDate deliveryDate;

    @Column
    private String paymentStatus;

    @Column
    private String razorpayOrderId;

    @Column
    private String razorpayPaymentId;

    @Column
    private String invoiceUrl;

    private String email;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "addr_id")
    private ShippingAddress shippingAddress;

}

