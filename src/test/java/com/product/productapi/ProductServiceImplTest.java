package com.product.productapi;

import com.product.productapi.application.ProductServiceImpl;
import com.product.productapi.domain.Product;
import com.product.productapi.domain.ProductIntegrationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.product.productapi.ProductIntegrationServiceImplTest.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
 class ProductServiceImplTest {

    @Mock
    private ProductIntegrationService integrationService;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
     void similarProducts_returnsListOfProducts() {

        when(integrationService.similarProductId(testProductId)).thenReturn(List.of(expectedSimilarProductIds));
        when(integrationService.productDetails(product.getId())).thenReturn(product);
        when(integrationService.productDetails(product2.getId())).thenReturn(product2);
        when(integrationService.productDetails(product3.getId())).thenReturn(product3);

        List<Product> result = productService.similarProducts(testProductId);

        assertEquals(3, result.size());
        assertEquals(product, result.get(0));
        assertEquals(product2, result.get(1));
        assertEquals(product3, result.get(2));
    }
}
