package com.product.productapi.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Product {
    private String id;
    private String name;
    private Double price;
    private Boolean availability;
}
