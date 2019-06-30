/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haipm.home_controller;

import haipm.dtos.AccessoryDTO;
import haipm.dtos.ShoppingCart;
import haipm.error_obj.ErrorCart;
import haipm.models.ProcessAccessory;
import haipm.models.ProcessAccount;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
public class CheckCartController extends HttpServlet {

    private static final String SUCCESS = "SaveBillAndFinishCart";
    private static final String LOGIN = "Auth/login.jsp";
    private static final String FALIED = "LoadAccessoryUserController";
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
        ErrorCart cartError = new ErrorCart();
        String url = ERROR;
        boolean valid = true;
        try {
            HttpSession session = request.getSession();
            ShoppingCart cart = (ShoppingCart) session.getAttribute("CART");
            String quantity[] = request.getParameterValues("txtAccessQuantity");
            ArrayList<String> listKey = new ArrayList<String>();
            int i = 0;
            for (AccessoryDTO item : cart.getCart().values()) {
                if (Integer.parseInt(quantity[i]) == 0) {
                    listKey.add(item.getAccessID());
                } else {
                    item.setQuantity(Integer.parseInt(quantity[i]));
                }
                i++;
            }
            for (String key : listKey) {
                cart.getCart().remove(key);
//                System.out.println(key);
            }
            if (cart.getCart().size() == 0) {
                url = FALIED;
            } else {
                if (cart.getUsername() == null) {
                    session.setAttribute("LOGIN_TO_BUY", FALIED);
                    url = LOGIN;
                    valid = false;
                }
                if (valid) {
                    ProcessAccessory bean = new ProcessAccessory();
                    for (AccessoryDTO item : cart.getCart().values()) {
                        bean.setQuantity(item.getQuantity());
                        bean.setAccessoryID(item.getAccessID());
                        bean.getQuantityByID();
                        valid = bean.checkValidQuantity();
                        if (!valid) {
                            url = FALIED;
                            cartError.setErrorMaxQuantity("Quantity Accessory Name : " + item.getAccessName() + " can't enough");
                            request.setAttribute("ERROR_CART", cartError);
                            break;
                        }
                    }
                    ProcessAccount acc = new ProcessAccount();
                    acc.setUsername(session.getAttribute("USERNAME").toString());
                    Float wallet = acc.getWallet();
                    if (wallet > cart.getTotal()) {
                        wallet = wallet - cart.getTotal();
                        acc.setCost(wallet);
                        valid = acc.cashWallet();
                    } else {
                        valid = false;
                    }
                    if (valid) {
                        for (Map.Entry<String, AccessoryDTO> entry : cart.getCart().entrySet()) {
                            bean.setAccessoryID(entry.getValue().getAccessID());
                            bean.setQuantity(entry.getValue().getQuantity());
                            int afterBuy = bean.countQuantityAfterBuy();
                            bean.setQuantity(afterBuy);
                            if (!bean.updateQuantity()) {
                                url = ERROR;
                                return;
                            }
                        }
                        if (valid) {
                            url = SUCCESS;
                        } else {
                            url = FALIED;
                        }
                    } else {
                        url = FALIED;
                        request.setAttribute("WALLET", 3);
                    }
                }
            }

        } catch (Exception e) {
            log("Error at checkCartController : " + e.getMessage());
        } finally {
            if (url.equals(LOGIN)) {
                request.getRequestDispatcher(url).forward(request, response);
            } else if (url.equals(FALIED)) {
                request.getRequestDispatcher(url).forward(request, response);
            } else {
                response.sendRedirect(url);
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
