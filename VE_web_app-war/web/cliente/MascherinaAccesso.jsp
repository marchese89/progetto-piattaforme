<%-- 
    Document   : MascherinaAccesso
    Created on : 28-set-2014, 11.06.28
    Author     : giovanni
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>accedi</title>
    </head>
    <body>
        <FORM action="LoginServlet" method="post">
            <table>
                <tr>         
                    Email: 
                <p>
                    <br>
                    </tr>
                <tr>
                <input type="text" name = "email" maxlength="45" size="14">
                <p>
                    <br>
                    </tr>
                <tr>
                    Password:
                <p>
                    <br>
                    </tr>
                <tr>
                <input type ="password" name = "password" maxlength="45" size="14">
                </tr>
                <p>
                    <br>
                <tr>
                <input type="submit"  value="Login" >
                <p>
                    <br>
                    </tr>
                <tr> 
                <a href="index.jsp?pagina=datiDim" class="collegamento"><font size=4>dati dimenticati</font></a>
                <p>
                    <br>
                    </tr>
                <tr>
                <a href="index.jsp?pagina=signIn" class="collegamento"><font size=4> Iscriviti </font></a>
                </tr>   
            </table>
        </FORM> 

    </body>
</html>
