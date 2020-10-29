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
public class Igrac_Utakmica implements IGeneralObject, IGeneralObjectG, IGeneralObjectD {

    private int igracId;
    private int utakmicaId;

    public Igrac_Utakmica(int igracId, int utakmicaId) {
        this.igracId = igracId;
        this.utakmicaId = utakmicaId;
    }

    public Igrac_Utakmica() {
    }

    public int getIgracId() {
        return igracId;
    }

    public void setIgracId(int igracId) {
        this.igracId = igracId;
    }

    public int getUtakmicaId() {
        return utakmicaId;
    }

    public void setUtakmicaId(int utakmicaId) {
        this.utakmicaId = utakmicaId;
    }

    @Override
    public String getTableName() {
        return "igrac_utakmica";
    }

    @Override
    public String getColumnNamesForInsert() {
        return "igrac_id, utakmica_id";
    }

    @Override
    public String getInsertValues() {
        StringBuilder sb = new StringBuilder();
        sb.append(igracId).append(",")
                .append(utakmicaId);
        return sb.toString();
    }

    @Override
    public void setId(int id) {
        this.igracId = id;
    }

    @Override
    public String getTableNameG() {
        return "igrac_utakmica";
    }

    @Override
    public int getAttNum() {
        return 2;
    }

    @Override
    public void setGeneralObject(IGeneralObjectG obj) {
        obj = new Igrac_Utakmica();
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
        return "utakmica_id";
    }

    @Override
    public String getId() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.utakmicaId);
        return sb.toString();
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
    public String getCriteriaRowName2() {
        return "";
    }

    @Override
    public String getCriteriaValue2() {
        return "";
    }

}
