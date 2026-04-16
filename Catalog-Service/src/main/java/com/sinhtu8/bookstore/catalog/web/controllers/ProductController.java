package com.sinhtu8.bookstore.catalog.web.controllers;

import com.sinhtu8.bookstore.catalog.domain.PagedResult;
import com.sinhtu8.bookstore.catalog.domain.Product;
import com.sinhtu8.bookstore.catalog.domain.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
class ProductController {

  private final ProductService productService;

  @GetMapping
  PagedResult<Product> getProduct(@RequestParam(name = "page", defaultValue = "1") int pageNo) {
    return productService.getProduct(pageNo);
  }
}
