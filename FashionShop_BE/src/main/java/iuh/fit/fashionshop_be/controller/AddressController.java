/*
 * @ (#) f.java     1.0    17-Oct-25
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
import iuh.fit.fashionshop_be.model.Address;
import iuh.fit.fashionshop_be.service.AddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/addresses")
    public ResponseEntity<List<Address>> getAllAddresses() {
        return ResponseEntity.ok(addressService.getAllAddresses());
    }

    @GetMapping("/addresses/{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable Long id) {
        return ResponseEntity.ok(addressService.getAddressById(id));
    }

    @GetMapping("/addresses/customer/{customerId}")
    public ResponseEntity<List<Address>> getAddressesByCustomerId(@PathVariable Long customerId) {
        return ResponseEntity.ok(addressService.getAddressesByCustomerId(customerId));
    }

    @PostMapping("/addresses")
    public ResponseEntity<Address> createAddress(@RequestBody Address address) {
        return ResponseEntity.status(201).body(addressService.createAddress(address));
    }

    @PutMapping("/addresses/{id}")
    public ResponseEntity<Address> updateAddress(@PathVariable Long id, @RequestBody Address addressDetails) {
        return ResponseEntity.ok(addressService.updateAddress(id, addressDetails));
    }
}