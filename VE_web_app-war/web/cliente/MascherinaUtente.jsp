<%-- 
    Document   : MascherinaUtente
    Created on : 28-set-2014, 11.13.18
    Author     : giovanni
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>userpage</title>
    </head>
    <body>
        <% String utente = request.getParameter("user");%>
        <h3> Ciao <%= utente%> </h3>
        <br>
        <form action="LogoutServlet" method="get">
            <input type="submit" value="Logout">
        </form>
        <br>
        <br>
        <%

            if (request.getParameter("user").equals("enzorisoli@hotmail.it")) {
        %>
        <a href="index.jsp?pagina=admin">Home amministratore</a>
        <%
        } else {
        %>
        <p><font size="3">
            <a href="index.jsp?pagina=homeclient&user=<%=utente%>&account=ok" class="collegamento">Il mio account</a>
            </font>
        </p>

        <%
            }

        %>



    </body>
</html>
