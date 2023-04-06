/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session_beans;

import entities.Videogioco;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import utility.VideogiocoPrint;

/**
 *
 * @author Giovanni
 */
@Stateless
public class ProdottiCatBean implements ProdottiCatBeanLocal {

    @PersistenceContext(unitName = "VE_web_app-ejbPU")
    private EntityManager manager;

    @Override
    public LinkedList<VideogiocoPrint> elencoProdottiCat(String categoria, int dimPagina, int pagina) {
        Query trovaProdotti = manager.createNamedQuery("Videogioco.findAll");
        Collection<Videogioco> prodotti = null;
        try {
            prodotti = trovaProdotti.getResultList();
            if (prodotti.isEmpty()) {
                return null;
            }
        } catch (Exception e) {
            return null;
        }

        LinkedList<VideogiocoPrint> result = new LinkedList<>();
        Iterator<Videogioco> it = prodotti.iterator();
        int indiceStart = dimPagina * (pagina - 1);
        int indiceEnd = indiceStart + dimPagina;
        int indice = 0;
        while (it.hasNext()) {
            Videogioco v = it.next();
            String cat = v.getCategoria().getNome();
            if (cat.equals(categoria)) {
                if (indice >= indiceStart && indice < indiceEnd) {
                    String nome, id, descrizione, percorso, qtaDisp, prezzo;
                    nome = v.getNome();
                    id = v.getId().toString();
                    descrizione = v.getDescrizione();
                    percorso = v.getPercorso();
                    prezzo = v.getPrezzoVendita().toString();
                    qtaDisp = v.getQtaDisp().toString();
                    VideogiocoPrint vv = new VideogiocoPrint(nome, id, descrizione,
                            percorso, qtaDisp, prezzo, categoria);
                    result.add(vv);
                }
                indice++;
            }

        }
        return result;

    }

    @Override
    public LinkedList<VideogiocoPrint> elencoProdottiAlpha(String alpha, int dimPagina, int pagina) {
        Query trovaProdotti = manager.createNamedQuery("Videogioco.findAll");
        Collection<Videogioco> prodotti = null;
        try {
            prodotti = trovaProdotti.getResultList();
            if (prodotti.isEmpty()) {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
        LinkedList<VideogiocoPrint> result = new LinkedList<>();
        Iterator<Videogioco> it = prodotti.iterator();
        int indiceStart = dimPagina * (pagina - 1);
        int indiceEnd = indiceStart + dimPagina;
        int indice = 0;
        while (it.hasNext()) {
            Videogioco v = it.next();
            String nomeU = v.getNome().toUpperCase();
            if (nomeU.charAt(0) == alpha.charAt(0)) {
                if (indice >= indiceStart && indice < indiceEnd) {
                    String nome, id, descrizione, percorso, qtaDisp, prezzo, categoria;
                    nome = v.getNome();
                    id = v.getId().toString();
                    descrizione = v.getDescrizione();
                    percorso = v.getPercorso();
                    prezzo = v.getPrezzoVendita().toString();
                    qtaDisp = v.getQtaDisp().toString();
                    categoria = v.getCategoria().getNome();
                    VideogiocoPrint vv = new VideogiocoPrint(nome, id, descrizione,
                            percorso, qtaDisp, prezzo, categoria);
                    result.add(vv);
                }
                indice++;
            }
        }

        return result;
    }

    @Override
    public LinkedList<VideogiocoPrint> elencoCompleto(int dimPagina, int pagina) {

        Query trovaProdotti = manager.createNamedQuery("Videogioco.findAll");
        Collection<Videogioco> prodotti = null;
        try {
            prodotti = trovaProdotti.setMaxResults(dimPagina).setFirstResult(dimPagina*(pagina-1)).getResultList();
            if (prodotti.isEmpty()) {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
        LinkedList<VideogiocoPrint> risultato = new LinkedList<>();
        Iterator<Videogioco> it = prodotti.iterator();
        while (it.hasNext()) {
           
                Videogioco v = it.next();
                manager.refresh(v);
                String nome, id, descrizione, percorso, qtaDisp, prezzo, categoria;
                nome = v.getNome();
                id = v.getId().toString();
                descrizione = v.getDescrizione();
                percorso = v.getPercorso();
                prezzo = v.getPrezzoVendita().toString();
                qtaDisp = v.getQtaDisp().toString();
                categoria = v.getCategoria().getNome();
                VideogiocoPrint vv = new VideogiocoPrint(nome, id, descrizione,
                        percorso, qtaDisp, prezzo, categoria);
                risultato.add(vv);
            
        }
        return risultato;
    }

    @Override
    public int prodottiCatDim(String categoria) {
        Query q = manager.createNamedQuery("Videogioco.findAll");
        Collection<Videogioco> tutti = q.getResultList();
        int dim = 0;
        for (Videogioco v : tutti) {
            if (v.getCategoria().getNome().equals(categoria)) {
                dim++;
            }
        }
        return dim;
    }

    @Override
    public int prodottiAlphaDim(String alpha) {
        Query q = manager.createNamedQuery("Videogioco.findAll");
        Collection<Videogioco> tutti = q.getResultList();
        int dim = 0;
        for (Videogioco v : tutti) {
            String nomeU = v.getNome().toUpperCase();
            if (nomeU.charAt(0) == alpha.charAt(0)) {
                dim++;
            }
        }

        return dim;
    }

    @Override
    public int elencoCompletoDim() {
        Query q = manager.createNamedQuery("Videogioco.findAll");
        Collection<Videogioco> tutti = q.getResultList();
        return tutti.size();
    }
}
