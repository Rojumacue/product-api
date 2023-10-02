package com.product.productapi.infrastructure;

import com.product.productapi.domain.Product;

import java.util.List;

public interface ProductIntegrationService {

    Product productDetails(String productId);

    List<String> similarProductId(String productId);
}
