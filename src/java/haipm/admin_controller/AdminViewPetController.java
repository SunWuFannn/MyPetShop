/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haipm.admin_controller;

import haipm.daos.PetDAO;
import haipm.dtos.PetDTO;
import haipm.models.ProcessPet;
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
public class AdminViewPetController extends HttpServlet {

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
        String url = "Admin/adminViewPet.jsp";
        List<PetDTO> listSplit = null;
        int pageID = Integer.parseInt(request.getParameter("idPage"));
        try {
            ProcessPet bean = new ProcessPet();
            ArrayList<PetDTO> listPet = (ArrayList<PetDTO>) bean.getAllPet();
            PetDAO dao = new PetDAO();
            for (PetDTO petDTO : listPet) {
                petDTO.setOwner(dao.findOwner(petDTO.getPetID()));
            }
            int numberOfAll = listPet.size();
            int numberOfPage = numberOfAll % 5 == 0 ? (numberOfAll / 5) : (numberOfAll / 5) + 1;
            request.setAttribute("NUMBER_PAGE", numberOfPage);
            if (5 * pageID > listPet.size()) {
                listSplit = (List<PetDTO>) listPet.subList(5 * pageID - 5, listPet.size());
            } else {
                listSplit = (List<PetDTO>) listPet.subList(5 * pageID - 5, 5 * pageID);
            }
            request.setAttribute("LIST_PET", listSplit);
        } catch (Exception e) {
            log("Error at AdminViewPetController : " + e.getMessage());
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
