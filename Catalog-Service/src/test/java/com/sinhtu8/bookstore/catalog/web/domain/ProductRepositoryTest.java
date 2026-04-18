package com.sinhtu8.bookstore.catalog.web.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.List;

import com.sinhtu8.bookstore.catalog.domain.ProductEntity;
import com.sinhtu8.bookstore.catalog.domain.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest(
        properties = {
                "spring.test.database.replace=none",
                "spring.datasource.url=jdbc:tc:postgresql:16-alpine:///db",
        })
// @Import(ContainersConfig.class)
@Sql("/test-data.sql")
public class ProductRepositoryTest {

        @Autowired
        private ProductRepository productRepository;

        // You don't need to test the methods provided by Spring Data JPA.
        // Below test is to demonstrate how to write tests for the repository layer.
        @Test
        void shouldGetAllProducts() {
                List<ProductEntity> products = productRepository.findAll();
                assertThat(products).hasSize(15);
        }

        @Test
        void shouldGetProductByCode() {
                ProductEntity product = productRepository.findByCode("P100").orElseThrow();
                assertThat(product.getCode()).isEqualTo("P100");
                assertThat(product.getName()).isEqualTo("The Hunger Games");
                assertThat(product.getDescription()).isEqualTo("Winning will make you famous. Losing means certain death...");
                assertThat(product.getPrice()).isEqualTo(new BigDecimal("34.0"));
        }

        @Test
        void shouldReturnEmptyWhenProductCodeNotExists() {
                assertThat(productRepository.findByCode("invalid_product_code")).isEmpty();
        }
}
