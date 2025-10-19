/*
 * @ (#) d.java     1.0    17-Oct-25
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
import iuh.fit.fashionshop_be.model.ProductVariant;
import iuh.fit.fashionshop_be.repository.ProductVariantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductVariantService {
    private final ProductVariantRepository variantRepository;

    public List<ProductVariant> getAllVariants() {
        return variantRepository.findAll();
    }

    public ProductVariant getVariantById(Long id) {
        return variantRepository.findById(id).orElseThrow(() -> new RuntimeException("ProductVariant not found"));
    }

    public List<ProductVariant> getVariantsByProductId(Long productID) {
        return variantRepository.findByProductProductID(productID);
    }

    public List<ProductVariant> getVariantsBySku(String sku) {
        return variantRepository.findBySku(sku);
    }

    public ProductVariant createVariant(ProductVariant variant) {
        return variantRepository.save(variant);
    }

    public ProductVariant updateVariant(Long id, ProductVariant variantDetails) {
        ProductVariant variant = getVariantById(id);
        variant.setProduct(variantDetails.getProduct());
        variant.setSku(variantDetails.getSku());
        variant.setSize(variantDetails.getSize());
        variant.setColor(variantDetails.getColor());
        variant.setPriceAdjustment(variantDetails.getPriceAdjustment());
        variant.setImages(variantDetails.getImages());
        variant.setStatus(variantDetails.getStatus());
        return variantRepository.save(variant);
    }

    public void deleteVariant(Long id) {
        variantRepository.deleteById(id);
    }
}