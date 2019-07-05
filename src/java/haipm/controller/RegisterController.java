/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haipm.controller;

import haipm.dtos.AccountDTO;
import haipm.error_obj.ErrorAccount;
import haipm.models.ProcessAccount;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 99hai
 */
public class RegisterController extends HttpServlet {

    private static final String SUCCESS = "Auth/login.jsp";
    private static final String FALIED = "Auth/register.jsp";
    private static final String FALIED2 = "Admin/registerAdmin.jsp";

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
        boolean valid = true;
        ErrorAccount errAccount = new ErrorAccount();
        try {
            String username = request.getParameter("txtUsername");
            String password = request.getParameter("txtPassword");
            String passwordConfirm = request.getParameter("txtPasswordConfirm");
            String fullname = request.getParameter("txtFullname");
            System.out.println("hi");
            if (username.length() == 0) {
                errAccount.setErrorUsername("Cant empty Username");
                valid = false;
            }
            if (password.length() == 0) {
                errAccount.setErrorPassword("Cant empty password");
                valid = false;
            }
            if (passwordConfirm.length() == 0) {
                errAccount.setErrorPasswordConfirm("Cant empty password Confirm");
                valid = false;
            }
            if (fullname.length() == 0) {
                errAccount.setErrorFullname("Cant empty fullname.");
                valid = false;
            }
            if (valid) {
                if (!password.equals(passwordConfirm)) {
                    valid = false;
                    errAccount.setErrorPassword("Not Match Password.");
                    request.setAttribute("ERROR_ACCOUNT", errAccount);
                    if(request.getParameter("state").toString().equals("admin")){
                        url = FALIED2;
                    }
                }
                if (valid) {
                    ProcessAccount bean = new ProcessAccount();
                    AccountDTO dto = new AccountDTO();
                    dto.setUsername(username);
                    dto.setPassword(password);
                    if (request.getParameter("state").toString().equals("admin")) {
                        dto.setRole("admin");
                    } else {
                        dto.setRole("user");
                    }
                    dto.setWallet(0);
                    dto.setFullname(fullname);
                    bean.setAccount(dto);
                    if (bean.register()) {
                        url = SUCCESS;
                    }
                }
            } else {
                if (request.getParameter("state").toString().equals("admin")) {
                    url = FALIED2;
                }
                request.setAttribute("ERROR_ACCOUNT", errAccount);
            }
        } catch (Exception e) {
            log("Error at Register Controller : " + e.getMessage());
            if (e.getMessage().contains("duplicate")) {
                errAccount.setErrorUsername("Username have been existed.");
                request.setAttribute("ERROR_ACCOUNT", errAccount);
                valid = false;
                if (request.getParameter("state").toString().equals("admin")) {
                    url = FALIED2;
                }
            }
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
