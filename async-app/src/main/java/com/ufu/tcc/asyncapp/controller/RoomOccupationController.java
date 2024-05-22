package com.ufu.tcc.asyncapp.controller;

import com.ufu.tcc.commonsdomain.records.request.RoomOccupationRequestRecord;
import com.ufu.tcc.commonsdomain.records.response.RoomOccupationResponseRecord;
import com.ufu.tcc.commonsdomain.service.RoomOccupationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/room-occupation")
public class RoomOccupationController {

    private final RoomOccupationService roomOccupationService;

    @Autowired
    public RoomOccupationController(RoomOccupationService roomOccupationService) {
        this.roomOccupationService = roomOccupationService;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/create")
    public RoomOccupationResponseRecord createRoomOccupation(@RequestBody RoomOccupationRequestRecord roomOccupationRecord) {
        return roomOccupationService.createRoomOccupation(roomOccupationRecord);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/batch/create")
    public List<RoomOccupationResponseRecord> createRoomOccupationBatch(@RequestBody RoomOccupationRequestRecord roomOccupationRecord) {
        return roomOccupationService.createRoomOccupationBatch(roomOccupationRecord);
    }

}
