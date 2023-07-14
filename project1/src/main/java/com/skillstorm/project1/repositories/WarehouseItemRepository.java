package com.skillstorm.project1.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.skillstorm.project1.models.CompositeKey;
import com.skillstorm.project1.models.Warehouse;
import com.skillstorm.project1.models.WarehouseItem;

// This is the repository layer. It is responsible for communicating with the database.
@Repository
public interface WarehouseItemRepository extends JpaRepository<WarehouseItem, Integer> {

    Optional<WarehouseItem> findById(CompositeKey compositeKey);

    void deleteById(CompositeKey compositeKey);

}
