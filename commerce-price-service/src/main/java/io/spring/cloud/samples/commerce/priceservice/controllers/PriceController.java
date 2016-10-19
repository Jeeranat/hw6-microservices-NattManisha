package io.spring.cloud.samples.commerce.priceservice.controllers;

import io.spring.cloud.samples.commerce.priceservice.repositories.PriceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@EnableConfigurationProperties(PriceConfig.class)
public class PriceController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PriceController.class);

  @Autowired
  PriceConfig priceConfig;

  @RequestMapping("/prices")
  public Map<String,String> prices() {
    return priceConfig.getPrices();
  }

  @RequestMapping("/price/{item}")
  public Map<String,String> priceForItem(@PathVariable("item") String itemdId) {
    String price = priceConfig.getPriceForItem(itemdId);
    Map<String,String> result = new HashMap<>();
    if(price != null) {
      result.put("itemdId", itemdId);
      result.put("price", price);
    }
    return result;
  }
}
