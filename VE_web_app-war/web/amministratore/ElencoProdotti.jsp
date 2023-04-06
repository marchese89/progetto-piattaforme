<%-- 
    Document   : ElencoProdotti
    Created on : 24-ott-2014, 7.40.51
    Author     : Antonio Giovanni
--%>

<%@page import="java.util.Iterator"%>
<%@page import="utility.VideogiocoPrint"%>
<%@page import="java.util.LinkedList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>tutti_i_prodotti</title>
        <link href="fogliStileCSS/visualizzaElenchi.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <h2>Elenco prodotti disponibili nel sistema</h2>
        <%
            int paginaCorrente = Integer.parseInt((String)request.getParameter("rp"));
            LinkedList<VideogiocoPrint> elencoP = (LinkedList<VideogiocoPrint>) request.getAttribute("prodotti#rp"+paginaCorrente);
        %>
        <br>
        <a href="index.jsp?pagina=addProd">
            <input type="submit" value="Nuovo Prodotto">
        </a>
        <br>
        <div id="elenco" align="center" style="align:center;">
            
                <table border="0" cellspacing="2" cellspadding="2" style="border-collapse: collapse" height="100%">

                    <%
                        if (elencoP != null) {
                            Iterator<VideogiocoPrint> it = elencoP.iterator();
                            VideogiocoPrint v;
                            while (it.hasNext()) {
                                v = it.next();
                    %>
                    
                    <tr style="border-bottom:solid 1px;opacity: 0.7" bgcolor="#439A91">
                        <td width="5%"valign="middle" align="center" height="70" width="48">
                            <img border=0 src="<%=v.getPercorso()%>" width="55" height="55"><b><%=v.getNome()%></b>
                        </td>
                        <td width="5%" valign="middle">
                            <b>id: <%=v.getId()%></b><br>
                        </td>
                        <td>
                            <a href="VisualizzaProdotto.jsp?idVid=<%=v.getId()%>" class="collegamento">
                                <b>dettagli</b></a>
                        </td>
                        <td width="2%"></td>
                        <td valign="middle" width="48%">
                            <b>Descrizione</b><br>
                            <%=v.getDescrizione().length() > 50 ? v.getDescrizione().substring(0, 50) : v.getDescrizione()%>
                        </td>

                        <td width="3%"></td>
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
                            <p>
                            
                              <form action="index.jsp">  
                                <input type="hidden" name="pagina" value="addProd">
                                <input type="hidden" name="idVid" value="<%=v.getId()%>">
                                <input type="hidden" name="update" value="ok">
                                <input type="submit" value="Modifica">
                              </form>
                            </p>
                            <a href="EliminaProdottoServlet?idVid=<%=v.getId()%>&rp=<%=(String)request.getParameter("rp")%>">Elimina</a>
                        </td>
                    </tr>
                    
                    <%
                        }
                    } else {
                    %>
                    <br>
                    <br>
                    <font size="5" color="black">Non ci sono prodotti nel Database</font>
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
                    
                
                <a href="index.jsp?pagina=showAllP&rp=<%=i%>"><%=i%></td> 
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
