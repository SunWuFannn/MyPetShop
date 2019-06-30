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
public class AccountDTO implements Serializable {

    String username, password, role, fullname, phone, address;
    float wallet;
    boolean status;

    public AccountDTO() {
    }

    public AccountDTO(String username, String password, String role, String fullname, String phone, String address, float wallet) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.fullname = fullname;
        this.phone = phone;
        this.address = address;
        this.wallet = wallet;
    }

    public AccountDTO(String username, String role, String fullname, String phone, String address, float wallet) {
        this.username = username;
        this.role = role;
        this.fullname = fullname;
        this.phone = phone;
        this.address = address;
        this.wallet = wallet;
    }

    public AccountDTO(String username, String fullname, String phone, String address) {
        this.username = username;
        this.fullname = fullname;
        this.phone = phone;
        this.address = address;
    }

    public AccountDTO(String username, String role, String fullname, String phone, String address, float wallet, boolean status) {
        this.username = username;
        this.role = role;
        this.fullname = fullname;
        this.phone = phone;
        this.address = address;
        this.wallet = wallet;
        this.status = status;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getWallet() {
        return wallet;
    }

    public void setWallet(float wallet) {
        this.wallet = wallet;
    }

    @Override
    public String toString() {
        return "AccountDTO{" + "username=" + username + ", password=" + password + ", role=" + role + ", fullname=" + fullname + ", phone=" + phone + ", address=" + address + ", wallet=" + wallet + ", status=" + status + '}';
    }

}
