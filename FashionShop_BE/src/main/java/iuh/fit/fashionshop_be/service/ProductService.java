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
import iuh.fit.fashionshop_be.dto.response.ProductResponse;
import iuh.fit.fashionshop_be.exception.AppException;
import iuh.fit.fashionshop_be.exception.ErrorCode;
import iuh.fit.fashionshop_be.mapper.ProductMapper;
import iuh.fit.fashionshop_be.model.Product;
import iuh.fit.fashionshop_be.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    // === Trả về List<ProductResponse> ===
    public List<ProductResponse> getAllProductResponses() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(product -> productMapper.toProductResponse(product, this))
                .toList();
    }

    public List<ProductResponse> getProductResponsesByCategoryId(Long categoryID) {
        List<Product> products = productRepository.findByCategoryCategoryID(categoryID);
        return products.stream()
                .map(product -> productMapper.toProductResponse(product, this))
                .toList();
    }

    public List<ProductResponse> getProductResponsesByBrand(String brand) {
        List<Product> products = productRepository.findByBrand(brand);
        return products.stream()
                .map(product -> productMapper.toProductResponse(product, this))
                .toList();
    }

    // === Trả về Product (entity) ===
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
    }

    public Product createProduct(Product product) {
        product.setCreatedDate(LocalDateTime.now());
        product.setStatus("ACTIVE");
        product.setReviewCount(0);
        product.setAverageRating(0.0f);
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product productDetails) {
        Product product = getProductById(id);
        product.setName(productDetails.getName());
        product.setDescription(productDetails.getDescription());
        product.setBrand(productDetails.getBrand());
        product.setBasePrice(productDetails.getBasePrice());
        product.setDiscountPrice(productDetails.getDiscountPrice());
        product.setMaterial(productDetails.getMaterial());
        product.setStatus(productDetails.getStatus());
        product.setIsFeatured(productDetails.getIsFeatured());
        product.setImage(productDetails.getImage());
        // category, variants... nếu cần
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new AppException(ErrorCode.PRODUCT_NOT_FOUND);
        }
        productRepository.deleteById(id);
    }

    public Integer getSoldQuantity(Long productId) {
        return productRepository.getSoldQuantity(productId);
    }
}