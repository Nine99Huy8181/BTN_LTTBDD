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
import iuh.fit.fashionshop_be.model.Shipping;
import iuh.fit.fashionshop_be.repository.ShippingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShippingService {
    private final ShippingRepository shippingRepository;

    public List<Shipping> getAllShippings() {
        return shippingRepository.findAll();
    }

    public Shipping getShippingById(Long id) {
        return shippingRepository.findById(id).orElseThrow(() -> new RuntimeException("Shipping not found"));
    }

    public Shipping getShippingByOrderId(Long orderID) {
        return shippingRepository.findByOrderOrderID(orderID);
    }

    public List<Shipping> getShippingsByStatus(String shippingStatus) {
        return shippingRepository.findByShippingStatus(shippingStatus);
    }

    public Shipping createShipping(Shipping shipping) {
        return shippingRepository.save(shipping);
    }

    public Shipping updateShipping(Long id, Shipping shippingDetails) {
        Shipping shipping = getShippingById(id);
        shipping.setOrder(shippingDetails.getOrder());
        shipping.setCarrier(shippingDetails.getCarrier());
        shipping.setTrackingNumber(shippingDetails.getTrackingNumber());
        shipping.setShippingFee(shippingDetails.getShippingFee());
        shipping.setEstimatedDeliveryDate(shippingDetails.getEstimatedDeliveryDate());
        shipping.setShippingStatus(shippingDetails.getShippingStatus());
        return shippingRepository.save(shipping);
    }

    public void deleteShipping(Long id) {
        shippingRepository.deleteById(id);
    }
}