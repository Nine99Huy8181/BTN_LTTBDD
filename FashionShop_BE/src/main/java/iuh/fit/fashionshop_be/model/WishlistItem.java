/*
 * @ (#) n.java     1.0    17-Oct-25
 *
 * Copyright (c) 2025 IUH. All rights reserved.
 */

package iuh.fit.fashionshop_be.model;

/*
 * @description:
 * @author: Nguyen Quoc Huy
 * @date:17-Oct-25
 * @version: 1.0
 */
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "wishlist_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WishlistItem {

    @Id
    @Column(name = "WishlistItemID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wishlistItemID;

    @ManyToOne
    @JoinColumn(name = "WishlistID", nullable = false)
    private Wishlist wishlist;

    @ManyToOne
    @JoinColumn(name = "ProductID", nullable = false)
    private Product product;

    @Column(name = "AddedDate")
    private LocalDateTime addedDate;
}