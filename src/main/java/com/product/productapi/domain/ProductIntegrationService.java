package com.product.productapi.domain;


import java.util.List;

public interface ProductIntegrationService {

    Product productDetails(String productId);

    List<String> similarProductId(String productId);
}
