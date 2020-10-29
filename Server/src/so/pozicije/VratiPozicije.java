/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.pozicije;

import domen.IGeneralObjectG;
import domen.Pozicija;
import java.sql.ResultSet;
import java.util.ArrayList;
import so.AbstractGenericOperation;

/**
 *
 * @author Windows HD
 */
public class VratiPozicije extends AbstractGenericOperation {

    @Override
    protected void preconditions(Object entity) throws Exception {
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected Object executeOperation(Object entity) throws Exception {
        ResultSet rs = databaseBroker.vratiSve((Pozicija) entity);
        ArrayList<Pozicija> pozicije = new ArrayList<>();

        while (rs.next()) {
            Pozicija poz = new Pozicija();
            poz.setPozicijaId(rs.getInt(1));
            poz.setNaziv(rs.getString(2));
            poz.setSkraceniNaziv(rs.getString(3));

            pozicije.add(poz);
        }
        return pozicije;
    }

}
