/*
 * @ (#) r.java     1.0    17-Oct-25
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
@Table(name = "categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {

    @Id
    @Column(name = "CategoryID")
    private String categoryID;

    @Column(name = "Name")
    private String name;

    @Column(name = "ParentCategoryID")
    private String parentCategoryID;

    @Column(name = "Description")
    private String description;

    @Column(name = "Image")
    private String image;

    // Self-reference
    @ManyToOne
    @JoinColumn(name = "ParentCategoryID", insertable = false, updatable = false)
    @JsonIgnore
    private Category parent;

    @OneToMany(mappedBy = "parent")
    @JsonIgnore
    private List<Category> children;

    @OneToMany(mappedBy = "category")
    @JsonIgnore
    private List<Product> products;
}