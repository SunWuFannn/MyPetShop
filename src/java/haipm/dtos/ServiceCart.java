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
public class ServiceCart implements Serializable {

    private HashMap<String, ServiceDTO> serviceCart;
    private String username;

    public ServiceCart() {
        serviceCart = new HashMap<String, ServiceDTO>();
    }

    public HashMap<String, ServiceDTO> getServiceCart() {
        return serviceCart;
    }

    public void setServiceCart(HashMap<String, ServiceDTO> serviceCart) {
        this.serviceCart = serviceCart;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public boolean addServiceToCart(ServiceDTO service) throws Exception{
        boolean check = false;
        if(serviceCart.containsKey(service.getServiceID())){
            int slot = serviceCart.get(service.getServiceID()).getSlot() +1 ;
            serviceCart.get(service.getServiceID()).setSlot(slot);
            check = true;
        }
        else{
            serviceCart.put(service.getServiceID(), service);
            check = true;
        }
        return check;
    }
    
    public boolean deleteServiceItemFromCart(String serviceID) throws Exception{
        boolean check = false;
        if(this.serviceCart.containsKey(serviceID)){
            this.serviceCart.remove(serviceID);
            check = true;
        }
        return check;
    }
    
    public float getTotalServicePriceInCart() throws Exception{
        float total = 0;
        for (ServiceDTO service : this.serviceCart.values()) {
            total = total + service.getSlot()*service.getPrice();
        }
        return total;
    }
}
