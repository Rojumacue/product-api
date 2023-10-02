package com.product.productapi.application;

import com.product.productapi.domain.Product;
import com.product.productapi.domain.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("product")
public class ProductRestController {
    @Autowired
    private ProductService productService;

    @GetMapping("/{productId}/similar/")
    public List<Product> similarProducts(@PathVariable("productId") String productId) {
        return productService.similarProducts(productId);
    }

}
