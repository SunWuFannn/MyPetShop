/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haipm.home_controller;

import haipm.dtos.AccessoryDTO;
import haipm.dtos.OrderDTO;
import haipm.dtos.OrderLineAccessory;
import haipm.dtos.ShoppingCart;
import haipm.models.ProcessOrder;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 99hai
 */
public class SaveBillAndFinishCart extends HttpServlet {
    private static String SUCCESS = "success.jsp";
    private static String ERROR = "error.jsp";
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
        HttpSession session = request.getSession();
        Date date = new Date(System.currentTimeMillis());
        boolean valid = false;
        float total = 0;
        String url = ERROR;
        try {
            ProcessOrder bean = new ProcessOrder();
            int id = bean.countIdOrder();

            id = id + 1;
            
            ShoppingCart cart = (ShoppingCart) session.getAttribute("CART");

            OrderDTO dto = new OrderDTO(cart.getUsername(), date.toString(), 0);

            bean.setOrder(dto);

            valid = bean.createOrder();
            OrderLineAccessory line = null;
            if (valid) {
                for (Map.Entry<String, AccessoryDTO> entry : cart.getCart().entrySet()) {
                    AccessoryDTO item = entry.getValue();
                    line = new OrderLineAccessory(id, item.getAccessID(), item.getQuantity(), item.getPrice()*item.getQuantity());
                    total = total + item.getPrice()*item.getQuantity();
                    bean.setLine(line);
                    bean.setOrderID(id);
                    valid = bean.addToOrder();
                    if(!valid){
                        break;
                    }
                }
            }
            if(valid){
                bean.getOrder().setTotal(total);
                valid = bean.updateTotal();
            }
            if(valid){
                url = SUCCESS;
                session.removeAttribute("CART");
            }
        } catch (Exception e) {
            log("Error at SaveBillController : " + e.getMessage());
        } finally {
            response.sendRedirect(url);
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
