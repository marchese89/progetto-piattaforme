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
import session_beans.InviaOrdineBeanLocal;

/**
 *
 * @author Giovanni
 */
public class InviaOrdineServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @EJB(mappedName = "InviaOrdineBeanLocal")
    private InviaOrdineBeanLocal inviaOrdine;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        RequestDispatcher rd;
        String user = (String) request.getSession().getAttribute("idUser");
        boolean risultato = false;
        if (user == null || user.equals("null")) {
            rd = request.getRequestDispatcher("index.jsp?pagina=noUserLogin&pagamento="+request.getParameter("pagamento"));
        } else {
            risultato = inviaOrdine.invia(user, (HashMap<Integer, Integer>) request.getSession().getAttribute("carrello"));
            if (risultato) {
                rd = request.getRequestDispatcher("index.jsp?pagina=ordineOk&clearCarr=ok");
            } else {
                rd = request.getRequestDispatcher("index.jsp?pagina=ordineNo&pagamento="+request.getParameter("pagamento"));
            }
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
