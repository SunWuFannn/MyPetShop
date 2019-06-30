/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haipm.admin_controller;

import haipm.dtos.ServiceDTO;
import haipm.error_obj.ErrorService;
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
public class AdminAddServiceController extends HttpServlet {
    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "LoadServiceAdminController";
    private static final String FALIED = "Admin/adminAddService.jsp";
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
        boolean valid = true;
        ErrorService errService = new ErrorService();
        try {
            String serviceID = request.getParameter("txtServiceID");
            String serviceName = request.getParameter("txtServiceName");
            String image = request.getParameter("txtImage");
            String date = request.getParameter("txtDate");
            if (serviceID.length() == 0) {
                errService.setErrID("Cant empty ID");
                valid = false;
            }
            if (serviceName.length() == 0) {
                errService.setErrName("Cant empty Name");
                valid = false;
            }
            if (date.length() == 0) {
                errService.setErrDate("this field is required");
                valid = false;
            }
            if (request.getParameter("txtSlot").equals("")) {
                errService.setErrSlot("Cant empty slot");
                valid = false;
            }
            if (request.getParameter("txtPrice").equals("")) {
                errService.setErrPrice("Cant empty Price");
                valid = false;
            }
            if (valid) {
                int slot = Integer.parseInt(request.getParameter("txtSlot"));
                float price = Float.parseFloat(request.getParameter("txtPrice"));
                ServiceDTO service = new ServiceDTO(serviceID, serviceName, date, image, slot, price, true);
                ProcessService bean = new ProcessService();
                bean.setService(service);
                valid = bean.addService();
                if(valid){
                    url = SUCCESS;
                    HttpSession session = request.getSession();
                    session.setAttribute("CHECK", 1);
                }
                else{
                    request.setAttribute("ERROR", "Add Service Falied");
                }
            }
            else{
                url = FALIED;
                request.setAttribute("ERROR_SERVICE", errService);
            }
        } catch (Exception e) {
            log("Error At AdminAddServiceController : " + e.getMessage());
            if(e.getMessage().contains("duplicate")){
                valid = false;
                errService.setErrID("ID have existed");
                request.setAttribute("ERROR_SERVICE", errService);
                url = FALIED;
            }
        }
        finally{
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
