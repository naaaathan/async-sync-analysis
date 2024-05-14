package com.ufu.tcc.commonsdomain.exception;

abstract class CustomException extends RuntimeException{

    private ErrorMessage errorMessage;

    public CustomException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
    }
}
