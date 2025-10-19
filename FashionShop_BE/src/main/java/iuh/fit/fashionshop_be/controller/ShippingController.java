/*
 * @ (#) d.java     1.0    17-Oct-25
 *
 * Copyright (c) 2025 IUH. All rights reserved.
 */

package iuh.fit.fashionshop_be.controller;

/*
 * @description:
 * @author: Nguyen Quoc Huy
 * @date:17-Oct-25
 * @version: 1.0
 */
import iuh.fit.fashionshop_be.model.Shipping;
import iuh.fit.fashionshop_be.service.ShippingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ShippingController {

    private final ShippingService shippingService;

    public ShippingController(ShippingService shippingService) {
        this.shippingService = shippingService;
    }

    @GetMapping("/shippings")
    public ResponseEntity<List<Shipping>> getAllShippings() {
        return ResponseEntity.ok(shippingService.getAllShippings());
    }

    @GetMapping("/shippings/{id}")
    public ResponseEntity<Shipping> getShippingById(@PathVariable Long id) {
        return ResponseEntity.ok(shippingService.getShippingById(id));
    }

    @GetMapping("/shippings/order/{orderId}")
    public ResponseEntity<Shipping> getShippingByOrderId(@PathVariable Long orderId) {
        return ResponseEntity.ok(shippingService.getShippingByOrderId(orderId));
    }

    @PostMapping("/shippings")
    public ResponseEntity<Shipping> createShipping(@RequestBody Shipping shipping) {
        return ResponseEntity.status(201).body(shippingService.createShipping(shipping));
    }

    @PutMapping("/shippings/{id}")
    public ResponseEntity<Shipping> updateShipping(@PathVariable Long id, @RequestBody Shipping shippingDetails) {
        return ResponseEntity.ok(shippingService.updateShipping(id, shippingDetails));
    }
}