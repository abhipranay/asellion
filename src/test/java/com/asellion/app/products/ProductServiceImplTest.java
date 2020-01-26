package com.asellion.app.products;

import com.asellion.Application;
import com.asellion.exceptions.ResourceNotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ProductServiceImplTest {
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductDtoMapper productDtoMapper;

    @Before
    public void setUp() throws Exception {
        productRepository.deleteAll();
        productService = new ProductServiceImpl(productRepository, productDtoMapper);
    }

    @Test
    public void testGetProductsListShouldReturnList() throws Exception {
        Product product1 = ProductStub.generateProduct();
        Product product2 = ProductStub.generateProduct();
        Product product3 = ProductStub.generateProduct();
        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);
        Page<Product> productPage = productService.getProductsList(0, 10);
        Assertions.assertThat(productPage.getTotalElements()).isEqualTo(3);

        productPage = productService.getProductsList(1, 10);
        Assertions.assertThat(productPage.getContent().size()).isEqualTo(0);
    }

    @Test
    public void testCreateNewProductShouldCreateNewProduct() throws Exception {
        Product newProduct = ProductStub.generateProduct();
        ProductDto productDto = productDtoMapper.toProductDto(newProduct);
        Product createdProduct = productService.createNewProduct(productDto);
        Assertions.assertThat(createdProduct).isNotNull();
        Assertions.assertThat(createdProduct.getProductName()).isEqualTo(newProduct.getProductName());
    }

    @Test
    public void testProductShouldGetUpdatedSuccessfully() throws Exception {
        Product newProduct = ProductStub.generateProduct();
        ProductDto productDto = productDtoMapper.toProductDto(newProduct);
        newProduct = productService.createNewProduct(productDto);

        productService.updateProduct(newProduct.getId(), new ProductDto()
                .withName("updated")
                .withCurrentPrice(newProduct.getCurrentPrice())
        );

        Optional<Product> updatedProduct = productRepository.findById(newProduct.getId());
        Assertions.assertThat(updatedProduct).isPresent();
        Assertions.assertThat(updatedProduct.get().getProductName()).isEqualTo("updated");
    }

    @Test
    public void testGetProductShouldGiveCorrectProduct() throws Exception {
        Product newProduct = ProductStub.generateProduct();
        ProductDto productDto = productDtoMapper.toProductDto(newProduct);
        newProduct = productService.createNewProduct(productDto);

        Product product = productService.getProduct(newProduct.getId());
        Assertions.assertThat(product).isNotNull();
        Assertions.assertThat(product.getProductName()).isEqualTo(newProduct.getProductName());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testGetProductShouldThrowNotFoundException() throws Exception {
        Product product = productService.getProduct(1000);
    }
}