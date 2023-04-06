/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import session_beans.CaricaUtenteBeanLocal;
import utility.OrdinePrint;
import utility.UtentePrint;

/**
 *
 * @author Giovanni
 */
public class CaricaUtentiServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @EJB(mappedName = "CaricaUtenteBeanLocal")
    private CaricaUtenteBeanLocal caricaUtenti;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        RequestDispatcher rd;
        String azione = request.getParameter("azione");
        if(azione != null && azione.equals("mostraUtenti")){
            LinkedList<UtentePrint> utenti = caricaUtenti.getUtenti();
            request.getSession(false).setAttribute("utenti", utenti);
            rd = request.getRequestDispatcher("index.jsp?pagina=showAllUsers");
            rd.forward(request, response);
        }else if(azione != null && azione.equals("mostraOrdiniCliente")){
            String codCliente = request.getParameter("cfCliente");
            LinkedList<OrdinePrint> ordiniUser = caricaUtenti.getOrdiniUtente(codCliente);
            request.getSession(false).setAttribute("ordiniUtente", ordiniUser);
            rd = request.getRequestDispatcher("index.jsp?pagina=showUsersOrders");
            rd.forward(request, response);
        }else{
            rd = request.getRequestDispatcher("index.jsp?pagina=errorecause=ci sono stati problemi col caricamento dei dati");
            rd.forward(request, response);
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
