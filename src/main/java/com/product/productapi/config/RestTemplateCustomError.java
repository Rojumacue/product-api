package com.product.productapi.config;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatusCode;

@Getter
@Setter
@ToString
public class RestTemplateCustomError extends RuntimeException  {
    private final HttpStatusCode statusCode;
    private final String error;
    public RestTemplateCustomError(HttpStatusCode statusCode, String error) {
        super(error);
        this.statusCode = statusCode;
        this.error = error;
    }

}