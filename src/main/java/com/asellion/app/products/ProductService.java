package com.asellion.app.products;

import com.asellion.exceptions.ResourceNotFoundException;
import org.springframework.data.domain.Page;

public interface ProductService {
    Page<Product> getProductsList(int page, int limit) throws Exception;

    Product createNewProduct(ProductDto productDto) throws Exception;

    Product updateProduct(long productId, ProductDto productDto) throws ResourceNotFoundException;

    Product getProduct(long productId) throws ResourceNotFoundException;
}
