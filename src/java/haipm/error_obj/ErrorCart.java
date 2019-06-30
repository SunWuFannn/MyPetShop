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
public class ErrorCart {

    private String errorLogin, errorMaxQuantity;

    public ErrorCart() {
    }

    public String getErrorLogin() {
        return errorLogin;
    }

    public void setErrorLogin(String errorLogin) {
        this.errorLogin = errorLogin;
    }

    public String getErrorMaxQuantity() {
        return errorMaxQuantity;
    }

    public void setErrorMaxQuantity(String errorMaxQuantity) {
        this.errorMaxQuantity = errorMaxQuantity;
    }

}
