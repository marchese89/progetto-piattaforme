<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>pagina_admin</title>
        <link href="fogliStileCSS/admin.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <img src="immagini/img_prodotti/halo4.jpg" id="sfondo" />
        <div  align="center" style="align:center;">      
            <form name="form1" id="form" method="get" style="width:100%">

                <table border="0" cellspacing="2" cellspadding="2" style="border-collapse: collapse" height="100%">
                    <tr style="border-bottom:solid 1px"> 
                        <td colspan="3" valign="middle" align="center" height="90">
                            <p style="margin-top: 0pt; margin-bottom: 0pt;" align="center">
                                <img src='immagini/img_admin/admin.gif' alt='' style='vertical-align:-7px'>
                                <font class="titolo_news">Pannello di amministrazione</font>
                            </p>                        
                        </td>
                    </tr>
                    <tr style="border-bottom:solid 1px" bgcolor="#439A91"> 
                        <td valign="middle" align="center" height="80" width="48">
                            <img border="0" src="immagini/img_admin/gruppo_utenti.gif" width="35" height="35">
                        </td>
                        <td width="120" valign="middle">
                            <a href="index.jsp?pagina=showAllUsers" class="collegamento"><b>Utenti</b></a>
                        </td>
                        <td valign="middle"> 
                            Da questa sezione &egrave; possibile gestire i dati relativi agli utenti registrati.
                        </td>
                    </tr>

                    <!--amministratore e gestore web-->
                    <tr style="border-bottom:solid 1px" bgcolor="#439A91"> 
                        <td valign="middle" align="center" height="80" width="48">
                            <img border="0" src="immagini/img_admin/categorie.gif" width="35" height="35">
                        </td>
                        <td valign="middle">
                            <a href="index.jsp?pagina=alterCat" class="collegamento"><b>Categorie</b></a>
                        </td>
                        <td valign="middle"> 
                            Da questa sezione &egrave; possibile inserire o eliminare categorie di videogiochi.
                        </td>
                    </tr>

                    <tr style="border-bottom:solid 1px" bgcolor="#439A91"> 
                        <td valign="middle" align="center" height="80">
                            <img border="0" src="immagini/img_admin/Gothic1.gif" width="35" height="35">
                        </td>
                        <td valign="middle">
                            <a href="index.jsp?pagina=showAllP&rp=1" class="collegamento"><b>Prodotti</b></a>
                        </td>
                        <td valign="middle" style="padding-right: 8px;"> 
                            Da questa sezione &egrave; possibile inserire, eliminare, modificare i prodotti presenti nel sito.
                        </td>
                    </tr>

                    <tr style="border-bottom:solid 1px" bgcolor="#439A91"> 
                        <td valign="middle" align="center" height="70">
                            <img border="0" src="immagini/img_admin/ordine.gif" width="35" height="32">
                        </td>
                        <td valign="middle">
                            <a href="index.jsp?pagina=nonEvasi" class="collegamento"><b>Elenco Ordini</b></a>
                        </td>
                        <td valign="middle"> 
                            Da questa sezione &egrave; possibile vedere gli ordini effettuati dagli utenti.
                        </td>
                    </tr>

                </table>

            </form>        
        </div>			      
    </body>
</html>



