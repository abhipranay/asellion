package com.asellion.app.products;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProductRepository productRepository;

    @Before
    public void setUp() throws Exception {
        entityManager.clear();
    }

    @Test
    public void shouldFindByNameCorrectly() {
        Product stub = ProductStub.generateProduct();
        entityManager.persistAndFlush(stub);
        Product savedProduct = productRepository.findByProductName(stub.getProductName());
        Assertions.assertThat(savedProduct)
                .isNotNull()
                .isEqualTo(stub);
    }

    @Test
    public void shouldFindByIdCorrectly() {
        Product stub = ProductStub.generateProduct();
        entityManager.persistAndFlush(stub);
        Optional<Product> savedProduct = productRepository.findById(stub.getId());
        Assertions.assertThat(savedProduct)
                .isPresent();
        Assertions.assertThat(savedProduct.get().getProductName())
                .isEqualTo(stub.getProductName());
    }

    @Test
    public void shouldReturnEmptyOptionalIfIdNotPresent() {
        Optional<Product> savedProduct = productRepository.findById(1000L);
        Assertions.assertThat(savedProduct)
                .isNotPresent();
    }
}