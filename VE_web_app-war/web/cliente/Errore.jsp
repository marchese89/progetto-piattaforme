<%-- 
    Document   : Errore
    Created on : 20-set-2014, 14.35.58
    Author     : giovanni
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>errore</title>
    </head>
    <body>
        <h2>Ops!</h2>
        <br>
        <b>
        <font size="6" color="#439A91">Sono stati riportati i seguenti errori:</font>
        <font size=5 color="white">
        <br>
        <%
            String cf = request.getParameter("cf");
            if (cf != null && cf.equals("nonOk")) {
        %>
        Codice fiscale malformato<br>"
        <%
            }
            String email = request.getParameter("email");
            if (email != null && email.equals("nonOk")) {
        %>
        Le due email non corrispondono<br>
        <%
            }
            String pass = request.getParameter("pass");
            if (pass != null && pass.equals("nonOk")) {
        %>
        Le due password non corrispondono<br>
        <%
            }
            String dupl = request.getParameter("dupl");
            if (dupl != null && dupl.equals("nonOk")) {
        %>
        Utente già registrato<br>
        <%
            }
            String errGen = request.getParameter("errGen");
            if (errGen != null && errGen.equals("nonOk")) {
        %>
        Errore interno<br>
        <%
            }
            String utente = request.getParameter("utente");
            if (utente != null && utente.equals("no")) {
        %>
        Non è stato possibile recuperare la password perchè l'utente non esiste<br>
        <%
            }
            String causa = request.getParameter("cause");
            if (causa != null && causa.equals("nouser")) {
        %>
        L'utente non risulta Registrato<br>
        <%
                return;
            }
            if (causa != null && causa.equals("noProd")) {
        %>
        Il prodotto richiesto non esiste<br>
        <%
                return;
        } else if (causa != null && causa.equals("noNumber")) {
        %>
        Codice Prodotto sbagliato<br>
        <%
            return;
        } else if (causa != null) {
        %>
        codice errore <%=causa%>
        <%
            } else {
            }
        %>
        </font>
    </b>
    </body>
</html>
