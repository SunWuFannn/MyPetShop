/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haipm.dtos;

import java.io.Serializable;

/**
 *
 * @author 99hai
 */
public class OrderLineAccessory implements Serializable{
    private String accessoryID;
    private int orderID,unit;
    private float total;
    
    public OrderLineAccessory(int orderID, String accessoryID, int unit, float total) {
        this.orderID = orderID;
        this.accessoryID = accessoryID;
        this.unit = unit;
        this.total = total;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getAccessoryID() {
        return accessoryID;
    }

    public void setAccessoryID(String accessoryID) {
        this.accessoryID = accessoryID;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
    
}
