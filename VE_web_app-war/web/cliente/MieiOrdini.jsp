<%-- 
    Document   : MieiOrdini
    Created on : 2-nov-2014, 6.13.53
    Author     : Giovanni
--%>

<%@page import="utility.OrdinePrint"%>
<%@page import="java.util.LinkedList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>i_miei_ordini</title>
        <link href="fogliStileCSS/visualizzaElenchi.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <%
            String cfUtente = request.getParameter("utente");
        %>
        <h1>I miei Ordini</h1>
        <%
            LinkedList<OrdinePrint> elencoOrdini = (LinkedList<OrdinePrint>) request.getSession().getAttribute("ordini_" + cfUtente);
        %>

        <table border="1" cellspacing="2" cellspadding="2" style="border-collapse: collapse" align="center">

            <tr width="100%" style="border-bottom:solid 1px;opacity: 0.7" bgcolor="#439A91">
                <td valign="middle" align="center" height="40px">
                    <font size = 4.8 color ="red">codice</font>
                </td>
                <td valign="middle" align="center">
                    <font size = 4.8 color ="red">data</font>
                </td>
                <td valign="middle" align="center">
                    <font size = 4.8 color ="red">importo</font>
                </td><td valign="middle" align="center">
                    <font size = 4.8 color ="red">mod. pagamento</font>
                </td><td valign="middle" align="center">
                    <font size = 4.8 color ="red">stato</font>
                </td>
                <td valign="middle" align="center">
                    <font size = 4.8 color ="red">dettagli</font>
                </td>
            </tr>

            <%
                for (OrdinePrint ord : elencoOrdini) {
            %>
            <tr width="100%" style="border-bottom:solid 1px;opacity: 0.7" bgcolor="#439A91">

                <td valign="middle" align="center" height="40px">
                    <font size="4.7" color="white"><%=ord.getCodiceOrdine()%></font>
                </td>
                <td valign="middle" align="center">
                    <font size="4.7" color="white"><%=ord.getDataOrdine()%></font>
                </td>
                <td valign="middle" align="center">
                    <font size="4.7" color="white"><%=ord.getTotale()%></font>
                </td>
                <td valign="middle" align="center">
                    <%
                        int mod = ord.getModPagamento();
                        if (mod == 0) {
                    %>
                    <font size="4.7" color="white">carta</font>
                    <%
                    } else {
                    %>
                    <font size="4.7" color="white">contrassegno</font>
                    <%
                        }
                    %>
                </td>
                <td valign="middle" align="center">
                    <%String st = ord.getStato();
                        String color = "";
                        if (st.equals("da evadere")) {
                            color = "orange";
                        } else if (st.equals("in lavorazione")) {
                            color = "yellow";
                        } else {
                            color = "green";
                        }
                    %>
                    <font size="4.7" color="<%=color%>"><%=st%></font> 
                </td>
                <td>
                    <a href="index.jsp?pagina=dettagliOrdine&idOrdine=<%=ord.getCodiceOrdine()%>">visualizza</a>
                </td>
            </tr>
            <%
                }
            %>

        </table>
    </body>
</html>
