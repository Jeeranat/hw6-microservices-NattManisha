package io.spring.cloud.samples.commerce.ui.services;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service

public class CommerceService {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "fallbackCommerce")
    public String getItems() {

        // pool the data from priceservice and itemservice using REST
        Iterable<LinkedHashMap> itemInfo = restTemplate.getForObject("http://item/items", Iterable.class);
        Map<String, String> priceInfo = restTemplate.getForObject("http://price/prices", Map.class);

        StringBuilder sb = new StringBuilder();

        // combine price to item info
        Iterator<LinkedHashMap> itemIterator = itemInfo.iterator();
        while (itemIterator.hasNext()) {
            LinkedHashMap items = itemIterator.next();
            String id = String.valueOf(items.get("id"));

            // append the query information into StringBuilder
            sb.append("Item: ").append(items.toString());
            sb.append("  |  Price: {").append(priceInfo.get(id)).append("}<br>");
        }
        sb.append("Total ").append(priceInfo.size()).append(" items");

        // return the query result to the controller
        return sb.toString();
    }

    public String getItemByCategory(String category) {

        // pool the data from priceservice and itemservice using REST
        Iterable<LinkedHashMap> itemInfo = restTemplate.getForObject("http://item/category/{cat}", Iterable.class, category);
        Map<String, String> priceInfo = restTemplate.getForObject("http://price/prices", Map.class);

        StringBuilder sb = new StringBuilder();

        // combine price to item info
        Iterator<LinkedHashMap> itemIterator = itemInfo.iterator();
        while (itemIterator.hasNext()) {
            LinkedHashMap items = itemIterator.next();
            String id = String.valueOf(items.get("id"));

            // append the query information into StringBuilder
            sb.append("Item: ").append(items.toString());
            sb.append("  |  Price: {").append(priceInfo.get(id)).append("}<br>");
        }

        // return the query result to the controller
        return sb.toString();
    }

    public String getItemById(String id) {

        // pool the data from priceservice and itemservice using REST
        Iterable<LinkedHashMap> itemInfo = restTemplate.getForObject("http://item/item/{id}", Iterable.class, id);
        Map<String, String> priceInfo = restTemplate.getForObject("http://price/price/{item}", Map.class, id);

        StringBuilder sb = new StringBuilder();

        // combine price to item info
        Iterator<LinkedHashMap> itemIterator = itemInfo.iterator();
        LinkedHashMap items = itemIterator.next();

        // append the query information into StringBuilder
        sb.append("Item: ").append(items.toString());
        sb.append("  |  Price: {").append(priceInfo.get("price")).append("}<br>");

        // return the query result to the controller
        return sb.toString();
    }

    public String fallbackCommerce() {
        return "Please try again, the server cannot provide the service at this time.";
    }

}
