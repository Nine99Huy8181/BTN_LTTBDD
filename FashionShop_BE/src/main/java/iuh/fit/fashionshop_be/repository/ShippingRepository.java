/*
 * @ (#) ShippingRopository.java     1.0    17-Oct-25
 *
 * Copyright (c) 2025 IUH. All rights reserved.
 */

package iuh.fit.fashionshop_be.repository;

import iuh.fit.fashionshop_be.model.Shipping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
 * @description:
 * @author:
 * @date:17-Oct-25
 * @version: 1.0
 */
@Repository
public interface ShippingRepository extends JpaRepository<Shipping, String> {
    Shipping findByOrderOrderID(String orderID);
    List<Shipping> findByShippingStatus(String shippingStatus);
}