<%-- 
    Document   : ProdottiAlpha
    Created on : 24-ott-2014, 5.32.02
    Author     : Antonio Giovanni
--%>

<%@page import="java.util.Iterator"%>
<%@page import="java.util.LinkedList"%>
<%@page import="utility.VideogiocoPrint"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>prodotti_per_categoria</title>
        <link href="fogliStileCSS/visualizzaElenchi.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <%String iniziale = request.getParameter("alpha");%>
        <h2>Videogiochi per <%=iniziale%></h2>
        <br>
        <br>
        <%
            LinkedList<VideogiocoPrint> elencoP = 
                    (LinkedList<VideogiocoPrint>) request.getAttribute("prodotti_" + iniziale+"#rp"+request.getParameter("rp"));

        %>  
        <div id="elenco" align="center" style="align:center;">

            <table border="0" cellspacing="2" cellspadding="2" style="border-collapse: collapse" height="100%">
                <%   Iterator<VideogiocoPrint> it = elencoP.iterator();
                    while (it.hasNext()) {
                        VideogiocoPrint v = it.next();
                %>

                <tr style="border-bottom:solid 1px;opacity:0.7" bgcolor="#439A91">
                    <td width="5%"valign="middle" align="center" height="70" width="48">
                        <img border=0 src="<%=v.getPercorso()%>" width="55" height="55"><b><%=v.getNome()%></b>
                    </td>
                    <td width="5%" valign="middle">
                        <b>id: <%=v.getId()%></b><br>
                    </td>
                    <td>
                        <a href="index.jsp?pagina=ShowSingleP&idVideogioco=<%=v.getId()%>" class="collegamento">
                            <b>dettagli</b></a>
                    </td>
                    <td width="2%"></td>
                    <td valign="middle" width="48%">
                        <b>Descrizione</b><br>
                        <%=v.getDescrizione().length() > 50 ? v.getDescrizione().substring(0, 50) : v.getDescrizione()%>
                    </td>
                
                    <td width="6%">
                        Qta disp.
                    </td>
                    <td>
                        <%=v.getQtaDisp()%>
                    </td>
                    <td width="3%"></td>
                    <td width="10%">
                        <b>Prezzo  <%=v.getPrezzo()%> &euro;</b>
                    </td>
                    <td width="10%">
                        <form action="index.jsp" method="get">
                        <input type="hidden" name="pagina" value="showAlpha">
                        <input type="hidden" name="idVid" value="<%=v.getId()%>">
                        <input type="hidden" name="azione" value="aggiungi">
                        <input type="hidden" name="rp" value="<%=request.getParameter("rp")%>">
                        <input type="hidden" name="alpha" value="<%=request.getParameter("alpha")%>">
                        <%
                            int quantDisp = Integer.parseInt(v.getQtaDisp());
                            if (quantDisp > 0) {
                        %>
                        <input type="image" title="Aggiungi al Carrello" src="immagini/img_sistema/carrello.png" width="50" height="50">
                        <%
                        } else {
                        %>
                            <img  title="Prodotto non disponibile" src="immagini/img_sistema/carrello_nd.png" width="50" height="50">
                        <%
                            }
                        %>
                        </form>
                    </td>
                
                </tr>
                <%
                    }
                %>

            </table>
                
                
                <%
                int numPagine = (Integer) request.getAttribute("nPagine")+1;
                if (numPagine > 1) {
            %>
            <table>
                <tr>
                    <td align="left" width="100%">pagina</td>
                </tr>
                    <%
                        for (int i = 1; i <= numPagine; i++) {
                    %>
                    
                
                    <a href="index.jsp?pagina=showAlpha&alpha=<%=iniziale%>&rp=<%=i%>"><%=i%></a></td> 
                    </td>
                    <%
                            }
                        }
                        if (numPagine > 1) {
                    %>
                    </tr>
            </table>
            <%
                }
            %>

        </div>
    </body>
</html>

