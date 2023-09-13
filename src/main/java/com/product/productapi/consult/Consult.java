package com.product.productapi.consult;

import com.product.productapi.dto.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("product")
public class Consult {
    @GetMapping("{productId}")
    public Product productDetails(@PathVariable("productId") String productId){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Product> response =
                restTemplate.
                        getForEntity(String.format("http://localhost:3001/product/%s",productId), Product.class);
        return response.getBody();
    }
}
