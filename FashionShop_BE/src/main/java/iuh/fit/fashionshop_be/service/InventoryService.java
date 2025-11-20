/*
 * @ (#) f.java     1.0    17-Oct-25
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
import iuh.fit.fashionshop_be.model.Inventory;
import iuh.fit.fashionshop_be.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    public Inventory getInventoryById(Long id) {
        return inventoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Inventory not found"));
    }

    public Inventory getInventoryByVariantId(Long variantID) {
        return inventoryRepository.findByVariantVariantID(variantID);
    }

    public Inventory createInventory(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    public Inventory updateInventory(Long id, Inventory inventoryDetails) {
        Inventory inventory = getInventoryById(id);
        inventory.setVariant(inventoryDetails.getVariant());
        inventory.setQuantity(inventoryDetails.getQuantity());
        inventory.setReservedQuantity(inventoryDetails.getReservedQuantity());
        inventory.setUpdatedDate(inventoryDetails.getUpdatedDate());
        return inventoryRepository.save(inventory);
    }

    public void deleteInventory(Long id) {
        inventoryRepository.deleteById(id);
    }

    // hung
    @Transactional
    public void deleteByVariantId(Long variantId) {
        inventoryRepository.deleteByVariantVariantID(variantId);
    }


}