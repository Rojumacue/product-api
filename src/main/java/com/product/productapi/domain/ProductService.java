package com.product.productapi.domain;


import java.util.List;

public interface ProductService {

    List<Product> similarProducts(String productId);

}
