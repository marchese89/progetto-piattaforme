<%-- 
    Document   : SignIn
    Created on : 20-set-2014, 12.58.34
    Author     : giovanni
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>iscrizione</title>
        <link href="fogliStileCSS/addProd.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <font color="white">
        <p>
        <img src="immagini/img_prodotti/halo4.jpg" id="sfondo" />
        <h2><font color="white">Iscrizione</font></h2>
        <div align="left" style="align:center;">
        <form action="SignInServlet" method="post">
            <table border="0" cellspacing="2" cellspadding="2" style="border-collapse: collapse" height="100%">
                
                <tr>
                <p></p>
                    <td valign="middle" align="left" height="70" width="48">
                        <p>Nome</p>
                        <p>
                            <input type="text" name = "nome" maxlength="45" size="30">
                        </p>
                    </td>
                
                </tr>
                <tr>                                  
                    <td valign="middle" align="left" height="70" width="48">
                        <p>Cognome</p>
                        <p>
                        <input type="text" name = "cognome" maxlength="45" size="30">
                        </p>
                    </td>
                
                </tr>
                <tr>                              
                    <td valign="middle" align="left" height="70" width="48">
                        <p>Codice Fiscale</p>
                        <p><input type="text" name = "cf" maxlength="16" siize="16"></p>
                    </td>
                
                </tr>
                
                <tr>
                     
                    <td valign="middle" align="left" height="70" width="48">                  
                        Indirizzo<br><br>
                        Via/Piazza <input type="text" name="viaP" maxlength="45" size="30">        
                    </td>
                    <td>&nbsp;&nbsp;</td>
                </tr>
                <tr>
                    
                    <td valign="middle" align="left" height="70" width="48">
                        <br><br>
                        Comune    <input type="text" name="comune" maxlength="45" size="30">
                    </td>
                    <td>&nbsp;&nbsp;</td>
                    <td valign="middle" align="left" height="70" width="48">
                        <br><br>
                        CAP    <input type="text" name="cap" maxlength="5" size="5">
                    </td>
                    <td>&nbsp;&nbsp;</td>
                    <td valign="middle" align="left" height="70" width="48">
                        <br><br>
                        N. Civico    <input type="text" name="num" maxlength="7" size="7">
                    </td>
                    <td>&nbsp;&nbsp;</td>
                    <td valign="middle" align="left" height="70" width="48">
                        <br><br>
                        Provincia   <input type="text" name="prov" maxlength="2" size="2">
                    </td>
                </tr>
                
            <tr>                              
                    <td valign="middle" align="left" height="70" width="48">
                        <p>Email</p>
                        <p><input type="text" name="email1" maxlength="45" size="30"></p>
                    </td>
                
            </tr> 
            <tr>                              
                    <td valign="middle" align="left" height="70" width="48">
                        <p>Conferma Email</p>
                        <p><input type="text" name="email2" maxlength="45" size="30"></p>
                    </td>
                
            </tr>  
            <tr>                              
                    <td valign="middle" align="left" height="70" width="48">
                        <p>Password</p>
                        <p><input type="password" name="pass1" maxlength="45" size="20"></p>
                    </td>
                
            </tr>  
            <tr>                              
                    <td valign="middle" align="left" height="70" width="48">
                        <p>Conferma Password</p>
                        <p><input type="password" name="pass2" maxlength="45" size="20"></p>
                    </td>
                
            </tr>
            <tr>
                <td>
                    <p>
                <input type="submit"  name="iscrizione" value="Conferma">
                    </p>
                    <br>
                    <p>
                </td>
            </tr>
            

            </table>
        </form>
        </div>
        </font>
    </body>
</html>
