/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import domen.Igrac;
import domen.Klub;
import domen.Korisnik;
import domen.Pozicija;
import domen.Takmicenje;
import domen.Utakmica;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import konstante.Operacije;
import logika.Kontroler;
import transfer.KlijentskiZahtev;
import transfer.ServerskiOdgovor;

/**
 *
 * @author Windows HD
 */
public class ObradaKlijentskihZahteva extends Thread {

    Socket socket;
    boolean kraj=false;

    public ObradaKlijentskihZahteva(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        while(!kraj) {
            KlijentskiZahtev kz = primiZahtev();
            ServerskiOdgovor so = new ServerskiOdgovor();

            switch (kz.getOperacija()) {
                case Operacije.VRATI_POZICIJE:
                    Pozicija poz = new Pozicija();
                    ArrayList<Pozicija> pozicije = new ArrayList<>();
                     {
                        try {
                            pozicije = Kontroler.getInstanca().vratiPozicije(poz);
                        } catch (Exception ex) {
                            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    so.setOdgovor(pozicije);
                    break;
                case Operacije.VRATI_TAKMICENJE:
                    Takmicenje t = new Takmicenje();
                    ArrayList<Takmicenje> takmicenja = new ArrayList<>();
                     {
                        try {
                            takmicenja = Kontroler.getInstanca().vratiTakmicenje(t);
                        } catch (Exception ex) {
                            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    so.setOdgovor(takmicenja);
                    break;
                case Operacije.VRATI_KLUBOVE:
                    Klub k = new Klub();
                    ArrayList<Klub> klubovi = new ArrayList<>();
                     {
                        try {
                            klubovi = Kontroler.getInstanca().vratiKlubove(k);
                        } catch (Exception ex) {
                            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    so.setOdgovor(klubovi);
                    break;
                case Operacije.VRATI_IGRACA:
                    Igrac i = new Igrac();
                    ArrayList<Igrac> igraci = new ArrayList<>();
                     {
                        try {
                            igraci = Kontroler.getInstanca().vratiIgrace(i);
                        } catch (Exception ex) {
                            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    so.setOdgovor(igraci);
                    break;
                case Operacije.PRETRAZI_IGRACE:
                    String ime = (String) kz.getParametar();
                    Igrac i1 = new Igrac();
                    i1.setIme(ime);
                    i1.setPrezime(ime);
                    ArrayList<Igrac> igraci1 = new ArrayList<>();
                     {
                        try {
                            igraci1 = Kontroler.getInstanca().vratiIgracePoKriterijumu(i1);
                        } catch (Exception ex) {
                            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    so.setOdgovor(igraci1);
                    break;
                case Operacije.UCITAJ_PODATKE:
                    Igrac i2 = (Igrac) kz.getParametar();
                    Igrac igrac1 = new Igrac();
                     {
                        try {
                            igrac1 = Kontroler.getInstanca().vratiIgracaPoId(i2);
                        } catch (Exception ex) {
                            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    so.setOdgovor(igrac1);
                    break;
                case Operacije.KREIRAJ_IGRACA:
                    Igrac kreirajIgraca = (Igrac) kz.getParametar();
                    Igrac kreiranIgrac = new Igrac();
                     {
                        try {
                            kreiranIgrac = Kontroler.getInstanca().sacuvajIgraca(kreirajIgraca);
                        } catch (Exception ex) {
                            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    so.setOdgovor(kreiranIgrac);
                    break;
                case Operacije.OBRISI_IGRACA:
                    Igrac obrsiIgraca = (Igrac) kz.getParametar();
                    Igrac obrisanIgrac = new Igrac();
                     {
                        try {
                            obrisanIgrac = Kontroler.getInstanca().obrisiIgraca(obrsiIgraca);
                        } catch (Exception ex) {
                            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    so.setOdgovor(obrisanIgrac);
                    break;
                case Operacije.IZMENI_IGRACA:
                    Igrac izmeniIgraca = (Igrac) kz.getParametar();
                    Igrac izmenjenIgrac = new Igrac();
                     {
                        try {
                            izmenjenIgrac = Kontroler.getInstanca().izmeniIgraca(izmeniIgraca);
                        } catch (Exception ex) {
                            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    so.setOdgovor(izmenjenIgrac);
                    break;
                case Operacije.VRATI_UTAKMICE:
                    Utakmica u = new Utakmica();
                    ArrayList<Utakmica> utakmice = new ArrayList<>();
                     {
                        try {
                            utakmice = Kontroler.getInstanca().vratiUtakmice(u);
                            Utakmica u1 = utakmice.get(0);
                            for (Igrac ig : u1.getIgraci()) {
                                System.out.println(ig.getIme());
                            }
                        } catch (Exception ex) {
                            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    so.setOdgovor(utakmice);
                    break;
                case Operacije.PRETRAZI_UTAKMICE:
                    //Date datum = (Date) kz.getParametar();
                    String text = (String) kz.getParametar();
                    Utakmica u1 = new Utakmica();
                    u1.setKlub(new Klub(-1, text));
                    Date datum = null;
                    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                    try {
                        datum = sdf.parse(text);
                    } catch (ParseException ex) {
                    }
                    u1.setDatumOdigravanja(datum);
                    ArrayList<Utakmica> utakmice1 = new ArrayList<>();
                     {
                        try {
                            utakmice1 = Kontroler.getInstanca().vratiUtakmicePoKriterijumu(u1);
                        } catch (Exception ex) {
                            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    so.setOdgovor(utakmice1);
                    break;
                case Operacije.UCITAJ_PODATKE_O_UTAKMICI:
                    Utakmica u2 = (Utakmica) kz.getParametar();
                    Utakmica utakmica1 = new Utakmica();
                     {
                        try {
                            utakmica1 = Kontroler.getInstanca().vratiUtakmicePoId(u2);
                        } catch (Exception ex) {
                            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    so.setOdgovor(utakmica1);
                    break;
                case Operacije.KREIRAJ_UTAKMICU:
                    Utakmica krairajUtakmicu = (Utakmica) kz.getParametar();
                    Utakmica kreiranaUtakmica = new Utakmica();
                     {
                        try {
                            kreiranaUtakmica = Kontroler.getInstanca().sacuvajUtakmkcu(krairajUtakmicu);
                        } catch (Exception ex) {
                            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    so.setOdgovor(kreiranaUtakmica);
                    break;
                case Operacije.OBRISI_UTAKMICU:
                    Utakmica obrisiUtakmicu = (Utakmica) kz.getParametar();
                    Utakmica obrisanaUtakmica = new Utakmica();
                     {
                        try {
                            obrisanaUtakmica = Kontroler.getInstanca().obrisiUtakmicu(obrisiUtakmicu);
                        } catch (Exception ex) {
                            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    so.setOdgovor(obrisanaUtakmica);
                    break;
                case Operacije.IZMENI_UTAKMICU:
                    Utakmica izmeniUtakmicu = (Utakmica) kz.getParametar();
                    Utakmica izmenjenaUtakmica = new Utakmica();
                     {
                        try {
                            izmenjenaUtakmica = Kontroler.getInstanca().izmeniUtakmicu(izmeniUtakmicu);
                        } catch (Exception ex) {
                            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    so.setOdgovor(izmenjenaUtakmica);
                    break;
                case Operacije.OBRADI_KORISNIKA:
                    Korisnik prosledjeniKorisnik = (Korisnik) kz.getParametar();
                    Korisnik provereniKorisnik = null;
                     {
                        try {
                            provereniKorisnik = Kontroler.getInstanca().obradiKorisnika(prosledjeniKorisnik);
                        } catch (Exception ex) {
                            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    so.setOdgovor(provereniKorisnik);
                    break;
            }

            posaljiOdgovor(so);
        }
    }

//    private KlijentskiZahtev primiKlijentskiZahtev() {
//        KlijentskiZahtev kz = new KlijentskiZahtev();
//        try {
//            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
//            kz = (KlijentskiZahtev) ois.readObject();
//        } catch (IOException ex) {
//            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return kz;
//    }
//
//    public void posaljiOdgovor(ServerskiOdgovor so) {
//        try {
//            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
//            oos.writeObject(so);
//        } catch (IOException ex) {
//            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    private KlijentskiZahtev primiZahtev() {
        KlijentskiZahtev kz = new KlijentskiZahtev();
        try {
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            kz = (KlijentskiZahtev) ois.readObject();
        } catch (IOException ex) {
            kraj = true;
            //Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Klijent se otkacio");
        } catch (ClassNotFoundException ex) {
            kraj = true;
            //Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Klijent se otkacio");
        }
        return kz;
    }

    public void posaljiOdgovor(ServerskiOdgovor so) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(so);
        } catch (IOException ex) {
            //Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
