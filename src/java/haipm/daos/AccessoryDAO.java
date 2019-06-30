/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haipm.daos;

import haipm.db.MyConnection;
import haipm.dtos.AccessoryDTO;
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
public class AccessoryDAO implements Serializable {

    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;

    public AccessoryDAO() {
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

    public List<AccessoryDTO> getAllAccessory(String status) throws Exception {
        List<AccessoryDTO> mylist = null;
        String AccessoryID, AccessoryName, Image, Status;
        int quantity, typeID;
        float price;
        AccessoryDTO dto = null;
        try {
            conn = MyConnection.getConnection();
            String sql = "Select AccessoryID,AccessoryName,Price,Quantity,Image,TypeID,Status from tbl_Accessory where Status = ?";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, status);
            rs = preStm.executeQuery();
            mylist = new ArrayList<AccessoryDTO>();
            while (rs.next()) {

                AccessoryID = rs.getString("AccessoryID");
                AccessoryName = rs.getString("AccessoryName");
                Image = rs.getString("Image");
                Status = rs.getString("Status");
                quantity = rs.getInt("Quantity");
                typeID = rs.getInt("TypeID");
                price = rs.getFloat("Price");

                dto = new AccessoryDTO(AccessoryID, AccessoryName, Image, Status, typeID, quantity, price);

                mylist.add(dto);
            }
        } finally {
            closeConnection();
        }

        return mylist;
    }

    public List<AccessoryDTO> getAllAccessory() throws Exception {
        List<AccessoryDTO> mylist = null;
        String AccessoryID, AccessoryName, Image, Status;
        int quantity, typeID;
        float price;
        AccessoryDTO dto = null;
        try {
            conn = MyConnection.getConnection();
            String sql = "Select AccessoryID,AccessoryName,Price,Quantity,Image,TypeID,Status from tbl_Accessory";
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            mylist = new ArrayList<AccessoryDTO>();
            while (rs.next()) {

                AccessoryID = rs.getString("AccessoryID");
                AccessoryName = rs.getString("AccessoryName");
                Image = rs.getString("Image");
                Status = rs.getString("Status");
                quantity = rs.getInt("Quantity");
                typeID = rs.getInt("TypeID");
                price = rs.getFloat("Price");

                dto = new AccessoryDTO(AccessoryID, AccessoryName, Image, Status, typeID, quantity, price);

                mylist.add(dto);
            }
        } finally {
            closeConnection();
        }

        return mylist;
    }

    public boolean addAccessory(AccessoryDTO accessory) throws Exception {
        boolean check = false;
        try {
            conn = MyConnection.getConnection();

            String sql = "Insert into tbl_Accessory(AccessoryID,AccessoryName,Price,Quantity,Image,TypeID,Status)  "
                    + "values(?,?,?,?,?,?,?)";

            preStm = conn.prepareStatement(sql);

            preStm.setString(1, accessory.getAccessID());
            preStm.setString(2, accessory.getAccessName());
            preStm.setFloat(3, accessory.getPrice());
            preStm.setInt(4, accessory.getQuantity());
            preStm.setString(5, accessory.getImage());
            preStm.setInt(6, accessory.getTypeID());
            preStm.setString(7, "true");

            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }

        return check;
    }

    public boolean updateAccessory(AccessoryDTO accessory) throws Exception {
        boolean check = false;
        try {
            conn = MyConnection.getConnection();
            String sql = "Update tbl_Accessory set AccessoryName = ?,Price = ?,Quantity = ?,Image= ?,TypeID = ?  "
                    + "where AccessoryID = ?";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, accessory.getAccessName());
            preStm.setFloat(2, accessory.getPrice());
            preStm.setInt(3, accessory.getQuantity());
            preStm.setString(4, accessory.getImage());
            preStm.setInt(5, accessory.getTypeID());
            preStm.setString(6, accessory.getAccessID());

            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }

        return check;
    }

    public boolean deleteAccessory(String accessoryID) throws Exception {
        boolean check = false;
        try {
            conn = MyConnection.getConnection();
            String sql = "Update tbl_Accessory set Status = ? where AccessoryID = ? ";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, "false");
            preStm.setString(2, accessoryID);
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public AccessoryDTO findByPK(String accessID) throws Exception {
        AccessoryDTO dto = null;
        String AccessoryID, AccessoryName, Image, Status;
        int quantity, typeID;
        float price;
        try {
            conn = MyConnection.getConnection();
            String sql = "Select AccessoryID,AccessoryName,Price,Quantity,Image,TypeID from tbl_Accessory where AccessoryID = ?";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, accessID);
            rs = preStm.executeQuery();
            if (rs.next()) {
                AccessoryID = rs.getString("AccessoryID");
                AccessoryName = rs.getString("AccessoryName");
                Image = rs.getString("Image");
                quantity = rs.getInt("Quantity");
                typeID = rs.getInt("TypeID");
                price = rs.getFloat("Price");
                dto = new AccessoryDTO(accessID, AccessoryName, Image, "true", typeID, quantity, price);
            }
        } finally {
            closeConnection();
        }

        return dto;
    }

    public List<AccessoryDTO> search(String search) throws Exception {
        List<AccessoryDTO> mylist = null;
        String AccessoryID, AccessoryName, Image, Status;
        int quantity, typeID;
        float price;
        AccessoryDTO dto = null;
        try {
            conn = MyConnection.getConnection();
            String sql = "Select AccessoryID,AccessoryName,Price,Quantity,Image,TypeID,Status from tbl_Accessory  "
                    + "where AccessoryName like ? ";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, "%" + search + "%");
            rs = preStm.executeQuery();
            mylist = new ArrayList<AccessoryDTO>();
            while (rs.next()) {
                AccessoryID = rs.getString("AccessoryID");
                AccessoryName = rs.getString("AccessoryName");
                Image = rs.getString("Image");
                quantity = rs.getInt("Quantity");
                typeID = rs.getInt("TypeID");
                price = rs.getFloat("Price");
                Status = rs.getString("Status");
                dto = new AccessoryDTO(AccessoryID, AccessoryName, Image, Status, typeID, quantity, price);
                mylist.add(dto);
            }
        } finally {
            closeConnection();
        }

        return mylist;
    }
    
    public int getQuantity(String accessID) throws Exception{
        int quantity = -1;
        
        try {
            conn = MyConnection.getConnection();
            String sql = "Select Quantity from tbl_Accessory where AccessoryID = ? ";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, accessID);
            rs = preStm.executeQuery();
            if(rs.next()){
                quantity = rs.getInt("Quantity");
            }
        } finally {
            closeConnection();
        }
        
        return quantity;
    }
    
    public boolean updateQuantity(String accessID,int quantity) throws Exception{
        boolean check = false;
        try {
            conn = MyConnection.getConnection();
            String sql = "Update tbl_Accessory set Quantity = ? where AccessoryID = ?";
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, quantity);
            preStm.setString(2, accessID);
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }
}
