/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haipm.home_controller;

import haipm.dtos.ServiceCart;
import haipm.dtos.ServiceDTO;
import haipm.dtos.ServiceProcessDTO;
import haipm.models.ProcessServiceInProcess;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 99hai
 */
public class SaveServiceProcessAndServiceLine extends HttpServlet {

    private static final String SUCCESS = "success.jsp";
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
        String url = SUCCESS;
        HttpSession session = request.getSession();
        boolean valid = false;
        ServiceProcessDTO serviceProcess = null;
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            LocalDate localDate = LocalDate.now();
            ServiceCart cart = (ServiceCart) session.getAttribute("CART_SERVICE");
            ProcessServiceInProcess bean = new ProcessServiceInProcess();
            for (ServiceDTO service : cart.getServiceCart().values()) {
                serviceProcess = new ServiceProcessDTO(cart.getUsername(), service.getServiceID(),
                         dtf.format(localDate), service.getSlot(), service.getPrice() * service.getSlot(), false);
                bean.setServiceProcess(serviceProcess);
                valid = bean.addServiceProcess();
                if (!valid) {
                    url = ERROR;
                    break;
                }
            }
            if(valid){
                session.removeAttribute("CART_SERVICE");
            }
        } catch (Exception e) {
            log("ERROR at SaveServiceProcessAndServiceLine : " + e.getMessage());
            url  = ERROR;
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
