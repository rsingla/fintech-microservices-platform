package com.rsingla.fintech.product.api;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record ProductPriceRequest(@NotBlank String name, @NotNull @DecimalMin("0.01") BigDecimal price) {
}
