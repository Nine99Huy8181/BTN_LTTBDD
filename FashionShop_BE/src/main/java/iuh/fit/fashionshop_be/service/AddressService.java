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

    public Address getAddressById(Long id) {
        return addressRepository.findById(id).orElseThrow(() -> new RuntimeException("Address not found"));
    }

    public List<Address> getAddressesByCustomerId(Long customerID) {
        return addressRepository.findByCustomerCustomerID(customerID);
    }

    public Address createAddress(Address address) {
        // Nếu địa chỉ mới được đánh dấu là mặc định
        if (Boolean.TRUE.equals(address.getIsDefault())) {
            List<Address> existingAddresses = addressRepository.findByCustomerCustomerID(address.getCustomer().getCustomerID());
            for (Address existing : existingAddresses) {
                if (Boolean.TRUE.equals(existing.getIsDefault())) {
                    existing.setIsDefault(false);
                    addressRepository.save(existing);
                }
            }
        }

        // Lưu địa chỉ mới
        return addressRepository.save(address);
    }



    public Address updateAddress(Long id, Address addressDetails) {
        Address address = getAddressById(id);

        // Nếu địa chỉ này được đánh dấu là mặc định,
        // cần bỏ mặc định của các địa chỉ khác cùng customer
        if (Boolean.TRUE.equals(addressDetails.getIsDefault())) {
            List<Address> existingAddresses = addressRepository.findByCustomerCustomerID(
                    address.getCustomer().getCustomerID()
            );
            for (Address existing : existingAddresses) {
                if (!existing.getAddressID().equals(id) && Boolean.TRUE.equals(existing.getIsDefault())) {
                    existing.setIsDefault(false);
                    addressRepository.save(existing);
                }
            }
        }

        // Cập nhật các thuộc tính
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


    public Address setDefaultAddress(Long id) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Address not found with id: " + id));

        Long customerId = address.getCustomer().getCustomerID();

        // Bước 1: Bỏ mặc định tất cả các địa chỉ khác cùng customer
        List<Address> addresses = addressRepository.findByCustomerCustomerID(customerId);
        for (Address addr : addresses) {
            if (!addr.getAddressID().equals(id) && Boolean.TRUE.equals(addr.getIsDefault())) {
                addr.setIsDefault(false);
                addressRepository.save(addr);
            }
        }

        // Bước 2: Đặt địa chỉ này làm mặc định
        address.setIsDefault(true);
        addressRepository.save(address);

        return address;
    }

    public void deleteAddress(Long id) {
        addressRepository.deleteById(id);
    }
}