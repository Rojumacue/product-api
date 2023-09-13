package com.product.productapi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
    private String id;
    private String name;
    private Double price;
    private Boolean availability;
}
