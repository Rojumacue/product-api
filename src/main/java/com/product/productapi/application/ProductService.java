package com.product.productapi.application;

import com.product.productapi.domain.Product;

import java.util.List;

public interface ProductService {


    List<Product> similarProducts(String productId);

}
