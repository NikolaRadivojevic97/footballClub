/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeli;

import domen.Igrac;
import domen.Takmicenje;
import domen.Utakmica;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import komunikacija.KomunikacijaSaServerom;
import konstante.Operacije;
import kontroler.GUIKontroler;
import transfer.KlijentskiZahtev;
import transfer.ServerskiOdgovor;

/**
 *
 * @author nikol
 */
public class IgracTableModel extends AbstractTableModel {

    ArrayList<Igrac> lista;
    String[] kolone = new String[]{"ID", "Ime", "Prezime", "Datum rodjenja", "Adresa", "Mejl", "broj dresa", "pozicija"};

    public IgracTableModel() {
        lista = new ArrayList<>();
    }

    public IgracTableModel(ArrayList<Igrac> lista) {
        this.lista = lista;
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return 8;
    }

    @Override
    public Object getValueAt(int red, int kolona) {
        Igrac i = lista.get(red);
        switch (kolona) {
            case 0:
                return i.getIgracId();
            case 1:
                return i.getIme();
            case 2:
                return i.getPrezime();
            case 3:
                String datum = null;
                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                datum = sdf.format(i.getDatumRodjenja());
                return datum;
            case 4:
                return i.getAdresa();
            case 5:
                return i.getMejl();
            case 6:
                return i.getBrojDresa();
            case 7:
                return i.getPozicija().getNaziv();

            default:
                return "n/a";
        }
    }

    @Override
    public String getColumnName(int kolona) {
        return kolone[kolona];
    }

    public void dodajIgraca(Igrac i) {
//        if(lista.contains(i)){
//            return;
//        }
        for(Igrac i1:lista){
            if(i1.getIgracId()==i.getIgracId())
                return;
        }
        lista.add(i);
        fireTableDataChanged();
    }

    public void obrisiIgraca(Igrac i) {
        lista.remove(i);
        fireTableDataChanged();

    }

    public void obrisiListu() {
        lista.clear();
    }

    public ArrayList<Igrac> getLista() {
        return lista;
    }

    public Igrac vratiElement(int red) {
        if (red == -1) {
            return null;
        }
        return lista.get(red);
    }
}
