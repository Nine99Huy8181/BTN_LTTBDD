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
import iuh.fit.fashionshop_be.model.Cart;
import iuh.fit.fashionshop_be.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;

    public Cart getCartById(String id) {
        return cartRepository.findById(id).orElseThrow(() -> new RuntimeException("Cart not found"));
    }

    public Cart getCartByCustomerId(String customerID) {
        return cartRepository.findByCustomerCustomerID(customerID);
    }

    public Cart createCart(Cart cart) {
        return cartRepository.save(cart);
    }

    public Cart updateCart(String id, Cart cartDetails) {
        Cart cart = getCartById(id);
        cart.setCustomer(cartDetails.getCustomer());
        cart.setUpdatedDate(cartDetails.getUpdatedDate());
        cart.setTotalAmount(cartDetails.getTotalAmount());
        return cartRepository.save(cart);
    }

    public void deleteCart(String id) {
        cartRepository.deleteById(id);
    }
}