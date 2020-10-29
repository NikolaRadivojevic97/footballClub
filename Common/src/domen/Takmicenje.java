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
public class Takmicenje implements IGeneralObjectG {

    private int takmicenjeId;
    private String naziv;

    public Takmicenje() {
    }

    public Takmicenje(int takmicenjeId, String naziv) {
        this.takmicenjeId = takmicenjeId;
        this.naziv = naziv;
    }

    public int getTakmicenjeId() {
        return takmicenjeId;
    }

    public void setTakmicenjeId(int takmicenjeId) {
        this.takmicenjeId = takmicenjeId;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @Override
    public String toString() {
        return this.naziv;
    }

    @Override
    public String getTableNameG() {
        return "takmicenje";
    }

    @Override
    public int getAttNum() {
        return 2;
    }

    @Override
    public void setGeneralObject(IGeneralObjectG obj) {
        obj = new Takmicenje();
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
