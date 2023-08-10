package com.edhet.store.product;

import com.edhet.store.util.DtoMapper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/all/{count}")
    public List<ProductDTO> getAllProducts(@PathVariable("count") String count) {
        return productService
                .getProductsWithCount(count)
                .stream().map(dtoMapper::productToDto)
                .toList();
    }
}
