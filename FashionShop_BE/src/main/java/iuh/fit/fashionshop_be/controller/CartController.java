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
import iuh.fit.fashionshop_be.model.Cart;
import iuh.fit.fashionshop_be.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/carts/{id}")
    public ResponseEntity<Cart> getCartById(@PathVariable Long id) {
        return ResponseEntity.ok(cartService.getCartById(id));
    }

    @GetMapping("/carts/customer/{customerId}")
    public ResponseEntity<Cart> getCartByCustomerId(@PathVariable Long customerId) {
        return ResponseEntity.ok(cartService.getCartByCustomerId(customerId));
    }

    @PostMapping("/carts")
    public ResponseEntity<Cart> createCart(@RequestBody Cart cart) {
        return ResponseEntity.status(201).body(cartService.createCart(cart));
    }

    @PutMapping("/carts/{id}")
    public ResponseEntity<Cart> updateCart(@PathVariable Long id, @RequestBody Cart cartDetails) {
        return ResponseEntity.ok(cartService.updateCart(id, cartDetails));
    }
}