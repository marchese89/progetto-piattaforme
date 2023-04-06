<%-- 
    Document   : ModificaDati
    Created on : 2-nov-2014, 14.30.34
    Author     : Giovanni
--%>

<%@page import="java.util.LinkedList"%>
<%@page import="utility.UtentePrint"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>modificaDati</title>
    </head>
    <body>
        <h1>Modifica Dati</h1>
        <font color="white">
        <p>
            <img src="immagini/img_prodotti/halo4.jpg" id="sfondo" />
        <div align="left" style="align:center;">
            <form action="index.jsp" method="doGet">

                <table border="0" cellspacing="2" cellspadding="2" style="border-collapse: collapse" height="100%">
                    <input type="hidden" name="pagina" value="modificaDatiCliente">
                    <input type="hidden" name="update" value="ok">
                    <%
                        LinkedList<UtentePrint> utenti = (LinkedList<UtentePrint>) request.getSession().getAttribute("datiUtente");
                        UtentePrint u = utenti.getFirst();
                    %>
                    <tr>
                    <p></p>
                    <td valign="middle" align="left" height="70" width="48">
                        <p><b>Nome</b></p>
                        <input type="hidden" name="nome" value="<%=u.getNome()%>">
                        <p>
                            <%=u.getNome()%>
                        </p>
                    </td>

                    </tr>
                    <tr>                                  
                        <td valign="middle" align="left" height="70" width="48">
                            <p><b>Cognome</b></p>
                            <p>
                                <input type="hidden" name="cognome" value="<%=u.getCognome()%>">
                                <%=u.getCognome()%>
                            </p>
                        </td>

                    </tr>
                    <tr>                              
                        <td valign="middle" align="left" height="70" width="48">
                            <p><b>Codice Fiscale</b></p>
                            <input type="hidden" name="cf" value="<%=u.getCF()%>">
                            <%=u.getCF()%><br>
                            <br>
                            <br>
                        </td>

                    </tr>

                    <tr>

                        <td valign="middle" align="left" height="70" width="48">                  
                            <b>Indirizzo</b><br>
                            <br>

                            Via/Piazza <input type="text" name="viaP" value="<%=u.getVia()%>" maxlength="45" size="30">        
                        </td>
                        <td>&nbsp;&nbsp;</td>
                    </tr>
                    <tr>

                        <td valign="middle" align="left" height="70" width="48">
                            <br><br>
                            Comune    <input type="text" name="comune" value="<%=u.getComune()%>" maxlength="45" size="30">
                        </td>
                        <td>&nbsp;&nbsp;</td>
                        <td valign="middle" align="left" height="70" width="48">
                            <br><br>
                            CAP    <input type="text" name="cap"  value="<%=u.getCap()%>" maxlength="5" size="5">
                        </td>
                        <td>&nbsp;&nbsp;</td>
                        <td valign="middle" align="left" height="70" width="48">
                            <br><br>
                            N. Civico    <input type="text" name="num" value="<%=u.getNumeroC()%>" maxlength="7" size="7"><br>
                        </td>
                        <td>&nbsp;&nbsp;</td>
                        <td valign="middle" align="left" height="70" width="48">
                            <br><br>
                            Provincia   <input type="text" name="prov" value="<%=u.getProv()%>" maxlength="2" size="2">
                        </td>
                    </tr>

                    <tr>                              
                        <td valign="middle" align="left" height="70" width="48">
                            <p>Email</p>
                            <p><input type="text" name="email1" value="<%=u.getEmail()%>" gemaxlength="45" size="30"></p>
                        </td>

                    </tr> 
                    <tr>                              
                        <td valign="middle" align="left" height="70" width="48">
                            <p>Password</p>
                            <p><input type="password" name="pass1" value="<%=u.getPassword()%>" maxlength="45" size="20"></p>
                        </td>

                    </tr>  
                    <tr>
                        <td>
                            <p>
                                <input type="submit"  name="iscrizione" value="Conferma Modifiche">
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
