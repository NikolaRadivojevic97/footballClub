/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.klijent;

import domen.Klub;
import domen.Korisnik;
import java.sql.ResultSet;
import java.util.ArrayList;
import so.AbstractGenericOperation;

/**
 *
 * @author nikol
 */
public class ObradiKlijenta extends AbstractGenericOperation {

    @Override
    protected void preconditions(Object entity) throws Exception {
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected Object executeOperation(Object entity) throws Exception {
          ResultSet rs = databaseBroker.vratiSve((Korisnik) entity);
          Korisnik kor=(Korisnik)entity;
        ArrayList<Korisnik> korisnici = new ArrayList<>();

        while (rs.next()) {
            Korisnik korisnik = new Korisnik();
            korisnik.setKorisnikId(rs.getInt(1));
            korisnik.setIme(rs.getString(2));
            korisnik.setKorisnickoIme(rs.getString(3));
            korisnik.setLozinka(rs.getString(4));

            korisnici.add(korisnik);
        }
        for(Korisnik k: korisnici){
            if(k.getKorisnickoIme().equals(kor.getKorisnickoIme()) && k.getLozinka().equals(kor.getLozinka()))
                return k;
        }
        return null;
    }
}