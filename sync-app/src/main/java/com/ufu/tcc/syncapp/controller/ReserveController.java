package com.ufu.tcc.syncapp.controller;

import com.ufu.tcc.commonsdomain.records.ReserveDataRecord;
import com.ufu.tcc.syncapp.service.SyncReserveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reserve")
public class ReserveController {

    private final SyncReserveService syncReserveService;

    @Autowired
    public ReserveController(SyncReserveService syncReserveService) {
        this.syncReserveService = syncReserveService;
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping("/{customerId}")
    public void reserveHotelRoom(@PathVariable Long customerId, @RequestBody ReserveDataRecord reserveDataRecord) {
        syncReserveService.reserveHotelRoom(customerId, reserveDataRecord, reserveDataRecord.paymentMethod());
    }


}
