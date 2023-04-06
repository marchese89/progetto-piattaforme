/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import session_beans.ModificaProdottoBeanLocal;

/**
 *
 * @author Antonio Giovanni
 */
public class ModificaProdottoServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private String id;
    private String nome;
    private String descrizione;
    private double prezzoVendita;
    private int disponibile;
    private String categoria;
    private String percorsoFoto;

    @EJB(mappedName = "ModificaProdottoBeanLocal")
    private ModificaProdottoBeanLocal modificaP;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        id = request.getParameter("idVid");
        nome = request.getParameter("nome");
        descrizione = request.getParameter("descrizione");
        prezzoVendita = Double.parseDouble(request.getParameter("prezzoVendita"));
        disponibile = Integer.parseInt(request.getParameter("disponibile"));
        categoria = request.getParameter("categoria");
        percorsoFoto = request.getParameter("percorsoFoto");

        boolean res = modificaP.modifica(id, nome,
                descrizione, prezzoVendita, disponibile, categoria, percorsoFoto);

        RequestDispatcher rd;
        if (res) {
            rd = request.getRequestDispatcher("index.jsp?pagina=showAllP");
        } else {
            rd = request.getRequestDispatcher("index.jsp?pagina=errorecause=Il prodotto non è stato modificato");
        }
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
