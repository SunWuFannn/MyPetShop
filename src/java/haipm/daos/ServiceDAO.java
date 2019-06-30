/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haipm.daos;

import haipm.db.MyConnection;
import haipm.dtos.ServiceDTO;
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
public class ServiceDAO implements Serializable {

    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;

    public ServiceDAO() {
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

    public List<ServiceDTO> getAllService() throws Exception {
        List<ServiceDTO> mylist = null;
        String serviceID, serviceName, date, image;
        int slot;
        float price;
        boolean isActive;
        ServiceDTO service = null;
        try {
            conn = MyConnection.getConnection();
            String sql = "Select ServiceID,ServiceName,Price,DateSchedual,Image,Slot,Active from tbl_Service";
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            mylist = new ArrayList<ServiceDTO>();
            while (rs.next()) {
                serviceID = rs.getString("ServiceID");
                serviceName = rs.getString("ServiceName");
                price = rs.getFloat("Price");
                slot = rs.getInt("Slot");
                image = rs.getString("Image");
                date = rs.getString("DateSchedual");
                isActive = rs.getString("Active").equals("1") ? true : false;
                service = new ServiceDTO(serviceID, serviceName, date, image, slot, price, isActive);
                mylist.add(service);
            }
        } finally {
            closeConnection();
        }

        return mylist;
    }

    public boolean addService(ServiceDTO service) throws Exception {
        boolean check = false;
        try {
            conn = MyConnection.getConnection();
            String sql = "Insert into tbl_Service(ServiceID,ServiceName,Price,Slot,Image,DateSchedual,Active)  "
                    + "values(?,?,?,?,?,?,?)";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, service.getServiceID());
            preStm.setString(2, service.getServiceName());
            preStm.setFloat(3, service.getPrice());
            preStm.setInt(4, service.getSlot());
            preStm.setString(5, service.getImage());
            preStm.setString(6, service.getDate());
            preStm.setString(7, service.isIsActive() ? "1" : "0");
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }

        return check;
    }

    public boolean updateService(ServiceDTO service) throws Exception {
        boolean check = false;
        try {
            conn = MyConnection.getConnection();
            String sql = "Update tbl_Service set ServiceName = ? , Price = ? , Slot = ? , Image = ? where ServiceID = ?";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, service.getServiceName());
            preStm.setFloat(2, service.getPrice());
            preStm.setInt(3, service.getSlot());
            preStm.setString(4, service.getImage());
            preStm.setString(5, service.getServiceID());
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }

        return check;
    }

    public boolean deleteService(ServiceDTO service) throws Exception {
        boolean check = false;
        try {
            conn = MyConnection.getConnection();
            String sql = "Updatet tbl_Service set Active = ? where ServiceID = ?";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, "0");
            preStm.setString(2, service.getServiceID());
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public List<ServiceDTO> searchServiceByName(String name) throws Exception {
        List<ServiceDTO> mylist = null;
        ServiceDTO service = null;
        String serviceName, serviceID, image, date;
        int slot;
        float price;
        boolean active;
        try {
            conn = MyConnection.getConnection();
            String sql = "Select ServiceID , ServiceName, DateSchedual , Price,Slot, Image, Active from tbl_Service where ServiceName like ? ";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, "%" + name + "%");
            rs = preStm.executeQuery();
            mylist = new ArrayList<ServiceDTO>();
            while (rs.next()) {
                serviceID = rs.getString("ServiceID");
                serviceName = rs.getString("ServiceName");
                image = rs.getString("Image");
                date = rs.getString("DateSchedual");
                price = rs.getFloat("Price");
                slot = rs.getInt("Slot");
                active = rs.getString("Active").equals("1") ? true : false;
                service = new ServiceDTO(serviceID, serviceName, date, image, slot, price, active);
                mylist.add(service);
            }
        } finally {
            closeConnection();
        }

        return mylist;
    }

    public ServiceDTO findByKey(String serviceID) throws Exception {
        ServiceDTO service = null;
        try {
            conn = MyConnection.getConnection();
            String sql = "Select ServiceID , ServiceName, Image, Price , Slot from tbl_Service where ServiceID = ?";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, serviceID);
            rs = preStm.executeQuery();
            if (rs.next()) {
                service = new ServiceDTO(rs.getString("ServiceID"), rs.getString("ServiceName"), rs.getInt("Slot"), rs.getFloat("Price"));
                service.setImage(rs.getString("Image"));
            }
        } finally {
            closeConnection();
        }

        return service;
    }

    public boolean closeService(String serviceID) throws Exception {
        boolean check = false;
        try {
            conn = MyConnection.getConnection();
            String sql = "Update tbl_Service set Active = ? where ServiceID = ?";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, "0");
            preStm.setString(2, serviceID);
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean openService(String serviceID) throws Exception {
        boolean check = false;
        try {
            conn = MyConnection.getConnection();
            String sql = "Update tbl_Service set Active = ? where ServiceID = ?";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, "1");
            preStm.setString(2, serviceID);
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }
}
