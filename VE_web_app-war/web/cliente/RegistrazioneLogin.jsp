<%-- 
    Document   : RegistrazioneLogin
    Created on : 28-ott-2014, 4.50.01
    Author     : Giovanni
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="fogliStileCSS/admin.css" rel="stylesheet" type="text/css">
        <title>registrazione_login</title>
    </head>
    <body>
        <img src="immagini/img_prodotti/halo4.jpg" id="sfondo" />
        <h2><font color="white"> Non sei registrato</font></h2>
        <br>
        <br>
        <font color="white">
        <b>
            <table border="0" cellspacing="2" cellspadding="2" style="border-collapse: collapse" width="60%" height="100%" align="center" valign="top">
                <tr>
                    <td valign="top" align="center" height="50">Login
                        <form action="LoginServlet" name="form1" id="form" method="post" style="width:60%">
                            <input type="hidden" name="pagina" value="backOrdine">
                            <input type="hidden" name="pagamento" value="<%=request.getParameter("pagamento")%>">
                            <p>
                                <font color="#2BCB91">Nome Utente</font>
                            <p>
                                <input type="text" name = "email" maxlength="45" size="21">
                            <p>
                                <font color="#2BCB91">Password</font>
                            <p>
                                </b>
                                </font>
                            <p>
                                <input type ="password" name = "password" maxlength="45" size="21">
                            <p>
                                <input type="submit" value="Accedi">
                        </form>
                    </td>
                    <td valign="top" align="center" height="50">
                        <form action="index.jsp" name="form2" id="form" method="post" style="width:60%">
                            <input type="hidden" name="pagina" value="signIn">
                            <input type="submit" value="Registrati">
                        </form>
                    </td>
                </tr>
            </table>

    </body>
</html>
