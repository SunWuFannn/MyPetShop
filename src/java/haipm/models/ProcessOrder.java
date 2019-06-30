/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haipm.models;

import haipm.daos.OrderDAO;
import haipm.daos.OrderLineDAO;
import haipm.dtos.OrderDTO;
import haipm.dtos.OrderLineAccessory;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 99hai
 */
public class ProcessOrder implements Serializable {

    private int orderID;
    private OrderDTO order;
    private OrderLineAccessory line;
    private String username;

    public ProcessOrder() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public OrderDTO getOrder() {
        return order;
    }

    public void setOrder(OrderDTO order) {
        this.order = order;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public OrderLineAccessory getLine() {
        return line;
    }

    public void setLine(OrderLineAccessory line) {
        this.line = line;
    }

    public boolean createOrder() throws Exception {
        OrderDAO dao = new OrderDAO();
        return dao.createOrder(order);
    }

    public int countIdOrder() throws Exception {
        OrderDAO dao = new OrderDAO();
        return dao.countIdOrder();
    }

    public OrderDTO getOrderByID() throws Exception {
        OrderDAO dao = new OrderDAO();
        return dao.getOrderByID(orderID);
    }

    public boolean addToOrder() throws Exception {
        OrderLineDAO dao = new OrderLineDAO();
        return dao.addToOrder(line, orderID);
    }

    public boolean updateTotal() throws Exception {
        OrderDAO dao = new OrderDAO();
        return dao.updateTotal(orderID, order.getTotal());
    }

    public List<OrderDTO> getAllOrder() throws Exception {
        OrderDAO dao = new OrderDAO();
        return dao.getAllOrder();
    }

    public ArrayList<String> getAllLineOfOrder() throws Exception {
        OrderDAO dao = new OrderDAO();
        return dao.getLineOfOrder(orderID);
    }

    public List<OrderDTO> getOrderOfUser() throws Exception {
        OrderDAO dao = new OrderDAO();
        return dao.getOrderOfUser(username);
    }
}
