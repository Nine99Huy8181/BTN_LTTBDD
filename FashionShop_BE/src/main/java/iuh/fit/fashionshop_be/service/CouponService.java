/*
 * @ (#) f.java     1.0    17-Oct-25
 *
 * Copyright (c) 2025 IUH. All rights reserved.
 */

package iuh.fit.fashionshop_be.service;

/*
 * @description:
 * @author: Nguyen Quoc Huy
 * @date:17-Oct-25
 * @version: 1.0
 */
import iuh.fit.fashionshop_be.model.Coupon;
import iuh.fit.fashionshop_be.repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CouponService {
    private final CouponRepository couponRepository;

    public List<Coupon> getAllCoupons() {
        return couponRepository.findAll();
    }

    public Coupon getCouponById(Long id) {
        return couponRepository.findById(id).orElseThrow(() -> new RuntimeException("Coupon not found"));
    }

    public Coupon getCouponByCode(String code) {
        return couponRepository.findByCode(code);
    }

    public List<Coupon> getCouponsByStatus(String status) {
        return couponRepository.findByStatus(status);
    }

    public Coupon createCoupon(Coupon coupon) {
        return couponRepository.save(coupon);
    }

    public Coupon updateCoupon(Long id, Coupon couponDetails) {
        Coupon coupon = getCouponById(id);
        coupon.setCode(couponDetails.getCode());
        coupon.setDescription(couponDetails.getDescription());
        coupon.setDiscountValue(couponDetails.getDiscountValue());
        coupon.setDiscountType(couponDetails.getDiscountType());
        coupon.setStartDate(couponDetails.getStartDate());
        coupon.setEndDate(couponDetails.getEndDate());
        coupon.setMaxUses(couponDetails.getMaxUses());
        coupon.setUsedCount(couponDetails.getUsedCount());
        coupon.setConditions(couponDetails.getConditions());
        coupon.setStatus(couponDetails.getStatus());
        return couponRepository.save(coupon);
    }

    public void deleteCoupon(Long id) {
        couponRepository.deleteById(id);
    }
    public List<Coupon> getAvailableCoupons() {
        LocalDate today = LocalDate.now();
        return couponRepository.findAll().stream()
                .filter(c -> "ACTIVE".equalsIgnoreCase(c.getStatus()))
                .filter(c -> (c.getStartDate() == null || !c.getStartDate().isAfter(today)))
                .filter(c -> (c.getEndDate() == null || !c.getEndDate().isBefore(today)))
                .filter(c -> c.getMaxUses() == null || (c.getUsedCount() == null || c.getUsedCount() < c.getMaxUses()))
                .collect(Collectors.toList());
    }
}