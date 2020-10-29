/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroler;

import domen.Igrac;
import domen.Klub;
import domen.Korisnik;
import domen.Pozicija;
import domen.Takmicenje;
import domen.Utakmica;
import forme.DodajIgraca;
import forme.FormaDodajUtakmicu;
import forme.IzmeniIgraca;
import forme.FormaIgraci;
import forme.FormaMain;
import forme.FormaUtakmice;
import forme.IzmeniUtakmicu;
import forme.ObrisiIgraca;
import forme.ObrisiUtakmicu;
import forme.formPrijaviSe;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import konstante.Operacije;
import transfer.KlijentskiZahtev;
import transfer.ServerskiOdgovor;

/**
 *
 * @author nikol
 */
public class GUIKontroler {

    private static formPrijaviSe formaPrijaviSe;
    private static GUIKontroler instanca;
    FormaMain formMain;
    FormaIgraci formaIgraci;
    IzmeniIgraca formaIgrac;
    DodajIgraca dodajIgraca;
    ObrisiIgraca obrisiForma;
    Korisnik k;
    FormaUtakmice formaUtakmice;
    FormaDodajUtakmicu formaDodajUtakmicu;
    IzmeniUtakmicu formaIzmeniUtakmicu;
    ObrisiUtakmicu formaObrisiUtakmicu;

    private GUIKontroler() {

    }

