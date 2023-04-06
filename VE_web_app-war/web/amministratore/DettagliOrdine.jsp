<%-- 
    Document   : DettagliOrdine
    Created on : 2-nov-2014, 8.28.35
    Author     : Giovanni
--%>

<%@page import="java.math.BigDecimal"%>
<%@page import="java.math.MathContext"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.HashMap"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>riepilogo_ordine</title>
        <link href="fogliStileCSS/visualizzaElenchi.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <% String idOrdine = request.getParameter("idOrdine");%>
        <h1><font color="white">Riepilogo ordine #<%=idOrdine%></font></h1>
        <div id="elenco" align="center" style="align:center;">
            <b>
            <table border="0" cellspacing="2" cellspadding="2" style="border-collapse: collapse" align="center" width="80%">
                <%
                    Double totale = new Double(0);
                    HashMap<Integer, Integer> riepilogo = (HashMap<Integer, Integer>) request.getSession().getAttribute("riepilogoOrdine_" + idOrdine);
                    Set<Integer> idVideogiochi = riepilogo.keySet();
                    for (Integer i : idVideogiochi) {
                %>
                <tr bgcolor="#439A91" style="opacity: 0.7"> 
                    <td align="left" height="60" width="10%">
                        <%=i%>
                    </td>
                    <td align="right" width="15%">
                        <%=request.getAttribute("vid_" + i)%>
                    </td>
                    <td align="right" width="60%">  
                        x<%=riepilogo.get(i)%>
                    </td>
                    <td align="right" width="15%">
                        <%=request.getAttribute("vidPr_" + i)%> &euro;
                        <% totale+=(Double)request.getAttribute("vidPr_" + i)*riepilogo.get(i);%>
                    </td>
                </tr>
                <%
                    }
                %>
                <tr bgcolor="#439A91" style="opacity: 0.7">
                    <td align="left" height="60" width="10%">
                    </td>
                    <td align="right" width="15%">
                    </td>
                    <td align="right" width="60%">
                        spedizione
                    </td>
                    <td align="right" width="15%">
                        +9.90 &euro;
                    </td>
                </tr>
                <tr bgcolor="#439A91" style="opacity: 0.7">
                    <td align="left" height="60" width="10%">
                    </td>
                    <td align="right" width="15%">
                    </td>
                    <td align="right" width="60%">
                        Totale
                    </td>
                    <td align="right" width="15%">
                        <%
                            int modPag = (Integer)request.getAttribute("modP");
                            totale += 9.9;
                            if (modPag == 1) {
                                totale += 5.9;
                            }
                            MathContext mc = new MathContext(4);
                            BigDecimal totale2 = BigDecimal.valueOf(totale).round(mc);
                        %>
                        <%=totale2%> &euro;
                    </td>
                </tr>
            </table>
            </b>
        </div>
    </body>
</html>
