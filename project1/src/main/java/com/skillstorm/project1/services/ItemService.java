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
        Optional<Item> existingItem = itemRepository.findByModel(item.getModel());
        if (existingItem.isPresent()) {
            throw new EntityNotFoundException("Car already exists");
        }

        // if car does not exist, create it
        return itemRepository.save(item);
    }

    @Transactional
    public int updateItem(int id, String make, String model) {
        if (make == null || model == null) {
            throw new EntityNotFoundException("Fields cannot be null");
        }
        Optional<Item> existingItem = itemRepository.findById(id);

        if (existingItem.isPresent()) {
            Item item = existingItem.get();
            item.setMake(make);
            item.setModel(model);
            itemRepository.save(item);
            return 1;
        } else {
            return 0;
        }
    }

}
