package com.skillstorm.project1.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.project1.models.WarehouseItem;
import com.skillstorm.project1.services.ItemService;
import com.skillstorm.project1.services.WarehouseItemService;
import com.skillstorm.project1.services.WarehouseService;

@CrossOrigin
@RestController
@RequestMapping("/inventory")

public class WarehouseItemController {

    @Autowired
    WarehouseItemService warehouseItemService;

    @Autowired
    ItemService itemService;

    @Autowired
    WarehouseService warehouseService;

    /** GET MAPPING */
    @GetMapping
    public ResponseEntity<List<WarehouseItem>> getWarehouseItems() {
        List<WarehouseItem> warehouseItems = warehouseItemService.getWarehouseItems();
        return new ResponseEntity<List<WarehouseItem>>(warehouseItems, HttpStatus.OK);
    }

    @PostMapping("/create/{warehouseName}/{carModel}/{quantity}") // path variables
    public ResponseEntity<WarehouseItem> createWarehouseItem(@PathVariable String warehouseName,
            @PathVariable String carModel, @PathVariable int quantity) {
        WarehouseItem created = warehouseItemService.createWarehouseItem(warehouseName, carModel, quantity);
        return new ResponseEntity<WarehouseItem>(created, HttpStatus.CREATED);
    }

    /** PUT MAPPING */
    @PutMapping("/update/{warehouseId}/{itemId}/{quantity}") // path variables
    public ResponseEntity<Integer> updateWarehouseItem(@PathVariable int warehouseId, @PathVariable int itemId,
            @PathVariable int quantity) {

        System.out.println("Warehouse ID: " + warehouseId + " Item ID: " + itemId + " Quantity: " + quantity);
        int updated = warehouseItemService.updateWarehouseItem(warehouseId, itemId, quantity);
        return new ResponseEntity<Integer>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{warehouseId}/{itemId}")
    public ResponseEntity<Integer> deleteWarehouseItem(@PathVariable int warehouseId, @PathVariable int itemId) {
        int deleted = warehouseItemService.deleteWarehouseItem(warehouseId, itemId);

        return new ResponseEntity<Integer>(deleted, HttpStatus.OK);
    }

}
