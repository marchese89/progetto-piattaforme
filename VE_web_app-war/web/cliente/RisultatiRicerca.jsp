<%-- 
    Document   : RisultatiRicerca
    Created on : 23-ott-2014, 4.05.18
    Author     : Antonio Giovanni
--%>

<%@page import="java.util.Iterator"%>
<%@page import="java.util.HashSet"%>
<%@page import="utility.VideogiocoPrint"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>risultati_ricerca</title>

    </head>
    <body>
        <h1>Risultati Ricerca "<%=request.getParameter("cerca")%>"</h1>
        <div align="center" style="align:center;">      
             
                <table border="0" cellspacing="2" cellspadding="2" style="border-collapse: collapse" height="100%">
                    <% HashSet<VideogiocoPrint> risultati = (HashSet<VideogiocoPrint>) request.getAttribute("listaRisulatati");
                        if (risultati.size() == 0) {
                    %>
                    <h2>La ricerca non ha prodotto risultati</h2>
                    <%
                    } else {
                        Iterator<VideogiocoPrint> it = risultati.iterator();
                        while (it.hasNext()) {
                            VideogiocoPrint v = it.next();
                    %>
                    <tr style="border-bottom:solid 1px;opacity: 0.7" bgcolor="#439A91">
                        <td width="5%"valign="middle" align="center" height="70" width="48">
                            <img border=0 src="<%=v.getPercorso()%>" width="55" height="55"><b><%=v.getNome()%></b>
                        </td>
                        <td width="5%" valign="middle">
                            id: <%=v.getId()%><br>
                            <a href="index.jsp?pagina=ShowSingleP&idVideogioco=<%=v.getId()%>">
                                <b>Dettagli Prodotto</b></a>
                        </td>
                        <td valign="left" width="80%">
                            <b>Descrizione</b><br>
                            <%=v.getDescrizione().length() > 50 ? v.getDescrizione().substring(0, 50) : v.getDescrizione()%>
                        </td>
                        <td width="10%">
                            <form name="form1" id="form" method="post" action="index.jsp">
                            <input type="hidden" name="pagina" value="showCat">
                            <input type="hidden" name="azione" value="aggiungi">
                            <input type="hidden" name="idVid" value="<%=v.getId()%>">
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
                        }
                    %>

                </table>

                   
        </div>	
    </body>
</html>
