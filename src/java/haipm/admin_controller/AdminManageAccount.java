/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haipm.admin_controller;

import haipm.dtos.AccountDTO;
import haipm.models.ProcessAccount;
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
public class AdminManageAccount extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "Admin/adminManageAccount.jsp";

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
        int i = 0;
        try {
            int pageID = Integer.parseInt(request.getParameter("idPage"));
            i++;
            List<AccountDTO> listSplit = null;
            i++;
            ProcessAccount bean = new ProcessAccount();
            i++;
            ArrayList<AccountDTO> mylist = (ArrayList<AccountDTO>)bean.getAllUser();
            System.out.println(mylist.size());
            System.out.println(mylist == null);
            if (mylist.size() >= 0) {
                System.out.println("hi");
                url = SUCCESS;
                i++;
                int numberOfAll = mylist.size();
                int numberOfPage = numberOfAll % 5 == 0 ? (numberOfAll / 5) : (numberOfAll / 5) + 1;
                request.setAttribute("NUMBER_PAGE", numberOfPage);
                if (5 * pageID > mylist.size()) {
                    listSplit = (List<AccountDTO>) mylist.subList(5 * pageID - 5, mylist.size());
                } else {
                    listSplit = (List<AccountDTO>) mylist.subList(5 * pageID - 5, 5 * pageID);
                }
                request.setAttribute("LIST_USER", listSplit);
                HttpSession session = request.getSession();
                if (session.getAttribute("CHECK") != null) {
                    request.setAttribute("CHECK", 1);
                    session.removeAttribute("CHECK");
                }
            } else {
                request.setAttribute("ERROR", "Fail to load list user");
            }
        } catch (Exception e) {
            log("Error at AdminManageAccount Controller: " + e.getMessage() + "   " + i);
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
