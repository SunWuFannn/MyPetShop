/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haipm.user_controller;

import haipm.dtos.AccountDTO;
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
public class UpdateUserController extends HttpServlet {

    private static final String SUCCESS = "index.jsp";
    private static final String FALIED = "User/userInfo.jsp";
    private static final String ERROR = "error.jsp";

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
        String url = ERROR;
        ErrorAccount errAcc = new ErrorAccount();
        HttpSession session = request.getSession();
        boolean valid = true;
        try {
            String username = request.getParameter("txtUsername");
            String fullname = request.getParameter("txtFullname");
            String phone = request.getParameter("txtPhone");
            String address = request.getParameter("txtAddress");
            if (fullname.length() == 0) {
                errAcc.setErrorFullname("Cant empty fullname");
                valid = false;
            }
            if (phone.length() == 0) {
                errAcc.setErrorPhone("Cant empty Phone");
                valid = false;
            }
            if (address.length() == 0) {
                errAcc.setErrorAddress("Cant empty Address");
                valid = false;
            }
            if (valid) {
                AccountDTO account = new AccountDTO(username, fullname, phone, address);
                ProcessAccount bean = new ProcessAccount();
                bean.setAccount(account);
                valid = bean.updateAccount();
                if(valid){
                    url = SUCCESS;
                    session.setAttribute("FULLNAME", bean.getAccount().getFullname());
                }
                else{
                    url = ERROR;
                    request.setAttribute("ERROR", "Falied to update User");
                }
            }
            else{
                url = FALIED;
                request.setAttribute("ERROR_ACCOUNT", errAcc);
            }

        } catch (Exception e) {
            log("Error at UpdateUserController : " + e.getMessage());
        } finally {
            if(url.equals(FALIED) || url.equals(ERROR)){
                request.getRequestDispatcher(url).forward(request, response);
            }
            else{
                response.sendRedirect(url);
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
