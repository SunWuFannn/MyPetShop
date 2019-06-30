/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haipm.pet_controller;

import haipm.dtos.PetDTO;
import haipm.error_obj.ErrorPet;
import haipm.models.ProcessPet;
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
public class AddPetController extends HttpServlet {

    private static final String SUCCESS = "index.jsp";
    private static final String ERROR = "error.jsp";
    private static final String FALIED = "Pet/addPet.jsp";

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
        ErrorPet err = new ErrorPet();
        try {
            String petID = request.getParameter("txtPetID");
            String petName = request.getParameter("txtPetName");
            String petImage = request.getParameter("txtPetImage");
            String petType = request.getParameter("txtPetType");
            if (petID.length() == 0) {
                err.setErrorID("Can't Empty ID");
                valid = false;
            }
            if (petName.length() == 0) {
                err.setErrorName("Can't Empty Name");
                valid = false;
            }
            if (petType.equals("choose")) {
                err.setErrorType("Please choose Type");
                valid = false;
            }
            if (request.getParameter("txtPetAge").equals("")) {
                err.setErrorAge("Can't empty Age");
                valid = false;
            }
            int petAge = Integer.parseInt(request.getParameter("txtPetAge"));
            if (petAge <= 0) {
                err.setErrorAge("Cant negative number number");
                valid = false;
            }
            if (valid) {
                PetDTO pet = new PetDTO(petID, petName, petType, petImage, true, petAge);
                ProcessPet bean = new ProcessPet();
                bean.setPet(pet);
                HttpSession session = request.getSession();
                bean.setUsername((String) session.getAttribute("USERNAME"));
                valid = bean.addPet();
                if (valid) {
                    valid = bean.addToUser();
                    if (valid) {
                        url = SUCCESS;
                    }
                } else {
                    request.setAttribute("ERROR", "ADD PET FALIED");
                }
            } else {
                url = FALIED;
                request.setAttribute("ERROR_PET", err);
            }

        } catch (Exception e) {
            log("Error at AddPetController : " + e.getMessage());
            if (e.getMessage().contains("duplicate")) {
                err.setErrorID("Id have Existed");
                valid = false;
                url = FALIED;
            }
            if (e.getMessage().contains("For input string:")) {
                err.setErrorAge("Wong format !");
                valid = false;
                url = FALIED;
            }
            request.setAttribute("ERROR_PET", err);
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
