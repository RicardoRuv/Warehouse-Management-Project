package com.skillstorm.project1.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillstorm.project1.models.Warehouse;
import com.skillstorm.project1.repositories.WarehouseRepository;

//all business logic for controllers and repositories
@Service
public class WarehouseService {

    private final WarehouseRepository warehouseRepository;

    @Autowired // constructor injection
    public WarehouseService(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    public List<Warehouse> getWarehouses() {
        return warehouseRepository.findAll();
    }

}
