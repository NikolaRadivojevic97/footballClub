/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.klubovi;

import domen.Klub;
import domen.Pozicija;
import java.sql.ResultSet;
import java.util.ArrayList;
import so.AbstractGenericOperation;

/**
 *
 * @author Windows HD
 */
public class VratiKlubove extends AbstractGenericOperation {

    @Override
    protected void preconditions(Object entity) throws Exception {
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected Object executeOperation(Object entity) throws Exception {
          ResultSet rs = databaseBroker.vratiSve((Klub) entity);
        ArrayList<Klub> klubovi = new ArrayList<>();

        while (rs.next()) {
            Klub klub = new Klub();
            klub.setKlubId(rs.getInt(1));
            klub.setNaziv(rs.getString(2));

            klubovi.add(klub);
        }
        return klubovi;
    }
}


