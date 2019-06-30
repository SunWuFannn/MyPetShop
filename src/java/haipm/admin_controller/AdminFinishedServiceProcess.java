/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haipm.admin_controller;

import haipm.dtos.ServiceDTO;
import haipm.models.ProcessService;
import haipm.models.ProcessServiceInProcess;
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
public class AdminFinishedServiceProcess extends HttpServlet {

    private static final String SUCCESS = "AdminManageServiceProcessController";
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
            String serviceID = request.getParameter("serviceID");
            int slot = Integer.parseInt(request.getParameter("slot"));
            int idProcess = Integer.parseInt(request.getParameter("idProcess"));
            ProcessService bean = new ProcessService();
            bean.setServiceID(serviceID);
            ServiceDTO service = bean.findByKey();
            bean.setService(service);
            bean.setSlot(slot);
            bean.increaseSlot();
            valid = bean.updateService();
            if (valid) {
                ProcessServiceInProcess beanProcess = new ProcessServiceInProcess();
                beanProcess.setIdProcess(idProcess);
                valid = beanProcess.finishedProcess();
                if (valid) {
                    url = SUCCESS;
                    HttpSession session = request.getSession();
                    session.setAttribute("CHECK", 1);
                } else {
                    request.setAttribute("ERROR", "error when admin finish service");
                }
            } else {
                request.setAttribute("ERROR", "error when admin finish service");
            }
        } catch (Exception e) {
            log("Error at AdminFinishedServiceProcess : " + e.getMessage());
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
