/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haipm.dtos;

import java.io.Serializable;
import java.util.HashMap;

/**
 *
 * @author 99hai
 */
public class ShoppingCart implements Serializable {

    private String username;
    private HashMap<String, AccessoryDTO> cart;

    public ShoppingCart() {
        this.cart = new HashMap<String,AccessoryDTO>();
    }

    public ShoppingCart(String username, HashMap<String, AccessoryDTO> cart) {
        this.username = username;
        this.cart = cart;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public HashMap<String, AccessoryDTO> getCart() {
        return cart;
    }

    public void setCart(HashMap<String, AccessoryDTO> cart) {
        this.cart = cart;
    }

    public void addCart(AccessoryDTO accessory) throws Exception {
        if (this.cart.containsKey(accessory.getAccessID())) {
            int quantity = cart.get(accessory.getAccessID()).getQuantity();
            cart.get(accessory.getAccessID()).setQuantity(quantity + 1);
        } else {
            this.cart.put(accessory.getAccessID(), accessory);
        }
    }

    public void deleteCart(String accessoryID) throws Exception {
        if (cart.containsKey(accessoryID)) {
            cart.remove(accessoryID);
        }
    }
    
    public float getTotal() throws Exception{
        float total = 0;
        for (AccessoryDTO value : cart.values()) {
            total = total + value.getPrice()*value.getQuantity();
        }
        return total;
    }
}
