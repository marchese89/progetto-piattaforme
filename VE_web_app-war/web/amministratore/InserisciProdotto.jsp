<%@page import="utility.VideogiocoPrint"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.LinkedList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <title>inserimento_prodotto</title>
    <br>
    <br>
    <% String id = request.getParameter("idVid");
    if(id == null){
    %>
    <h2 align="center">Aggiungi nuovo prodotto</h2>
    <%}else{
    %>
    <h2 align="center">Modifica prodotto</h2>
    <%
    }
    %>
    <br>
    <br>
    <link href="fogliStileCSS/addProd.css" rel="stylesheet" type="text/css">
</head>
<body>

    <div align="center" style="align:center;overflow-y:scroll">
          <form id="form" action="<%=id != null ? "ModificaProdottoServlet"
                  : "InserimentoProdottoServlet"%>" method="get">

            <%  String prName = request.getParameter("nome") != null ? request.getParameter("nome") : "";
                String prDescr = request.getParameter("descrizione") != null ? request.getParameter("descrizione") : "descrizione prodotto";
                String prPrezzo = request.getParameter("prezzoVendita") != null ? request.getParameter("prezzoVendita") : "";
                String prDisp = request.getParameter("disponibile") != null ? request.getParameter("disponibile") : "";
                String prCat = request.getParameter("categoria") != null ? request.getParameter("categoria") : "";
                String prPercorso = request.getParameter("percorsoFoto") != null ? request.getParameter("percorsoFoto") : "immagini/img_prodotti/img_vuota.jpg";
                
                if (id != null) {
                    VideogiocoPrint vid = (VideogiocoPrint) request.getAttribute("videogioco");
                    prName = vid.getNome();
                    prDescr = vid.getDescrizione();
                    prPrezzo = vid.getPrezzo();
                    prDisp = vid.getQtaDisp();
                    prCat = vid.getCategoria();
                    prPercorso = vid.getPercorso();
                }

            %>


            <table border="0" cellspacing="2" cellspadding="2" style="border-collapse: collapse" height="100%">

                <tr style="border-bottom:solid 1px"> 
                    <td colspan="3" valign="middle" align="center" height="70">
                        <p style="margin-top: 0pt; margin-bottom: 0pt;" align="center">

                            <font class="titolo_news">Nome</font>
                        </p>
                        <input type="text"  name="nome" value="<%=prName%>">

                        <br>
                        <br>
                    </td>
                    <td colspan="3" valign="middle" align="center" height="70">

                        <p style="margin-top: 0pt; margin-bottom: 0pt;" align="center">

                            <font class="titolo_news">Categoria</font>
                        </p>
                        <%
                            if (id == null) {
                        %>
                        <select name="categoria">
                            <%LinkedList<String> opzioniCat
                                        = (LinkedList<String>) request.getAttribute("categorie");
                                Iterator<String> it = opzioniCat.iterator();
                                while (it.hasNext()) {

                            %>   

                            <option><%=it.next()%></option>
                            <%

                                }
                            %>
                        </select>
                        <%
                        } else {
                        %>
                        
                        <font size="4.5" color="000"><%=prCat%></font>
                        <%
                            }
                        %>

                        <br>
                        <br>
                    </td>

                </tr>    

                <tr style="border-bottom:solid 1px"> 
                    <td colspan="3" valign="middle" align="center" height="70">
                        <p style="margin-top: 0pt; margin-bottom: 0pt;" align="center">

                            <font class="titolo_news">Descrizione</font>
                        </p>
                        <input type="text"  name="descrizione" value="<%=prDescr%>"> 
                        <br>
                        <br>
                    </td>

                    <td colspan="3" valign="middle" align="center" height="70">
                        <p style="margin-top: 0pt; margin-bottom: 0pt;" align="center">

                            <font class="titolo_news">Prezzo</font>
                        </p>
                        <input type="text"  name="prezzoVendita" value="<%=prPrezzo%>">

                        <br>
                        <br>

                        <p style="margin-top: 0pt; margin-bottom: 0pt;" align="center">

                            <font class="titolo_news">Quantità in magazzino</font>
                        </p>
                        <input type="text"  name="disponibile" value="<%=prDisp%>">

                        <br>
                        <br>
                    </td>

                </tr>

                <tr style="border-bottom:solid 1px"> 
                    <td colspan="3" valign="middle" align="center" height="70">
                        <p style="margin-top: 0pt; margin-bottom: 0pt;" align="center">

                            <font class="titolo_news">Percorso Foto</font>
                        </p>
                        <p><img src="<%=prPercorso%>" width="70%"></a></p>
                        <input type="text" name="percorsoFoto" value="<%=prPercorso%>"><br>
                        <br>
                        <br>
                    </td>

                    <td colspan="3" valign="middle" align="center" height="70">
                        <% String path = request.getParameter("percorsoFoto");
                            if (path == null) {
                                if (id == null) {
                        %>
                        <input type="submit"  value="Carica Prodotto"><br>
                        <%
                        } else {
                        %>
                        <input type="hidden" name="pagina" value="alterProd">
                        <input type="hidden" name="idVid" value="<%=id%>">
                        <input type="hidden" name="update" value="ok">
                        <input type="submit"  value="Modifica Prodotto"><br>
                        <%
                            }
                        } else {
                            if (id == null) {
                        %>
                        <a href="index.jsp?pagina=addProd">
                            <input type="submit" value="Carica Nuovo Prodotto">
                        </a>
                        <br>
                        <%
                                }
                            }
                        %>

                    </td>

                    <td colspan="3" valign="middle" align="center" height="70">
                        <p>
                            <%
                                if (request.getParameter("res") != null) {
                                    if (request.getParameter("res").equals("ok")) {
                            %>
                            <font size=4.5 color="white">Prodotto inserito correttamente</font>
                            <%
                                }

                                if (request.getParameter("res").equals("no")) {
                            %>
                            <font color=red size=4.5>Ci sono stati problemi nell'inserimento del prodotto<font>
                            <%
                                    }

                                }
                            %>
                        </p>
                    </td>

                </tr>

            </table>
        </form>

        <br>


    </div>




</body>
</html>