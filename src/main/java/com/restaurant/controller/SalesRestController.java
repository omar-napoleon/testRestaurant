package com.restaurant.controller;

import com.restaurant.model.Sales;
import com.restaurant.response.Message;
import com.restaurant.service.SalesImpl;
import java.util.List;
import org.hibernate.validator.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SalesRestController {

    @Autowired
    private SalesImpl salesImpl;

    @GetMapping(value = "/sales/date/{date}", produces = {"application/json"})
    public ResponseEntity<Message> getSalesByDate(
            @PathVariable("date") @NotBlank String date) {
        Message message = new Message();
        
        try {
            List<Sales> saleList = salesImpl.getSalesByDate(date);
            if (saleList == null || saleList.isEmpty()) {
                 message.setDescription("not found");
                return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
            }
            message.setDescription("success");
            message.setObject(saleList);
            return new ResponseEntity<>(message, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/sales", produces = {"application/json"},
            consumes = {"application/json"})
    public ResponseEntity<Message> add(@RequestBody Sales sales) {
        try {
            Message message = new Message();
            message.setDescription(salesImpl.createSales(sales));
            return new ResponseEntity<>(message, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
