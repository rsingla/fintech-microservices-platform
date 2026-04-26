package com.rsingla.fintech.product.application;

import com.rsingla.fintech.common.DomainException;
import com.rsingla.fintech.product.api.ProductPriceRequest;
import com.rsingla.fintech.product.domain.ProductPrice;
import com.rsingla.fintech.product.infrastructure.ProductEntity;
import com.rsingla.fintech.product.infrastructure.ProductRepository;
import java.time.Instant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductService {
    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public ProductPrice upsert(String sku, ProductPriceRequest request) {
        var entity = repository.findById(sku).orElseGet(() -> ProductEntity.create(sku));
        entity.apply(request, Instant.now());
        return repository.save(entity).toDomain();
    }

    @Transactional(readOnly = true)
    public ProductPrice get(String sku) {
        return repository.findById(sku).orElseThrow(() -> new DomainException("Product not found: " + sku)).toDomain();
    }

    @Transactional(readOnly = true)
    public Page<ProductPrice> list(Pageable pageable) {
        return repository.findAll(pageable).map(ProductEntity::toDomain);
    }
}
