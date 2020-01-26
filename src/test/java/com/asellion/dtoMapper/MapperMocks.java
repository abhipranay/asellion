package com.asellion.dtoMapper;

import com.asellion.app.products.ProductDto;
import com.asellion.app.products.ProductDtoMapper;
import com.asellion.app.products.ProductStub;
import com.asellion.app.user.UserDto;
import com.asellion.app.user.UserDtoMapper;
import com.asellion.app.user.UserStubs;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

public class MapperMocks {

    public void initMocks(UserDtoMapper userDtoMapper) {
        when(userDtoMapper.toUser(any(UserDto.class))).thenReturn(UserStubs.generateUser());
    }

    public void initMocks(ProductDtoMapper productDtoMapper) {
        when(productDtoMapper.toProduct(any(ProductDto.class))).thenReturn(ProductStub.generateProduct());
    }

}
