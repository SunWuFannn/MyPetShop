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
public class ServiceDTO implements Serializable {

    private String serviceID, serviceName, date, image;
    private int slot;
    private float price;
    private boolean isActive;

    public ServiceDTO() {
    }

    public ServiceDTO(String serviceID, String serviceName, String date, String image, int slot, float price, boolean isActive) {
        this.serviceID = serviceID;
        this.serviceName = serviceName;
        this.date = date;
        this.image = image;
        this.slot = slot;
        this.price = price;
        this.isActive = isActive;
    }

    public ServiceDTO(String serviceID, String serviceName, int slot, float price) {
        this.serviceID = serviceID;
        this.serviceName = serviceName;
        this.slot = slot;
        this.price = price;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public String getServiceID() {
        return serviceID;
    }

    public void setServiceID(String serviceID) {
        this.serviceID = serviceID;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

}
