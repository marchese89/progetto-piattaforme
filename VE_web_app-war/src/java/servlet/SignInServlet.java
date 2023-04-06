/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import session_beans.SignInBeanLocal;

/**
 *
 * @author giovanni
 */
public class SignInServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @EJB
    SignInBeanLocal signIn;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        boolean[] res = signIn.iscriviti(request.getParameter("nome"), request.getParameter("cognome"), request.getParameter("cf"),
                request.getParameter("email1"), request.getParameter("email2"), request.getParameter("pass1"), 
                request.getParameter("pass2"), request.getParameter("data"),request.getParameter("viaP"),request.getParameter("num"),
                request.getParameter("cap"),request.getParameter("comune"),request.getParameter("prov"));
        //vediamo se si sono verificati errori e di che tipo
        boolean ok = true;
        for(int i = 0; i < res.length; i++)
            if(res[i]){
                ok = false;
            }
        if(ok){
            response.sendRedirect("index.jsp?pagina=iscrizioneOk");
        }else{
        String okV = "ok";
        String nonOkV = "nonOk";
        String cf,mail,pass,dupl,errGen;
        if(res[0])
            cf = nonOkV;
        else 
            cf = okV;
        if(res[1])
            mail = nonOkV;
        else
            mail = okV;
        if(res[2])
            pass = nonOkV;
        else 
            pass = okV;
        if(res[3])
            dupl = nonOkV;
        else
            dupl = okV;
        if(res[4])
            errGen = nonOkV;
        else errGen = okV;
        StringBuilder sb = new StringBuilder();
        sb.append("index.jsp?pagina=errorecf=");
        sb.append(cf);
        sb.append("&email=");
        sb.append(mail);
        sb.append("&pass=");
        sb.append(pass);
        sb.append("&dupl=");
        sb.append(dupl);
        sb.append("&errGen=");
        sb.append(errGen);
        response.sendRedirect(sb.toString()); 
        
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
