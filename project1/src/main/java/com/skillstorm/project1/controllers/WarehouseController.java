package com.skillstorm.project1.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.project1.models.Warehouse;
import com.skillstorm.project1.services.WarehouseService;

@RestController
@RequestMapping("/warehouses")
public class WarehouseController {

    @Autowired
    WarehouseService warehouseService;

    /* Get */
    @GetMapping // GET /warehouse
    public List<Warehouse> getWarehouses() {
        return warehouseService.getWarehouses();
    }

    @GetMapping("{id}")
    public Warehouse getWarehouseById(@PathVariable int id) {
        return warehouseService.getWarehouseById(id);
    }

    @PutMapping("/warehouse/update")
    public ResponseEntity<Integer> updateWarehouse(@RequestParam int id, @RequestParam String name,
            @RequestParam int capacity) {
        int updated = warehouseService.updateWarehouse(id, name, capacity);
        return new ResponseEntity<Integer>(updated, HttpStatus.OK);
    }
    // /* Put mapping */
    // @PutMapping("/car/update")
    // public ResponseEntity<Integer> updateItem(@RequestParam int id, @RequestParam
    // String make,
    // @RequestParam String model) {
    // int updated = itemService.updateItem(id, make, model);
    // return new ResponseEntity<Integer>(updated, HttpStatus.OK);
    // }

    /* Post Mappings */

    @PostMapping("/warehouse")
    public ResponseEntity<Warehouse> createWarehouse(Warehouse warehouse) {
        Warehouse created = warehouseService.createWarehouse(warehouse);
        return new ResponseEntity<Warehouse>(created, HttpStatus.CREATED);
    }

    /* Delete Mappings */

    @DeleteMapping("/warehouse")
    public ResponseEntity<Warehouse> deleteWarehouse(Warehouse warehouse) {
        warehouseService.deleteWarehouse(warehouse);
        return new ResponseEntity<Warehouse>(HttpStatus.OK);
    }
}
