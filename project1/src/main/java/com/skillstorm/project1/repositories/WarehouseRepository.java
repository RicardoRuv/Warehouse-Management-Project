package com.skillstorm.project1.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skillstorm.project1.models.Warehouse;

// This is the repository layer. It is responsible for communicating with the database.

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Integer> {

    // JPA will generate the code for most of these -- save , findall, delete, etc
    public Optional<Warehouse> findById(int id);

    public Optional<Warehouse> findByWarehouseName(String name);

    // public Optional<Warehouse> findByName(String warehouseName);
}
