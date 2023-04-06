<%-- 
    Document   : MostraUtenti
    Created on : 29-ott-2014, 13.20.12
    Author     : Giovanni
--%>

<%@page import="utility.UtentePrint"%>
<%@page import="java.util.LinkedList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>mostra_utenti</title>
        <link href="fogliStileCSS/visualizzaElenchi.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <h1><font color="white">Utenti registrati</font></h1>
        <br>
        <br>
        <div id="elenco" align="center" style="align:center;">
            
            <table border="1" cellspacing="2" cellspadding="2" style="border-collapse: collapse" height="100%">
                <%
                    LinkedList<UtentePrint> utenti = (LinkedList<UtentePrint>) request.getSession(false).getAttribute("utenti");
                %>
                <tr width="100%" style="border-bottom:solid 1px;opacity: 0.7" bgcolor="#439A91"><b>
                    <td><font color="red">Codice Fiscale</font></td>
                    <td><font color="red">Nome</font></td>
                    <td><font color="red">Cognome</font></td>
                    <td><font color="red">Recapito</font></td>
                    <td><font color="red">Lista Ordini</font></td>
                </b>
                </tr>
                <%
                    for (UtentePrint u : utenti) {
                %>
                <tr style="border-bottom:solid 1px;opacity: 0.7" bgcolor="#439A91">
                    <td><%=u.getCF()%></td>
                    <td><%=u.getNome()%></td>
                    <td><%=u.getCognome()%></td>
                    <td><a href="index.jsp?pagina=recapito&user=<%=u.getCF()%>&nomeUtente=<%=u.getNome()%>">Recapito</a></td>
                    <td><a href="index.jsp?pagina=elencoOrdini&user=<%=u.getCF()%>&nomeUtente=<%=u.getNome()%>">Lista Ordini</a></td>
                </tr>
                <%
                    }
                %>
            </table>   
            </font>
    </body>
</html>
