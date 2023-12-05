package com.oy.expensetrackerapi.exception;

public class ItemAlreadyExistsException extends RuntimeException{

    private static final Long serialVersionUID = 1L;

    public ItemAlreadyExistsException(String message){
        super(message);
    }

}
