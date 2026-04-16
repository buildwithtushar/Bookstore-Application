package com.sinhtu8.bookstore.catalog.domain;

import com.sinhtu8.bookstore.catalog.ApplicationProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;
  private final ApplicationProperties applicationProperties;

  public PagedResult<Product> getProduct(int pageNo) {
    Sort sort = Sort.by("name").ascending();
    pageNo = pageNo <= 1 ? 0 : pageNo - 1;
    Pageable pageable = PageRequest.of(pageNo, applicationProperties.pageSize(), sort);
    Page<Product> productPage = productRepository.findAll(pageable).map(ProductMapper::toProduct);
    return new PagedResult<>(
        productPage.getContent(),
        productPage.getTotalElements(),
        productPage.getNumber() + 1,
        productPage.getTotalPages(),
        productPage.isFirst(),
        productPage.isLast(),
        productPage.hasNext(),
        productPage.hasPrevious());
  }
}
