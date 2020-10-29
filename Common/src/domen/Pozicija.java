/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

/**
 *
 * @author nikol
 */
public class Pozicija implements IGeneralObjectG {

    private int pozicijaId;
    private String naziv;
    private String skraceniNaziv;

    public Pozicija(int pozicijaId, String naziv, String skraceniNaziv) {
        this.pozicijaId = pozicijaId;
        this.naziv = naziv;
        this.skraceniNaziv = skraceniNaziv;
    }

    public Pozicija() {
    }

    public int getPozicijaId() {
        return pozicijaId;
    }

    public void setPozicijaId(int pozicijaId) {
        this.pozicijaId = pozicijaId;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getSkraceniNaziv() {
        return skraceniNaziv;
    }

    public void setSkraceniNaziv(String skraceniNaziv) {
        this.skraceniNaziv = skraceniNaziv;
    }

    @Override
    public String getTableNameG() {
        return "pozicija";
    }

    @Override
    public int getAttNum() {
        return 3;
    }

    @Override
    public void setGeneralObject(IGeneralObjectG obj) {
        obj = new Pozicija();
    }

    @Override
    public String getCriteriaRowName() {
        return "";
    }

    @Override
    public String getCriteriaValue() {
        return "";
    }

    @Override
    public String getIdRowName() {
        return "";
    }

    @Override
    public String getId() {
        return "";
    }

    @Override
    public String toString() {
        return this.naziv;
    }

    @Override
    public String getCriteriaRowName2() {
        return "";
    }

    @Override
    public String getCriteriaValue2() {
        return "";
    }
}
