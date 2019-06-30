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
public class ServiceProcessDTO implements Serializable {

    private String username, serviceID, dateBook;
    private int slot;
    private float total;
    private boolean finished;
    private int idprocess;

    public int getIdprocess() {
        return idprocess;
    }

    public void setIdprocess(int idprocess) {
        this.idprocess = idprocess;
    }

    public ServiceProcessDTO(String username, String serviceID, String dateBook, int slot, float total, boolean finished) {
        this.username = username;
        this.serviceID = serviceID;
        this.dateBook = dateBook;
        this.slot = slot;
        this.total = total;
        this.finished = finished;
    }

    public ServiceProcessDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getServiceID() {
        return serviceID;
    }

    public void setServiceID(String serviceID) {
        this.serviceID = serviceID;
    }

    public String getDateBook() {
        return dateBook;
    }

    public void setDateBook(String dateBook) {
        this.dateBook = dateBook;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

}
