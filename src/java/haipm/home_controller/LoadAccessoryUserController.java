/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haipm.home_controller;

import haipm.dtos.AccessoryDTO;
import haipm.models.ProcessAccessory;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 99hai
 */
public class LoadAccessoryUserController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = "Home/accessory.jsp";
        HttpSession session = request.getSession();
        try {
            ProcessAccessory bean = new ProcessAccessory();
            if (session.getAttribute("LOGIN_TO_BUY") != null) {
                session.removeAttribute("LOGIN_TO_BUY");
            }
            ArrayList<AccessoryDTO> mylist = (ArrayList<AccessoryDTO>) bean.getAllActiveAccessory();
            if (mylist.size() > 0) {
                request.setAttribute("LIST_ACCESSORY", mylist);
                if (session.getAttribute("CHECK") != null) {
                    if (session.getAttribute("CHECK").toString().equals("1")) {
                        request.setAttribute("CHECK", 1);
                        session.removeAttribute("CHECK");
                    }
                    else{
                        request.setAttribute("CHECK", 2);
                        session.removeAttribute("CHECK");
                    }
                }
                if(request.getAttribute("WALLET") != null){
                    request.setAttribute("WALLET", 3);
                }
            }
        } catch (Exception e) {
            log("Error at LoadAccessoryUserController : " + e.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
