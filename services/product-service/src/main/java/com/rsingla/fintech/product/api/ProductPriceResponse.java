package com.rsingla.fintech.product.api;

import com.rsingla.fintech.product.domain.ProductPrice;
import java.math.BigDecimal;
import java.time.Instant;

public record ProductPriceResponse(String sku, String name, BigDecimal price, String currency, Instant updatedAt) {
    static ProductPriceResponse from(ProductPrice product) {
        return new ProductPriceResponse(product.sku(), product.name(), product.price().amount(), product.price().currency().getCurrencyCode(), product.updatedAt());
    }
}
