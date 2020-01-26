package com.asellion.app.products;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/products")
@Api(value = "Products", description = "Operations related to products")
@Validated
public class ProductsController {
    private ProductService productService;
    private ProductDtoMapper productDtoMapper;

    @Autowired
    public ProductsController(ProductService productService, ProductDtoMapper productDtoMapper) {
        this.productService = productService;
        this.productDtoMapper = productDtoMapper;
    }

    @GetMapping
    public ResponseEntity<?> listProducts(@RequestParam(value = "page", defaultValue = "0", required = false) int page, @RequestParam(value = "limit", required = false, defaultValue = "50") int limit) throws Exception {
        Page<Product> products = productService.getProductsList(page, limit);
        Page<ProductDto> productDtos = products.map(product -> productDtoMapper.toProductDto(product));
        return new ResponseEntity<>(productDtos, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getProduct(@NotNull @PathVariable(value = "id") int productId) throws Exception {
        Product product = productService.getProduct(productId);
        return new ResponseEntity<>(productDtoMapper.toProductDto(product), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addProduct(@Valid @RequestBody ProductDto productDto) throws Exception {
        Product product = productService.createNewProduct(productDto);
        return new ResponseEntity<>(productDtoMapper.toProductDto(product), HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> updateProduct(@NotNull @PathVariable("id") long productId, @Valid @RequestBody ProductDto productDto) throws Exception {
        Product product = productService.updateProduct(productId, productDto);
        return new ResponseEntity<>(productDtoMapper.toProductDto(product), HttpStatus.OK);
    }
}
