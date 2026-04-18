package com.sinhtu8.bookstore.catalog.web.controllers;

import com.sinhtu8.bookstore.catalog.domain.PagedResult;
import com.sinhtu8.bookstore.catalog.domain.Product;
import com.sinhtu8.bookstore.catalog.domain.ProductNotFoundException;
import com.sinhtu8.bookstore.catalog.domain.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
class ProductController {

  private final ProductService productService;

  @GetMapping
  PagedResult<Product> getProduct(@RequestParam(name = "page", defaultValue = "1") int pageNo) {
    return productService.getProduct(pageNo);
  }

  @GetMapping("/{code}")
  ResponseEntity<Product> getProductByCode(@PathVariable String code) {
    return new ResponseEntity<>(productService.getProductByCode(code).orElseThrow(()-> ProductNotFoundException.forCode(code)), HttpStatus.OK);
  }
}
