<%-- 
    Document   : VisualizzaNonEvasi
    Created on : 1-nov-2014, 9.59.30
    Author     : Giovanni
--%>

<%@page import="utility.OrdinePrint"%>
<%@page import="java.util.LinkedList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ordini_non_evasi</title>
        <link href="fogliStileCSS/visualizzaElenchi.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <h1><font color="white">Ordini da Evadere o il Lavorazione</font></h1>
        <br>
        <br>
        <div id="elenco" align="center" style="align:center;">
            <table border="1" cellspacing="2" cellspadding="2" style="border-collapse: collapse" height="50%">
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
                    </td>
                    <td valign="middle" align="center">
                        <font size = 4.8 color ="red">dettagli</font>
                    </td>
                </tr>
                <%
                    LinkedList<OrdinePrint> nonEvasi = (LinkedList<OrdinePrint>) request.getSession().getAttribute("nonEvasi");
                    for (OrdinePrint ord : nonEvasi) {
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
                            <input type="hidden" name="pagina" value="nonEvasi">
                            <input type="hidden" name="idOrdine" value="<%=ord.getCodiceOrdine()%>">
                            <input type="hidden" name="user" value="<%=ord.getCliente()%>">
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
        </div>
</body>
</html>
