/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haipm.admin_controller;

import haipm.dtos.ServiceProcessDTO;
import haipm.models.ProcessServiceInProcess;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 99hai
 */
public class AdminManageHistoryService extends HttpServlet {

    private static final String SUCCESS = "Admin/adminViewHistoryService.jsp";
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
        try {
            int pageID = Integer.parseInt(request.getParameter("idPage"));
            List<ServiceProcessDTO> listSplit = null;
            ProcessServiceInProcess bean = new ProcessServiceInProcess();
            ArrayList<ServiceProcessDTO> mylist = (ArrayList<ServiceProcessDTO>) bean.getFinishedProcess();
            if (mylist != null) {
                url = SUCCESS;
                int numberOfAll = mylist.size();
                int numberOfPage = numberOfAll % 5 == 0 ? (numberOfAll / 5) : (numberOfAll / 5) + 1;
                request.setAttribute("NUMBER_PAGE", numberOfPage);
                if (5 * pageID > mylist.size()) {
                    listSplit = (List<ServiceProcessDTO>) mylist.subList(5 * pageID - 5, mylist.size());
                } else {
                    listSplit = (List<ServiceProcessDTO>) mylist.subList(5 * pageID - 5, 5 * pageID);
                }
                request.setAttribute("LIST_HISTORY", listSplit);
            } else {
                request.setAttribute("ERROR", "Falied to load finished process admin !");
            }
        } catch (Exception e) {
            log("Error at AdminViewHistoryService :  " + e.getMessage());
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
