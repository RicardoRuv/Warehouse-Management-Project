package com.skillstorm.project1.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillstorm.project1.models.Warehouse;
import com.skillstorm.project1.repositories.WarehouseRepository;

//all business logic for controllers and repositories
@Service
public class WarehouseService {
    @Autowired
    WarehouseRepository warehouseRepository;

    // @Autowired // constructor injection
    // public WarehouseService(WarehouseRepository warehouseRepository) {
    // this.warehouseRepository = warehouseRepository;
    // }

    public List<Warehouse> getWarehouses() {

        try {
            return warehouseRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("No Warehouses found");
        }
    }

    public Warehouse createWarehouse(Warehouse warehouse) {
        Optional<Warehouse> existingWarehouse = warehouseRepository.findByWarehouseName(warehouse.getWarehouse_name());
        if (existingWarehouse.isPresent()) {
            throw new RuntimeException("Warehouse already exists");
        }
        return warehouseRepository.save(warehouse);
    }

    public void deleteWarehouse(Warehouse warehouse) {
        Optional<Warehouse> existingWarehouse = warehouseRepository.findById(warehouse.getWarehouse_id());
        if (existingWarehouse.isPresent()) {
            warehouseRepository.delete(warehouse);
        } else {
            throw new RuntimeException("Warehouse does not exist");
        }

    }

}
