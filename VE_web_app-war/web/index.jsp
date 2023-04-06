<%-- 
    Document   : index
    Created on : 21-ott-2014, 17.30.33
    Author     : Antonio Giovanni
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>homehandler</title>
    </head>
    <body>
        <%
           RequestDispatcher rd = request.getRequestDispatcher("RefreshServlet");
           rd.forward(request, response);
        %>
    </body>
</html>
