package com.ufu.tcc.commonsdomain.exception;

public enum ErrorMessage {

    NO_ROOM_OCCUPATION_FOUND("No room occupation found"),
    ROOM_ALREADY_OCCUPIED("Room already occupied");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
