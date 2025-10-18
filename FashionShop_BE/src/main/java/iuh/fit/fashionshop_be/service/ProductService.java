/*
 * @ (#) r.java     1.0    17-Oct-25
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
import iuh.fit.fashionshop_be.model.Product;
import iuh.fit.fashionshop_be.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(String id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public List<Product> getProductsByCategoryId(String categoryID) {
        return productRepository.findByCategoryCategoryID(categoryID);
    }

    public List<Product> getProductsByBrand(String brand) {
        return productRepository.findByBrand(brand);
    }

    public List<Product> getProductsByStatus(String status) {
        return productRepository.findByStatus(status);
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(String id, Product productDetails) {
        Product product = getProductById(id);
        product.setName(productDetails.getName());
        product.setDescription(productDetails.getDescription());
        product.setCategoryID(productDetails.getCategoryID());
        product.setBrand(productDetails.getBrand());
        product.setBasePrice(productDetails.getBasePrice());
        product.setDiscountPrice(productDetails.getDiscountPrice());
        product.setMaterial(productDetails.getMaterial());
        product.setCreatedDate(productDetails.getCreatedDate());
        product.setStatus(productDetails.getStatus());
        product.setAverageRating(productDetails.getAverageRating());
        product.setReviewCount(productDetails.getReviewCount());
        product.setIsFeatured(productDetails.getIsFeatured());
        return productRepository.save(product);
    }

    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }
}