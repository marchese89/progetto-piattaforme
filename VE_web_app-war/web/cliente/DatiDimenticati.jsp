<%-- 
    Document   : DatiDimenticati
    Created on : 25-set-2014, 9.56.06
    Author     : giovanni
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dati Dimenticati</title>
    </head>
    <body>
        <font size="4.8" color="white">
        <h3>Recupero password</h3>
        <br>
        <form action="RecuperaPassServlet" method="post">
            <table align="center">
            Inserire di seguito il proprio indirizzo email per recuperare la password<br>
            <input type="text" name="mail">
            <br>
            <input type="submit" name="Invia Email">
            </table>
        </form>
         </font>
    </body>
</html>
