/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

/**
 *
 * @author Antonio Giovanni
 */
public class VideogiocoPrint {
    
    private  String nome;
    private  String id;
    private String descrizione;
    private String percorso;
    private String qtaDisp;
    private String prezzo;
    private String categoria;
    public VideogiocoPrint(String nome, String id, String descrizione,
            String percorso,String qtaDisp,String prezzo,String categoria){
        this.nome = nome;
        this.id = id;
        this.descrizione = descrizione;
        this.percorso = percorso;
        this.qtaDisp = qtaDisp;
        this.prezzo = prezzo;
        this.categoria = categoria;
    }
    public VideogiocoPrint(){
        
    }
    public String getNome(){
        return nome;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
    public String getId(){
        return id;
    }
    public void setId(String id){
        this.id = id;
    }
    public String getDescrizione(){
        return descrizione;
    }
    public void setDescrizione(String descrizione){
        this.descrizione = descrizione;
    }
    public String getPercorso(){
        return percorso;
    }
    public void setPercorso(String percorso){
        this.percorso = percorso;
    }
    public String getQtaDisp(){
        return qtaDisp;
    }
    public void setQtaDisp(String qtaDisp){
        this.qtaDisp = qtaDisp;
    }
    public String getPrezzo(){
        return prezzo;
    }
    public void setPrezzo(String prezzo){
        this.prezzo = prezzo;
    }
    public String getCategoria(){
        return categoria;
    }
    public void setCategoria(String categoria){
        this.categoria = categoria;
    }
    
    
}
