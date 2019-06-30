/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haipm.admin_controller;

import haipm.dtos.ServiceDTO;
import haipm.models.ProcessService;
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
public class AdminUpdateServiceController extends HttpServlet {

    private static final String SUCCESS = "LoadServiceAdminController";
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
        boolean valid = false;
        try {
            String serviceName = request.getParameter("txtServiceName");
            String serviceID = request.getParameter("txtServiceID");
            float price = Float.parseFloat(request.getParameter("txtPrice"));
            int slot = Integer.parseInt(request.getParameter("txtSlot"));
            ProcessService bean = new ProcessService();
            bean.setServiceID(serviceID);
            ServiceDTO service = bean.findByKey();
            String image = "";
            if (request.getParameter("txtImage").length() > 0) {
                image = request.getParameter("txtImage");
            }
            else{
                image = service.getImage();
            }
            service.setServiceName(serviceName);
            service.setPrice(price);
            service.setSlot(slot);
            service.setImage(image);
            bean.setService(service);
            valid = bean.updateService();
            if(valid){
                url = SUCCESS;
                HttpSession session = request.getSession();
                session.setAttribute("CHECK", 1);
            }
            else{
                request.setAttribute("ERROR", "Update Service Falied");
            }
        } catch (Exception e) {
            log("Error at AdminUpdateServiceController :  " + e.getMessage());
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
