package com.asellion.app.products;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ProductMocks {
    public void initMocks(ProductRepository productRepository) {
        when(productRepository.save(any(Product.class))).then(x -> {
            Product product = x.getArgument(0);
            return ProductStub.generateProduct(product.getProductName());
        });
    }

    public void initProductServiceMocks(ProductService productService) throws Exception {
        when(productService.createNewProduct(any(ProductDto.class))).then(x -> {
            ProductDto productDto = x.getArgument(0);
            return ProductStub.generateProduct(productDto.getProductName());
        });
    }
}
