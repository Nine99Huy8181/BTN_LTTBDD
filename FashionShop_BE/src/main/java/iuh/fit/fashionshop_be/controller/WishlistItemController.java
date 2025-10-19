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
import iuh.fit.fashionshop_be.model.WishlistItem;
import iuh.fit.fashionshop_be.service.WishlistItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class WishlistItemController {

    private final WishlistItemService wishlistItemService;

    public WishlistItemController(WishlistItemService wishlistItemService) {
        this.wishlistItemService = wishlistItemService;
    }

    @GetMapping("/wishlist-items")
    public ResponseEntity<List<WishlistItem>> getAllWishlistItems() {
        return ResponseEntity.ok(wishlistItemService.getAllWishlistItems());
    }

    @GetMapping("/wishlist-items/{id}")
    public ResponseEntity<WishlistItem> getWishlistItemById(@PathVariable Long id) {
        return ResponseEntity.ok(wishlistItemService.getWishlistItemById(id));
    }

    @GetMapping("/wishlist-items/wishlist/{wishlistId}")
    public ResponseEntity<List<WishlistItem>> getWishlistItemsByWishlistId(@PathVariable Long wishlistId) {
        return ResponseEntity.ok(wishlistItemService.getWishlistItemsByWishlistId(wishlistId));
    }

    @PostMapping("/wishlist-items")
    public ResponseEntity<WishlistItem> createWishlistItem(@RequestBody WishlistItem wishlistItem) {
        return ResponseEntity.status(201).body(wishlistItemService.createWishlistItem(wishlistItem));
    }

    @PutMapping("/wishlist-items/{id}")
    public ResponseEntity<WishlistItem> updateWishlistItem(@PathVariable Long id, @RequestBody WishlistItem itemDetails) {
        return ResponseEntity.ok(wishlistItemService.updateWishlistItem(id, itemDetails));
    }
}