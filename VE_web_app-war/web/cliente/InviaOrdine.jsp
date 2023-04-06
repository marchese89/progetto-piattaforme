<%-- 
    Document   : Invia Ordine
    Created on : 26-ott-2014, 17.26.51
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
        <title>invia_ordine</title>
        <link href="fogliStileCSS/visualizzaElenchi.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        

        <%
            HashMap<Integer, Integer> prodotti = (HashMap<Integer, Integer>) request.getSession(false).getAttribute("carrello");
            if (prodotti == null || prodotti.size() == 0) {
                %>
                <jsp:include page="../Sfondo.jsp" flush="true"/>
                <%
            } else {
        %>
        <h1>Inviare Ordine</h1>
        <div id="elenco" align="center" style="align:center;">
            <font size="4.5">
            <form action="InviaOrdineServlet?pagamento=<%=request.getParameter("pagamento")%>" method="post">
                <table border="1" cellspacing="2" cellspadding="2" style="border-collapse: collapse" height="100%">
                    <%                    Set<Integer> elencoP = prodotti.keySet();
                        Iterator<Integer> it = elencoP.iterator();
                        while (it.hasNext()) {
                            Integer idVid = it.next();
                            VideogiocoPrint v = (VideogiocoPrint) request.getSession(false).getAttribute("vid_" + idVid);
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
                        <td align="left"><h2>Modalità di Pagamento</h2></td>
                        <td></td>
                        <td align="left" height="50">
                        </td>
                    </tr>
                    <% String modPag = request.getParameter("pagamento");
                        if (modPag.equals("1")) {

                    %>
                    <tr width="100%" style="border-bottom:solid 1px;opacity: 0.7" bgcolor="#439A91">
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td align="center">
                            <b>Contrassegno</b>
                        </td>
                        <td></td>
                        <td align="right" height="50">
                            <b>+5,90 &euro;</b>
                        </td>
                    </tr>
                    <%                } else {
                    %>
                    <tr width="100%" style="border-bottom:solid 1px;opacity: 0.7" bgcolor="#439A91">
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td align="center">
                            <b>Carta di Credito</b>
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
                        <td align="center">
                            <b>Numero Carta</b>   <input type="text" maxlength="16" size="16">    <b>Intestatario   <input type="text" name="intest">
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
                        <td align="center">
                            <b>Scadenza</b>     <input type="text" max="2" size="2" name="mese">   <input type="text" max="2" size="2" name="anno"> 
                            CCV <input type="text" max="2" size="2" name="ccv">
                        </td>
                        <td>
                        </td>
                        <td align="left" height="50">
                        </td>
                    </tr>

                    <%}%>

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
                                totale += 9.9;
                                if (modPag.equals("Contrassegno")) {
                                    totale += 5.9;
                                }
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
                        <td></td>
                        <td></td>
                        <td align="center" height="50">

                            <input type="hidden" name="pagamento" value="<%=request.getParameter("pagamento")%>">
                            <input type="submit" value="Invia Ordine">

                        </td>
                    </tr>

                </table>
            </form>
            </font>
        </div>
        <%
            }
        %>
    </body>
</html>
