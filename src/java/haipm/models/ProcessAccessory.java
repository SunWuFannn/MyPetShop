/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haipm.models;

import haipm.daos.AccessoryDAO;
import haipm.dtos.AccessoryDTO;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author 99hai
 */
public class ProcessAccessory implements Serializable {

    private AccessoryDTO accessory;
    private String accessoryID;
    private String search;
    private int quantity;

    public ProcessAccessory() {
    }

    public String getAccessoryID() {
        return accessoryID;
    }

    public void setAccessoryID(String accessoryID) {
        this.accessoryID = accessoryID;
    }

    public AccessoryDTO getAccessory() {
        return accessory;
    }

    public void setAccessory(AccessoryDTO accessory) {
        this.accessory = accessory;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public List<AccessoryDTO> getAllAccessory() throws Exception {
        AccessoryDAO dao = new AccessoryDAO();
        return dao.getAllAccessory();
    }

    public List<AccessoryDTO> getAllActiveAccessory() throws Exception {
        AccessoryDAO dao = new AccessoryDAO();
        return dao.getAllAccessory("true");
    }

    public boolean addAccessory() throws Exception {
        AccessoryDAO dao = new AccessoryDAO();
        return dao.addAccessory(accessory);
    }

    public boolean updateAccessory() throws Exception {
        AccessoryDAO dao = new AccessoryDAO();
        return dao.updateAccessory(accessory);
    }

    public boolean deleteAccessory() throws Exception {
        AccessoryDAO dao = new AccessoryDAO();
        return dao.deleteAccessory(accessoryID);
    }

    public AccessoryDTO findByPK() throws Exception {
        AccessoryDAO dao = new AccessoryDAO();
        return dao.findByPK(accessoryID);
    }

    public List<AccessoryDTO> search() throws Exception {
        AccessoryDAO dao = new AccessoryDAO();
        return dao.search(search);
    }

    public int getQuantityByID() throws Exception {
        AccessoryDAO dao = new AccessoryDAO();
        return dao.getQuantity(accessoryID);
    }

    public boolean checkValidQuantity() throws Exception {
        return quantity <= getQuantityByID();
    }
    public int countQuantityAfterBuy() throws Exception{
        return getQuantityByID() - quantity;
    }
    
    public boolean updateQuantity() throws Exception{
        AccessoryDAO dao = new AccessoryDAO();
        return dao.updateQuantity(accessoryID, quantity);
    }
}
