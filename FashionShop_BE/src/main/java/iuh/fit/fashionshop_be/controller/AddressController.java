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
import iuh.fit.fashionshop_be.dto.AddressDTO;
import iuh.fit.fashionshop_be.model.Address;
import iuh.fit.fashionshop_be.model.Customer;
import iuh.fit.fashionshop_be.repository.CustomerRepository;
import iuh.fit.fashionshop_be.service.AddressService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AddressController {

    private final AddressService addressService;
    private final CustomerRepository customerRepository;

    public AddressController(AddressService addressService, CustomerRepository customerRepository) {
        this.addressService = addressService;
        this.customerRepository = customerRepository;
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
    public ResponseEntity<?> createAddress(@RequestBody AddressDTO dto) {
        try {
            Customer customer = customerRepository.findById(dto.getCustomerId())
                    .orElseThrow(() -> new RuntimeException("Customer not found with id: " + dto.getCustomerId()));

            Address address = Address.builder()
                    .customer(customer)
                    .recipientName(dto.getRecipientName())
                    .recipientPhone(dto.getRecipientPhone())
                    .streetAddress(dto.getStreetAddress())
                    .district(dto.getDistrict())
                    .city(dto.getCity())
                    .postalCode(dto.getPostalCode())
                    .country(dto.getCountry())
                    .isDefault(dto.getIsDefault())
                    .build();

            Address saved = addressService.createAddress(address);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);

        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(java.util.Map.of("message", ex.getMessage()));
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(java.util.Map.of("message", "Uncategorized error"));
        }
    }

    @PutMapping("/addresses/{id}")
    public ResponseEntity<?> updateAddress(@PathVariable Long id, @RequestBody AddressDTO dto) {
        try {
            Customer customer = customerRepository.findById(dto.getCustomerId())
                    .orElseThrow(() -> new RuntimeException("Customer not found with id: " + dto.getCustomerId()));

            Address addressDetails = Address.builder()
                    .customer(customer)
                    .recipientName(dto.getRecipientName())
                    .recipientPhone(dto.getRecipientPhone())
                    .streetAddress(dto.getStreetAddress())
                    .district(dto.getDistrict())
                    .city(dto.getCity())
                    .postalCode(dto.getPostalCode())
                    .country(dto.getCountry())
                    .isDefault(dto.getIsDefault())
                    .build();

            Address updated = addressService.updateAddress(id, addressDetails);
            return ResponseEntity.ok(updated);

        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(java.util.Map.of("message", ex.getMessage()));
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(java.util.Map.of("message", "Uncategorized error"));
        }
    }


    @DeleteMapping("/addresses/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id) {
        addressService.deleteAddress(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/addresses/{id}/default")
    public ResponseEntity<Address> setDefaultAddress(@PathVariable Long id) {
        return ResponseEntity.ok(addressService.setDefaultAddress(id));
    }

}