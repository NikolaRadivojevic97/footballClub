/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.utakmica;

import domen.Igrac;
import domen.Igrac_Utakmica;
import domen.Utakmica;
import so.AbstractGenericOperation;

/**
 *
 * @author nikol
 */
public class ZapamtiUtakmicu extends AbstractGenericOperation {

    @Override
    protected void preconditions(Object entity) throws Exception {
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected Object executeOperation(Object entity) throws Exception {
//        Utakmica u =(Utakmica) entity;
         Utakmica u= (Utakmica) databaseBroker.sacuvaj((Utakmica) entity);
        for(Igrac i:u.getIgraci()){
            Igrac_Utakmica iu=new Igrac_Utakmica(i.getIgracId(), u.getUtakmicaId());
            databaseBroker.sacuvajBezVracanja((Igrac_Utakmica) iu);
        }
        return u;
    }
}
        