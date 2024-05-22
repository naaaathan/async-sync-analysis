package com.ufu.tcc.asyncapp.controller;

import com.ufu.tcc.asyncapp.service.AsyncReserveService;
import com.ufu.tcc.commonsdomain.records.ReserveDataRecord;
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

    private final AsyncReserveService syncReserveService;

    @Autowired
    public ReserveController(AsyncReserveService syncReserveService) {
        this.syncReserveService = syncReserveService;
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping("/{customerId}")
    public void reserveHotelRoom(@PathVariable Long customerId, @RequestBody ReserveDataRecord reserveDataRecord) {
        syncReserveService.reserveHotelRoom(customerId, reserveDataRecord, reserveDataRecord.paymentMethod());
    }


}
