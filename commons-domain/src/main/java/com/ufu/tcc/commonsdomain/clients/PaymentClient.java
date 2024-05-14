package com.ufu.tcc.commonsdomain.clients;

import com.ufu.tcc.commonsdomain.records.PaymentMethod;
import com.ufu.tcc.commonsdomain.records.PaymentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

@Component
@FeignClient(name = "payment", url = "${payment.uri}")
public interface PaymentClient {

    @PostMapping("/credit-card/process")
    ResponseEntity<PaymentResponse> processPayment(PaymentMethod paymentRequest);
}
