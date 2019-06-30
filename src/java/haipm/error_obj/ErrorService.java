/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haipm.error_obj;

import java.io.Serializable;

/**
 *
 * @author 99hai
 */
public class ErrorService implements Serializable {

    private String errID, errName, errSlot, errPrice, errDate;

    public ErrorService() {
    }

    public String getErrID() {
        return errID;
    }

    public void setErrID(String errID) {
        this.errID = errID;
    }

    public String getErrName() {
        return errName;
    }

    public void setErrName(String errName) {
        this.errName = errName;
    }

    public String getErrSlot() {
        return errSlot;
    }

    public void setErrSlot(String errSlot) {
        this.errSlot = errSlot;
    }

    public String getErrPrice() {
        return errPrice;
    }

    public void setErrPrice(String errPrice) {
        this.errPrice = errPrice;
    }

    public String getErrDate() {
        return errDate;
    }

    public void setErrDate(String errDate) {
        this.errDate = errDate;
    }

}
