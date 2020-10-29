/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.igrac;

import domen.Igrac;
import so.AbstractGenericOperation;

/**
 *
 * @author nikol
 */
public class ObrisiIgraca extends AbstractGenericOperation {

    @Override
    protected void preconditions(Object entity) throws Exception {
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected Object executeOperation(Object entity) throws Exception {
        return (Igrac) databaseBroker.obrisi((Igrac) entity);
    }
}
