/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haipm.daos;

import haipm.db.MyConnection;
import haipm.dtos.OrderLineAccessory;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author 99hai
 */
public class OrderLineDAO implements Serializable {

    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;

    public OrderLineDAO() {
    }

    private void closeConnection() {
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
    
    public boolean addToOrder(OrderLineAccessory line , int orderID) throws Exception{
        boolean check = false;
        try {
            conn = MyConnection.getConnection();
            String sql ="Insert into tbl_OrderLineAccessory(OrderID,AccessoryID,Unit,Total)  "
                    + "values(?,?,?,?)";
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, orderID);
            preStm.setString(2, line.getAccessoryID());
            preStm.setInt(3, line.getUnit());
            preStm.setFloat(4, line.getTotal());
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }
    
}
