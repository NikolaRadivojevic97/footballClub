/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.util.Date;

/**
 *
 * @author nikol
 */
public class Igrac implements IGeneralObjectG, IGeneralObjectD, IGeneralObject, IGeneralObjectU {

    private int igracId;
    private String ime;
    private String prezime;
    private Date datumRodjenja;
    private String adresa;
    private String mejl;
    private int brojDresa;
    private Pozicija pozicija;

    public Igrac() {
    }

    public Igrac(int igracId, String ime, String prezime, Date datumRodjenja, String adresa, String mejl, int brojDresa, Pozicija pozicija) {
        this.igracId = igracId;
        this.ime = ime;
        this.prezime = prezime;
        this.datumRodjenja = datumRodjenja;
        this.adresa = adresa;
        this.mejl = mejl;
        this.brojDresa = brojDresa;
        this.pozicija = pozicija;
    }

    public int getIgracId() {
        return igracId;
    }

    public void setIgracId(int igracId) {
        this.igracId = igracId;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public Date getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(Date datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getMejl() {
        return mejl;
    }

    public void setMejl(String mejl) {
        this.mejl = mejl;
    }

    public int getBrojDresa() {
        return brojDresa;
    }

    public void setBrojDresa(int brojDresa) {
        this.brojDresa = brojDresa;
    }

    public Pozicija getPozicija() {
        return pozicija;
    }

    public void setPozicija(Pozicija pozicija) {
        this.pozicija = pozicija;
    }

    @Override
    public String getTableNameG() {
        return "Igrac";
    }

    @Override
    public int getAttNum() {
        return 8;
    }

    @Override
    public void setGeneralObject(IGeneralObjectG obj) {
        obj = new Igrac();
    }

    @Override
    public String getTableName() {
        return "igrac";
    }

    @Override
    public String getColumnNameForDelete() {
        return "igrac_id";
    }

    @Override
    public String getDeletedValue() {
        StringBuilder sb = new StringBuilder();
        sb.append(igracId);
        return sb.toString();
    }

    @Override
    public String getColumnNamesForInsert() {
        return "ime,prezime,datum_rodjenja,adresa,mejl,broj_dresa,pozicija_id";
    }

    @Override
    public String getInsertValues() {
        StringBuilder sb = new StringBuilder();
        sb.append("'").append(ime).append("',")
                .append("'").append(prezime).append("',")
                .append("'").append(new java.sql.Date(this.datumRodjenja.getTime())).append("',")
                .append("'").append(adresa).append("',")
                .append("'").append(mejl).append("',")
                .append(brojDresa).append(",")
                .append(pozicija.getPozicijaId());
        return sb.toString();
    }

    @Override
    public void setId(int id) {
        this.igracId = id;
    }

    @Override
    public String getColumnNameForUpdate() {
        return "ime,prezime,datum_rodjenja,adresa,mejl,broj_dresa,pozicija_id";
    }

    @Override
    public String getUpdateValue() {
        StringBuilder sb = new StringBuilder();
        sb.append("'").append(ime).append("',")
                .append("'").append(prezime).append("',")
                .append("'").append(new java.sql.Date(this.datumRodjenja.getTime())).append("',")
                .append("'").append(adresa).append("',")
                .append("'").append(mejl).append("',")
                .append(brojDresa).append(",")
                .append(pozicija.getPozicijaId());
        return sb.toString();
    }

    @Override
    public String getColumnNameForSet() {
        return "igrac_id";
    }

    @Override
    public String getSetValue() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.igracId);
        return sb.toString();
    }

    @Override
    public String getCriteriaRowName() {
        return "ime";
    }

    @Override
    public String getCriteriaValue() {
        StringBuilder sb = new StringBuilder();
        sb.append(" LIKE '%").append(this.ime).append("%'");
        return sb.toString();
    }

    @Override
    public String getIdRowName() {
        return "igrac_id";
    }

    @Override
    public String getId() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.igracId);
        return sb.toString();
    }

    @Override
    public String getCriteriaRowName2() {
        return "prezime";
    }

    @Override
    public String getCriteriaValue2() {
        StringBuilder sb = new StringBuilder();
        sb.append(" LIKE '%").append(this.prezime).append("%'");
        return sb.toString();
    }

}
