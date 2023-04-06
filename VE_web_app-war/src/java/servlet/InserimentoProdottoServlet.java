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
import session_beans.InserisciProdottoLocal;

/**
 *
 * @author Giovanni
 */
public class InserimentoProdottoServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private String nome;
    private String descrizione;
    private double prezzoVendita;
    private int disponibile;
    private int venduta;
    private String categoria;
    private String percorsoFoto;

    @EJB
    private InserisciProdottoLocal inserimento;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        boolean risultato = false;
        nome = request.getParameter("nome").toLowerCase();
        descrizione = request.getParameter("descrizione").toLowerCase();
        prezzoVendita = Double.parseDouble(request.getParameter("prezzoVendita"));
        disponibile = Integer.parseInt(request.getParameter("disponibile"));
        categoria = request.getParameter("categoria");
        percorsoFoto = request.getParameter("percorsoFoto");
        risultato = inserimento.inserisciProdotto(nome, descrizione, prezzoVendita, disponibile, categoria, percorsoFoto);
        StringBuilder sb = new StringBuilder();
        sb.append("index.jsp?pagina=insertP");
        if (risultato) {
            sb.append("&res=ok");
        } else {
            sb.append("&res=no");
        }
        RequestDispatcher rd = request.getRequestDispatcher(sb.toString());
        
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
