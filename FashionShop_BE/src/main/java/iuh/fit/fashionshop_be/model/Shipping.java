/*
 * @ (#) k.java     1.0    17-Oct-25
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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "shipping")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Shipping {

    @Id
    @Column(name = "ShippingID")
    private String shippingID;

    @OneToOne
    @JoinColumn(name = "OrderID", nullable = false)
    private Order order;

    @Column(name = "Carrier")
    private String carrier;

    @Column(name = "TrackingNumber")
    private String trackingNumber;

    @Column(name = "ShippingFee")
    private Float shippingFee;

    @Column(name = "EstimatedDeliveryDate")
    private LocalDate estimatedDeliveryDate;

    @Column(name = "ShippingStatus")
    private String shippingStatus;
}