/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haipm.admin_controller;

import haipm.dtos.AccessoryDTO;
import haipm.error_obj.ErrorAccessory;
import haipm.models.ProcessAccessory;
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
public class UpdateAccessoryController extends HttpServlet {

    private static final String SUCCESS = "LoadAccessoryAdmin?idPage=1";
    private static final String ERROR = "error.jsp";
    private static final String FALIED = "Admin/editAccessory.jsp";

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
        ErrorAccessory errAccess = new ErrorAccessory();
        boolean valid = true;
        try {
            String accessID = request.getParameter("txtAccessID");
            ProcessAccessory bean = new ProcessAccessory();
            bean.setAccessoryID(accessID);
            AccessoryDTO dto = bean.findByPK();
            String accessName = request.getParameter("txtAccessName");
            String image = request.getParameter("txtImage");
            if (accessName.length() == 0) {
                errAccess.setErrName("Cant empty Name");
                valid = false;
            }
            if (image.length() == 0) {
                image = dto.getImage();
            }
            System.out.println(image);
            if (request.getParameter("txtPrice").length() == 0) {
                errAccess.setErrPrice("Cant empty Price");
                valid = false;
            }
            if (request.getParameter("txtQuantity").length() == 0) {
                errAccess.setErrQuantity("Cant empty Quantity");
                valid = false;
            }
            if (Integer.parseInt(request.getParameter("txtAccessTypeID")) == 0) {
                errAccess.setErrTypeID("Please choose type");
                valid = false;
            }
            System.out.println("hi");
            if (valid) {
                float price = Float.parseFloat(request.getParameter("txtPrice"));
                int typeID = Integer.parseInt(request.getParameter("txtAccessTypeID"));
                int quantity = Integer.parseInt(request.getParameter("txtQuantity"));
                dto.setAccessName(accessName);
                dto.setImage(image);
                dto.setPrice(price);
                dto.setQuantity(quantity);
                dto.setTypeID(typeID);
                bean.setAccessory(dto);
                valid = bean.updateAccessory();
                if (valid) {
                    url = SUCCESS;
                    HttpSession session = request.getSession();
                    session.setAttribute("CHECK", 1);
                } else {
                    url = ERROR;
                    request.setAttribute("ERROR", "Falied Update");
                }
            } else {
                url = FALIED;
                request.setAttribute("ERROR_ACCESSORY", errAccess);
            }
        } catch (Exception e) {
            log("Error at UpdateAccessoryController :" + e.getMessage());
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
