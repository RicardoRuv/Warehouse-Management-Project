package com.skillstorm.project1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skillstorm.project1.models.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

}
