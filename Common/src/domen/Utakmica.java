/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author nikol
 */
public class Utakmica implements IGeneralObjectG, IGeneralObjectD, IGeneralObject, IGeneralObjectU {

    private int utakmicaId;
    private int brojGolova;
    private int brojGolovaGost;
    private Date datumOdigravanja;
    private int brojBodova;
    private Klub klub;
    private Takmicenje takmicenje;
    private ArrayList<Igrac> igraci;

    public Utakmica() {
        igraci = new ArrayList<Igrac>();
    }

    public Utakmica(int utakmicaId, int brojGolova, int brojGolovaGost, Date datumOdigravanja, int brojBodova, Klub klub, Takmicenje takmicenje, ArrayList<Igrac> igraci) {
        this.utakmicaId = utakmicaId;
        this.brojGolova = brojGolova;
        this.brojGolovaGost = brojGolovaGost;
        this.datumOdigravanja = datumOdigravanja;
        this.brojBodova = brojBodova;
        this.klub = klub;
        this.takmicenje = takmicenje;
        this.igraci = igraci;
    }

    public int getUtakmicaId() {
        return utakmicaId;
    }

    public void setUtakmicaId(int utakmicaId) {
        this.utakmicaId = utakmicaId;
    }

    public int getBrojGolova() {
        return brojGolova;
    }

    public void setBrojGolova(int brojGolova) {
        this.brojGolova = brojGolova;
    }

    public int getBrojGolovaGost() {
        return brojGolovaGost;
    }

    public void setBrojGolovaGost(int brojGolovaGost) {
        this.brojGolovaGost = brojGolovaGost;
    }

    public Date getDatumOdigravanja() {
        return datumOdigravanja;
    }

    public void setDatumOdigravanja(Date datumOdigravanja) {
        this.datumOdigravanja = datumOdigravanja;
    }

    public int getBrojBodova() {
        return brojBodova;
    }

    public void setBrojBodova(int brojBodova) {
        this.brojBodova = brojBodova;
    }

    public Klub getKlub() {
        return klub;
    }

    public void setKlub(Klub klub) {
        this.klub = klub;
    }

    public Takmicenje getTakmicenje() {
        return takmicenje;
    }

    public void setTakmicenje(Takmicenje takmicenje) {
        this.takmicenje = takmicenje;
    }

    public ArrayList<Igrac> getIgraci() {
        return igraci;
    }

    public void setIgraci(ArrayList<Igrac> igraci) {
        this.igraci = igraci;
    }

    @Override
    public String getTableNameG() {
        return "utakmica u JOIN klub k on u.klub_id=k.klub_id";
    }

    @Override
    public int getAttNum() {
        return 7;
    }

    @Override
    public void setGeneralObject(IGeneralObjectG obj) {
        obj = new Utakmica();
    }

    @Override
    public String getTableName() {
        return "Utakmica";
    }

    @Override
    public String getColumnNameForDelete() {
        return "utakmica_id";
    }

    @Override
    public String getDeletedValue() {
        StringBuilder sb = new StringBuilder();
        sb.append(utakmicaId);
        return sb.toString();
    }

    @Override
    public String getColumnNamesForInsert() {
        return "broj_golova,broj_golova_protivnik,datum_odigravanja,broj_bodova, takmicenje_id, klub_id";
    }

    @Override
    public String getInsertValues() {
        StringBuilder sb = new StringBuilder();
        sb.append(brojGolova).append(",")
                .append(brojGolovaGost).append(",")
                .append("'").append(new java.sql.Date(this.datumOdigravanja.getTime())).append("',")
                .append(brojBodova).append(",")
                .append(takmicenje.getTakmicenjeId()).append(",")
                .append(klub.getKlubId());
        return sb.toString();
    }

    @Override
    public void setId(int id) {
        this.utakmicaId = id;
    }

    @Override
    public String getColumnNameForUpdate() {
        return "broj_golova,broj_golova_protivnik,datum_odigravanja,broj_bodova,takmicenje_id,klub_id";
    }

    @Override
    public String getUpdateValue() {
        StringBuilder sb = new StringBuilder();
        sb.append(brojGolova).append(",")
                .append(brojGolovaGost).append(",")
                .append("'").append(new java.sql.Date(this.datumOdigravanja.getTime())).append("',")
                .append(brojBodova).append(",")
                .append(takmicenje.getTakmicenjeId()).append(",")
                .append(klub.getKlubId());
        return sb.toString();
    }

    @Override
    public String getColumnNameForSet() {
        return "utakmica_id";
    }

    @Override
    public String getSetValue() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.utakmicaId);
        return sb.toString();
    }

    @Override
    public String getCriteriaRowName() {
        return "datum_odigravanja";
    }

    @Override
    public String getCriteriaValue() {
        if(this.datumOdigravanja!=null){
        StringBuilder sb = new StringBuilder();
        sb.append(" = '").append(new java.sql.Date(this.datumOdigravanja.getTime())).append("'");

        return sb.toString();
        }else{
            return " = ''";
        }
    }

    @Override
    public String getIdRowName() {
        return "utakmica_id";
    }

    @Override
    public String getId() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.utakmicaId);
        return sb.toString();
    }

    @Override
    public String getCriteriaRowName2() {
        return "k.naziv";
    }

    @Override
    public String getCriteriaValue2() {
        StringBuilder sb = new StringBuilder();
        sb.append(" LIKE '%").append(this.klub.getNaziv()).append("%'");

        return sb.toString();
    }
}
