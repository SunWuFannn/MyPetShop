/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haipm.daos;

import haipm.db.MyConnection;
import haipm.dtos.AccountDTO;
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
public class AccountDAO implements Serializable {

    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;

    public AccountDAO() {
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

    public AccountDTO checkLogin(String username, String password) throws Exception {
        AccountDTO account = null;
        try {
            conn = MyConnection.getConnection();
            String sql = "Select Role,Fullname,Phone,Address,Wallet from tbl_Account where Username = ? and Password = ? and Status = ?";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, username);
            preStm.setString(2, password);
            preStm.setString(3, "1");
            rs = preStm.executeQuery();
            if (rs.next()) {
                account = new AccountDTO(username, rs.getString("Role"), rs.getString("Fullname"),
                        rs.getString("Phone"), rs.getString("Address"), rs.getFloat("Wallet"));
            }
        } finally {
            closeConnection();
        }
        return account;
    }

    public boolean registerAccount(AccountDTO account) throws Exception {
        boolean check = false;
        System.out.println(account.getFullname());
        try {
            conn = MyConnection.getConnection();

            String sql = "Insert into tbl_Account(Username,Password,Fullname,Role,Wallet,Status)  "
                    + "values(?,?,?,?,?,1)";

            preStm = conn.prepareStatement(sql);

            preStm.setString(1, account.getUsername());
            preStm.setString(2, account.getPassword());
            preStm.setString(3, account.getFullname());
            preStm.setString(4, account.getRole());
            preStm.setFloat(5, account.getWallet());
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }

        return check;
    }

    public boolean updateInforAccount(AccountDTO account) throws Exception {
        boolean check = false;
        try {
            conn = MyConnection.getConnection();
            String sql = "Update tbl_Account set Fullname = ? , Phone = ? ,Address= ?  where Username = ?";
            preStm = conn.prepareStatement(sql);

            preStm.setString(1, account.getFullname());
            preStm.setString(2, account.getPhone());
            preStm.setString(3, account.getAddress());
            preStm.setString(4, account.getUsername());

            check = preStm.executeUpdate() > 0;

        } finally {
            closeConnection();
        }

        return check;
    }

    public AccountDTO getInfoUser(String username) throws Exception {
        AccountDTO user = null;
        String role, fullname, phone, address;
        float wallet;
        try {
            conn = MyConnection.getConnection();
            String sql = "Select Username,Role,Fullname,Phone,Address,Wallet from tbl_Account where Username = ?";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, username);
            rs = preStm.executeQuery();
            while (rs.next()) {
                role = rs.getString("Role");
                fullname = rs.getString("Fullname");
                phone = rs.getString("Phone");
                address = rs.getString("Address");
                wallet = rs.getFloat("Wallet");
                user = new AccountDTO(username, role, fullname, phone, address, wallet);
            }
        } finally {
            closeConnection();
        }

        return user;
    }

    public List<AccountDTO> getAllAccount() throws Exception {
        List<AccountDTO> mylist = null;
        String username, role, fullname, address, phone;
        float wallet;
        boolean status;
        AccountDTO dto = null;
        try {
            conn = MyConnection.getConnection();
            String sql = "Select Username,Role,Fullname,Phone,Address,Wallet,Status from tbl_Account";
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            mylist = new ArrayList<AccountDTO>();
            while (rs.next()) {
                username = rs.getString("Username");
                role = rs.getString("Role");
                fullname = rs.getString("Fullname");
                if (rs.getString("Phone") != null) {
                    phone = rs.getString("Phone");
                } else {
                    phone = "";
                }
                if (rs.getString("Address") != null) {
                    address = rs.getString("Address");
                } else {
                    address = "";
                }
                wallet = rs.getFloat("Wallet");
                status = rs.getString("Status").equals("1") ? true : false;
                dto = new AccountDTO(username, role, fullname, phone, address, wallet, status);
                System.out.println(dto.toString());
                mylist.add(dto);
            }
        } finally {
            closeConnection();
        }

        return mylist;
    }

    public boolean banUser(String username) throws Exception {
        boolean check = false;
        try {
            conn = MyConnection.getConnection();
            String sql = "Update tbl_Account set Status = ? where Username = ?";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, "0");
            preStm.setString(2, username);
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean unBanUser(String username) throws Exception {
        boolean check = false;
        try {
            conn = MyConnection.getConnection();
            String sql = "Update tbl_Account set Status = ? where Username = ?";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, "1");
            preStm.setString(2, username);
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public float getWallet(String username) throws Exception {
        float wallet = -1;
        try {
            conn = MyConnection.getConnection();
            String sql = "Select Wallet from tbl_Account where Username = ? ";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, username);
            rs = preStm.executeQuery();
            if (rs.next()) {
                wallet = rs.getFloat("Wallet");
            }
        } finally {
            closeConnection();
        }
        return wallet;
    }

    public boolean crash(String username, float cost) throws Exception {
        boolean check = false;
        try {
            conn = MyConnection.getConnection();
            String sql = "Update tbl_Account set Wallet = ? where Username = ?";
            preStm = conn.prepareStatement(sql);
            preStm.setFloat(1, cost);
            preStm.setString(2, username);
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public List<AccountDTO> searchByUsername(String search) throws Exception {
        List<AccountDTO> mylist = null;
        String username, role, fullname, address, phone;
        float wallet;
        boolean status;
        AccountDTO dto = null;
        try {
            conn = MyConnection.getConnection();
            String sql = "Select Username,Role,Fullname,Phone,Address,Wallet,Status from tbl_Account Where Username like ? ";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, "%" + search + "%");
            rs = preStm.executeQuery();
            mylist = new ArrayList<AccountDTO>();
            while (rs.next()) {
                username = rs.getString("Username");
                role = rs.getString("Role");
                fullname = rs.getString("Fullname");
                address = rs.getString("Address");
                phone = rs.getString("Phone");
                wallet = rs.getFloat("Wallet");
                status = rs.getString("Status").equals("1") ? true : false;
                dto = new AccountDTO(username, role, fullname, phone, address, wallet, status);
                mylist.add(dto);
            }
        } finally {
            closeConnection();
        }
        return mylist;
    }
}
