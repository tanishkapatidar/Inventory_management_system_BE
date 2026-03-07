package com.backend.inventory.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ProductResponseDTO {

    private Long id;
    private String name;
    private String sku;
    private BigDecimal price;
    private Integer quantity;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
