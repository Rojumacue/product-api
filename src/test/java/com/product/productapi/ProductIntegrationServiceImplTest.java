package com.product.productapi;

import com.product.productapi.domain.Product;
import com.product.productapi.infrastructure.ProductIntegrationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
@SpringBootTest
@ActiveProfiles("test")
public class ProductIntegrationServiceImplTest {

    private ProductIntegrationServiceImpl service;

    private RestTemplate restTemplateMock;

    @Value("${api.product.details}")
    private String urlProductDetail;
    @Value("${api.similar.idproduct}")
    private String urlSimilarIdProduct;

    public static final String testProductId = "1";
    public static final  String[] expectedSimilarProductIds = {"2", "3","4"};

    public static final Product product = new Product("2","Dress",19.99,Boolean.TRUE);
    public static final Product product2 = new Product("3","Blazer",29.99,Boolean.FALSE);
    public static final Product product3 = new Product("4","Boots",39.99,Boolean.TRUE);
    @BeforeEach
    public void setUp() {
        restTemplateMock = mock(RestTemplate.class);

        RestTemplateBuilder restTemplateBuilderMock = mock(RestTemplateBuilder.class);
        when(restTemplateBuilderMock.errorHandler(any())).thenReturn(restTemplateBuilderMock);
        when(restTemplateBuilderMock.build()).thenReturn(restTemplateMock);

        service = new ProductIntegrationServiceImpl(restTemplateBuilderMock);
        service.urlProductDetail = urlProductDetail;
        service.urlSimilarIdProduct=urlSimilarIdProduct;

    }
    @Test
    void similarProductId_returnsList_whenResponseBodyNotNull() {
        when(restTemplateMock.getForEntity(String.format(urlSimilarIdProduct, testProductId), String[].class))
                .thenReturn(ResponseEntity.ok(expectedSimilarProductIds));

        List<String> result = service.similarProductId(testProductId);

        assertEquals(List.of(expectedSimilarProductIds), result);
    }

    @Test
    void similarProductId_throwsException_whenResponseBodyIsNull() {

        when(restTemplateMock.getForEntity(String.format(service.urlSimilarIdProduct, testProductId), String[].class))
                .thenReturn(ResponseEntity.ok(null));

        assertThrows(NullPointerException.class, () -> {
            service.similarProductId(testProductId);
        });
    }

    @Test
    void similarProductId_handlesException_whenRestTemplateThrowsException() {
        when(restTemplateMock.getForEntity(String.format(service.urlSimilarIdProduct, testProductId), String[].class))
                .thenThrow(new RuntimeException("Simulated exception"));

        assertThrows(RuntimeException.class, () -> {
            service.similarProductId(testProductId);
        });
    }
    @Test
    void productDetails_shouldReturnCorrectProduct() {

        when(restTemplateMock.getForEntity(String.format(urlProductDetail, testProductId), Product.class))
                .thenReturn(ResponseEntity.ok(product));

        Product result = service.productDetails(testProductId);

        assertEquals(product, result);
    }


}