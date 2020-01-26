package com.asellion.app.products;

import com.github.javafaker.Faker;

import java.util.Random;

public class ProductStub {
    private static Faker faker = new Faker();
    private static Random random = new Random();

    public static Product generateProduct(String productName) {
        Product product = new Product();
        product.setProductName(productName);
        product.setCurrentPrice(random.nextDouble());

        return product;
    }

    public static Product generateProduct() {
        Product product = new Product();
        String name = faker.superhero().name();
        if (name.length() > 30) {
            name = name.substring(0, 29);
        }
        product.setProductName(name);
        product.setCurrentPrice(random.nextDouble());
        return product;
    }
}
