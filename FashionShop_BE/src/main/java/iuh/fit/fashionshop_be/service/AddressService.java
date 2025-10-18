/*
 * @ (#) df.java     1.0    17-Oct-25
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
import iuh.fit.fashionshop_be.model.Address;
import iuh.fit.fashionshop_be.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;

    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    public Address getAddressById(String id) {
        return addressRepository.findById(id).orElseThrow(() -> new RuntimeException("Address not found"));
    }

    public List<Address> getAddressesByCustomerId(String customerID) {
        return addressRepository.findByCustomerCustomerID(customerID);
    }

    public Address createAddress(Address address) {
        return addressRepository.save(address);
    }

    public Address updateAddress(String id, Address addressDetails) {
        Address address = getAddressById(id);
        address.setCustomer(addressDetails.getCustomer());
        address.setRecipientName(addressDetails.getRecipientName());
        address.setRecipientPhone(addressDetails.getRecipientPhone());
        address.setStreetAddress(addressDetails.getStreetAddress());
        address.setDistrict(addressDetails.getDistrict());
        address.setCity(addressDetails.getCity());
        address.setPostalCode(addressDetails.getPostalCode());
        address.setCountry(addressDetails.getCountry());
        address.setIsDefault(addressDetails.getIsDefault());
        return addressRepository.save(address);
    }

    public void deleteAddress(String id) {
        addressRepository.deleteById(id);
    }
}