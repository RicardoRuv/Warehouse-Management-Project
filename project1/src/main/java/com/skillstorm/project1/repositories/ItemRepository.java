package com.skillstorm.project1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skillstorm.project1.models.Item;
import java.util.Optional;

// This is the repository layer. It is responsible for communicating with the database.

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

    public Optional<Item> findByModel(String model);
}
