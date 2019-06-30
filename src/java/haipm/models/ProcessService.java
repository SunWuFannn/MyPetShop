/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haipm.models;

import haipm.daos.ServiceDAO;
import haipm.dtos.ServiceDTO;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author 99hai
 */
public class ProcessService implements Serializable {

    private ServiceDTO service;
    private String search;
    private int slot;
    private String serviceID;

    public ProcessService() {

    }

    public ServiceDTO getService() {
        return service;
    }

    public void setService(ServiceDTO service) {
        this.service = service;
    }

    public String getSearch() {
        return search;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public String getServiceID() {
        return serviceID;
    }

    public void setServiceID(String serviceID) {
        this.serviceID = serviceID;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    //get All
    public List<ServiceDTO> getAllService() throws Exception {
        ServiceDAO dao = new ServiceDAO();
        return dao.getAllService();
    }

    //add
    public boolean addService() throws Exception {
        ServiceDAO dao = new ServiceDAO();
        return dao.addService(service);
    }

    // update
    public boolean updateService() throws Exception {
        ServiceDAO dao = new ServiceDAO();
        return dao.updateService(service);
    }

    //delete
    public boolean deleteService() throws Exception {
        ServiceDAO dao = new ServiceDAO();
        return dao.deleteService(service);
    }

    //search
    public List<ServiceDTO> search() throws Exception {
        ServiceDAO dao = new ServiceDAO();
        return dao.searchServiceByName(search);
    }

    // check slot
    public boolean checkSlot() throws Exception {
        boolean check = false;
        if (service.getSlot() >= slot) {
            check = true;
        }
        return check;
    }
    //find by key
    
    public ServiceDTO findByKey() throws Exception{
        ServiceDAO dao = new ServiceDAO();
        return dao.findByKey(serviceID);
    }
    
    // process Slot
    public void updateSlot() throws Exception{
        service.setSlot(service.getSlot() - slot);
    }
    
    //increase slot
    
    public void increaseSlot() throws Exception{
        service.setSlot(service.getSlot() + slot);
    }
    
    // close service
    
    public boolean closeService() throws Exception{
        ServiceDAO dao = new ServiceDAO();
        return dao.closeService(serviceID);
    }
    
    //open service
    
    public boolean openService() throws Exception{
        ServiceDAO dao = new ServiceDAO();
        return dao.openService(serviceID);
    }
}
