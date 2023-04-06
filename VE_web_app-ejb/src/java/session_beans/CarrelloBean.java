/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session_beans;

import java.util.HashMap;
import javax.ejb.Stateful;

/**
 *
 * @author giovanni
 */
@Stateful
public class CarrelloBean implements CarrelloBeanLocal {

    private HashMap<Integer, Integer> elements;
    private int modPagamento = 0;

    public CarrelloBean() {
        elements = new HashMap<>();
    }

    @Override
    public void addVideogioco(int idV) {
        if (elements.containsKey(idV)) {
            int qta = elements.get(idV);
            qta++;
            elements.put(idV, qta);
        } else {
            elements.put(idV, 1);
        }
    }

    @Override
    public void removeVideogioco(int idV) {
        if (elements.containsKey(idV)) {
            elements.remove(idV);
        }
    }

    @Override
    public void modifyQta(int idV, int newQta) {
        if (newQta >= 0) {
            if (elements.containsKey(idV)) {
                if (newQta == 0) {
                    elements.remove(idV);
                } else {
                    elements.put(idV, newQta);
                }

            }
        }
    }

    @Override
    public HashMap<Integer, Integer> getContenuto() {
        return elements;
    }

    @Override
    public void clear() {
        elements = new HashMap<>();
    }

    @Override
    public void setModPagamento(int mod) {
        modPagamento = mod;
    }

    @Override
    public int getModPagamento() {
        return modPagamento;
    }

}
