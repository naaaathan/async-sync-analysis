package com.ufu.tcc.commonsdomain.exception;

public class RoomAlreadyOccupiedException  extends CustomException {

    public RoomAlreadyOccupiedException(ErrorMessage errorMessage) {
        super(errorMessage);
    }

    public RoomAlreadyOccupiedException() {
        super(ErrorMessage.ROOM_ALREADY_OCCUPIED);
    }


}

