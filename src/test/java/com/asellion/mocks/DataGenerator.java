package com.asellion.mocks;

import com.asellion.app.products.Product;
import com.asellion.app.products.ProductRepository;
import com.asellion.app.products.ProductStub;
import com.asellion.app.role.Role;
import com.asellion.app.role.RoleRepository;
import com.asellion.app.role.RoleStubs;
import com.asellion.app.role.RoleType;
import com.asellion.app.user.User;
import com.asellion.app.user.UserRepository;
import com.asellion.app.user.UserStubs;
import com.asellion.app.userRole.UserRole;
import com.asellion.app.userRole.UserRoleRepository;
import com.asellion.app.userRole.UserRoleStubs;

import java.util.Optional;

public class DataGenerator {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private UserRoleRepository userRoleRepository;
    private ProductRepository productRepository;

    public static class Builder {
        private UserRepository userRepository;
        private RoleRepository roleRepository;
        private UserRoleRepository userRoleRepository;
        private ProductRepository productRepository;

        public Builder(UserRepository userRepository, RoleRepository roleRepository, ProductRepository productRepository) {
            this.userRepository = userRepository;
            this.roleRepository = roleRepository;
            this.productRepository = productRepository;
        }

        public Builder userRoleRepo(UserRoleRepository userRoleRepository) {
            this.userRoleRepository = userRoleRepository;
            return this;
        }

        public DataGenerator build() {
            return new DataGenerator(this);
        }
    }

    private DataGenerator(Builder builder) {
        userRepository = builder.userRepository;
        roleRepository = builder.roleRepository;
        userRoleRepository = builder.userRoleRepository;
        productRepository = builder.productRepository;
    }

    public User createUser(int i) {
        User user = UserStubs.generateUser(i);
        return userRepository.save(user);
    }

    public User createUser() {
        User user = UserStubs.generateUser();
        user.addUserRole(UserRoleStubs.generateUserRole());
        return userRepository.save(user);
    }

    public UserRole createUserRole() {
        UserRole userRole = UserRoleStubs.generateUserRole();
        return userRoleRepository.save(userRole);
    }


    public Role createRole(RoleType roleType) {
        Optional<Role> optionalRole = roleRepository.findByName(roleType);
        if (optionalRole.isPresent()) {
            return optionalRole.get();
        }
        Role role = RoleStubs.generateRole();
        return roleRepository.saveAndFlush(role);
    }

    public Product createProduct(String productName) {
        return productRepository.save(ProductStub.generateProduct(productName));
    }

    public Product createProduct() {
        return productRepository.save(ProductStub.generateProduct());
    }
}

