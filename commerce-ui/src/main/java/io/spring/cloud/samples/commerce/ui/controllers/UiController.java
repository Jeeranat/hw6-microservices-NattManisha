package io.spring.cloud.samples.commerce.ui.controllers;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.spring.cloud.samples.commerce.ui.services.CommerceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UiController {

    @Autowired
    CommerceService service;

    // Call "getItems" method in CommerceService to query all the items with price
    @RequestMapping("/items")
    public String getItems() {
        return service.getItems();
    }

    // Call "getItemByCategory" method in CommerceService to query the item and price based on the category
    @RequestMapping("/category/{cat}")
    public String getItemByCategory(@PathVariable("cat") String category) {
        return service.getItemByCategory(category);
    }

    // Call "getItemByCategory" method in CommerceService to query the item and price based on the itemID
    @RequestMapping("/item/{id}")
    public String getItemById(@PathVariable("id") String id) {
        return service.getItemById(id);
    }
}
