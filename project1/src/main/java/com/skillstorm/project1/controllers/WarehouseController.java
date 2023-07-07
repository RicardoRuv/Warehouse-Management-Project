package com.skillstorm.project1.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.project1.models.Warehouse;
import com.skillstorm.project1.services.WarehouseService;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {

    private final WarehouseService warehouseService;

    @Autowired // constructor injection
    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @GetMapping
    public List<Warehouse> getWarehouses() {
        return warehouseService.getWarehouses();
    }

}
