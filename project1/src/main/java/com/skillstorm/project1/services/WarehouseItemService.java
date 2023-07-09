package com.skillstorm.project1.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillstorm.project1.models.CompositeKey;
import com.skillstorm.project1.models.Item;
import com.skillstorm.project1.models.Warehouse;
import com.skillstorm.project1.models.WarehouseItem;
import com.skillstorm.project1.repositories.ItemRepository;
import com.skillstorm.project1.repositories.WarehouseItemRepository;
import com.skillstorm.project1.repositories.WarehouseRepository;

//all business logic for controllers and repositories
@Service
public class WarehouseItemService {

    @Autowired
    WarehouseItemRepository warehouseItemRepository;

    @Autowired
    WarehouseRepository warehouseRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    WarehouseService warehouseService;

    // method to make sure warehouseItem entry exists
    public WarehouseItem findWarehouseItemIds(int warehouseId, int itemId) {
        CompositeKey compositeKey = new CompositeKey(warehouseId, itemId);
        Optional<WarehouseItem> existingWarehouseItem = warehouseItemRepository.findById(compositeKey);

        if (existingWarehouseItem.isPresent()) {
            return warehouseItemRepository.findById(compositeKey).get();
        } else {
            throw new RuntimeException("Inventory does not exist");
        }
    }

    public List<WarehouseItem> getWarehouseItems() {
        try {
            return warehouseItemRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("No WarehouseItems found");
        }
    }

    @Transactional
    public int updateWarehouseItem(int warehouseId, int itemId, int quantity) {
        Optional<WarehouseItem> existingWarehouseItem = warehouseItemRepository
                .findById(findWarehouseItemIds(warehouseId, itemId).getId());
        if (existingWarehouseItem.isPresent()) {
            Optional<Warehouse> existingWarehouse = warehouseRepository
                    .findById(existingWarehouseItem.get().getWarehouseId());
            int currentCapacity = existingWarehouse.get().getWarehouse_capacity();

            if (quantity + existingWarehouseItem.get().getQuantity() > currentCapacity) {
                throw new RuntimeException("Warehouse capacity exceeded");
            } else {
                existingWarehouseItem.get().setQuantity(existingWarehouseItem.get().getQuantity() + quantity);
                existingWarehouse.get().setWarehouse_capacity(currentCapacity - quantity);
            }
            return 1;
        } else {
            throw new RuntimeException("Create a new inventory item, item right now does not exist");
        }
    }

    @Transactional
    public WarehouseItem createWarehouseItem(int warehouseId, int itemId, int quantity) {
        Optional<Warehouse> existingWarehouse = warehouseRepository.findById(warehouseId);
        Optional<Item> existingItem = itemRepository.findById(itemId);

        int currentCapacity = warehouseService.getWarehouseById(warehouseId).getWarehouse_capacity();
        if (quantity > currentCapacity) {
            throw new RuntimeException("Warehouse capacity exceeded");
        }
        warehouseService.updateCapacity(warehouseId, currentCapacity - quantity);
        WarehouseItem warehouseItem = new WarehouseItem();
        CompositeKey compositeKey = new CompositeKey(warehouseId, itemId);

        warehouseItem.setId(compositeKey);
        warehouseItem.setItem(existingItem.get());
        warehouseItem.setQuantity(quantity);
        warehouseItem.setWarehouse(existingWarehouse.get());
        return warehouseItemRepository.save(warehouseItem);
    }

}
