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
public class AccessoryDTO implements Serializable {

    private String accessID, accessName, image, status;
    private int typeID, quantity;
    private float price;

    public AccessoryDTO(String accessID, String accessName, int quantity, float price) {
        this.accessID = accessID;
        this.accessName = accessName;
        this.quantity = quantity;
        this.price = price;
    }

    public AccessoryDTO(String accessID, String accessName, String image, String status, int typeID, int quantity, float price) {
        this.accessID = accessID;
        this.accessName = accessName;
        this.image = image;
        this.status = status;
        this.typeID = typeID;
        this.quantity = quantity;
        this.price = price;
    }

    public AccessoryDTO() {
    }

    public String getAccessID() {
        return accessID;
    }

    public void setAccessID(String accessID) {
        this.accessID = accessID;
    }

    public String getAccessName() {
        return accessName;
    }

    public void setAccessName(String accessName) {
        this.accessName = accessName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTypeID() {
        return typeID;
    }

    public void setTypeID(int typeID) {
        this.typeID = typeID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

}
