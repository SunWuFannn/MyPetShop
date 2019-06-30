/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haipm.controller;

import haipm.dtos.AccountDTO;
import haipm.dtos.ServiceCart;
import haipm.dtos.ShoppingCart;
import haipm.error_obj.ErrorAccount;
import haipm.models.ProcessAccount;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 99hai
 */
public class LoginController extends HttpServlet {

    private static final String USER = "index.jsp";
    private static final String ADMIN = "LoadAccessoryAdmin?idPage=1";
    private static final String FALIED = "Auth/login.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = FALIED;
        ErrorAccount errObj = new ErrorAccount();
        boolean valid = true;
        try {
            HttpSession session = request.getSession();
            String username = request.getParameter("txtUsername");
            String password = request.getParameter("txtPassword");
            if (username.length() == 0) {
                errObj.setErrorUsername("Empty User name");
                valid = false;
            }
            if (password.length() == 0) {
                errObj.setErrorPassword("Empty Password");
                valid = false;
            }
            if (valid) {
                ProcessAccount bean = new ProcessAccount();
                bean.setUsername(username);
                bean.setPassword(password);
                AccountDTO account = bean.checkLogin();
                if (account == null) {
                    errObj.setErrorUsername("Wrong Pass Or Username");
                    errObj.setErrorPassword("Wrong Pass or Username");
                    request.setAttribute("ERROR_ACCOUNT", errObj);
                    valid = false;
                } else if (account.getRole().equals("user")) {
                    url = USER;
                    session.setAttribute("FULLNAME", account.getFullname());
                    session.setAttribute("USERNAME", account.getUsername());
                    session.setAttribute("ROLE", account.getRole());
                    if(session.getAttribute("LOGIN_TO_BUY") != null){
                        url = (String)session.getAttribute("LOGIN_TO_BUY");
                        ShoppingCart cart = (ShoppingCart)session.getAttribute("CART");
                        cart.setUsername(account.getUsername());
                        session.setAttribute("CART", cart);
                    }
                    if(session.getAttribute("LOGIN_TO_SERVICE") != null){
                        url = (String)session.getAttribute("LOGIN_TO_SERVICE");
                        ServiceCart cart = (ServiceCart)session.getAttribute("CART_SERVICE");
                        cart.setUsername(account.getUsername());
                        session.setAttribute("CART_SERVICE", cart);
                    }
                } else if (account.getRole().equals("admin")) {
                    url = ADMIN;
                    session.setAttribute("FULLNAME", account.getFullname());
                    session.setAttribute("ROLE", account.getRole());
                    session.setAttribute("USERNAME", account.getUsername());
                }
            } else {
                request.setAttribute("ERROR_ACCOUNT", errObj);
            }
        } catch (Exception e) {
            log("Error at LoginController : " + e.getMessage());
        } finally {
            if (valid) {
                response.sendRedirect(url);
            } else {
                request.getRequestDispatcher(url).forward(request, response);
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
