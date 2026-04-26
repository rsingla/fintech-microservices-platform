package com.rsingla.fintech.product.domain;

import com.rsingla.fintech.common.Money;
import java.time.Instant;

public record ProductPrice(String sku, String name, Money price, Instant updatedAt) {
}
