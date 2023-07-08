package com.skillstorm.project1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skillstorm.project1.models.Warehouse;

// This is the repository layer. It is responsible for communicating with the database.
@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Integer> {

}
