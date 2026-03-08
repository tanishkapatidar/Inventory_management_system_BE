package com.backend.inventory.service;

import com.backend.inventory.dto.ProductRequestDTO;
import com.backend.inventory.dto.ProductResponseDTO;
import com.backend.inventory.exception.DuplicateResourceException;
import com.backend.inventory.exception.ResourceNotFoundException;
import com.backend.inventory.models.Product;
import com.backend.inventory.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO) {

        if(productRepository.existsBySku(productRequestDTO.getSku())) {
            throw new DuplicateResourceException("Product with Sku" + productRequestDTO.getSku() + "already exists");
        }
        Product product = Product.builder()
                .name(productRequestDTO.getName())
                .sku(productRequestDTO.getSku())
                .price(productRequestDTO.getPrice())
                .quantity(productRequestDTO.getQuantity())
                .description(productRequestDTO.getDescription())
                .build();

        Product savedProduct = productRepository.saveAndFlush(product);
        return  mapToProductResponseDTO(savedProduct);
    }

    public ProductResponseDTO getProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Product with id " + id + " not found")
        );
        return mapToProductResponseDTO(product);
    }

    public List<ProductResponseDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();

        return products.stream()
                .map(this::mapToProductResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public ProductResponseDTO updateProduct(Long id, ProductRequestDTO productRequestDTO) {

        Product product = productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Product with id " + id + " not found")
        );

        product.setName(productRequestDTO.getName());
        product.setSku(productRequestDTO.getSku());
        product.setPrice(productRequestDTO.getPrice());
        product.setQuantity(productRequestDTO.getQuantity());
        product.setDescription(productRequestDTO.getDescription());
        Product updatedProduct = productRepository.save(product);

        return mapToProductResponseDTO(updatedProduct);
    }

    @Transactional
    public void deleteProduct(Long id) {

        Product product = productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Product with id " + id + " not found")

        );
        productRepository.delete(product);
    }

    private ProductResponseDTO mapToProductResponseDTO(Product product) {

        return ProductResponseDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .sku(product.getSku())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .description(product.getDescription())
                .createdAt(product.getCreatedAt())
                .modifiedAt(product.getModifiedAt())
                .build();
    }
}
