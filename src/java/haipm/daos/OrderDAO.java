/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haipm.daos;

import haipm.db.MyConnection;
import haipm.dtos.OrderDTO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 99hai
 */
public class OrderDAO implements Serializable {

    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;

    public OrderDAO() {
    }

    private void closeConnection() throws Exception {
        try {
            if (rs != null) {
                rs.close();
            }
            if (preStm != null) {
                preStm.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean createOrder(OrderDTO dto) throws Exception {
        boolean check = false;
        try {
            conn = MyConnection.getConnection();
            String sql = "Insert into tbl_Order(Username,Total,DateOrder)  "
                    + "values(?,?,?)";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, dto.getUsername());
            preStm.setFloat(2, dto.getTotal());
            preStm.setString(3, dto.getDate());
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }

        return check;
    }

    public OrderDTO getOrderByID(int id) throws Exception {
        OrderDTO order = null;
        int orderID;
        String username, date;
        float total;
        try {
            conn = MyConnection.getConnection();
            String sql = "Select OrderID,Username,Total,DateOrder from tbl_Order where OrderID = ?";
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            if (rs.next()) {
                orderID = rs.getInt("OrderID");
                username = rs.getString("Username");
                date = rs.getString("DateOrder");
                total = rs.getFloat("Total");
                order = new OrderDTO(username, date, total);
                order.setOrderID(orderID);
            }
        } finally {
            closeConnection();
        }
        return order;
    }

    public int countIdOrder() throws Exception {
        int count = 0;
        try {
            conn = MyConnection.getConnection();
            String sql = "Select MAX(OrderID) as count from tbl_Order";
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            if (rs.next()) {
                count = rs.getInt("count");
            }
        } finally {
            closeConnection();
        }
        return count;
    }

    public boolean updateTotal(int id, float total) throws Exception {
        boolean check = false;
        try {
            conn = MyConnection.getConnection();
            String sql = "Update tbl_Order set Total = ? where OrderID = ?";
            preStm = conn.prepareStatement(sql);
            preStm.setFloat(1, total);
            preStm.setInt(2, id);
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }

        return check;
    }

    public List<OrderDTO> getAllOrder() throws Exception {
        List<OrderDTO> mylist = null;
        String username, date;
        float total;
        int orderID;
        OrderDTO order = null;
        try {
            conn = MyConnection.getConnection();
            String sql = "Select OrderID,Username,Total,DateOrder from tbl_Order";
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            mylist = new ArrayList<OrderDTO>();
            while (rs.next()) {
                orderID = rs.getInt("OrderID");
                username = rs.getString("Username");
                date = rs.getString("DateOrder");
                total = rs.getFloat("Total");
                order = new OrderDTO(username, date, total);
                order.setOrderID(orderID);
                mylist.add(order);
            }
        } finally {
            closeConnection();
        }

        return mylist;
    }

    public ArrayList<String> getLineOfOrder(int orderID) throws Exception {
        ArrayList<String> allLine = null;
        String acessID;
        int unit;
        float total;
        String line;
        try {
            conn = MyConnection.getConnection();
            String sql = "Select AccessoryID,Unit,Total from tbl_OrderLineAccessory where OrderID = ? ";
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, orderID);
            rs = preStm.executeQuery();
            allLine = new ArrayList<String>();
            while (rs.next()) {
                acessID = rs.getString("AccessoryID");
                unit = rs.getInt("Unit");
                total = rs.getFloat("Total");
                line = acessID + "-" + unit + "-" + total;
                allLine.add(line);
            }
        } finally {
            closeConnection();
        }

        return allLine;
    }

    public List<OrderDTO> getOrderOfUser(String username) throws Exception {
        List<OrderDTO> mylist = null;
        String date;
        float total;
        int orderID;
        OrderDTO order = null;
        try {
            conn = MyConnection.getConnection();
            String sql = "Select OrderID,Total,DateOrder from tbl_Order where Username = ?";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, username);
            rs = preStm.executeQuery();
            mylist = new ArrayList<OrderDTO>();
            while (rs.next()) {
                orderID = rs.getInt("OrderID");
                date = rs.getString("DateOrder");
                total = rs.getFloat("Total");
                order = new OrderDTO(username, date, total);
                order.setOrderID(orderID);
                mylist.add(order);
            }
        } finally {
            closeConnection();
        }

        return mylist;
    }

    public List<OrderDTO> searchByDate(String date) throws Exception {
        List<OrderDTO> mylist = null;
        String username;
        float total;
        int orderID;
        OrderDTO order = null;
        try {
            conn = MyConnection.getConnection();
            String sql = "Select OrderID,Username,Total,DateOrder from tbl_Order where DateOrder = ?";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, date);
            rs = preStm.executeQuery();
            mylist = new ArrayList<OrderDTO>();
            while (rs.next()) {
                orderID = rs.getInt("OrderID");
                username = rs.getString("Username");
                total = rs.getFloat("Total");
                order = new OrderDTO(username, date, total);
                order.setOrderID(orderID);
                mylist.add(order);
            }
        } finally {
            closeConnection();
        }
        return mylist;
    }
}
