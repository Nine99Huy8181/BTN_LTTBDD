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
import iuh.fit.fashionshop_be.model.Customer;
import iuh.fit.fashionshop_be.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    public List<Customer> getCustomersByAccountId(Long accountID) {
        return customerRepository.findByAccountAccountID(accountID);
    }

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(Long id, Customer customerDetails) {
        Customer customer = getCustomerById(id);
        customer.setAccount(customerDetails.getAccount());
        customer.setFullName(customerDetails.getFullName());
        customer.setPhoneNumber(customerDetails.getPhoneNumber());
        customer.setDateOfBirth(customerDetails.getDateOfBirth());
        customer.setGender(customerDetails.getGender());
        customer.setLoyaltyPoints(customerDetails.getLoyaltyPoints());
        customer.setReferralCode(customerDetails.getReferralCode());
        return customerRepository.save(customer);
    }


    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}