<%-- 
    Document   : CartaDiCredito
    Created on : 4-nov-2014, 9.01.32
    Author     : Giovanni
--%>

<%@page import="utility.CartaPrint"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="fogliStileCSS/visualizzaElenchi.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <h1>Carta di Credito</h1>
        <font size ="4.4" color="white">
        <%
            CartaPrint carta = (CartaPrint)request.getSession().getAttribute("cartaCredito");
            //if(carta != null){
            %>
        <div  align="center" style="align:center;">

            <form name="form1" action="index.jsp?pagina="id="form" method="post" style="width:100%">
                <table border="0" cellspacing="2" cellspadding="2" style="border-collapse: collapse" height="100%">
                    <tr bgcolor="#439A91" style="opacity: 0.7"> 
                        <td>
                        </td>
                        <td>
                            <b>NumeroCarta</b>
                        </td>
                        <td>
                        </td>
                    </tr>
                    <tr bgcolor="#439A91" style="opacity: 0.7">
                        <td>
                        </td>
                        <td>
                            <input type="text" name="nCarta" maxlength="16" size="16">
                        </td>
                        <td>
                        </td>
                    </tr>
                    <tr bgcolor="#439A91" style="opacity: 0.7">
                        <td>
                        </td>
                        <td>
                            Intestatario
                        </td>
                        <td>
                        </td>
                    </tr>
                    <tr bgcolor="#439A91" style="opacity: 0.7">
                        <td>
                        </td>
                        <td>
                            <input type="text" name="intestatario">
                        </td>
                        <td>
                        </td>
                    </tr>
                    <tr bgcolor="#439A91" style="opacity: 0.7">
                        <td>
                        </td>
                        <td>
                            Scadenza
                        </td>
                        <td>
                        </td>
                    </tr>
                    <tr bgcolor="#439A91" style="opacity: 0.7">
                        <td>
                            mese <input type="text" name="mese" maxlength="2" size="2">
                        </td>
                        <td>
                            anno <input type="text" name="anno" maxlength="2" size="2">
                        </td>
                        <td>
                            CCV <input type="text" name="ccv" maxlength="3" size="3">
                        </td>
                    </tr>
                    <tr bgcolor="#439A91" style="opacity: 0.7">
                        <td>
                        </td>
                        <td>
                        </td>
                        <td>
                            <input type="submit" value="Inserici Carta">
                        </td>
                    </tr>
                </table>
            </form>
        </div>
        <%
            //}else
        %>
        </font>
    </body>
</html>
