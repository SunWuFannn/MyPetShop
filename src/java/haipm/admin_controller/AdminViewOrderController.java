/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haipm.admin_controller;

import haipm.dtos.OrderDTO;
import haipm.models.ProcessOrder;
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
public class AdminViewOrderController extends HttpServlet {

    private static final String SUCCESS = "Admin/adminViewOrder.jsp";
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
        int newCount;
        List<OrderDTO> listSplit = null;
        try {
            int idPage = Integer.parseInt(request.getParameter("idPage"));
            ProcessOrder bean = new ProcessOrder();
            ArrayList<OrderDTO> listOrder = (ArrayList<OrderDTO>) bean.getAllOrder();
            for (OrderDTO orderDTO : listOrder) {
                bean.setOrderID(orderDTO.getOrderID());
                orderDTO.setAllLine(bean.getAllLineOfOrder());
            }
            System.out.println("Size of All : " + listOrder.size());
            int numberItem = listOrder.size();
            int numberPage = numberItem % 5 == 0 ? numberItem / 5 : (numberItem / 5) + 1;
            request.setAttribute("NUMBER_PAGE", numberPage);
            if (5 * idPage > listOrder.size()) {
                newCount = listOrder.size();
                listSplit = (List<OrderDTO>) listOrder.subList(5 * idPage - 5, newCount);

            } else {
                listSplit = (List<OrderDTO>) listOrder.subList(5 * idPage - 5, 5 * idPage);

            }
            request.setAttribute("LIST_ORDER", listSplit);
            url = SUCCESS;
        } catch (Exception e) {
            log("Error at adminViewController  :  " + e.getMessage());
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
