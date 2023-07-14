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

import com.skillstorm.project1.models.Warehouse;
import com.skillstorm.project1.services.WarehouseService;

@CrossOrigin
@RestController
@RequestMapping("/warehouses")
public class WarehouseController {
    // CRUD - Create, Read, Update, Delete
    @Autowired
    WarehouseService warehouseService;

    /* Get Mappings (READ) */
    @GetMapping // GET /warehouse
    public List<Warehouse> getWarehouses() {
        return warehouseService.getWarehouses();
    }

    @GetMapping("{id}")
    public Warehouse getWarehouseById(@PathVariable int id) {
        return warehouseService.getWarehouseById(id);
    }

    @GetMapping("/name/{name}")
    public Warehouse getWarehouseByName(@PathVariable String name) {
        return warehouseService.getWarehouseByName(name);
    }

    /* PutMapping (UPDATE) */
    @PutMapping("/warehouse/update")
    public ResponseEntity<Integer> updateWarehouse(@RequestBody Warehouse warehouse,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer capacity) {

        int updated = warehouseService.updateWarehouse(warehouse, name, capacity);
        return new ResponseEntity<Integer>(updated, HttpStatus.OK);
    }

    /* Post Mappings (CREATE) */

    @PostMapping("/warehouse/create")
    public ResponseEntity<Warehouse> createWarehouse(@RequestBody Warehouse warehouse) {
        Warehouse created = warehouseService.createWarehouse(warehouse);
        return new ResponseEntity<Warehouse>(created, HttpStatus.CREATED);
    }

    /* Delete Mappings (DELETE) */

    @DeleteMapping("/warehouse/delete")
    public ResponseEntity<Warehouse> deleteWarehouse(@RequestBody Warehouse warehouse) {
        warehouseService.deleteWarehouse(warehouse);
        return new ResponseEntity<Warehouse>(HttpStatus.OK);
    }
}
