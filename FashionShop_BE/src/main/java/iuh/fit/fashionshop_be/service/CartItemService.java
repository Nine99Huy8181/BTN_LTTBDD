/*
 * @ (#) d.java     1.0    17-Oct-25
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
import iuh.fit.fashionshop_be.model.CartItem;
import iuh.fit.fashionshop_be.repository.CartItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartItemService {
    private final CartItemRepository cartItemRepository;

    public List<CartItem> getAllCartItems() {
        return cartItemRepository.findAll();
    }

    public CartItem getCartItemById(Long id) {
        return cartItemRepository.findById(id).orElseThrow(() -> new RuntimeException("CartItem not found"));
    }

    public List<CartItem> getCartItemsByCartId(Long cartID) {
        return cartItemRepository.findByCartCartID(cartID);
    }

    public List<CartItem> getCartItemsByVariantId(Long variantID) {
        return cartItemRepository.findByVariantVariantID(variantID);
    }

    public CartItem createCartItem(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }

    public CartItem updateCartItem(Long id, CartItem itemDetails) {
        CartItem item = getCartItemById(id);
        item.setCart(itemDetails.getCart());
        item.setVariant(itemDetails.getVariant());
        item.setQuantity(itemDetails.getQuantity());
        return cartItemRepository.save(item);
    }

    public void deleteCartItem(Long id) {
        cartItemRepository.deleteById(id);
    }
}