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
public class AddAccessoryController extends HttpServlet {

    private static final String SUCCESS = "LoadAccessoryAdmin?idPage=1";
    private static final String ERROR = "error.jsp";
    private static final String FALIED = "Admin/adminAddAccessory.jsp";
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
        ErrorAccessory errAccess = new ErrorAccessory();
        try {
            String accessID = request.getParameter("txtAccessID");
            String accessName = request.getParameter("txtAccessName");

            String img = request.getParameter("txtAccessImage");
            if (accessID.length() == 0) {
                errAccess.setErrID("Cant empty ID");
                valid = false;
            }
            if (accessName.length() == 0) {
                errAccess.setErrName("Cant empty name");
                valid = false;
            }
            if (request.getParameter("txtAccessPrice").length() == 0) {
                errAccess.setErrPrice("Cant empty Price");
                valid = false;
            }
            if (request.getParameter("txtAccessQuantity").length() == 0) {
                errAccess.setErrQuantity("Cant empty Quantity");
                valid = false;
            }
            if (Integer.parseInt(request.getParameter("txtAccessTypeID")) == 0) {
                errAccess.setErrTypeID("Please Choose Type");
                valid = false;
            }
            if (img.length() == 0) {
                errAccess.setErrImage("Cant empty Image");
                valid = false;
            }
            if (valid) {
                float price = Float.parseFloat(request.getParameter("txtAccessPrice"));
                int quantity = Integer.parseInt(request.getParameter("txtAccessQuantity"));
                int typeID = Integer.parseInt(request.getParameter("txtAccessTypeID"));
                AccessoryDTO dto = new AccessoryDTO(accessID, accessName, img, "true", typeID, quantity, price);
                ProcessAccessory bean = new ProcessAccessory();
                bean.setAccessory(dto);
                valid = bean.addAccessory();
                if (valid) {
                    url = SUCCESS;
                    HttpSession session = request.getSession();
                    session.setAttribute("CHECK", 1);
                } else {
                    url = ERROR;
                    request.setAttribute("ERROR", "Add accessory Falied");
                }
            } else {
                url = FALIED;
                request.setAttribute("ERROR_ACCESSORY", errAccess);
            }
        } catch (Exception e) {
            log("Error at AddAccessoryController : " + e.getMessage());
            if (e.getMessage().contains("duplicate")) {
                errAccess.setErrID("Accessory ID have been existed");
                url = FALIED;
                valid = false;
            }
            if (e.getMessage().contains("For input string")) {
                errAccess.setErrPrice("Please check format");
                url = FALIED;
                valid = false;
            }
            if (e.getMessage().contains("INSERT statement conflicted")) {
                errAccess.setErrPrice("Please check format");
                url = FALIED;
                valid = false;
            }
            request.setAttribute("ERROR_ACCESSORY", errAccess);
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
