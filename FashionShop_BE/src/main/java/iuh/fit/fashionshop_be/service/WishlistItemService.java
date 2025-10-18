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
import iuh.fit.fashionshop_be.model.WishlistItem;
import iuh.fit.fashionshop_be.repository.WishlistItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WishlistItemService {
    private final WishlistItemRepository wishlistItemRepository;

    public List<WishlistItem> getAllWishlistItems() {
        return wishlistItemRepository.findAll();
    }

    public WishlistItem getWishlistItemById(String id) {
        return wishlistItemRepository.findById(id).orElseThrow(() -> new RuntimeException("W MSWishItem not found"));
    }

    public List<WishlistItem> getWishlistItemsByWishlistId(String wishlistID) {
        return wishlistItemRepository.findByWishlistWishlistID(wishlistID);
    }

    public List<WishlistItem> getWishlistItemsByProductId(String productID) {
        return wishlistItemRepository.findByProductProductID(productID);
    }

    public WishlistItem createWishlistItem(WishlistItem wishlistItem) {
        return wishlistItemRepository.save(wishlistItem);
    }

    public WishlistItem updateWishlistItem(String id, WishlistItem itemDetails) {
        WishlistItem item = getWishlistItemById(id);
        item.setWishlist(itemDetails.getWishlist());
        item.setProduct(itemDetails.getProduct());
        item.setAddedDate(itemDetails.getAddedDate());
        return wishlistItemRepository.save(item);
    }

    public void deleteWishlistItem(String id) {
        wishlistItemRepository.deleteById(id);
    }
}