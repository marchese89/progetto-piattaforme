<%-- 
    Document   : InserisciCategoria
    Created on : 25-set-2014, 15.06.50
    Author     : giovanni
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>inseriscicategoria</title>
    </head>
    <body>
        <h1>Inserire sotto la nuova Categoria</h1>
        <form action="AddCategoriaServlet" method="post" align="center">
            <table border="0" cellspacing="2" cellspadding="2" style="border-collapse: collapse" height="100%" align="center">
            <tr>
                <h3>Nome Categoria</h3>
            </tr>
            <tr>
            <input type="text" name="nomeCat"> 
            </tr>
            <tr>
            <input type="submit" value="Aggiungi Categoria">
            <% String update = request.getParameter("update");
                    if (update != null) {
                        if (request.getParameter("update").equals("ok")) {
                            %>
                              Categoria Aggiunta
                              <%
                        } else {
                            %>
                            <font color="red" >Ci sono stati dei problemi</font>
                            <%
                        }
                    }
            %>
            </tr>
        </form>
    </body>
</html>
