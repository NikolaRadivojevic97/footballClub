/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.igraci;

import domen.Igrac;
import domen.Klub;
import domen.Pozicija;
import domen.Takmicenje;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import logika.Kontroler;
import so.AbstractGenericOperation;

/**
 *
 * @author Windows HD
 */
public class VratiIgrace extends AbstractGenericOperation {

    @Override
    protected void preconditions(Object entity) throws Exception {
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected Object executeOperation(Object entity) throws Exception {
        ResultSet rs = databaseBroker.vratiSve((Igrac) entity);
        ArrayList<Igrac> igraci = new ArrayList<>();

        while (rs.next()) {
            Igrac igrac = new Igrac();
            igrac.setIgracId(rs.getInt(1));
            igrac.setIme(rs.getString(2));
            igrac.setPrezime(rs.getString(3));
            igrac.setDatumRodjenja(new Date(rs.getDate(4).getTime()));
            igrac.setAdresa(rs.getString(5));
            igrac.setMejl(rs.getString(6));
            igrac.setBrojDresa(rs.getInt(7));
            Pozicija pozicija=new Pozicija();
            ArrayList<Pozicija> pozicije=Kontroler.getInstanca().vratiPozicije(pozicija);
            for(Pozicija p:pozicije){
                if(p.getPozicijaId()==rs.getInt(8)){
                    pozicija=p;
                }
            }
            igrac.setPozicija(pozicija);
            
            igraci.add(igrac);
        }
        return igraci;
    }
}
