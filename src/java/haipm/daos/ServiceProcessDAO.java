/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haipm.daos;

import haipm.db.MyConnection;
import haipm.dtos.ServiceProcessDTO;
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
public class ServiceProcessDAO implements Serializable {

    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;

    public ServiceProcessDAO() {
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

    public boolean addServiceProcess(ServiceProcessDTO process) throws Exception {
        boolean check = false;
        try {
            conn = MyConnection.getConnection();
            String sql = "Insert into tbl_ServiceInProcess(Username,ServiceID,dateBook,slot,total,Finished,State)  "
                    + "values(?,?,?,?,?,?,?)";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, process.getUsername());
            preStm.setString(2, process.getServiceID());
            preStm.setString(3, process.getDateBook());
            preStm.setInt(4, process.getSlot());
            preStm.setFloat(5, process.getTotal());
            preStm.setString(6, process.isFinished() ? "1" : "0");
            preStm.setString(7, "1");
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public List<ServiceProcessDTO> getAllProcessService() throws Exception {
        List<ServiceProcessDTO> mylist = null;
        String username, serviceID, dateBook;
        int slot;
        float total;
        boolean isFinished;
        ServiceProcessDTO dto = null;
        try {
            conn = MyConnection.getConnection();
            String sql = "Select Username,ServiceID,dateBook,slot,total,Finished, IDProcess from tbl_ServiceInProcess where Finished = ? ";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, "0");
            rs = preStm.executeQuery();
            mylist = new ArrayList<ServiceProcessDTO>();
            while (rs.next()) {
                username = rs.getString("Username");
                serviceID = rs.getString("ServiceID");
                dateBook = rs.getString("dateBook");
                slot = rs.getInt("slot");
                total = rs.getFloat("total");
                isFinished = rs.getString("Finished").equals("1") ? true : false;
                dto = new ServiceProcessDTO(username, serviceID, dateBook, slot, total, isFinished);
                dto.setIdprocess(rs.getInt("IDProcess"));
                mylist.add(dto);
            }
        } finally {
            closeConnection();
        }
        return mylist;
    }

    public boolean finishedProcess(int idProccess) throws Exception {
        boolean check = false;
        try {
            conn = MyConnection.getConnection();
            String sql = "Update tbl_ServiceInProcess set Finished = ? where IDProcess = ?";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, "1");
            preStm.setInt(2, idProccess);
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }

        return check;
    }

    public boolean CancelProcess(int idProccess) throws Exception {
        boolean check = false;
        try {
            conn = MyConnection.getConnection();
            String sql = "Update tbl_ServiceInProcess set Finished = ? , State = ? where IDProcess = ?";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, "1");
            preStm.setString(2, "0");
            preStm.setInt(3, idProccess);
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }

        return check;
    }

    public List<ServiceProcessDTO> getFinishedProcess() throws Exception {
        List<ServiceProcessDTO> mylist = null;
        String username, serviceID, date;
        int slot, IDProcess;
        float total;
        ServiceProcessDTO service = null;
        try {
            conn = MyConnection.getConnection();
            String sql = "Select Username,ServiceID,dateBook,slot,total,IDProcess,Finished,State from tbl_ServiceInProcess where Finished = ? ";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, "1");
            rs = preStm.executeQuery();
            mylist = new ArrayList<ServiceProcessDTO>();
            while (rs.next()) {
                username = rs.getString("Username");
                serviceID = rs.getString("ServiceId");
                date = rs.getString("dateBook");
                slot = rs.getInt("slot");
                IDProcess = rs.getInt("IDProcess");
                total = rs.getFloat("total");
                service = new ServiceProcessDTO(username, serviceID, date, slot, total, true);
                if(rs.getString("State").equals("0")){
                    service.setState(false);
                }
                else{
                    service.setState(true);
                }
                service.setIdprocess(IDProcess);
                mylist.add(service);
            }

        } catch (Exception e) {
        }

        return mylist;
    }

    public List<ServiceProcessDTO> getServiceOfUSer(String username) throws Exception {
        List<ServiceProcessDTO> mylist = null;
        String serviceID, date;
        int slot, IDprocess;
        float total;
        boolean finished;
        ServiceProcessDTO dto = null;
        try {
            conn = MyConnection.getConnection();
            String sql = "Select ServiceID,dateBook,slot,total,IDProcess,Finished from tbl_ServiceInProcess where Username= ?";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, username);
            rs = preStm.executeQuery();
            mylist = new ArrayList<ServiceProcessDTO>();
            while (rs.next()) {
                serviceID = rs.getString("ServiceID");
                date = rs.getString("dateBook");
                slot = rs.getInt("slot");
                total = rs.getFloat("total");
                IDprocess = rs.getInt("IDProcess");
                finished = rs.getString("Finished").equals("1") ? true : false;
                dto = new ServiceProcessDTO(username, serviceID, date, slot, total, finished);
                mylist.add(dto);
            }
        } finally {
            closeConnection();
        }

        return mylist;
    }
}
