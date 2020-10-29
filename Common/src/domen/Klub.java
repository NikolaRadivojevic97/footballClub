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
public class Klub implements IGeneralObjectG {

    private int klubId;
    private String naziv;

    public Klub() {
    }

    public Klub(int klubId, String naziv) {
        this.klubId = klubId;
        this.naziv = naziv;
    }

    public int getKlubId() {
        return klubId;
    }

    public void setKlubId(int klubId) {
        this.klubId = klubId;
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
        return "klub";
    }

    @Override
    public int getAttNum() {
        return 2;
    }

    @Override
    public void setGeneralObject(IGeneralObjectG obj) {
        obj = new Klub();
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
