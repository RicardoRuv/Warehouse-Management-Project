package com.skillstorm.project1.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

    /* Post Mappings */

    @PostMapping("/warehouse")
    public ResponseEntity<Warehouse> createWarehouse(Warehouse warehouse) {
        Warehouse created = warehouseService.createWarehouse(warehouse);
        return new ResponseEntity<Warehouse>(created, HttpStatus.CREATED);
    }
}
