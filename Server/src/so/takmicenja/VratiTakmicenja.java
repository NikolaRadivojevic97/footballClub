/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.pozicije;

import domen.IGeneralObjectG;
import domen.Pozicija;
import domen.Takmicenje;
import java.sql.ResultSet;
import java.util.ArrayList;
import so.AbstractGenericOperation;

/**
 *
 * @author Windows HD
 */
public class VratiTakmicenja extends AbstractGenericOperation {

    @Override
    protected void preconditions(Object entity) throws Exception {
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected Object executeOperation(Object entity) throws Exception {
        ResultSet rs = databaseBroker.vratiSve((Takmicenje) entity);
        ArrayList<Takmicenje> takmicenja = new ArrayList<>();

        while (rs.next()) {
            Takmicenje takmicenje = new Takmicenje();
            takmicenje.setTakmicenjeId(rs.getInt(1));
            takmicenje.setNaziv(rs.getString(2));
            
            takmicenja.add(takmicenje);
        }
        return takmicenja;
    }

}