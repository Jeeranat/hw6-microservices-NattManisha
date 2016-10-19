package io.spring.cloud.samples.commerce.itemservice.repositories;

import io.spring.cloud.samples.commerce.itemservice.domain.Item;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ItemRepository extends PagingAndSortingRepository<Item, String> {

    Iterable<Item> findByCategory(String category);
    Iterable<Item> findById(String id);
}
