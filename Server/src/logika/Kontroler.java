/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logika;

import domen.Igrac;
import domen.Klub;
import domen.Korisnik;
import domen.Pozicija;
import domen.Takmicenje;
import domen.Utakmica;
import java.util.ArrayList;

import so.AbstractGenericOperation;
import so.igrac.IzmeniIgraca;
import so.igrac.ObrisiIgraca;
import so.igrac.VratiIgracePoId;
import so.igrac.VratiIgracePoKriterijumu;
import so.igrac.ZapamtiIgraca;
import so.igraci.VratiIgrace;
import so.klijent.ObradiKlijenta;
import so.pozicije.VratiPozicije;
import so.klubovi.VratiKlubove;
import so.pozicije.VratiTakmicenja;
import so.utakmica.IzmeniUtakmicu;
import so.utakmica.ObrisiUtakmicu;
import so.utakmica.VratiUtakmice;
import so.utakmica.VratiUtakmicePoKriterijumu;
import so.utakmica.VratiUtakmicuPoId;
import so.utakmica.ZapamtiUtakmicu;

/**
 *
 * @author Windows HD
 */
public class Kontroler {

    private static Kontroler instanca;

    private Kontroler() {

    }

    public static Kontroler getInstanca() {
        if (instanca == null) {
            instanca = new Kontroler();
        }
        return instanca;
    }

    public Igrac izmeniIgraca(Igrac entity) throws Exception {
        AbstractGenericOperation izmeniIgraca = new IzmeniIgraca();
        return (Igrac) izmeniIgraca.execute(entity);
    }

    public Igrac obrisiIgraca(Igrac entity) throws Exception {
        AbstractGenericOperation obrisiIgraca = new ObrisiIgraca();
        return (Igrac) obrisiIgraca.execute(entity);
    }

    public ArrayList<Pozicija> vratiPozicije(Pozicija entity) throws Exception {
        AbstractGenericOperation vratiPozicije = new VratiPozicije();
        return (ArrayList<Pozicija>) vratiPozicije.execute(entity);
    }

    public ArrayList<Klub> vratiKlubove(Klub entity) throws Exception {
        AbstractGenericOperation vratiKlubove = new VratiKlubove();
        return (ArrayList<Klub>) vratiKlubove.execute(entity);
    }

    public ArrayList<Takmicenje> vratiTakmicenje(Takmicenje entity) throws Exception {
        AbstractGenericOperation vratiTakmicenje = new VratiTakmicenja();
        return (ArrayList<Takmicenje>) vratiTakmicenje.execute(entity);
    }

    public ArrayList<Igrac> vratiIgrace(Igrac entity) throws Exception {
        AbstractGenericOperation igraci = new VratiIgrace();
        return (ArrayList<Igrac>) igraci.execute(entity);
    }

    public ArrayList<Igrac> vratiIgracePoKriterijumu(Igrac entity) throws Exception {
        AbstractGenericOperation igraci = new VratiIgracePoKriterijumu();
        return (ArrayList<Igrac>) igraci.execute(entity);
    }

    public Igrac vratiIgracaPoId(Igrac entity) throws Exception {
        AbstractGenericOperation igrac = new VratiIgracePoId();
        return (Igrac) igrac.execute(entity);
    }

    public ArrayList<Utakmica> vratiUtakmice(Utakmica entity) throws Exception {
        AbstractGenericOperation utakmica = new VratiUtakmice();
        return (ArrayList<Utakmica>) utakmica.execute(entity);
    }

    public Igrac sacuvajIgraca(Igrac entity) throws Exception{
        AbstractGenericOperation igrac = new ZapamtiIgraca();
        return (Igrac) igrac.execute(entity);
    }
     public Utakmica izmeniUtakmicu(Utakmica entity) throws Exception {
        AbstractGenericOperation izmeniUtakmicu = new IzmeniUtakmicu();
        return (Utakmica) izmeniUtakmicu.execute(entity);
    }

    public Utakmica obrisiUtakmicu(Utakmica entity) throws Exception {
        AbstractGenericOperation obrisiUtakmicu = new ObrisiUtakmicu();
        return (Utakmica) obrisiUtakmicu.execute(entity);
    }
    public Utakmica sacuvajUtakmkcu(Utakmica entity) throws Exception{
        AbstractGenericOperation sacuvajUtakmkcu = new ZapamtiUtakmicu();
        return (Utakmica) sacuvajUtakmkcu.execute(entity);
    }
     public ArrayList<Utakmica> vratiUtakmicePoKriterijumu(Utakmica entity) throws Exception {
        AbstractGenericOperation vratiUtakmicePoKriterijumu = new VratiUtakmicePoKriterijumu();
        return (ArrayList<Utakmica>) vratiUtakmicePoKriterijumu.execute(entity);
    }

    public Utakmica vratiUtakmicePoId(Utakmica entity) throws Exception {
        AbstractGenericOperation vratiUtakmicePoId = new VratiUtakmicuPoId();
        return (Utakmica) vratiUtakmicePoId.execute(entity);
    }
     public Korisnik obradiKorisnika(Korisnik entity) throws Exception {
        AbstractGenericOperation obradiKorisnika = new ObradiKlijenta();
        return (Korisnik) obradiKorisnika.execute(entity);
    }
    
}
