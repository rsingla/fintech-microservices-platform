package com.rsingla.fintech.product.api;

import com.rsingla.fintech.common.PageResponse;
import com.rsingla.fintech.product.application.ProductService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/products")
class ProductController {
    private final ProductService products;

    ProductController(ProductService products) {
        this.products = products;
    }

    @PutMapping("/{sku}/price")
    ProductPriceResponse upsert(@PathVariable String sku, @Valid @RequestBody ProductPriceRequest request) {
        return ProductPriceResponse.from(products.upsert(sku, request));
    }

    @GetMapping("/{sku}/price")
    ProductPriceResponse get(@PathVariable String sku) {
        return ProductPriceResponse.from(products.get(sku));
    }

    @GetMapping
    PageResponse<ProductPriceResponse> list(Pageable pageable) {
        var page = products.list(pageable).map(ProductPriceResponse::from);
        return new PageResponse<>(page.getContent(), page.getNumber(), page.getSize(), page.getTotalElements(), page.getTotalPages());
    }
}
