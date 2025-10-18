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
import iuh.fit.fashionshop_be.model.Inventory;
import iuh.fit.fashionshop_be.service.InventoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/inventories/{id}")
    public ResponseEntity<Inventory> getInventoryById(@PathVariable String id) {
        return ResponseEntity.ok(inventoryService.getInventoryById(id));
    }

    @GetMapping("/inventories/variant/{variantId}")
    public ResponseEntity<Inventory> getInventoryByVariantId(@PathVariable String variantId) {
        return ResponseEntity.ok(inventoryService.getInventoryByVariantId(variantId));
    }

    @PostMapping("/inventories")
    public ResponseEntity<Inventory> createInventory(@RequestBody Inventory inventory) {
        return ResponseEntity.status(201).body(inventoryService.createInventory(inventory));
    }

    @PutMapping("/inventories/{id}")
    public ResponseEntity<Inventory> updateInventory(@PathVariable String id, @RequestBody Inventory inventoryDetails) {
        return ResponseEntity.ok(inventoryService.updateInventory(id, inventoryDetails));
    }
}