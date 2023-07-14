package com.skillstorm.project1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skillstorm.project1.models.Item;

import java.util.List;
import java.util.Optional;

// This is the repository layer. It is responsible for communicating with the database.

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

    public Optional<Item> findById(int id);

    public Optional<Item> findByModel(String model);

    public Optional<Item> findByMake(String make);

    public List<Item> findAllByModel(String model);
}
