<%-- 
    Document   : VisualizzaCarrello
    Created on : 11-ott-2014, 11.18.18
    Author     : Giovanni
--%>

<%@page import="java.math.MathContext"%>
<%@page import="java.math.RoundingMode"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="utility.VideogiocoPrint"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.HashSet"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.HashMap"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>visualizza_carrello</title>    
        <link href="fogliStileCSS/visualizzaElenchi.css" rel="stylesheet" type="text/css">
    </head>
    <h2>Il tuo carrello</h2>
    <br>
    <br>
    <body>
        <%
            HashMap<Integer, Integer> prodotti = (HashMap<Integer, Integer>) request.getSession().getAttribute("carrello");
            if (prodotti.size() != 0) {
        %>
        <div id="elenco" align="center" style="align:center;">
            <table border="0" cellspacing="2" cellspadding="2" style="border-collapse: collapse" height="100%">
                <%                    Set<Integer> elencoP = prodotti.keySet();
                    Iterator<Integer> it = elencoP.iterator();
                    while (it.hasNext()) {
                        Integer idVid = it.next();
                        VideogiocoPrint v = (VideogiocoPrint) request.getSession().getAttribute("vid_" + idVid);
                %>
                <tr style="border-bottom:solid 1px;opacity: 0.7" bgcolor="#439A91">
                    <td valign="middle" align="center" height="70" width="5%">
                        <img border=0 src="<%=v.getPercorso()%>" width="55" height="55"><b><%=v.getNome()%></b>
                    </td>
                    <td width="5%" valign="middle">
                        <b>id: <%=v.getId()%><br></b>
                    </td>
                    <td>
                        <a href="index.jsp?pagina=ShowSingleP&idVideogioco=<%=v.getId()%>" class="collegamento">
                            <b>Dettagli Prodotto</b></a>
                    </td>
                    <td valign="middle" width="68%">
                        <%=v.getDescrizione().length() > 50 ? v.getDescrizione().substring(0, 50) : v.getDescrizione()%>
                    </td>

                <form action="index.jsp">
                    <td>Qta</td>
                    <td width="3%">
                        <%
                            String quantita = prodotti.get(idVid).toString();
                        %>
                        <input name="qta" type="text" value="<%=quantita%>" size="2" maxlength="2">
                    </td>
                    <td><input type="submit" value="aggiorna Qta"</td>
                    <td width="10%" >
                        <input type="hidden" name="idVid" value="<%=v.getId()%>">
                        <input type="hidden" name="pagina" value="modCarr">
                        <input type="hidden" name="azione" value="aggiungi">
                    </td>

                </form>
                <td valign="right" width="9%">
                    <% int qta = prodotti.get(idVid);%>
                    <b>Prezzo: <%=Double.parseDouble(v.getPrezzo()) * qta%> &euro;</b>
                </td>
                </tr>
                <%
                    }
                %>
                <tr width="100%">
                    <td align="left"></td>
                    <td align="left"></td>
                    <td align="left"></td>
                    <td align="left"></td>
                    <td align="left"></td>
                    <td align="left"></td>
                    <td align="left"></td>
                    <td align="left"></td>
                    <td align="left" height="50">
                        <%//Calcoliamo il prezzo totale dei prodotti
                            Set<Integer> elencoProd = prodotti.keySet();
                            Iterator<Integer> it2 = elencoProd.iterator();
                            double totale = 0;
                            while (it2.hasNext()) {
                                Integer idVid = it2.next();
                                VideogiocoPrint v = (VideogiocoPrint) request.getSession().getAttribute("vid_" + idVid);
                                totale += Double.parseDouble(v.getPrezzo()) * prodotti.get(idVid);
                            }
                            MathContext mc = new MathContext(4);
                            BigDecimal totale2 = BigDecimal.valueOf(totale).round(mc);
                        %>
                        <b>Totale: <%=totale2%> &euro;</b>
                    </td>
                </tr>
                <tr width="100%">
                    <td align="left"></td>
                    <td align="left"></td>
                    <td align="left"></td>
                    <td align="left"></td>
                    <td align="left"></td>
                    <td align="left"></td>
                    <td align="left"></td>
                    <td align="left"></td>
                    <td align="left" height="50">
                        <a href="index.jsp?pagina=modCarr&azione=compra"><input type="submit" value="Acquista"></a>
                    </td>
                </tr>
                <tr width="100%">
                    <td align="left"></td>
                    <td align="left"></td>
                    <td align="left"></td>
                    <td align="left"></td>
                    <td align="left"></td>
                    <td align="left"></td>
                    <td align="left"></td>
                    <td align="left"></td>
                    <td align="left" height="50">
                        <a href="index.jsp?pagina=modCarr&azione=svuota&clearCarr=ok"><input type="submit" value="Svuota Carrello"></a>
                    </td>
                </tr>
            </table>
        </div>
        <%
        } else {
        %>
        <h2>è vuoto!</h2>
        <%
            }
        %>

    </body>
</html>
