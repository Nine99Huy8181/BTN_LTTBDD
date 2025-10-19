/*
 * @ (#) g.java     1.0    17-Oct-25
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
import iuh.fit.fashionshop_be.model.Wishlist;
import iuh.fit.fashionshop_be.service.WishlistService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class WishlistController {

    private final WishlistService wishlistService;

    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @GetMapping("/wishlists/{id}")
    public ResponseEntity<Wishlist> getWishlistById(@PathVariable Long id) {
        return ResponseEntity.ok(wishlistService.getWishlistById(id));
    }

    @GetMapping("/wishlists/customer/{customerId}")
    public ResponseEntity<Wishlist> getWishlistByCustomerId(@PathVariable Long customerId) {
        return ResponseEntity.ok(wishlistService.getWishlistByCustomerId(customerId));
    }

    @PostMapping("/wishlists")
    public ResponseEntity<Wishlist> createWishlist(@RequestBody Wishlist wishlist) {
        return ResponseEntity.status(201).body(wishlistService.createWishlist(wishlist));
    }

    @PutMapping("/wishlists/{id}")
    public ResponseEntity<Wishlist> updateWishlist(@PathVariable Long id, @RequestBody Wishlist wishlistDetails) {
        return ResponseEntity.ok(wishlistService.updateWishlist(id, wishlistDetails));
    }
}