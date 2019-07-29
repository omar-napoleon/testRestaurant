/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurant.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.restaurant.model.Sales;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Omar Guillen
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Message implements Serializable{
       
    private List<Sales> object;
    
    private String description;

    public List<Sales> getObject() {
        return object;
    }

    public void setObject(List<Sales> object) {
        this.object = object;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}
