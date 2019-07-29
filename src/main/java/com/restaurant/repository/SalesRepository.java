package com.restaurant.repository;

import com.restaurant.model.Sales;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesRepository extends JpaRepository<Sales, Long> {
    //User findByUsername(String username);
    List<Sales> findByDate(LocalDate date);
}
