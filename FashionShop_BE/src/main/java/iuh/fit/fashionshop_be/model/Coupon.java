/*
 * @ (#) v.java     1.0    17-Oct-25
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
@Table(name = "coupons")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Coupon {

    @Id
    @Column(name = "CouponID")
    private String couponID;

    @Column(name = "Code", unique = true)
    private String code;

    @Column(name = "Description")
    private String description;

    @Column(name = "DiscountValue")
    private Float discountValue;

    @Column(name = "DiscountType")
    private String discountType;

    @Column(name = "StartDate")
    private LocalDate startDate;

    @Column(name = "EndDate")
    private LocalDate endDate;

    @Column(name = "MaxUses")
    private Integer maxUses;

    @Column(name = "UsedCount")
    private Integer usedCount;

    @Column(name = "Conditions")
    private String conditions;

    @Column(name = "Status")
    private String status;
}