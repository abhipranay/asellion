package com.asellion.app.products;

import com.asellion.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    private ProductDtoMapper mapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ProductDtoMapper mapper) {
        this.productRepository = productRepository;
        this.mapper = mapper;
    }

    @Override
    public Page<Product> getProductsList(int page, int limit) {
        Pageable pageable = PageRequest.of(page, limit);
        return productRepository.findAll(pageable);
    }

    @Override
    public Product createNewProduct(ProductDto productDto) {
        Product product = mapper.toProduct(productDto);
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(long productId, ProductDto productDto) throws ResourceNotFoundException {
        Product product = getProduct(productId);
        product.setCurrentPrice(productDto.getCurrentPrice());
        product.setProductName(productDto.getProductName());
        return productRepository.save(product);
    }

    @Override
    public Product getProduct(long productId) throws ResourceNotFoundException {
        Optional<Product> productOptional = productRepository.findById(productId);
        if (!productOptional.isPresent()) {
            throw new ResourceNotFoundException();
        }
        return productOptional.get();
    }
}
