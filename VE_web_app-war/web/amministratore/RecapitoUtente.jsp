<%-- 
    Document   : RecapitoUtente
    Created on : 31-ott-2014, 8.04.47
    Author     : Giovanni
--%>

<%@page import="utility.IndirizzoPrint"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>recapito_utente</title>
    </head>
    <body>
        <h1><font color="#2BCB91">Indirizzo <%=request.getParameter("nomeUtente")%></font></h1>
        <%
            IndirizzoPrint ind = (IndirizzoPrint) request.getSession(false).getAttribute("indirizzo_" + request.getParameter("user"));
        %>
        <font size="4.7" color="white">
        <table border="0" cellspacing="2" cellspadding="2" style="border-collapse: collapse">

            <tr >
                <td width="85%" valign="middle" align="left" height="60px"><%=ind.getVia()%></td>
                <td width="15%">n. <%=ind.getNumCivico()%></td>
            </tr>
            <tr>
                <td width="85%" valign="middle" align="left" height="60px"><%=ind.getCap()%>, <%=ind.getComune()%></td>
                <td width="15%">
                    <%=ind.getProvincia()%>
                </td>
            </tr>

        </table>
        </font>
    </body>
</html>
