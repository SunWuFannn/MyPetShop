/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haipm.user_controller;

import haipm.dtos.OrderDTO;
import haipm.dtos.ServiceProcessDTO;
import haipm.models.ProcessOrder;
import haipm.models.ProcessServiceInProcess;
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
public class LoadAllOrderUserController extends HttpServlet {

    private static final String SUCCESS = "User/allOrder.jsp";
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
            int newCount;
            HttpSession session = request.getSession();
            int idPage = Integer.parseInt(request.getParameter("idPage"));
            ProcessOrder beanOrder = new ProcessOrder();
            String username = (String) session.getAttribute("USERNAME");
            beanOrder.setUsername(username);
            ArrayList<OrderDTO> mylist = (ArrayList<OrderDTO>) beanOrder.getOrderOfUser();
            if (mylist != null) {
                for (OrderDTO order : mylist) {
                    beanOrder.setOrderID(order.getOrderID());
                    order.setAllLine(beanOrder.getAllLineOfOrder());
                }
                url = SUCCESS;
                request.setAttribute("LIST_ORDER", mylist);
                ProcessServiceInProcess service = new ProcessServiceInProcess();
                service.setUsername(username);
                ArrayList<ServiceProcessDTO> listService = (ArrayList<ServiceProcessDTO>) service.getServiceOfUSer();
                List<ServiceProcessDTO> listSplit = null;
                if (listService != null) {
                    url = SUCCESS;
                    int numberItem = listService.size();
                    int numberPage = numberItem % 5 == 0 ? numberItem / 5 : (numberItem / 5) + 1;
                    request.setAttribute("NUMBER_PAGE", numberPage);
                    if (5 * idPage > listService.size()) {
                        newCount = listService.size();
                        listSplit = (List<ServiceProcessDTO>) listService.subList(5 * idPage - 5, newCount);

                    } else {
                        listSplit = (List<ServiceProcessDTO>) listService.subList(5 * idPage - 5, 5 * idPage);

                    }
                    request.setAttribute("LIST_SERVICE", listSplit);
                } else {
                    url = ERROR;
                    request.setAttribute("ERROR", "Falied");
                }
            } else {
                request.setAttribute("ERROR", "FALIED");
            }
        } catch (Exception e) {
            log("Error at LoadAllOrderUserController  : " + e.getMessage());
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
