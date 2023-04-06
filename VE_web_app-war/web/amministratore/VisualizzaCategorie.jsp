<%-- 
    Document   : VisualizzaCategorie
    Created on : 22-ott-2014, 8.02.26
    Author     : Antonio Giovanni
--%>

<%@page import="java.util.LinkedList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>visualizza_categorie</title>
        <link href="fogliStileCSS/visualizzaElenchi.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <h1>Elenco Categorie</h1>

        <div id="elenco" align="center" style="align:center;">
            <table border="0" cellspacing="2" cellspadding="2" style="border-collapse: collapse" height="100%">
                <tr>
                    <td>
                        <a href="index.jsp?pagina=addCat"><input type="submit" value="Nuova Categoria"></a>
                    </td>
                    <td>
                    </td>
                    <td>
                    </td>
                </tr>
                <%
                    LinkedList<String> categorie = (LinkedList<String>) request.getAttribute("categorie");
                    for (String s : categorie) {
                %>
                <tr> 
                    <td colspan="3" valign="middle" align="center" height="90">
                        <p style="margin-top: 0pt; margin-bottom: 0pt;" align="center">
                            <img src='immagini/img_admin/admin.gif' alt='' style='vertical-align:-7px'>
                            <font class="titolo_news"><%=s%></font>              
                        </p>                        
                    </td>
                    <td>
                        <a href="index.jsp?pagina=alterCat&azioneCat=rimuovi&cat=<%=s%>"><input type="submit" value="elimina" name="eliminaCat"></a>
                    </td>

                </tr>
                <%
                    }
                %>
            </table>
        </div>
    </body>
</html>
