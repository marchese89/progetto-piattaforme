<%-- 
    Document   : HomeCliente
    Created on : 11-ott-2014, 5.22.44
    Author     : Giovanni
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>homeuser</title>
        <link href="fogliStileCSS/cliente.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <br>
        <br>
        <font color="white">
        <img src="immagini/img_prodotti/halo4.jpg" id="sfondo" />
        <h2>Area personale</h2>
        <br>
        <br>
        <%
            String user = (String) request.getSession().getAttribute("idUser");
        %>
        <div align="center" style="align:center">
            <table border="1" cellspacing="2" cellspadding="2" style="border-collapse: collapse" height="100%">
                <tr style="opacity: 0.7" bgcolor="#439A91">

                    <td valign="middle" align="center" height="70" width="48">
                        <img border="0" src="immagini/img_admin/gruppo_utenti.gif" width="35" height="35" alt="Gestione account" title="Gestione account">
                    </td>
                    <td width="120" valign="middle">
                        <a href="index.jsp?pagina=modificaDatiCliente" class="collegamento"><b>I miei dati</b></a>
                    </td>
                    <td valign="middle"> 
                        Da questa sezione &egrave; possibile gestire i dati relativi al proprio account.
                    </td>

                </tr>
                <tr style="opacity: 0.7" bgcolor="#439A91">

                    <td valign="middle" align="center" height="70" width="48">
                        <img border="0" src="immagini/img_admin/gruppo_utenti.gif" width="35" height="35" alt="Gestione account" title="Gestione account">
                    </td>
                    <td width="120" valign="middle">
                        <a href="index.jsp?pagina=storicoOrdini&utente=<%=user%>" class="collegamento"><b>Storico Ordini</b></a>
                    </td>
                    <td valign="middle"> 
                        Da questa sezione &egrave; possibile vedere i propri ordini.
                    </td>

                </tr>

            </table>
        </div>
        </font>
    </body>
</html>
