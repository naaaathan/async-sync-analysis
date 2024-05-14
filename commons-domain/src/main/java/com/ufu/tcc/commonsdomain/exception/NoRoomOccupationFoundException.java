package com.ufu.tcc.commonsdomain.exception;

public class NoRoomOccupationFoundException extends CustomException {

    public NoRoomOccupationFoundException() {
        super(ErrorMessage.NO_ROOM_OCCUPATION_FOUND);
    }
}
