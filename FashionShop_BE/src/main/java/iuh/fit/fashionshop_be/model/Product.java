/*
 * @ (#) Product.java     1.0    17-Oct-25
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
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @Column(name = "ProductID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productID;

    @Column(name = "Name")
    private String name;

    @Column(name = "Description")
    private String description;

    @Column(name = "Brand")
    private String brand;

    @Column(name = "BasePrice")
    private Float basePrice;

    @Column(name = "DiscountPrice")
    private Float discountPrice;

    @Column(name = "Material")
    private String material;

    @Column(name = "CreatedDate")
    private LocalDateTime createdDate;

    @Column(name = "Status")
    private String status;

    @Column(name = "AverageRating")
    private Float averageRating;

    @Column(name = "ReviewCount")
    private Integer reviewCount;

    @Column(name = "IsFeatured")
    private Boolean isFeatured;

    // Quan hệ
    @ManyToOne
    @JoinColumn(name = "CategoryID", insertable = false, updatable = false)
    @JsonIgnore
    private Category category;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private List<ProductVariant> variants;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private List<Review> reviews;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private List<WishlistItem> wishlistItems;
}