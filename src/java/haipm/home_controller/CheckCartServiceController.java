/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haipm.home_controller;

import haipm.dtos.ServiceCart;
import haipm.dtos.ServiceDTO;
import haipm.error_obj.ErrorService;
import haipm.models.ProcessAccount;
import haipm.models.ProcessService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 99hai
 */
public class CheckCartServiceController extends HttpServlet {

    private static final String SUCCESS = "SaveServiceProcessAndServiceLine";
    private static final String ERROR = "error.jsp";
    private static final String FALIED = "LoadServiceUser";

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
        boolean isLogin = true;
        HttpSession session = request.getSession();
        ServiceDTO dto = null;
        ErrorService errSer = new ErrorService();
        try {
            ServiceCart cart = (ServiceCart) session.getAttribute("CART_SERVICE");
            String quantity[] = request.getParameterValues("txtAccessQuantity");
            ArrayList<String> listKey = new ArrayList<String>();
            int i = 0;
            for (ServiceDTO item : cart.getServiceCart().values()) {
                if (Integer.parseInt(quantity[i]) == 0) {
                    listKey.add(item.getServiceID());
                } else {
                    item.setSlot(Integer.parseInt(quantity[i]));
                }
                i++;
            }
            for (String key : listKey) {
                cart.getServiceCart().remove(key);
//                System.out.println(key);
            }
            if (cart.getServiceCart().size() > 0) {
                ProcessService bean = new ProcessService();
                if (session.getAttribute("USERNAME") == null) {
                    session.setAttribute("LOGIN_TO_SERVICE", "LoadServiceUser");
                    isLogin = false;
                }
                if (isLogin) {
                    ProcessAccount acc = new ProcessAccount();
                    acc.setUsername(session.getAttribute("USERNAME").toString());
                    Float wallet = acc.getWallet();
                    if (wallet > cart.getTotalServicePriceInCart()) {
                        wallet = wallet - cart.getTotalServicePriceInCart();
                        acc.setCost(wallet);
                        valid = acc.cashWallet();
                    } else {
                        valid = false;
                    }
                    if (valid) {
                        for (ServiceDTO service : cart.getServiceCart().values()) {
                            bean.setServiceID(service.getServiceID());
                            dto = bean.findByKey();
                            bean.setService(dto);
                            bean.setSlot(service.getSlot());
                            if (bean.checkSlot()) {
                                valid = true;
                            } else {
                                errSer.setErrSlot("Out of Slot !");
                                request.setAttribute("ERROR_CART", errSer);
                                valid = false;
                                break;
                            }
                        }
                        if (valid) {
                            for (ServiceDTO service : cart.getServiceCart().values()) {
                                bean.setServiceID(service.getServiceID());
                                dto = bean.findByKey();
                                bean.setService(dto);
                                bean.setSlot(service.getSlot());
                                if (bean.checkSlot()) {
                                    valid = true;
                                    bean.updateSlot();
                                    valid = bean.updateService();
                                } else {
                                    errSer.setErrSlot("Out of Slot !");
                                    request.setAttribute("ERROR_CART", errSer);
                                    valid = false;
                                    break;
                                }
                            }
                            if (valid) {
                                url = SUCCESS;
                            } else {
                                url = FALIED;
                            }
                        } else {
                            url = FALIED;
                        }
                    } else {
                        url = FALIED;
                        session.setAttribute("WALLET", 3);
                    }
                } else {
                    url = "Auth/login.jsp";
                    valid = false;
                }
            } else {
                url = FALIED;
            }
        } catch (Exception e) {
            log("Error at CheckCartServiceController : " + e.getMessage());
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
