<%-- 
    Document   : VisualizzaProdotto
    Created on : 29-ott-2014, 4.40.14
    Author     : Giovanni
--%>

<%@page import="utility.VideogiocoPrint"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="fogliStileCSS/visualizzaElenchi.css" rel="stylesheet" type="text/css">
        <title>visualizza_prodotto</title>
    </head>
    <body>
        <font color="white">
        <%
            String id = request.getParameter("idVideogioco");
            VideogiocoPrint v = (VideogiocoPrint) request.getSession(false).getAttribute("vid_" + id);
        %>
        <h1><%=v.getNome()%></h1>
        <table border="0" cellspacing="2" cellspadding="2" style="border-collapse: collapse" height="100%">
            <tr>
                <td valign="top">
                    <img src="<%=v.getPercorso()%>" width="350px" height="350px">
                </td>
                <td valign="top">
                    <b><font size="5"><b>Descrizione</b></font></b>
                    <p>
                        <font size="4.4"><%=v.getDescrizione()%></font>
                    <p>
                        <%
                        int disp = Integer.parseInt(v.getQtaDisp());
                        if(disp>0){
                        %>
                        <font size="5"><b>Disponibilità: <%=v.getQtaDisp()%><b></font>
                                <%
                        }else{
                            %>
                            <font size="5" color="red"><b>Prodotto non disponibile<b></font>
                            <%
                        }
                            %>       
                    <p>
                        
                        <b><font size="5">prezzo  <font size="4.4"><%=v.getPrezzo()%> &euro;</font></font></b>
       
                </td>
                <% if(disp > 0){
                %>
                
                <td valig="middle">
                    <a href="index.jsp?pagina=showCat&azione=aggiungi&idVid=<%=v.getId()%>"><input type="submit" value="Acquista"></a>
                </td>
                <%
                }
                %>
            </tr>

        </table>
        </font>
    </body>
</html>
