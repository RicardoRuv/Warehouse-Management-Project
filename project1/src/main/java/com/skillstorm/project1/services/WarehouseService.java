package com.skillstorm.project1.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillstorm.project1.models.Warehouse;
import com.skillstorm.project1.repositories.WarehouseRepository;

//all business logic for controllers and repositories
//CRUD - Create, Read, Update, Delete
@Service
public class WarehouseService {
    @Autowired
    WarehouseRepository warehouseRepository;

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

    public Warehouse getWarehouseById(Integer id) {

        Optional<Warehouse> existingWarehouse = warehouseRepository.findById(id);
        if (existingWarehouse.isPresent()) {
            return warehouseRepository.findById(id).get();
        } else {
            throw new RuntimeException("Warehouse does not exist" + id + " does not exist");
        }
    }

    public void updateCapacity(int id, int capacity) {
        Optional<Warehouse> existingWarehouse = warehouseRepository.findById(id);
        if (existingWarehouse.isPresent()) {
            Warehouse warehouse = warehouseRepository.findById(id).get();
            warehouse.setWarehouse_capacity(capacity);
            warehouseRepository.save(warehouse);
        } else {
            throw new RuntimeException("Warehouse does not exist");
        }
    }

}
