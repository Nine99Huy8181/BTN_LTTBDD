/*
 * @ (#) d.java     1.0    17-Oct-25
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
import iuh.fit.fashionshop_be.dto.response.ProductVariantResponse;
import iuh.fit.fashionshop_be.model.ProductVariant;
import iuh.fit.fashionshop_be.service.ProductVariantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductVariantController {

    private final ProductVariantService variantService;

    public ProductVariantController(ProductVariantService variantService) {
        this.variantService = variantService;
    }

    @GetMapping("/variants")
    public ResponseEntity<List<ProductVariantResponse>> getAllVariants() { // <--- Thay đổi kiểu trả về
        return ResponseEntity.ok(variantService.getAllVariants());
    }

    @GetMapping("/variants/{id}")
    public ResponseEntity<ProductVariantResponse> getVariantById(@PathVariable Long id) { // <--- Thay đổi kiểu trả về
        return ResponseEntity.ok(variantService.getVariantById(id));
    }

    @GetMapping("/variants/product/{productId}")
    public ResponseEntity<List<ProductVariantResponse>> getVariantsByProductId(@PathVariable Long productId) { // <--- Thay đổi kiểu trả về
        return ResponseEntity.ok(variantService.getVariantsByProductId(productId));
    }

    @PostMapping("/variants")
    public ResponseEntity<ProductVariantResponse> createVariant(@RequestBody ProductVariant variant) { // <--- Thay đổi kiểu trả về
        return ResponseEntity.status(201).body(variantService.createVariant(variant));
    }

    @PutMapping("/variants/{id}")
    public ResponseEntity<ProductVariantResponse> updateVariant(@PathVariable Long id, @RequestBody ProductVariant variantDetails) { // <--- Thay đổi kiểu trả về
        return ResponseEntity.ok(variantService.updateVariant(id, variantDetails));
    }

    // (Bạn có thể thêm endpoint cho delete nếu cần)
    // @DeleteMapping("/variants/{id}")
    // public ResponseEntity<Void> deleteVariant(@PathVariable Long id) {
    //     variantService.deleteVariant(id);
    //     return ResponseEntity.noContent().build();
    // }
}