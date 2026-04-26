package com.rsingla.fintech.product.infrastructure;

import com.rsingla.fintech.common.Money;
import com.rsingla.fintech.product.api.ProductPriceRequest;
import com.rsingla.fintech.product.domain.ProductPrice;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Currency;

@Entity
@Table(name = "products")
public class ProductEntity {
    @Id
    private String sku;
    private String name;
    private BigDecimal price;
    private String currency;
    private Instant updatedAt;

    protected ProductEntity() {
    }

    public static ProductEntity create(String sku) {
        var entity = new ProductEntity();
        entity.sku = sku;
        entity.currency = "USD";
        return entity;
    }

    public void apply(ProductPriceRequest request, Instant updatedAt) {
        this.name = request.name();
        this.price = request.price();
        this.currency = "USD";
        this.updatedAt = updatedAt;
    }

    public ProductPrice toDomain() {
        return new ProductPrice(sku, name, new Money(price, Currency.getInstance(currency)), updatedAt);
    }
}
