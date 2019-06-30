/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haipm.models;

import haipm.daos.AccountDAO;
import haipm.dtos.AccountDTO;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author 99hai
 */
public class ProcessAccount implements Serializable {

    private String username, password;
    private AccountDTO account;
    private float cost;
    private String search;

    public ProcessAccount() {
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
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

    public AccountDTO getAccount() {
        return account;
    }

    public void setAccount(AccountDTO account) {
        this.account = account;
    }

    //check login
    public AccountDTO checkLogin() throws Exception {
        AccountDAO dao = new AccountDAO();
        return dao.checkLogin(username, password);
    }

    //register account
    public boolean register() throws Exception {
        AccountDAO dao = new AccountDAO();
        return dao.registerAccount(account);
    }

    //Update Account
    public boolean updateAccount() throws Exception {
        AccountDAO dao = new AccountDAO();
        return dao.updateInforAccount(account);
    }

    //getinfo
    public AccountDTO getInfoUser() throws Exception {
        AccountDAO dao = new AccountDAO();
        return dao.getInfoUser(username);
    }

    //get all
    public List<AccountDTO> getAllUser() throws Exception {
        AccountDAO dao = new AccountDAO();
        return dao.getAllAccount();
    }

    // ban account
    public boolean banAccount() throws Exception {
        AccountDAO dao = new AccountDAO();
        return dao.banUser(username);
    }

    //unban
    public boolean unBanAccount() throws Exception {
        AccountDAO dao = new AccountDAO();
        return dao.unBanUser(username);
    }

    //get wallet
    public float getWallet() throws Exception {
        AccountDAO dao = new AccountDAO();
        return dao.getWallet(username);
    }

    // cash
    public boolean cashWallet() throws Exception {
        AccountDAO dao = new AccountDAO();
        return dao.crash(username, cost);
    }
    
    //search 
    public List<AccountDTO> search() throws Exception{
        AccountDAO dao = new AccountDAO();
        return dao.searchByUsername(search);
    }
}
