package com.product.productapi.config;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;


@Component
public class RestTemplateResponseErrorHandler extends DefaultResponseErrorHandler implements ResponseErrorHandler  {


    @Override
    public boolean hasError(ClientHttpResponse httpResponse) throws IOException {
        HttpStatusCode statusCode = httpResponse.getStatusCode();
        return (statusCode.is4xxClientError() || statusCode.is5xxServerError());
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        if (response.getStatusCode().is4xxClientError() || (response.getStatusCode().is5xxServerError())) {
            throw new RestTemplateCustomError(response.getStatusCode(), "Not Found");

        }
        super.handleError(response);
    }

}
