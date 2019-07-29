/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurant.service;

import com.restaurant.model.Sales;
import com.restaurant.repository.SalesRepository;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Omar Guillen
 */
@Service
public class SalesImpl {

    @Autowired
    private SalesRepository repository;

    @Transactional(rollbackFor = Exception.class)
    public String createSales(Sales sale) throws Exception {
            repository.save(sale);
            return "success";
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public List<Sales> getSalesByDate(String date) throws Exception {
        return repository.findByDate(LocalDate.parse(date));
    }
}
