package io.spring.cloud.samples.commerce.itemservice.controllers;

import io.spring.cloud.samples.commerce.itemservice.domain.Item;
import io.spring.cloud.samples.commerce.itemservice.repositories.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ItemController.class);

    @Autowired
    ItemRepository repository;

    @RequestMapping("/items")
    public Iterable<Item> items() {
        return repository.findAll();
    }

    @RequestMapping("/category/{cat}")
    public Iterable<Item> itemsByCategory(@PathVariable("cat") String category) {
      Iterable<Item> items = repository.findByCategory(category);
      return items;
    }

    @RequestMapping("/item/{id}")
    public Iterable<Item> itemsByID(@PathVariable("id") String id) {
        Iterable<Item> items = repository.findById(id);
        return items;
    }
}
