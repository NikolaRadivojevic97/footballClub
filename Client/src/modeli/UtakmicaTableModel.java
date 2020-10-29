/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeli;

import domen.Igrac;
import domen.Utakmica;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author nikol
 */
public class UtakmicaTableModel extends AbstractTableModel {

    ArrayList<Utakmica> lista;
    String[] kolone = new String[]{"ID", "broj datih golova", "broj primljenih golova", "Datum odigravanja", "broj bodova", "Takmicenje", "Protivnicki klub"};

    public UtakmicaTableModel() {
        lista = new ArrayList<>();
    }

    public UtakmicaTableModel(ArrayList<Utakmica> lista) {
        this.lista = lista;
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public Object getValueAt(int red, int kolona) {
        Utakmica i = lista.get(red);
        switch (kolona) {
            case 0:
                return i.getUtakmicaId();
            case 1:
                return i.getBrojGolova();
            case 2:
                return i.getBrojGolovaGost();
            case 3:
                String datum = null;
                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                datum = sdf.format(i.getDatumOdigravanja());
                return datum;
            case 4:
                return i.getBrojBodova();
            case 5:
                return i.getTakmicenje().getNaziv();
            case 6:
                return i.getKlub().getNaziv();

            default:
                return "n/a";
        }
    }

    @Override
    public String getColumnName(int kolona) {
        return kolone[kolona];
    }

    public void dodajIgraca(Utakmica i) {
        lista.add(i);
    }

    public void obrisiListu() {
        lista.clear();
    }

    public ArrayList<Utakmica> getLista() {
        return lista;
    }

    public Utakmica vratiElement(int red) {
        if (red == -1) {
            return null;
        }
        return lista.get(red);
    }
}