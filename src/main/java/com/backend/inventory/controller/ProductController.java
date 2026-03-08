package com.backend.inventory.controller;

import com.backend.inventory.dto.ProductRequestDTO;
import com.backend.inventory.dto.ProductResponseDTO;
import com.backend.inventory.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@CrossOrigin
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductResponseDTO> createProduct(@Valid @RequestBody ProductRequestDTO productRequestDTO) {
        ProductResponseDTO newProduct = productService.createProduct(productRequestDTO);
        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {

        List<ProductResponseDTO> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable("id") Long id,
                                                            @Valid @RequestBody ProductRequestDTO productRequestDTO) {

        ProductResponseDTO updatedProduct = productService.updateProduct(id, productRequestDTO);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable Long id) {
        ProductResponseDTO product = productService.getProductById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> deleteProduct(@PathVariable Long id ) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
