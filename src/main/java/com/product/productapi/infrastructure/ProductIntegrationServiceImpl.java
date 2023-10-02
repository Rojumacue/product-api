package com.product.productapi.infrastructure;

import com.product.productapi.config.RestTemplateResponseErrorHandler;
import com.product.productapi.domain.Product;
import com.product.productapi.domain.ProductIntegrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class ProductIntegrationServiceImpl implements ProductIntegrationService {
    private final  RestTemplate restTemplate;
    @Value("${api.product.details}")
    public String urlProductDetail;
    @Value("${api.similar.idproduct}")
    public String urlSimilarIdProduct;

    @Autowired
    public ProductIntegrationServiceImpl (RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate =  restTemplateBuilder
                .errorHandler(new RestTemplateResponseErrorHandler())
                .build();

    }

    @Override
    public Product productDetails(String productId) {
            ResponseEntity<Product> response =
                    restTemplate.getForEntity(String.format(urlProductDetail, productId), Product.class);
            return response.getBody();
    }


    @Override
    public List<String> similarProductId(String productId) {
            ResponseEntity<String[]> response =
                    restTemplate.getForEntity(String.format(urlSimilarIdProduct, productId), String[].class);
            return Arrays.asList(Objects.requireNonNull(response.getBody()));
    }

}
