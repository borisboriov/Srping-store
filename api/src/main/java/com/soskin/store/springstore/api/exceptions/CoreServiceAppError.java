package com.soskin.store.springstore.api.exceptions;

public class CoreServiceAppError extends AppError {
    public enum CoreServiceErrors {
        PRODUCT_NOT_FOUND
    }

    public CoreServiceAppError(String code, String message) {
        super(code, message);
    }

    public CoreServiceAppError() {
    }//todo почем тут деф конструктор нужен??
}
