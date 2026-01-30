package com.devpull.antifraudservice.controller;

import com.devpull.antifraudservice.kafka.EventProducer;
import com.devpull.transactionservice.domain.enums.TransactionStatus;
import com.devpull.transactionservice.domain.model.Transaction;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/antifraud")
public class AntifraudController {

    private final EventProducer eventProducer;

    public AntifraudController(EventProducer eventProducer){
        this.eventProducer = eventProducer;
    }

    @PostMapping
    public String evaluateAntifraud(@RequestBody Transaction transaction) throws Exception {
        String msj = transaction.getAmount() > 1000 ? "REJECT" : "APPROVED";
        transaction.setStatus(TransactionStatus.valueOf(msj));
        eventProducer.sendMessage(transaction);

        return "Event handled successfully!";
    }
}
