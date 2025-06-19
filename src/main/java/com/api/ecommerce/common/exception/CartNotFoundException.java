package com.api.ecommerce.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CartNotFoundException extends RuntimeException implements ErrorResponse {
    private final ProblemDetail body;

    public CartNotFoundException() {
        this.body = ProblemDetail.forStatusAndDetail(getStatusCode(), "Carrito de compras no encontrado");
    }

    @Override
    public HttpStatusCode getStatusCode() {
        return HttpStatusCode.valueOf(404);
    }

    @Override
    public ProblemDetail getBody() {
        return body;
    }
}