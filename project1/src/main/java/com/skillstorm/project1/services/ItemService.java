package com.skillstorm.project1.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillstorm.project1.models.Item;
import com.skillstorm.project1.repositories.ItemRepository;

//all business logic for controllers and repositories
@Service
public class ItemService {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    WarehouseService warehouseService;

    public List<Item> getItems() {
        try {
            return itemRepository.findAll();
        } catch (Exception e) {
            throw new EntityNotFoundException("No Cars found");
        }
    }

    public Item createItem(Item item) {
        // check if car already exists inside the db
        // we check by model because the make can be repeated, and the id is auto
        // generated
        Optional<Item> existingItem = itemRepository.findByModel(item.getModel());
        if (existingItem.isPresent()) {
            throw new EntityNotFoundException("Car already exists");
        }

        // if car does not exist, create it
        return itemRepository.save(item);
    }

    // update car make:optional, model:optional
    @Transactional
    public int updateItem(Item item, String make, String model) {
        // check if car exists
        Optional<Item> existingItem = itemRepository.findById(item.getItem_id());
        // if car exists, update it
        if (existingItem.isPresent()) {
            // if make or model is not null, update it
            if (make != null) {
                existingItem.get().setMake(make);
            }
            if (model != null) {
                existingItem.get().setModel(model);
            }
            // save the updated car
            itemRepository.save(existingItem.get());
            return 1;
        } else {
            return 0;
        }
    }

    // get car by id
    public Item getItembyId(int id) {
        // check if car exists
        Optional<Item> existingItem = itemRepository.findById(id);
        // if car exists, return it
        if (existingItem.isPresent()) {
            return existingItem.get();
        } else {
            throw new EntityNotFoundException("Car not found");
        }
    }

    public int deleteItem(Item item) {
        // check if car exists
        Optional<Item> existingItem = itemRepository.findById(item.getItem_id());
        // if car exists, delete it
        if (existingItem.isPresent()) {
            itemRepository.delete(item);
            return 1;
        } else {
            throw new EntityNotFoundException("Car not found");
        }
    }

    // get all cars by model
    public List<Item> getAllItemByModel(String model) {
        try {
            return itemRepository.findAllByModel(model);
        } catch (Exception e) {
            throw new EntityNotFoundException("No Cars found");
        }
    }

}
