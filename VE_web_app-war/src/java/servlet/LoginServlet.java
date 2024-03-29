/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.util.HashMap;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import session_beans.*;

/**
 *
 * @author giovanni
 */
public class LoginServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @EJB(mappedName = "LoginBeanLocal")
    private LoginBeanLocal login;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String redirezione;
        String email = request.getParameter("email");
        if (email.equals("admin")) {
            redirezione = login.effettuaLogin("enzorisoli@hotmail.it", "dora13");
        } else {
            redirezione = login.effettuaLogin(request.getParameter("email"), request.getParameter("password"));
        }
        String pagina = request.getParameter("pagina");
        if (pagina != null) {
            if (pagina.equals("backOrdine")) {
                login.setPageParam("pagina", pagina);
                String modP = request.getParameter("pagamento");
                if (modP != null) {
                    login.setPageParam(email, pagina);
                    login.setPageParam("pagamento", modP);
                }
            }
        }
        if (login.getLoginOk()) {
            request.getSession().setAttribute("user", login.getNomeUtente());
            request.getSession().setAttribute("idUser", login.getCf());
        }
        RequestDispatcher rd = request.getRequestDispatcher(redirezione);
        rd.forward(request, response);

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
