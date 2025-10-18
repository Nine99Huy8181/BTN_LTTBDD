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
    public ResponseEntity<List<ProductVariant>> getAllVariants() {
        return ResponseEntity.ok(variantService.getAllVariants());
    }

    @GetMapping("/variants/{id}")
    public ResponseEntity<ProductVariant> getVariantById(@PathVariable String id) {
        return ResponseEntity.ok(variantService.getVariantById(id));
    }

    @GetMapping("/variants/product/{productId}")
    public ResponseEntity<List<ProductVariant>> getVariantsByProductId(@PathVariable String productId) {
        return ResponseEntity.ok(variantService.getVariantsByProductId(productId));
    }

    @PostMapping("/variants")
    public ResponseEntity<ProductVariant> createVariant(@RequestBody ProductVariant variant) {
        return ResponseEntity.status(201).body(variantService.createVariant(variant));
    }

    @PutMapping("/variants/{id}")
    public ResponseEntity<ProductVariant> updateVariant(@PathVariable String id, @RequestBody ProductVariant variantDetails) {
        return ResponseEntity.ok(variantService.updateVariant(id, variantDetails));
    }
}