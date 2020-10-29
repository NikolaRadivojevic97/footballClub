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
public class Korisnik implements IGeneralObjectG {

    private int korisnikId;
    private String ime;
    private String korisnickoIme;
    private String lozinka;

    public Korisnik() {
    }

    public Korisnik(int korisnikId, String ime, String korisnickoIme, String lozinka) {
        this.korisnikId = korisnikId;
        this.ime = ime;
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
    }

    public int getKorisnikId() {
        return korisnikId;
    }

    public void setKorisnikId(int korisnikId) {
        this.korisnikId = korisnikId;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    @Override
    public String getTableNameG() {
        return "korisnik";
    }

    @Override
    public int getAttNum() {
        return 4;
    }

    @Override
    public void setGeneralObject(IGeneralObjectG obj) {
        obj = new Korisnik();
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
    public String getCriteriaRowName2() {
        return "";
    }

    @Override
    public String getCriteriaValue2() {
        return "";
    }
}
