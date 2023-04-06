
<%@page import="java.util.Iterator"%>
<%@page import="java.util.LinkedList"%>
<%@page import="java.util.HashMap"%>
<%@page import="session_beans.HomeBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>videogiochieverytime</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <link href="fogliStileCSS/home.css" rel="stylesheet" type="text/css">

    </head>

    <body>

        <img src="immagini/img_prodotti/halo4.jpg" id="sfondo" />

        <!--<div id="container">-->
        <div id="header">
            <h1><font color="#439A91">Videogiochi Everytime</font></h1>
        </div>
        <div id="navigation">
            <a href="index.jsp"><img src="">Torna alla Home</a>
            <ol class="tree">
                <form action="RicercaServlet" method="doGet">
                    <input type="text" name="cerca" maxlength="40" size="5" />
                    <input type="submit" value="Cerca"/>
                </form>
                <br>
                <li>                   
                    <label for="folder1" id="categorie">Categorie</label> 
                    <input type="checkbox" id="folder1"/>  


                    <ol>
                        <%

                            LinkedList<String> categorie = (LinkedList<String>) request.getAttribute("categorie");

                            StringBuilder sb = new StringBuilder();
                            Iterator<String> it = categorie.iterator();
                            while (it.hasNext()) {
                                String nomeCategoria = it.next();
                        %>
                        <li class="file">
                            <a href="index.jsp?pagina=showCat&cat=<%=nomeCategoria%>&rp=1"><%=nomeCategoria%></a>
                        </li>
                        <%
                            }

                        %>

                    </ol>

                </li>
                <br>
                <li>
                    <label for="folder1">Ordine Alfabetico</label> <input type="checkbox" id="folder1" />

                    <ol>
                        <li class="file"><a href="index.jsp?pagina=showAlpha&alpha=A&rp=1">A</a></li>
                        <li class="file"><a href="index.jsp?pagina=showAlpha&alpha=B&rp=1">B</a></li>
                        <li class="file"><a href="index.jsp?pagina=showAlpha&alpha=C&rp=1">C</a></li>
                        <li class="file"><a href="index.jsp?pagina=showAlpha&alpha=D&rp=1">D</a></li>
                        <li class="file"><a href="index.jsp?pagina=showAlpha&alpha=E&rp=1">E</a></li>
                        <li class="file"><a href="index.jsp?pagina=showAlpha&alpha=F&rp=1">F</a></li>
                        <li class="file"><a href="index.jsp?pagina=showAlpha&alpha=G&rp=1">G</a></li>
                        <li class="file"><a href="index.jsp?pagina=showAlpha&alpha=H&rp=1">H</a></li>
                        <li class="file"><a href="index.jsp?pagina=showAlpha&alpha=I&rp=1">I</a></li>
                        <li class="file"><a href="index.jsp?pagina=showAlpha&alpha=J&rp=1">J</a></li>
                        <li class="file"><a href="index.jsp?pagina=showAlpha&alpha=K&rp=1">K</a></li>
                        <li class="file"><a href="index.jsp?pagina=showAlpha&alpha=L&rp=1">L</a></li>
                        <li class="file"><a href="index.jsp?pagina=showAlpha&alpha=M&rp=1">M</a></li>
                        <li class="file"><a href="index.jsp?pagina=showAlpha&alpha=N&rp=1">N</a></li>
                        <li class="file"><a href="index.jsp?pagina=showAlpha&alpha=O&rp=1">O</a></li>
                        <li class="file"><a href="index.jsp?pagina=showAlpha&alpha=P&rp=1">P</a></li>
                        <li class="file"><a href="index.jsp?pagina=showAlpha&alpha=Q&rp=1">Q</a></li>
                        <li class="file"><a href="index.jsp?pagina=showAlpha&alpha=R&rp=1">R</a></li>
                        <li class="file"><a href="index.jsp?pagina=showAlpha&alpha=S&rp=1">S</a></li>
                        <li class="file"><a href="index.jsp?pagina=showAlpha&alpha=T&rp=1">T</a></li>
                        <li class="file"><a href="index.jsp?pagina=showAlpha&alpha=U&rp=1">U</a></li>
                        <li class="file"><a href="index.jsp?pagina=showAlpha&alpha=V&rp=1">V</a></li>
                        <li class="file"><a href="index.jsp?pagina=showAlpha&alpha=W&rp=1">W</a></li>
                        <li class="file"><a href="index.jsp?pagina=showAlpha&alpha=X&rp=1">X</a></li>
                        <li class="file"><a href="index.jsp?pagina=showAlpha&alpha=Y&rp=1">Y</a></li>
                        <li class="file"><a href="index.jsp?pagina=showAlpha&alpha=Z&rp=1">Z</a></li>
                    </ol>

                </li>
            </ol>​
        </div>
        <div id="extra">
            <div class="newsbox">



                <%                    
                    HashMap<Integer, Integer> c = (HashMap<Integer, Integer>) request.getAttribute("carrello");
                    if(request.getParameter("clearCarr") != null){
                        c.clear();
                        request.getSession(false).removeAttribute("carrello");
                    }
                    request.getSession().setAttribute("carrello", c);
                    String utente = (String) request.getSession().getAttribute("user");
                    if (utente == null || !utente.equals("enzorisoli@hotmail.it")) {
                %>
                <a href="index.jsp?pagina=carrello">
                    <font size="4.7" color="white">
                    Carrello(</font><font size="4.7" color="red"><%=c.size()%></font><font size="4.7" color="white">)
                    </font>
                </a>
                <br>
                <br>
                <%
                    }
                %>
                <%
                    String innerNews = "";
                    if (utente == null || utente == "") {
                        innerNews = "cliente/MascherinaAccesso.jsp";
                    } else {
                        innerNews = "cliente/MascherinaUtente.jsp?user=" + utente;
                    }

                %>
                <jsp:include page="<%=innerNews%>" flush="true"/>

            </div>


        </div>



        <div id="content">

            <%String innerPage = (String) request.getAttribute("innerPage");%>

            <jsp:include page="<%=innerPage%>" flush="true"/>

        </div>
            <div id="footer"><font size="2" color="white">© 2014 - Web App a cura di Antonio Giovanni Marchese & Enzo Risoli -
            marchese89@hotmail.com - enzorisoli@hotmail.it </font>
        </div>

    </body>



</html>

