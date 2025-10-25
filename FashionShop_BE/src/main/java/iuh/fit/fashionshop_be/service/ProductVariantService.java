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
import iuh.fit.fashionshop_be.dto.response.ProductVariantResponse;
import iuh.fit.fashionshop_be.mapper.ProductVariantMapper;
import iuh.fit.fashionshop_be.model.ProductVariant;
import iuh.fit.fashionshop_be.repository.ProductVariantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductVariantService {
    private final ProductVariantRepository variantRepository;
    private final ProductVariantMapper variantMapper;
    private final ProductVariantRepository productVariantRepository;

    // Phương thức private helper để tìm entity, dùng nội bộ
    private ProductVariant findVariantById(Long id) {
        return variantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ProductVariant not found with id: " + id));
    }

    public List<ProductVariantResponse> getAllVariants() {
        List<ProductVariant> variants = variantRepository.findAll();
        return variantMapper.toProductVariantResponseList(variants, this); // <--- Map sang List DTO
    }

    public ProductVariantResponse getVariantById(Long id) {
        ProductVariant variant = findVariantById(id); // <--- Dùng helper
        return variantMapper.toProductVariantResponse(variant, this); // <--- Map sang DTO
    }

    public List<ProductVariantResponse> getVariantsByProductId(Long productID) {
        List<ProductVariant> variants = variantRepository.findByProductProductID(productID);
        return variantMapper.toProductVariantResponseList(variants, this); // <--- Map sang List DTO
    }

    public List<ProductVariantResponse> getVariantsBySku(String sku) {
        List<ProductVariant> variants = variantRepository.findBySku(sku);
        return variantMapper.toProductVariantResponseList(variants, this); // <--- Map sang List DTO
    }

    public ProductVariantResponse createVariant(ProductVariant variant) {
        ProductVariant savedVariant = variantRepository.save(variant);
        return variantMapper.toProductVariantResponse(savedVariant, this); // <--- Map sang DTO
    }

    public ProductVariantResponse updateVariant(Long id, ProductVariant variantDetails) {
        ProductVariant variant = findVariantById(id); // <--- Dùng helper để lấy entity

        // Cập nhật các trường từ DTO
        variant.setProduct(variantDetails.getProduct());
        variant.setSku(variantDetails.getSku());
        variant.setSize(variantDetails.getSize());
        variant.setColor(variantDetails.getColor());
        variant.setPriceAdjustment(variantDetails.getPriceAdjustment());
        variant.setImages(variantDetails.getImages());
        variant.setStatus(variantDetails.getStatus());

        ProductVariant updatedVariant = variantRepository.save(variant);
        return variantMapper.toProductVariantResponse(updatedVariant, this); // <--- Map sang DTO
    }
    public Integer getAvailableStockByVariant(Long id){
        return productVariantRepository.getAvailableStockByVariant(id);
    }

    public void deleteVariant(Long id) {
        // Đảm bảo variant tồn tại trước khi xóa
        ProductVariant variant = findVariantById(id);
        variantRepository.delete(variant);
    }
}