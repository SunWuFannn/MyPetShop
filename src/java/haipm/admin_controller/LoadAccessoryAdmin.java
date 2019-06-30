/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haipm.admin_controller;

import haipm.dtos.AccessoryDTO;
import haipm.models.ProcessAccessory;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 99hai
 */
public class LoadAccessoryAdmin extends HttpServlet {

    private static final String SUCCESS = "/Admin/admin.jsp";
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
        List<AccessoryDTO> listSlpit = null;
        int pageID = Integer.parseInt(request.getParameter("idPage"));
        try {
            ProcessAccessory bean = new ProcessAccessory();
            ArrayList<AccessoryDTO> mylist = (ArrayList<AccessoryDTO>) bean.getAllAccessory();
            int numberOfList = mylist.size();
            int numberOfPage = numberOfList % 5 == 0 ? numberOfList / 5 : (numberOfList / 5) + 1;
            if (5 * pageID > mylist.size()) {
                listSlpit = (List<AccessoryDTO>) mylist.subList(5 * pageID - 5, mylist.size());
            } else {
                listSlpit = (List<AccessoryDTO>) mylist.subList(5 * pageID - 5, 5 * pageID);
            }
            request.setAttribute("NUMBER_PAGE", numberOfPage);
            request.setAttribute("LIST_ACCESSORY", listSlpit);
            HttpSession session = request.getSession();
            if (session.getAttribute("CHECK") != null) {
                String text = session.getAttribute("CHECK").toString();
                request.setAttribute("CHECK", text);
                session.removeAttribute("CHECK");
            }
        } catch (Exception e) {
            log("Error at load accessory admin controller: " + e.getMessage());
            url = ERROR;
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
