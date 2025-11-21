/*
 * @ (#) f.java     1.0    17-Oct-25
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
import iuh.fit.fashionshop_be.dto.response.ProductResponse;
import iuh.fit.fashionshop_be.model.Product;
import iuh.fit.fashionshop_be.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    // === Danh sách: trả về List<ProductResponse> ===
    @GetMapping("/products")
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        List<ProductResponse> responses = productService.getAllProductResponses();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/products/category/{categoryId}")
    public ResponseEntity<List<ProductResponse>> getProductsByCategoryId(@PathVariable Long categoryId) {
        List<ProductResponse> responses = productService.getProductResponsesByCategoryId(categoryId);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/products/brand/{brand}")
    public ResponseEntity<List<ProductResponse>> getProductsByBrand(@PathVariable String brand) {
        List<ProductResponse> responses = productService.getProductResponsesByBrand(brand);
        return ResponseEntity.ok(responses);
    }

    // === Chi tiết: trả về Product ===
    @GetMapping("/products/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long id) {
        ProductResponse productResponse = productService.getProductResponseById(id);
        return ResponseEntity.ok(productResponse);
    }

    // === Tạo / Cập nhật ===
    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product saved = productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
        Product updated = productService.updateProduct(id, productDetails);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    // === PHẦN THÊM MỚI ===
    @GetMapping("/products/search")
    public ResponseEntity<List<ProductResponse>> searchProducts(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) Float minPrice,
            @RequestParam(required = false) Float maxPrice,
            @RequestParam(required = false) Float minRating,
            @RequestParam(required = false) Float maxRating
    ) {
        List<ProductResponse> responses = productService.searchProducts(keyword, minPrice, maxPrice, minRating, maxRating);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/products/top-selling")
    public ResponseEntity<Page<ProductResponse>> getTopSellingProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<ProductResponse> result = productService.getTopSellingProducts(page, size);
        return ResponseEntity.ok(result);
    }

    // Sản phẩm gợi ý ngẫu nhiên (rất hay cho trang chủ)
    @GetMapping("/products/random")
    public ResponseEntity<Page<ProductResponse>> getRandomActiveProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<ProductResponse> result = productService.getRandomActiveProducts(page, size);
        return ResponseEntity.ok(result);
    }
}