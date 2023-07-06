package com.skillstorm.project1.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.project1.models.Item;

@RestController
@RequestMapping("/item")
public class ItemController {

    public List<Item> getItems() {
        return null;
    }

}
