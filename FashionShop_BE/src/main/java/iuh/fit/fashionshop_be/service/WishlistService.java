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
import iuh.fit.fashionshop_be.model.Wishlist;
import iuh.fit.fashionshop_be.repository.WishlistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WishlistService {
    private final WishlistRepository wishlistRepository;

    public Wishlist getWishlistById(String id) {
        return wishlistRepository.findById(id).orElseThrow(() -> new RuntimeException("Wishlist not found"));
    }

    public Wishlist getWishlistByCustomerId(String customerID) {
        return wishlistRepository.findByCustomerCustomerID(customerID);
    }

    public Wishlist createWishlist(Wishlist wishlist) {
        return wishlistRepository.save(wishlist);
    }

    public Wishlist updateWishlist(String id, Wishlist wishlistDetails) {
        Wishlist wishlist = getWishlistById(id);
        wishlist.setCustomer(wishlistDetails.getCustomer());
        wishlist.setCreatedDate(wishlistDetails.getCreatedDate());
        return wishlistRepository.save(wishlist);
    }

    public void deleteWishlist(String id) {
        wishlistRepository.deleteById(id);
    }
}