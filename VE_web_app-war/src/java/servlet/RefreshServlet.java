/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import session_beans.CaricaOrdineBeanLocal;
import session_beans.CaricaUtenteBeanLocal;
import session_beans.CarrelloBeanLocal;
import session_beans.CategorieBeanLocal;
import session_beans.HomeBeanLocal;
import session_beans.ProdottiCatBeanLocal;
import session_beans.TrovaVideogiocoBeanLocal;
import utility.IndirizzoPrint;
import utility.OrdinePrint;
import utility.UtentePrint;
import utility.VideogiocoPrint;

/**
 *
 * @author Antonio Giovanni
 */
public class RefreshServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @EJB(mappedName = "CategorieBeanLocal")
    private CategorieBeanLocal beanCategorie;
    @EJB(mappedName = "CarrelloBeanLocal")
    private CarrelloBeanLocal carrello;
    @EJB(mappedName = "ProdottiCatBeanLocal")
    private ProdottiCatBeanLocal generaListaP;
    @EJB(mappedName = "TrovaVideogiocoBeanLocal")
    private TrovaVideogiocoBeanLocal trovaVideogioco;
    @EJB(mappedName = "HomeBeanLocal")
    private HomeBeanLocal caricaHome;
    @EJB(mappedName = "CaricaUtenteBeanLocal")
    private CaricaUtenteBeanLocal caricaUtenti;
    @EJB(mappedName = "CaricaOrdineBeanLocal")
    private CaricaOrdineBeanLocal caricaOrdine;

    private static String innerPage;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        RequestDispatcher rd = request.getRequestDispatcher("index2.jsp");
        request.setAttribute("categorie", beanCategorie.getCategorie());

        HashMap<String, String> img = caricaHome.generateRandomHome();
        request.getSession().setAttribute("imgHome", img);

        String azioneCat = request.getParameter("azioneCat");
        if (azioneCat != null && azioneCat.equals("rimuovi")) {
            beanCategorie.rimuovi(request.getParameter("cat"));
            request.setAttribute("categorie", beanCategorie.getCategorie());
        }

        for (int i = 1; i <= 10; i++) {
            if (i != 6) {
                VideogiocoPrint p = trovaVideogioco.findById(new Integer(i).toString());
                request.getSession().setAttribute("vid_" + i, p);
            }
        }
        HashMap<Integer, Integer> c = carrello.getContenuto();
        if (request.getAttribute("clearCarr") != null) {
            carrello.clear();
            request.removeAttribute("carrello");
        }
        request.setAttribute("carrello", c);
        boolean changed = false;

        String pageParameter = request.getParameter("pagina");
        if (pageParameter == null) {
            pageParameter = "";
        }

        if (pageParameter.equals("showAllUsers")) {
            LinkedList<UtentePrint> utenti = caricaUtenti.getUtenti();
            request.getSession(false).setAttribute("utenti", utenti);
            innerPage = "amministratore/MostraUtenti.jsp";
            changed = true;
        }

        if (pageParameter.equals("recapito")) {
            IndirizzoPrint ind = caricaUtenti.getRecapito(request.getParameter("user"));
            request.getSession().setAttribute("indirizzo_" + request.getParameter("user"), ind);
            innerPage = "amministratore/RecapitoUtente.jsp";
            changed = true;
        }

        if (pageParameter.equals("elencoOrdini")) {
            LinkedList<OrdinePrint> ord = caricaUtenti.getOrdiniUtente(request.getParameter("user"));
            request.getSession().setAttribute("ordini_" + request.getParameter("user"), ord);
            innerPage = "amministratore/OrdiniUtente.jsp";
            changed = true;
        }

        if (pageParameter.equals("dettagliOrdine")) {
            String codice = request.getParameter("idOrdine");
            HashMap<Integer, Integer> riepilogo = caricaOrdine.getCarrello(new Integer(codice));
            request.getSession().setAttribute("riepilogoOrdine_" + codice, riepilogo);
            request.setAttribute("modP", caricaOrdine.getModPagamento(new Integer(codice)));
            Set<Integer> codVideogiochi = riepilogo.keySet();

            for (Integer i : codVideogiochi) {
                request.setAttribute("vid_" + i, caricaOrdine.getNomeVideogioco(i));
            }
            for (Integer i : codVideogiochi) {
                request.setAttribute("vidPr_" + i, caricaOrdine.getPrezzoVideogioco(i));
            }
            innerPage = "amministratore/DettagliOrdine.jsp";
            changed = true;
        }

        if (pageParameter.equals("modificaDatiCliente")) {
            String update = request.getParameter("update");
            if (update != null) {
                UtentePrint utente = new UtentePrint(request.getParameter("cf"),
                        request.getParameter("nome"), request.getParameter("cognome"));
                utente.setEmail(request.getParameter("email1"));
                utente.setPassword(request.getParameter("pass1"));
                utente.setVia(request.getParameter("viaP"));
                utente.setComune(request.getParameter("comune"));
                utente.setCap(request.getParameter("cap"));
                utente.setNumeroC(request.getParameter("num"));
                utente.setProv(request.getParameter("prov"));
                caricaUtenti.modificaDatiUtente(utente);
                request.getSession().removeAttribute("datiUtente");
            }
            request.getSession().setAttribute("datiUtente", caricaUtenti.getUtente((String) request.getSession().getAttribute("idUser")));
            innerPage = "cliente/ModificaDati.jsp";
            changed = true;
        }

        if (pageParameter.equals("aggiornaStato")) {
            LinkedList<OrdinePrint> ord = caricaUtenti.aggiornaStato(Integer.parseInt(request.getParameter("idOrdine")),
                    request.getParameter("stato"));
            request.getSession().setAttribute("ordini_" + request.getParameter("user"), ord);
            innerPage = "amministratore/OrdiniUtente.jsp";
            changed = true;
        }
        

        if (pageParameter.equals("nonEvasi")) {
            String codiceOrd = request.getParameter("idOrdine");
            if (codiceOrd != null) {
                LinkedList<OrdinePrint> ord = caricaUtenti.aggiornaStato(Integer.parseInt(request.getParameter("idOrdine")),
                        request.getParameter("stato"));
                request.getSession().setAttribute("ordini_" + request.getParameter("user"), ord);

                request.getSession().setAttribute("nonEvasi",
                        caricaUtenti.getOrdiniNonEvasi(new Integer(codiceOrd)));
            } else {
                request.getSession().setAttribute("nonEvasi",
                        caricaUtenti.getOrdiniNonEvasi(-1));
            }
            innerPage = "amministratore/VisualizzaNonEvasi.jsp";
            changed = true;
        }

        if (pageParameter.equals("nav")) {
            innerPage = "CaricaProdottiServlet?cat=" + request.getParameter("categoria");
            changed = true;
        }
        if (pageParameter.equals("signIn")) {
            innerPage = "cliente/SignIn.jsp";
            changed = true;
        }
        if (pageParameter.equals("iscrizioneOk")) {
            innerPage = "cliente/IscrizioneOK.jsp";
            changed = true;
        }
        if (pageParameter.equals("carrello")) {
            innerPage = "cliente/VisualizzaCarrello.jsp";
            changed = true;
        }
        if (pageParameter.equals("admin")) {
            innerPage = "amministratore/PaginaAmministratore.jsp?";
            changed = true;
        }
        if (!pageParameter.equals("") && pageParameter.length() >= 6) {
            if (pageParameter.substring(0, 6).equals("errore")) {
                innerPage = "cliente/Errore.jsp?" + pageParameter.substring(6);
                changed = true;
            }

            if (!pageParameter.equals("") && pageParameter.length() >= 7) {
                if (pageParameter.substring(0, 7).equals("datiDim")) {
                    innerPage = "cliente/DatiDimenticati.jsp";
                    changed = true;
                }
                if (pageParameter.substring(0, 7).equals("insertP")) {
                    innerPage = "amministratore/InserisciProdotto.jsp?" + pageParameter.substring(7);
                    changed = true;
                }
            }
        }
        if (pageParameter.equals("addProd")) {
            innerPage = "amministratore/InserisciProdotto.jsp";
            String id = request.getParameter("idVid");
            if (id != null) {
                VideogiocoPrint p = trovaVideogioco.findById(id);
                request.setAttribute("videogioco", p);
            }
            changed = true;
        }

        if (pageParameter.equals("ShowSingleP")) {
            String id = request.getParameter("idVideogioco");
            VideogiocoPrint p = trovaVideogioco.findById(id);
            request.getSession().setAttribute("vid_" + Integer.parseInt(id), p);
            innerPage = "amministratore/VisualizzaProdotto.jsp";
            changed = true;
        }

        if (pageParameter.equals("passRecOk")) {
            innerPage = "cliente/PassRecuperata.jsp";
            changed = true;
        }

        if (pageParameter.equals("addCat")) {
            String update = request.getParameter("updateCat");
            if (update != null) {
                innerPage = "amministratore/InserisciCategoria.jsp?update=" + update;
            } else {
                innerPage = "amministratore/InserisciCategoria.jsp";
            }
            changed = true;
        }
        if (pageParameter.equals("showCat")) {
            String categoria = request.getParameter("cat");
            if (categoria != null) {
                int numElementi = generaListaP.prodottiCatDim(categoria);
                double dimPagina = 7;
                double nPagine = Math.floor(numElementi / dimPagina);
                int paginaCorrente = 1;
                try {
                    paginaCorrente = Integer.parseInt(request.getParameter("rp"));
                } catch (Exception e) {
                }
                request.setAttribute("nPagine", (int) nPagine);
                request.setAttribute("prodotti_" + request.getParameter("cat") + "#rp" + paginaCorrente,
                        generaListaP.elencoProdottiCat(request.getParameter("cat"), (int) dimPagina, paginaCorrente));
                String azione = request.getParameter("azione");
                if (azione != null && azione.equals("aggiungi")) {
                    String id = request.getParameter("idVid");
                    carrello.addVideogioco(Integer.parseInt(id));
                    request.getSession().setAttribute("vid_" + id, trovaVideogioco.findById(id));
                }
                innerPage = "amministratore/ProdottiPerCategoria.jsp";
                changed = true;
            } else {
                String azione = request.getParameter("azione");
                if (azione != null && azione.equals("aggiungi")) {
                    String id = request.getParameter("idVid");
                    carrello.addVideogioco(Integer.parseInt(id));
                    request.getSession().setAttribute("vid_" + id, trovaVideogioco.findById(id));
                }

            }
        }

        if (pageParameter.equals("modCarr")) {
            request.getSession().setAttribute("carrello", carrello);
            String azione = request.getParameter("azione");
            if (azione != null && azione.equals("aggiungi")) {
                String id = request.getParameter("idVid");
                int qta = Integer.parseInt(request.getParameter("qta"));
                carrello.modifyQta(Integer.parseInt(id), qta);
                request.getSession().setAttribute("vid_" + id, trovaVideogioco.findById(id));
            }
            if (azione != null && azione.equals("compra")) {
                innerPage = "cliente/ConfermaOrdine.jsp";
            } else if (azione != null && azione.equals("invia")) {
                innerPage = "cliente/InviaOrdine.jsp";
                
            } else if (azione != null && azione.equals("svuota")) {
                carrello.clear();
                innerPage = "cliente/VisualizzaCarrello.jsp";
            }
            changed = true;
        }

        if (pageParameter.equals("backOrdine")) {
            innerPage = innerPage = "cliente/InviaOrdine.jsp";
            changed = true;
        }

        if (pageParameter.equals("ordineOk")) {
            innerPage = "cliente/OrdineOk.jsp";
            changed = true;
        }
        if (pageParameter.equals("ordineNo")) {
            innerPage = "cliente/Errore.jsp?cause=L'ordine non è stato accettato";
            changed = true;
        }
        if (pageParameter.equals("noUserLogin")) {
            innerPage = "cliente/RegistrazioneLogin.jsp";
            changed = true;
        }
        if (pageParameter.equals("showAlpha")) {

            int numElementi = generaListaP.prodottiAlphaDim(request.getParameter("alpha"));
            double dimPagina = 7;
            double nPagine = Math.floor(numElementi / dimPagina);
            int paginaCorrente = 1;
            try {
                paginaCorrente = Integer.parseInt(request.getParameter("rp"));
            } catch (Exception e) {
            }
            request.setAttribute("nPagine", (int) nPagine);
            request.setAttribute("prodotti_" + request.getParameter("alpha") + "#rp" + paginaCorrente,
                    generaListaP.elencoProdottiAlpha(request.getParameter("alpha"), (int) dimPagina, paginaCorrente));
            String azione = request.getParameter("azione");
            if (azione != null && azione.equals("aggiungi")) {
                String id = request.getParameter("idVid");
                carrello.addVideogioco(Integer.parseInt(id));
                request.getSession().setAttribute("vid_" + id, trovaVideogioco.findById(id));
                innerPage = "amministratore/ProdottiAlpha.jsp?rp="+request.getParameter("rp");
                changed = true;
            } else {
                innerPage = "amministratore/ProdottiAlpha.jsp?rp="+request.getParameter("rp");
                changed = true;
            }
        }
        if (pageParameter.equals("showAllP")) {
            String update = request.getParameter("update");
            if (update != null) {
                request.removeAttribute("prodotti#rp" + request.getParameter("rp"));
            }
            int numElementi = generaListaP.elencoCompletoDim();
            double dimPagina = 8;
            double nPagine = Math.floor(numElementi / dimPagina);
            int paginaCorrente = 1;
            try {
                paginaCorrente = Integer.parseInt(request.getParameter("rp"));
            } catch (Exception e) {
            }
            request.setAttribute("nPagine", (int) nPagine);
            request.setAttribute("prodotti#rp" + paginaCorrente, generaListaP.elencoCompleto((int) dimPagina, paginaCorrente));
            innerPage = "amministratore/ElencoProdotti.jsp";
            changed = true;
        }
        if (pageParameter.equals("homeclient") && request.getParameter("user") != null) {
            if (request.getParameter("account").equals("ok")) {
                innerPage = "cliente/HomeCliente.jsp?user=" + request.getParameter("user") + "&account=" + request.getParameter("account");
                changed = true;
            }
        }

        if (pageParameter.equals("storicoOrdini")) {
            String cfUtente = request.getParameter("utente");
            LinkedList<OrdinePrint> ord = caricaUtenti.getOrdiniUtente(cfUtente);
            request.getSession().setAttribute("ordini_" + cfUtente, ord);
            innerPage = "cliente/MieiOrdini.jsp";
            changed = true;
        }

        if (pageParameter.equals("ricerca")) {
            innerPage = "cliente/RisultatiRicerca.jsp";
            changed = true;
        }

        if (pageParameter.equals("alterCat") || request.getParameter("eliminaCat") != null) {
            innerPage = "amministratore/VisualizzaCategorie.jsp";
            changed = true;
        }

        if (!changed || innerPage == null) {
            innerPage = "Sfondo.jsp";
        }
        request.setAttribute("innerPage", innerPage);
        rd.forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