    public static GUIKontroler getInstanca() {
        if (instanca == null) {
            instanca = new GUIKontroler();
        }
        return instanca;
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GUIKontroler.formaPrijaviSe = new formPrijaviSe();
                    GUIKontroler.formaPrijaviSe.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void obradiKorisnika(String korisnickoIme, String lozinka) {

        if (korisnickoIme.isEmpty() || lozinka.isEmpty()) {
            JOptionPane.showMessageDialog(formaPrijaviSe, "Sva polja moraju biti popunjena!", "", JOptionPane.ERROR_MESSAGE);
            return;
        }
        k = new Korisnik();
        k.setKorisnickoIme(korisnickoIme);
        k.setLozinka(lozinka);
        KlijentskiZahtev kz = new KlijentskiZahtev();
        kz.setOperacija(Operacije.OBRADI_KORISNIKA);
        kz.setParametar(k);
        komunikacija.KomunikacijaSaServerom.getInstanca().posaljiZahtev(kz);

        ServerskiOdgovor so = komunikacija.KomunikacijaSaServerom.getInstanca().primiOdgovor();
        Korisnik korisnik = (Korisnik) so.getOdgovor();
        if (korisnik != null) {
            JOptionPane.showMessageDialog(formaPrijaviSe, "Uspešno logovanje", "", JOptionPane.INFORMATION_MESSAGE);
            formaPrijaviSe.dispose();
            formMain = new FormaMain(korisnik);
            formMain.setLocationRelativeTo(formaPrijaviSe);
            formMain.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(formaPrijaviSe, "Pogresno korisnicko ime ili lozinka", "", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void pokreniIgrace() {
        KlijentskiZahtev kz = new KlijentskiZahtev();
        kz.setOperacija(Operacije.VRATI_IGRACA);
        komunikacija.KomunikacijaSaServerom.getInstanca().posaljiZahtev(kz);

        ServerskiOdgovor so = komunikacija.KomunikacijaSaServerom.getInstanca().primiOdgovor();
        ArrayList<Igrac> igraci = (ArrayList<Igrac>) so.getOdgovor();
        formaIgraci = new FormaIgraci(igraci);
        formaIgraci.setLocationRelativeTo(formMain);
        formaIgraci.setVisible(true);
        formMain.dispose();
    }

    public void izmeniIgraca(Igrac igrac) {
        if (igrac != null) {
            KlijentskiZahtev kz = new KlijentskiZahtev();
            kz.setOperacija(Operacije.VRATI_POZICIJE);
            komunikacija.KomunikacijaSaServerom.getInstanca().posaljiZahtev(kz);

            ServerskiOdgovor so = komunikacija.KomunikacijaSaServerom.getInstanca().primiOdgovor();
            ArrayList<Pozicija> pozicije = (ArrayList<Pozicija>) so.getOdgovor();
            formaIgrac = new IzmeniIgraca(igrac, pozicije);
            formaIgrac.setLocationRelativeTo(formMain);
            formaIgrac.setVisible(true);
            formaIgrac.setBackground(Color.red);
            //formaIgraci.dispose();
        } else {
            JOptionPane.showMessageDialog(formaPrijaviSe, "Niste izabrali igraca", "", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void zapamtiIzmenjenog(String text, String text0, String text1, String text2, String text3, String text4, Object selectedItem, String id) {
        if (text.isEmpty() || text0.isEmpty() || text1.isEmpty() || text2.isEmpty() || text3.isEmpty() || text4.isEmpty()) {
            JOptionPane.showMessageDialog(formaIgraci, "sva polja moraju biti popunjena", "", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        Date datum = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

        try {
            datum = sdf.parse(text1);
        } catch (ParseException ex) {
            Logger.getLogger(GUIKontroler.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(formaIgraci, "datum mora biti u formatu dd.mm.yyyy", "", JOptionPane.INFORMATION_MESSAGE);

        }
        try {
            Igrac i = new Igrac(Integer.parseInt(id), text, text0, datum, text2, text3, Integer.parseInt(text4), (Pozicija) selectedItem);
            KlijentskiZahtev kz = new KlijentskiZahtev();
            kz.setParametar(i);
            kz.setOperacija(Operacije.IZMENI_IGRACA);
            komunikacija.KomunikacijaSaServerom.getInstanca().posaljiZahtev(kz);

            ServerskiOdgovor so = komunikacija.KomunikacijaSaServerom.getInstanca().primiOdgovor();
            Igrac i1 = (Igrac) so.getOdgovor();
            if (i1.getIgracId() != 0) {
                JOptionPane.showMessageDialog(formaIgraci, "uspesno sacuvan igrac sa id" + i1.getIgracId(), "", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(formaIgraci, "sistem ne moze da izmeni igraca", "", JOptionPane.INFORMATION_MESSAGE);
            }
            //pokreniIgrace();
            KlijentskiZahtev kz1 = new KlijentskiZahtev();
            kz1.setOperacija(Operacije.VRATI_IGRACA);
            komunikacija.KomunikacijaSaServerom.getInstanca().posaljiZahtev(kz1);

            ServerskiOdgovor so1 = komunikacija.KomunikacijaSaServerom.getInstanca().primiOdgovor();
            ArrayList<Igrac> igraci = (ArrayList<Igrac>) so1.getOdgovor();
            formaIgraci.updateTable(igraci);
            formaIgrac.dispose();
        } catch (NumberFormatException | HeadlessException e) {
            Logger.getLogger(GUIKontroler.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(formaIgraci, "broj dresa mora biti broj", "", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    public void sacuvajIgraca(String ime, String prezime, String text1, String adresa, String mejl, String dres, Object selectedItem) {
        if (ime.isEmpty() || prezime.isEmpty() || text1.isEmpty() || adresa.isEmpty() || mejl.isEmpty()) {
            JOptionPane.showMessageDialog(formaIgraci, "sva polja moraju biti popunjena", "", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        Date datum = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

        try {
            datum = sdf.parse(text1);
        } catch (ParseException ex) {
            Logger.getLogger(GUIKontroler.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(formaIgraci, "datum mora biti u formatu dd.mm.yyyy", "", JOptionPane.INFORMATION_MESSAGE);
        }
        try {
            Igrac i = new Igrac(-1, ime, prezime, datum, adresa, mejl, Integer.parseInt(dres), (Pozicija) selectedItem);
            KlijentskiZahtev kz = new KlijentskiZahtev();
            kz.setParametar(i);
            kz.setOperacija(Operacije.KREIRAJ_IGRACA);
            komunikacija.KomunikacijaSaServerom.getInstanca().posaljiZahtev(kz);

            ServerskiOdgovor so = komunikacija.KomunikacijaSaServerom.getInstanca().primiOdgovor();
            Igrac i1 = (Igrac) so.getOdgovor();
            if (i1.getIgracId() != 0) {
                JOptionPane.showMessageDialog(formaIgraci, "uspesno sacuvan igrac sa id" + i1.getIgracId(), "", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(formaIgraci, "sistem ne moze da kreira igraca", "", JOptionPane.INFORMATION_MESSAGE);
            }
            //pokreniIgrace();
            KlijentskiZahtev kz1 = new KlijentskiZahtev();
            kz1.setOperacija(Operacije.VRATI_IGRACA);
            komunikacija.KomunikacijaSaServerom.getInstanca().posaljiZahtev(kz1);

            ServerskiOdgovor so1 = komunikacija.KomunikacijaSaServerom.getInstanca().primiOdgovor();
            ArrayList<Igrac> igraci = (ArrayList<Igrac>) so1.getOdgovor();
            formaIgraci.updateTable(igraci);
            dodajIgraca.dispose();
        } catch (NumberFormatException | HeadlessException e) {
            Logger.getLogger(GUIKontroler.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(formaIgraci, "broj dresa mora biti broj", "", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    public void prikaziDodaj() {
        KlijentskiZahtev kz = new KlijentskiZahtev();
        kz.setOperacija(Operacije.VRATI_POZICIJE);
        komunikacija.KomunikacijaSaServerom.getInstanca().posaljiZahtev(kz);

        ServerskiOdgovor so = komunikacija.KomunikacijaSaServerom.getInstanca().primiOdgovor();
        ArrayList<Pozicija> pozicije = (ArrayList<Pozicija>) so.getOdgovor();
        dodajIgraca = new DodajIgraca(pozicije);
        dodajIgraca.setLocationRelativeTo(formMain);
        dodajIgraca.setVisible(true);
        //formaIgraci.dispose();
    }

    public void obrisiIgraca(Igrac igrac) {
        KlijentskiZahtev kz = new KlijentskiZahtev();
        kz.setParametar(igrac);
        kz.setOperacija(Operacije.OBRISI_IGRACA);
        komunikacija.KomunikacijaSaServerom.getInstanca().posaljiZahtev(kz);

        ServerskiOdgovor so = komunikacija.KomunikacijaSaServerom.getInstanca().primiOdgovor();
        Igrac i1 = (Igrac) so.getOdgovor();
        if (i1.getIgracId() != 0) {
            JOptionPane.showMessageDialog(formaIgraci, "uspesno obrisan igrac sa id" + i1.getIgracId(), "", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(formaIgraci, "sistem ne moze da obrise igraca", "", JOptionPane.INFORMATION_MESSAGE);
        }

        //pokreniIgrace();
        KlijentskiZahtev kz1 = new KlijentskiZahtev();
        kz1.setOperacija(Operacije.VRATI_IGRACA);
        komunikacija.KomunikacijaSaServerom.getInstanca().posaljiZahtev(kz1);

        ServerskiOdgovor so1 = komunikacija.KomunikacijaSaServerom.getInstanca().primiOdgovor();
        ArrayList<Igrac> igraci = (ArrayList<Igrac>) so1.getOdgovor();
        formaIgraci.updateTable(igraci);
        obrisiForma.dispose();

    }

    public void otvoriObrisi(Igrac vratiElement) {
        if (vratiElement != null) {
            obrisiForma = new ObrisiIgraca(vratiElement);
            obrisiForma.setLocationRelativeTo(formMain);
            obrisiForma.setVisible(true);
            //formaIgraci.dispose();
        } else {
            JOptionPane.showMessageDialog(formaIgraci, "igrac nije pronadjen", "", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void pretraziIgrace(String text) {
        KlijentskiZahtev kz = new KlijentskiZahtev();
        kz.setParametar(text);
        if (text.isEmpty()) {
            //JOptionPane.showMessageDialog(formaIgraci, "unesite zeljeno ime u polje za pretragu", "", JOptionPane.INFORMATION_MESSAGE);
            kz.setOperacija(Operacije.VRATI_IGRACA);
        } else {
            kz.setOperacija(Operacije.PRETRAZI_IGRACE);
        }
        komunikacija.KomunikacijaSaServerom.getInstanca().posaljiZahtev(kz);

        ServerskiOdgovor so = komunikacija.KomunikacijaSaServerom.getInstanca().primiOdgovor();
        ArrayList<Igrac> igraci = (ArrayList<Igrac>) so.getOdgovor();
        if (igraci.isEmpty()) {
            JOptionPane.showMessageDialog(formaIgraci, "Sistem ne moze da nadje igraca po zadatoj vrednosti", "", JOptionPane.INFORMATION_MESSAGE);
        } else {
            formaIgraci.updateTable(igraci);
        }

    }

    public void zatvoriIgrace() {
        formaIgraci.dispose();
        formMain = new FormaMain(k);
        formMain.setLocationRelativeTo(formaPrijaviSe);
        formMain.setVisible(true);
    }

    public void pokreniUtakmice() {
        KlijentskiZahtev kz = new KlijentskiZahtev();
        kz.setOperacija(Operacije.VRATI_UTAKMICE);
        komunikacija.KomunikacijaSaServerom.getInstanca().posaljiZahtev(kz);

        ServerskiOdgovor so = komunikacija.KomunikacijaSaServerom.getInstanca().primiOdgovor();
        ArrayList<Utakmica> utakmice = (ArrayList<Utakmica>) so.getOdgovor();
        formaUtakmice = new FormaUtakmice(utakmice);
        formaUtakmice.setLocationRelativeTo(formMain);
        formaUtakmice.setVisible(true);
        formMain.dispose();
//        Utakmica u1=utakmice.get(utakmice.size()-1);
//        for(Igrac i: u1.getIgraci()){
//            System.out.println(i.getIme());
//        }
    }

    public void prikaziDodajUtakmice() {
        KlijentskiZahtev kz = new KlijentskiZahtev();
        kz.setOperacija(Operacije.VRATI_IGRACA);
        komunikacija.KomunikacijaSaServerom.getInstanca().posaljiZahtev(kz);

        ServerskiOdgovor so = komunikacija.KomunikacijaSaServerom.getInstanca().primiOdgovor();
        ArrayList<Igrac> igraci = (ArrayList<Igrac>) so.getOdgovor();
        kz.setOperacija(Operacije.VRATI_KLUBOVE);
        komunikacija.KomunikacijaSaServerom.getInstanca().posaljiZahtev(kz);

        ServerskiOdgovor so1 = komunikacija.KomunikacijaSaServerom.getInstanca().primiOdgovor();
        ArrayList<Klub> klubovi = (ArrayList<Klub>) so1.getOdgovor();

        kz.setOperacija(Operacije.VRATI_TAKMICENJE);
        komunikacija.KomunikacijaSaServerom.getInstanca().posaljiZahtev(kz);

        ServerskiOdgovor so2 = komunikacija.KomunikacijaSaServerom.getInstanca().primiOdgovor();
        ArrayList<Takmicenje> takmicenja = (ArrayList<Takmicenje>) so2.getOdgovor();

        formaDodajUtakmicu = new FormaDodajUtakmicu(igraci, klubovi, takmicenja);
        formaDodajUtakmicu.setLocationRelativeTo(formMain);
        formaDodajUtakmicu.setVisible(true);
    }

    public void zapamtiUtakmicu(Klub klub, Takmicenje takmicenje, String text, String text0, String text1, String text2, ArrayList<Igrac> lista) {
        if (text.isEmpty() || text0.isEmpty() || text1.isEmpty() || text2.isEmpty()) {
            JOptionPane.showMessageDialog(formaIgraci, "sva polja moraju biti popunjena", "", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
//        if (lista.size() != 11) {
//            JOptionPane.showMessageDialog(formaIgraci, "morate imati 11 igraca", "", JOptionPane.INFORMATION_MESSAGE);
//            return;
//        }
        Date datum = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

        try {
            datum = sdf.parse(text1);
        } catch (ParseException ex) {
            Logger.getLogger(GUIKontroler.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(formaIgraci, "datum mora biti u formatu dd.mm.yyyy", "", JOptionPane.INFORMATION_MESSAGE);

        }
        try {
            Utakmica u = new Utakmica(-1, Integer.parseInt(text), Integer.parseInt(text0), datum, Integer.parseInt(text2), klub, takmicenje, lista);
            KlijentskiZahtev kz = new KlijentskiZahtev();
            kz.setOperacija(Operacije.KREIRAJ_UTAKMICU);
            kz.setParametar(u);
            komunikacija.KomunikacijaSaServerom.getInstanca().posaljiZahtev(kz);

            ServerskiOdgovor so = komunikacija.KomunikacijaSaServerom.getInstanca().primiOdgovor();
            Utakmica u1 = (Utakmica) so.getOdgovor();
            if (u1.getUtakmicaId() != 0) {
                JOptionPane.showMessageDialog(formaIgraci, "uspesno zapamcenja utakmica sa id" + u1.getUtakmicaId(), "", JOptionPane.INFORMATION_MESSAGE);
                formaDodajUtakmicu.dispose();
                KlijentskiZahtev kz1 = new KlijentskiZahtev();
                kz1.setOperacija(Operacije.VRATI_UTAKMICE);
                komunikacija.KomunikacijaSaServerom.getInstanca().posaljiZahtev(kz1);

                ServerskiOdgovor so1 = komunikacija.KomunikacijaSaServerom.getInstanca().primiOdgovor();
                ArrayList<Utakmica> utakmica = (ArrayList<Utakmica>) so1.getOdgovor();
                formaUtakmice.updateTable(utakmica);

            } else {
                JOptionPane.showMessageDialog(formaIgraci, "sistem ne moze da zapamti utakmicu", "", JOptionPane.INFORMATION_MESSAGE);
                formaDodajUtakmicu.dispose();
            }
        } catch (NumberFormatException | HeadlessException e) {
            Logger.getLogger(GUIKontroler.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(formaIgraci, "broj dresa mora biti broj", "", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void izmeniUtakmicu(Utakmica vratiElement) {
        if (vratiElement != null) {
            KlijentskiZahtev kz = new KlijentskiZahtev();
            kz.setOperacija(Operacije.VRATI_IGRACA);
            komunikacija.KomunikacijaSaServerom.getInstanca().posaljiZahtev(kz);

            ServerskiOdgovor so = komunikacija.KomunikacijaSaServerom.getInstanca().primiOdgovor();
            ArrayList<Igrac> igraci = (ArrayList<Igrac>) so.getOdgovor();
            kz.setOperacija(Operacije.VRATI_KLUBOVE);
            komunikacija.KomunikacijaSaServerom.getInstanca().posaljiZahtev(kz);

            ServerskiOdgovor so1 = komunikacija.KomunikacijaSaServerom.getInstanca().primiOdgovor();
            ArrayList<Klub> klubovi = (ArrayList<Klub>) so1.getOdgovor();

            kz.setOperacija(Operacije.VRATI_TAKMICENJE);
            komunikacija.KomunikacijaSaServerom.getInstanca().posaljiZahtev(kz);

            ServerskiOdgovor so2 = komunikacija.KomunikacijaSaServerom.getInstanca().primiOdgovor();
            ArrayList<Takmicenje> takmicenja = (ArrayList<Takmicenje>) so2.getOdgovor();

            formaIzmeniUtakmicu = new IzmeniUtakmicu(igraci, klubovi, takmicenja, vratiElement);
            formaIzmeniUtakmicu.setLocationRelativeTo(formMain);
            formaIzmeniUtakmicu.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(formaPrijaviSe, "Utakmica nije pronadjena", "", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    public void zameniUtakmicu(String id, Klub klub, Takmicenje takmicenje, String text, String text0, String text1, String text2, ArrayList<Igrac> lista) {
        if (text.isEmpty() || text0.isEmpty() || text1.isEmpty() || text2.isEmpty()) {
            JOptionPane.showMessageDialog(formaIgraci, "sva polja moraju biti popunjena", "", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
//        if (lista.size() != 11) {
//            JOptionPane.showMessageDialog(formaIgraci, "morate imati 11 igraca", "", JOptionPane.INFORMATION_MESSAGE);
//            return;
//        }
        //dodati za golmana da mora da ima
        Date datum = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

        try {
            datum = sdf.parse(text1);
        } catch (ParseException ex) {
            Logger.getLogger(GUIKontroler.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(formaIgraci, "datum mora biti u formatu dd.mm.yyyy", "", JOptionPane.INFORMATION_MESSAGE);

        }
        try {
            Utakmica u = new Utakmica(Integer.parseInt(id), Integer.parseInt(text), Integer.parseInt(text0), datum, Integer.parseInt(text2), klub, takmicenje, lista);
            KlijentskiZahtev kz = new KlijentskiZahtev();
            kz.setOperacija(Operacije.IZMENI_UTAKMICU);
            kz.setParametar(u);
            komunikacija.KomunikacijaSaServerom.getInstanca().posaljiZahtev(kz);

            ServerskiOdgovor so = komunikacija.KomunikacijaSaServerom.getInstanca().primiOdgovor();
            Utakmica u1 = (Utakmica) so.getOdgovor();
            if (u1.getUtakmicaId() != 0) {
                JOptionPane.showMessageDialog(formaIgraci, "uspesno izmenjena utakmica sa id" + u1.getUtakmicaId(), "", JOptionPane.INFORMATION_MESSAGE);
                formaIzmeniUtakmicu.dispose();
                KlijentskiZahtev kz1 = new KlijentskiZahtev();
                kz1.setOperacija(Operacije.VRATI_UTAKMICE);
                komunikacija.KomunikacijaSaServerom.getInstanca().posaljiZahtev(kz1);

                ServerskiOdgovor so1 = komunikacija.KomunikacijaSaServerom.getInstanca().primiOdgovor();
                ArrayList<Utakmica> utakmica = (ArrayList<Utakmica>) so1.getOdgovor();
                formaUtakmice.updateTable(utakmica);

            } else {
                JOptionPane.showMessageDialog(formaIgraci, "sistem ne moze da izmeni utakmicu", "", JOptionPane.INFORMATION_MESSAGE);
                formaIzmeniUtakmicu.dispose();

            }
        } catch (NumberFormatException | HeadlessException e) {
            Logger.getLogger(GUIKontroler.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(formaIgraci, "broj dresa mora biti broj", "", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    public void otvoriObrisiUtakmicu(Utakmica vratiElement) {
        if (vratiElement != null) {
            formaObrisiUtakmicu = new ObrisiUtakmicu(vratiElement);
            formaObrisiUtakmicu.setLocationRelativeTo(formMain);
            formaObrisiUtakmicu.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(formaPrijaviSe, "Utakmica nije pronadjena", "", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    public void obrisiUtakmicu(Utakmica utakmica) {
        KlijentskiZahtev kz = new KlijentskiZahtev();
        kz.setParametar(utakmica);
        kz.setOperacija(Operacije.OBRISI_UTAKMICU);
        komunikacija.KomunikacijaSaServerom.getInstanca().posaljiZahtev(kz);

        ServerskiOdgovor so = komunikacija.KomunikacijaSaServerom.getInstanca().primiOdgovor();
        Utakmica u1 = (Utakmica) so.getOdgovor();
        if (u1.getUtakmicaId() != 0) {
            JOptionPane.showMessageDialog(formaIgraci, "uspesno obrisana utakmica sa id" + u1.getUtakmicaId(), "", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(formaIgraci, "sistem ne moze da obrise utakmicu", "", JOptionPane.INFORMATION_MESSAGE);
        }

        //pokreniIgrace();
        KlijentskiZahtev kz1 = new KlijentskiZahtev();
        kz1.setOperacija(Operacije.VRATI_UTAKMICE);
        komunikacija.KomunikacijaSaServerom.getInstanca().posaljiZahtev(kz1);

        ServerskiOdgovor so1 = komunikacija.KomunikacijaSaServerom.getInstanca().primiOdgovor();
        ArrayList<Utakmica> utakmice = (ArrayList<Utakmica>) so1.getOdgovor();
        formaUtakmice.updateTable(utakmice);
        formaObrisiUtakmicu.dispose();

    }

    public void pretraziUtakmice(String text) {

        KlijentskiZahtev kz = new KlijentskiZahtev();

        if (text.isEmpty()) {

            //JOptionPane.showMessageDialog(formaIgraci, "unesite zeljeno ime u polje za pretragu", "", JOptionPane.INFORMATION_MESSAGE);
            kz.setOperacija(Operacije.VRATI_UTAKMICE);
        } else {
//            Date datum = null;
//            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
//
//            try {
//                datum = sdf.parse(text);
//            } catch (ParseException ex) {
//                Logger.getLogger(GUIKontroler.class.getName()).log(Level.SEVERE, null, ex);
//            }
            kz.setParametar(text);
            kz.setOperacija(Operacije.PRETRAZI_UTAKMICE);
        }

        komunikacija.KomunikacijaSaServerom.getInstanca()
                .posaljiZahtev(kz);

        ServerskiOdgovor so = komunikacija.KomunikacijaSaServerom.getInstanca().primiOdgovor();
        ArrayList<Utakmica> utakmice = (ArrayList<Utakmica>) so.getOdgovor();

        if (utakmice.isEmpty()) {
            JOptionPane.showMessageDialog(formaIgraci, "Sistem ne moze da nadje utakmicu po zadataoj vrednosti", "", JOptionPane.INFORMATION_MESSAGE);
        } else {
            formaUtakmice.updateTable(utakmice);
        }

    }

    public void zatvoriUtakmice() {
        formaUtakmice.dispose();
        formMain = new FormaMain(k);
        formMain.setLocationRelativeTo(formaPrijaviSe);
        formMain.setVisible(true);
    }

}
