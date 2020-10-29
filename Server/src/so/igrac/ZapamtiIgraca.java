/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.igrac;

import domen.Igrac;
import domen.Igrac_Utakmica;
import domen.Pozicija;
import domen.Utakmica;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import logika.Kontroler;
import so.AbstractGenericOperation;
import so.utakmica.ObrisiUtakmicu;

/**
 *
 * @author nikol
 */
public class ZapamtiIgraca extends AbstractGenericOperation {

    @Override
    protected void preconditions(Object entity) throws Exception {
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected Object executeOperation(Object entity) throws Exception {
        return (Igrac) databaseBroker.sacuvaj((Igrac) entity);
    }
}