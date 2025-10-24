/*
 * @ (#) j.java     1.0    17-Oct-25
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
import java.util.List;

@Entity
@Table(name = "product_variants")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductVariant {

    @Id
    @Column(name = "VariantID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long variantID;

    @ManyToOne
    @JoinColumn(name = "ProductID", nullable = false)
    private Product product;

    @Column(name = "SKU")
    private String sku;

    @Column(name = "Size")
    private String size;

    @Column(name = "Color")
    private String color;

    @Column(name = "PriceAdjustment")
    private Float priceAdjustment;

    @jakarta.persistence.Convert(converter = iuh.fit.fashionshop_be.converter.StringArrayConverter.class)
    @Column(columnDefinition = "TEXT")
    private String[] images;

    @Column(name = "Status")
    private String status;

    // Quan hệ
    @OneToMany(mappedBy = "variant")
    @JsonIgnore
    private List<OrderItem> orderItems;

    @OneToMany(mappedBy = "variant")
    @JsonIgnore
    private List<CartItem> cartItems;

    @OneToOne(mappedBy = "variant")
    @JsonIgnore
    private Inventory inventory;
}