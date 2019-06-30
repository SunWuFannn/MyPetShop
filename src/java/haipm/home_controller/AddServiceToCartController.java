/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haipm.home_controller;

import haipm.dtos.ServiceCart;
import haipm.dtos.ServiceDTO;
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
public class AddServiceToCartController extends HttpServlet {
    private static final String SUCCESS = "LoadServiceUser";
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
        ServiceCart cart = null;
        ServiceDTO service = null;
        boolean valid = false;
        String url = ERROR;
        try {
            String serviceID = request.getParameter("serviceID");
            String serviceName = request.getParameter("serviceName");
            float price = Float.parseFloat(request.getParameter("servicePrice"));
            HttpSession session = request.getSession();
            if (session.getAttribute("CART_SERVICE") == null) {
                cart = new ServiceCart();
                cart.setUsername((String)session.getAttribute("USERNAME"));
                service = new ServiceDTO(serviceID, serviceName, 1, price);
                valid = cart.addServiceToCart(service);
            } else {
                cart = (ServiceCart) session.getAttribute("CART_SERVICE");
                service = new ServiceDTO(serviceID, serviceName, 1, price);
                valid = cart.addServiceToCart(service);
            }
            if(valid){
                session.setAttribute("CART_SERVICE", cart);
                url = SUCCESS;
                session.setAttribute("CHECK", 1);
            }
            else{
                request.setAttribute("ERROR", "add service to cart falied");
            }
        } catch (Exception e) {
            log("Error At AddServiceToCartController:  " + e.getMessage());
        } finally {
            if(valid){
                response.sendRedirect(url);
            }
            else{
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
