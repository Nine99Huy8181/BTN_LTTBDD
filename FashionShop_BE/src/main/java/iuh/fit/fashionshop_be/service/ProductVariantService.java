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

import iuh.fit.fashionshop_be.dto.request.ProductVariantRequest;
import iuh.fit.fashionshop_be.dto.response.ProductVariantResponse;
import iuh.fit.fashionshop_be.mapper.ProductVariantMapper;
import iuh.fit.fashionshop_be.model.Inventory;
import iuh.fit.fashionshop_be.model.Product;
import iuh.fit.fashionshop_be.model.ProductVariant;
import iuh.fit.fashionshop_be.repository.ProductVariantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductVariantService {
    private final ProductVariantRepository variantRepository;
    private final ProductVariantMapper variantMapper;
    private final ProductVariantRepository productVariantRepository;
    private final InventoryService inventoryService;

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

//    public ProductVariantResponse createVariant(ProductVariant variant) {
//        ProductVariant savedVariant = variantRepository.save(variant);
//        return variantMapper.toProductVariantResponse(savedVariant, this); // <--- Map sang DTO
//    }

    //hung
    @Transactional
    public ProductVariantResponse createVariant(ProductVariantRequest req) {
        // Map request -> entity
        ProductVariant variant = new ProductVariant();
        variant.setProduct(new Product());
        variant.getProduct().setProductID(req.getProductID());
        variant.setSku(req.getSku());
        variant.setSize(req.getSize());
        variant.setColor(req.getColor());
        variant.setPriceAdjustment(req.getPriceAdjustment());
        variant.setImages(req.getImages());
        variant.setStatus(req.getStatus());

        ProductVariant saved = variantRepository.save(variant);

        // ✅ CẬP NHẬT INVENTORY với reservedQuantity từ REQUEST
        if (req.getValidQuantity() != null) {
            Inventory inv = inventoryService.getInventoryByVariantId(saved.getVariantID());
            if (inv == null) {
                inv = new Inventory();
                inv.setVariant(saved);
            }

            // ✅ LẤY reservedQuantity từ REQUEST, không set cứng = 0
            int reserved = (req.getReservedQuantity() != null) ? req.getReservedQuantity() : 0;
            inv.setReservedQuantity(reserved);

            // validQuantity = quantity - reserved => quantity = validQuantity + reserved
            int newQuantity = Math.max(0, req.getValidQuantity() + reserved);
            inv.setQuantity(newQuantity);
            inv.setUpdatedDate(LocalDateTime.now());

            inventoryService.createInventory(inv);
        }

        return variantMapper.toProductVariantResponse(saved, this);
    }

//    public ProductVariantResponse updateVariant(Long id, ProductVariant variantDetails) {
//        ProductVariant variant = findVariantById(id); // <--- Dùng helper để lấy entity
//
//        // Cập nhật các trường từ DTO
//        variant.setProduct(variantDetails.getProduct());
//        variant.setSku(variantDetails.getSku());
//        variant.setSize(variantDetails.getSize());
//        variant.setColor(variantDetails.getColor());
//        variant.setPriceAdjustment(variantDetails.getPriceAdjustment());
//        variant.setImages(variantDetails.getImages());
//        variant.setStatus(variantDetails.getStatus());
//
//        ProductVariant updatedVariant = variantRepository.save(variant);
//        return variantMapper.toProductVariantResponse(updatedVariant, this); // <--- Map sang DTO
//    }

    //hung
    @Transactional
    public ProductVariantResponse updateVariant(Long id, ProductVariantRequest req) {
        ProductVariant variant = variantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Variant not found"));

        // Update fields
        if (req.getProductID() != null) {
            Product p = new Product();
            p.setProductID(req.getProductID());
            variant.setProduct(p);
        }
        variant.setSku(req.getSku());
        variant.setSize(req.getSize());
        variant.setColor(req.getColor());
        variant.setPriceAdjustment(req.getPriceAdjustment());
        variant.setImages(req.getImages());
        variant.setStatus(req.getStatus());

        ProductVariant updated = variantRepository.save(variant);

        // ✅ CẬP NHẬT INVENTORY
        if (req.getValidQuantity() != null) {
            Inventory inv = inventoryService.getInventoryByVariantId(updated.getVariantID());

            if (inv == null) {
                // Tạo mới inventory
                inv = new Inventory();
                inv.setVariant(updated);
                int reserved = (req.getReservedQuantity() != null) ? req.getReservedQuantity() : 0;
                inv.setReservedQuantity(reserved);
            } else {
                // ✅ CẬP NHẬT reservedQuantity nếu client gửi
                if (req.getReservedQuantity() != null) {
                    inv.setReservedQuantity(req.getReservedQuantity());
                }
            }

            int reserved = (inv.getReservedQuantity() != null) ? inv.getReservedQuantity() : 0;
            int newQuantity = Math.max(0, req.getValidQuantity() + reserved);
            inv.setQuantity(newQuantity);
            inv.setUpdatedDate(LocalDateTime.now());

            // Kiểm tra có inventoryID chưa để quyết định create hay update
            if (inv.getInventoryID() != null) {
                inventoryService.updateInventory(inv.getInventoryID(), inv);
            } else {
                inventoryService.createInventory(inv);
            }
        }

        return variantMapper.toProductVariantResponse(updated, this);
    }

    public Integer getAvailableStockByVariant(Long id) {
        return productVariantRepository.getAvailableStockByVariant(id);
    }
    public Integer getReservedQuantityByVariant(Long id) {
        return productVariantRepository.getReservedQuantityByVariant(id);
    }

    @Transactional
    public void deleteVariant(Long id) {
        inventoryService.deleteByVariantId(id);
        // Đảm bảo variant tồn tại trước khi xóa
        ProductVariant variant = findVariantById(id);
        variantRepository.delete(variant);
    }



}