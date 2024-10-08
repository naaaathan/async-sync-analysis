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
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/reserve")
public class ReserveController {

    private final AsyncReserveService asyncReserveService;

    @Autowired
    public ReserveController(AsyncReserveService asyncReserveService) {
        this.asyncReserveService = asyncReserveService;
    }

    @ResponseStatus(code = HttpStatus.OK)
    @PostMapping("/{customerId}")
    public Mono<Void> reserveHotelRoom(@PathVariable Long customerId, @RequestBody ReserveDataRecord reserveDataRecord) {
        return asyncReserveService.reserveHotelRoom(customerId, reserveDataRecord, reserveDataRecord.paymentMethod()).then(Mono.empty());
    }


}
