<%-- 
    Document   : MostraOrdiniUtente
    Created on : 31-ott-2014, 5.54.47
    Author     : Giovanni
--%>

<%@page import="utility.OrdinePrint"%>
<%@page import="java.util.LinkedList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>mostra_ordini</title>
        <link href="fogliStileCSS/visualizzaElenchi.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <%
            String cfUtente = request.getParameter("user");
        %>
        <h1>Elenco ordini <%=cfUtente%></h1>
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
                </td><td valign="middle" align="center">
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

                    <form action="index.jsp" method="doGet">
                        <input type="hidden" name="pagina" value="aggiornaStato">
                        <input type="hidden" name="idOrdine" value="<%=ord.getCodiceOrdine()%>">
                        <input type="hidden" name="user" value="<%=cfUtente%>">
                        <select name="stato">
                            <% String st = ord.getStato();
                                if (st.equals("da evadere")) {
                            %>
                            <option selected="selected">da evadere</option>
                            <%
                            } else {
                            %>
                            <option>da evadere</option>
                            <%
                                }
                                if (st.equals("in lavorazione")) {
                            %>
                            <option selected="selected">in lavorazione</option>
                            <%
                            } else {
                            %>
                            <%
                            }
                                %>
                            <option>in lavorazione</option>
                            <%
                                if (st.equals("evaso")) {

                            %>
                            <option selected="selected">evaso</option>
                            <% } else {
                            %>
                            <option>evaso</option>
                            <%
                                }
                            %>
                        </select>

                        <font size="4.7">
                        <input type="submit" value="aggiorna stato">
                        </font>
                    </form>
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
