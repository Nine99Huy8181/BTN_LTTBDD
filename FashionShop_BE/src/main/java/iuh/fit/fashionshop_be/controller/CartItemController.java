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
import iuh.fit.fashionshop_be.model.CartItem;
import iuh.fit.fashionshop_be.service.CartItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CartItemController {

    private final CartItemService cartItemService;

    public CartItemController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @GetMapping("/cart-items")
    public ResponseEntity<List<CartItem>> getAllCartItems() {
        return ResponseEntity.ok(cartItemService.getAllCartItems());
    }

    @GetMapping("/cart-items/{id}")
    public ResponseEntity<CartItem> getCartItemById(@PathVariable String id) {
        return ResponseEntity.ok(cartItemService.getCartItemById(id));
    }

    @GetMapping("/cart-items/cart/{cartId}")
    public ResponseEntity<List<CartItem>> getCartItemsByCartId(@PathVariable String cartId) {
        return ResponseEntity.ok(cartItemService.getCartItemsByCartId(cartId));
    }

    @PostMapping("/cart-items")
    public ResponseEntity<CartItem> createCartItem(@RequestBody CartItem cartItem) {
        return ResponseEntity.status(201).body(cartItemService.createCartItem(cartItem));
    }

    @PutMapping("/cart-items/{id}")
    public ResponseEntity<CartItem> updateCartItem(@PathVariable String id, @RequestBody CartItem itemDetails) {
        return ResponseEntity.ok(cartItemService.updateCartItem(id, itemDetails));
    }
}