package com.edhet.store.product;

import com.edhet.store.util.DtoMapper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final DtoMapper dtoMapper;

    @GetMapping("/all")
    public List<ProductDTO> getAllProducts() {
        return productService
                .getAllProducts()
                .stream().map(dtoMapper::productToDto)
                .toList();
    }

    @GetMapping("{id}")
    public ProductDTO getProduct(@PathVariable String id) {
        return dtoMapper.productToDto(productService.getProductFromRequest(id));
    }
}
