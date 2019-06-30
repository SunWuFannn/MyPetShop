/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haipm.dtos;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author 99hai
 */
public class OrderDTO implements Serializable {

    private String username, date;
    private float total;
    private int orderID;
    private ArrayList<String> allLine;

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public OrderDTO(String username, String date, float total) {
        this.username = username;
        this.date = date;
        this.total = total;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public OrderDTO() {
    }

    public ArrayList<String> getAllLine() {
        return allLine;
    }

    public void setAllLine(ArrayList<String> allLine) {
        this.allLine = allLine;
    }

}
