package com.asellion.utils;


import com.asellion.app.products.Product;
import com.asellion.app.products.ProductRepository;
import com.asellion.app.role.Role;
import com.asellion.app.role.RoleType;
import com.asellion.app.user.User;
import com.asellion.app.userRole.UserRole;
import com.asellion.app.userRole.UserRoleRepository;
import com.github.javafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DbSeed implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(DbSeed.class);

    private UserRoleRepository userRoleRepository;
    private ProductRepository productRepository;

    public DbSeed(
            UserRoleRepository userRoleRepository, ProductRepository productRepository) {
        this.userRoleRepository = userRoleRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... strings) {
        //Password is 'password' but it has been encrypted.
        logger.info("Loading data...");
        User user = getUser();
        Role roleUser = new Role(RoleType.ROLE_USER);
        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(roleUser);
        try {
            userRoleRepository.save(userRole);
            logger.info("Saved user");
            createProducts();
            logger.info("Created products");
        } catch (DataIntegrityViolationException e) {
            logger.debug("Already Migrated", e);
        }
    }

    private User getAdmin() {
        User admin = new User();
        admin.setPassword("$2a$10$a1i476ODUG7jqm1x30ThA.v8qYsAQlbLBpfPSW.8ISm2Z8QiC5ASm");
        admin.setEmail("admin@email.com");
        admin.setFirstname("Abhipranay");
        admin.setLastname("Chauhan");
        admin.setLastLogin(LocalDateTime.now());
        return admin;
    }

    private User getUser() {
        User user = new User();
        user.setPassword("$2a$10$a1i476ODUG7jqm1x30ThA.v8qYsAQlbLBpfPSW.8ISm2Z8QiC5ASm");
        user.setEmail("user@email.com");
        user.setFirstname("Emmanuel");
        user.setLastname("Pat");
        user.setLastLogin(LocalDateTime.now());
        return user;
    }

    private void createProducts() {
        int num = 100;
        Faker faker = new Faker();
        for (int i = 0; i < num; i++) {
            Product product = new Product();
            product.setProductName(faker.superhero().name());
            product.setCurrentPrice(100.01);
            productRepository.save(product);
        }
    }
}
