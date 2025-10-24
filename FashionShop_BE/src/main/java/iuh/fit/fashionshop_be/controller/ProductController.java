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
import iuh.fit.fashionshop_be.dto.response.ApiResponse;
import iuh.fit.fashionshop_be.dto.response.ProductResponse;
import iuh.fit.fashionshop_be.mapper.ProductMapper;
import iuh.fit.fashionshop_be.model.Product;
import iuh.fit.fashionshop_be.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @GetMapping("/products")
    public ApiResponse<List<ProductResponse>> getAllProducts() {
        List<ProductResponse> responses = productService.getAllProductResponses();
        return ApiResponse.<List<ProductResponse>>builder()
                .code(1000)
                .message("Products retrieved successfully")
                .result(responses)
                .build();
    }

    @GetMapping("/products/{id}")
    public ApiResponse<Product> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        return ApiResponse.<Product>builder()
                .code(1000)
                .message("Product found")
                .result(product)
                .build();
    }

    @GetMapping("/products/category/{categoryId}")
    public ApiResponse<List<ProductResponse>> getProductsByCategoryId(@PathVariable Long categoryId) {
        List<ProductResponse> responses = productService.getProductResponsesByCategoryId(categoryId);
        return ApiResponse.<List<ProductResponse>>builder()
                .code(1000)
                .message("Products by category retrieved")
                .result(responses)
                .build();
    }

    @GetMapping("/products/brand/{brand}")
    public ApiResponse<List<ProductResponse>> getProductsByBrand(@PathVariable String brand) {
        List<ProductResponse> responses = productService.getProductResponsesByBrand(brand);
        return ApiResponse.<List<ProductResponse>>builder()
                .code(1000)
                .message("Products by brand retrieved")
                .result(responses)
                .build();
    }

    @PostMapping("/products")
    public ApiResponse<Product> createProduct(@RequestBody Product product) {
        Product saved = productService.createProduct(product);
        return ApiResponse.<Product>builder()
                .code(1000)
                .message("Product created successfully")
                .result(saved)
                .build();
    }

    @PutMapping("/products/{id}")
    public ApiResponse<Product> updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
        Product updated = productService.updateProduct(id, productDetails);
        return ApiResponse.<Product>builder()
                .code(1000)
                .message("Product updated successfully")
                .result(updated)
                .build();
    }

    @DeleteMapping("/products/{id}")
    public ApiResponse<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ApiResponse.<Void>builder()
                .code(1000)
                .message("Product deleted successfully")
                .result(null)
                .build();
    }
}