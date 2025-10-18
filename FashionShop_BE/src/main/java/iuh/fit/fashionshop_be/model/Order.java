/*
 * @ (#) Order.java     1.0    17-Oct-25
 *
 * Copyright (c) 2025 IUH. All rights reserved.
 */

package iuh.fit.fashionshop_be.model;

/*
 * @description:
 * @author: Nguyen Quoc Huy
 * @date:17-Oct-25
 * @version: 1.0
 */
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

    @Id
    @Column(name = "OrderID")
    private String orderID;

    @ManyToOne
    @JoinColumn(name = "CustomerID", nullable = false)
    private Customer customer;

    @Column(name = "OrderDate")
    private LocalDateTime orderDate;

    @Column(name = "TotalAmount")
    private Float totalAmount;

    @Column(name = "PaymentMethod")
    private String paymentMethod;

    @Column(name = "PaymentStatus")
    private String paymentStatus;

    @Column(name = "TransactionID")
    private String transactionID;

    @Column(name = "PaymentDate")
    private LocalDateTime paymentDate;

    @Column(name = "OrderStatus")
    private String orderStatus;

    @Column(name = "AddressID")
    private String addressID;

    @Column(name = "ShippingFee")
    private Float shippingFee;

    @Column(name = "Notes")
    private String notes;

    // Quan hệ
    @ManyToOne
    @JoinColumn(name = "AddressID", insertable = false, updatable = false)
    @JsonIgnore
    private Address shippingAddress;

    @OneToMany(mappedBy = "order")
    @JsonIgnore
    private List<OrderItem> orderItems;

    @OneToOne(mappedBy = "order")
    @JsonIgnore
    private Shipping shipping;

    @ManyToOne
    @JoinColumn(name = "CouponCode", insertable = false, updatable = false)
    @JsonIgnore
    private Coupon coupon;
}