package com.skillstorm.project1.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.project1.models.Item;
import com.skillstorm.project1.services.ItemService;

@RestController
@RequestMapping("/cars")
public class ItemController {

    @Autowired
    ItemService itemService;
    // CRUD - Create, Read, Update, Delete
    /* Get Mappings */

    // represents the whole HTTP response: status code, headers, and body
    @GetMapping
    public ResponseEntity<List<Item>> getItems() {

        List<Item> items = itemService.getItems();
        return new ResponseEntity<List<Item>>(items, HttpStatus.OK);
    }

    @GetMapping("/car/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable int id) {
        Item item = itemService.getItembyId(id);
        return new ResponseEntity<Item>(item, HttpStatus.OK);
    }

    /* Post Mappings */
    @PostMapping("/car")
    public ResponseEntity<Item> createItem(Item item) {
        Item created = itemService.createItem(item);
        return new ResponseEntity<Item>(created, HttpStatus.CREATED);
    }

    /* Put mapping */
    @PutMapping("/car/update")
    public ResponseEntity<Integer> updateItem(@RequestParam int id, @RequestParam String make,
            @RequestParam String model) {
        int updated = itemService.updateItem(id, make, model);
        return new ResponseEntity<Integer>(updated, HttpStatus.OK);
    }

}
