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
public class ErrorPet implements Serializable {

    private String errorID, errorName, errorType, errorAge;

    public ErrorPet() {
    }

    public String getErrorID() {
        return errorID;
    }

    public void setErrorID(String errorID) {
        this.errorID = errorID;
    }

    public String getErrorName() {
        return errorName;
    }

    public void setErrorName(String errorName) {
        this.errorName = errorName;
    }

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    public String getErrorAge() {
        return errorAge;
    }

    public void setErrorAge(String errorAge) {
        this.errorAge = errorAge;
    }

}
