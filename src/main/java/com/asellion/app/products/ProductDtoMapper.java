package com.asellion.app.products;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductDtoMapper {
    private ModelMapper modelMapper;

    @Autowired
    public ProductDtoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ProductDto toProductDto(Product product) {
        return modelMapper.map(product, ProductDto.class);
    }

    public Product toProduct(ProductDto productDto) {
        return modelMapper.map(productDto, Product.class);
    }
}
