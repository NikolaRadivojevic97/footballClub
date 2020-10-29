/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.igrac;

import domen.Igrac;
import domen.Pozicija;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import logika.Kontroler;
import so.AbstractGenericOperation;

/**
 *
 * @author nikol
 */
public class VratiIgracePoId extends AbstractGenericOperation {

    @Override
    protected void preconditions(Object entity) throws Exception {
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected Object executeOperation(Object entity) throws Exception {
        ResultSet rs = databaseBroker.vratiPoId((Igrac) entity);
        Igrac igrac = new Igrac();

        while (rs.next()) {
            
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
            
            
        }
        return igrac;
    }
}