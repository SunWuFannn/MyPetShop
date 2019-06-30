/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haipm.error_obj;

/**
 *
 * @author 99hai
 */
public class ErrorAccount {

    private String errorUsername, errorPassword, errorPasswordConfirm, errorFullname;
    private String errorPhone, errorAddress;

    public ErrorAccount() {
    }

    public String getErrorPhone() {
        return errorPhone;
    }

    public void setErrorPhone(String errorPhone) {
        this.errorPhone = errorPhone;
    }

    public String getErrorAddress() {
        return errorAddress;
    }

    public void setErrorAddress(String errorAddress) {
        this.errorAddress = errorAddress;
    }

    public String getErrorPasswordConfirm() {
        return errorPasswordConfirm;
    }

    public void setErrorPasswordConfirm(String errorPasswordConfirm) {
        this.errorPasswordConfirm = errorPasswordConfirm;
    }

    public String getErrorFullname() {
        return errorFullname;
    }

    public void setErrorFullname(String errorFullname) {
        this.errorFullname = errorFullname;
    }

    public String getErrorUsername() {
        return errorUsername;
    }

    public void setErrorUsername(String errorUsername) {
        this.errorUsername = errorUsername;
    }

    public String getErrorPassword() {
        return errorPassword;
    }

    public void setErrorPassword(String errorPassword) {
        this.errorPassword = errorPassword;
    }

}
