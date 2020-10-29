/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transfer;

import java.io.Serializable;

/**
 *
 * @author Windows HD
 */
public class ServerskiOdgovor implements Serializable{
    private String tekst;
    private Object odgovor;

    public ServerskiOdgovor() {
    }

    public ServerskiOdgovor(String tekst, Object odgovor) {
        this.tekst = tekst;
        this.odgovor = odgovor;
    }

    public Object getOdgovor() {
        return odgovor;
    }

    public void setOdgovor(Object odgovor) {
        this.odgovor = odgovor;
    }

    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }
    
}
