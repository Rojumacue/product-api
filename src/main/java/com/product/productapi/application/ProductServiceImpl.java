package com.product.productapi.application;

import com.product.productapi.domain.Product;
import com.product.productapi.infrastructure.ProductIntegrationService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductServiceImpl  implements ProductService {

    private final ProductIntegrationService integrationService;

    public ProductServiceImpl(ProductIntegrationService integrationService) {
        this.integrationService = integrationService;
    }

    @Override
    public List<Product> similarProducts(String productId) {
        List<String> ids = integrationService.similarProductId(productId);
        return ids.stream()
                .map(integrationService::productDetails)
                .toList();
    }

}
