package io.spring.cloud.samples.commerce.priceservice.repositories;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

import java.util.HashMap;
import java.util.Map;

/**
 * @author sgupta
 * @since 10/13/16.
 */
@ConfigurationProperties(prefix = "commerce")
@RefreshScope
public class PriceConfig {

  Map<String,String> prices = new HashMap<>();

  public Map<String, String> getPrices() {
    return prices;
  }

  public void setPrices(Map<String, String> prices) {
    this.prices = prices;
  }

  public String getPriceForItem(String itemId) {
    return prices.get(itemId);
  }

  public void putPrice(String itemId, String price) {
    prices.put(itemId, price);
  }
}
