<%-- 
    Document   : ConfermaOrdine
    Created on : 26-ott-2014, 14.56.28
    Author     : Giovanni
--%>

<%@page import="java.math.BigDecimal"%>
<%@page import="java.math.MathContext"%>
<%@page import="utility.VideogiocoPrint"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.HashMap"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>conferma_ordine</title>
        <link href="fogliStileCSS/visualizzaElenchi.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <h1>Conferma Ordine</h1>

        <%
            HashMap<Integer, Integer> prodotti = (HashMap<Integer, Integer>) request.getSession().getAttribute("carrello");

        %>
        <div id="elenco" align="center" style="align:center;  overflow-y:scroll">
            <font size="4.5">
            <table border="1" cellspacing="2" cellspadding="2" style="border-collapse: collapse" height="100%">
                <%                    Set<Integer> elencoP = prodotti.keySet();
                    Iterator<Integer> it = elencoP.iterator();
                    while (it.hasNext()) {
                        Integer idVid = it.next();
                        VideogiocoPrint v = (VideogiocoPrint) request.getSession().getAttribute("vid_" + idVid);
                %>
                <tr style="border-bottom:solid 1px;opacity: 0.7" bgcolor="#439A91">

                    <td width="8%" valign="middle" height="65">
                        <b>id: <%=v.getId()%><br></b>
                    </td>
                    <td width="10%">
                        <%=v.getNome()%>
                    </td>
                    <td valign="right" width="5%">
                        Qta
                    </td>
                    <%
                        String quantita = prodotti.get(idVid).toString();
                    %>
                    <td width="4%" valign="middle">
                    <%=quantita%>
                    </td>
                    <td width ="62%"></td>
                    <td>Prezzo</td>
                    <td align="right" width="12%">
                        <% int qta = prodotti.get(idVid);%>
                        <b><%=Double.parseDouble(v.getPrezzo()) * qta%> &euro;</b>
                    </td>
                </tr>
                <%
                    }
                %>
                <tr width="100%" style="border-bottom:solid 1px;opacity: 0.7" bgcolor="#439A91">
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td align="center"><b>Spese di spedizione</b></td>
                    <td></td>
                    <td align="right" height="50"><b>+ 9,90 &euro;</b>
                    </td>
                </tr>
                <tr width="100%" style="border-bottom:solid 1px;opacity: 0.7" bgcolor="#439A91">
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td>Totale</td>
                    <td align="right" height="50">
                        <%//Calcoliamo il prezzo totale dei prodotti
                            Set<Integer> elencoProd = prodotti.keySet();
                            Iterator<Integer> it2 = elencoProd.iterator();
                            double totale = 0;
                            while (it2.hasNext()) {
                                Integer idVid = it2.next();
                                VideogiocoPrint v = (VideogiocoPrint) request.getSession().getAttribute("vid_" + idVid);
                                totale += Double.parseDouble(v.getPrezzo()) * prodotti.get(idVid);
                            }
                            totale+=9.9;
                            MathContext mc = new MathContext(4);
                            BigDecimal totale2 = BigDecimal.valueOf(totale).round(mc);
                        %>
                        <b><%=totale2%> &euro;</b>
                    </td>
                </tr>
                <tr width="100%" style="border-bottom:solid 1px;opacity: 0.7" bgcolor="#439A91">
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td align="left"><h2>Modalità di Pagamento</h2></td>
                    <td></td>
                    <td align="left" height="50">
                    </td>
                </tr>
                <form action="index.jsp" method="get">
                <tr width="100%" style="border-bottom:solid 1px;opacity: 0.7" bgcolor="#439A91">
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td align="center">
                        <input type="radio" name="pagamento" value="1"> <b>Contrassegno</b>
                    </td>
                    <td></td>
                    <td align="right" height="50">
                        <b>+5,90 &euro;</b>
                    </td>
                </tr style="border-bottom:solid 1px;opacity: 0.7" bgcolor="#439A91">
                <tr width="100%" style="border-bottom:solid 1px;opacity: 0.7" bgcolor="#439A91">
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td align="center">
                        <input type="radio" name="pagamento" value="0" checked="checked"> <b>Carta di Credito</b>
                    </td>
                    <td></td>
                    <td align="left" height="50">
                    </td>
                </tr>
                
                <tr width="100%" style="border-bottom:solid 1px;opacity: 0.7" bgcolor="#439A91">
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td align="center" height="50">
                        <input type ="hidden" name="pagina" value="modCarr">
                        <input type="hidden" name="azione" value="invia">
                        <input type="submit" value="Conferma">
                    </td>
                </tr>
                </form>
            </table>
            </font>
        </div>

    </body>
</html>
