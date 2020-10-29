/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.utakmica;

import domen.Igrac;
import domen.Igrac_Utakmica;
import domen.Utakmica;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import so.AbstractGenericOperation;

/**
 *
 * @author nikol
 */
public class IzmeniUtakmicu extends AbstractGenericOperation {

    @Override
    protected void preconditions(Object entity) throws Exception {
        // product preconditions and validation
    }

    @Override
    protected Object executeOperation(Object entity) throws Exception {
        Utakmica u=(Utakmica)databaseBroker.izmeni((Utakmica) entity);
        Igrac_Utakmica iu1=new Igrac_Utakmica();
        iu1.setUtakmicaId(u.getUtakmicaId());
        ResultSet rs =databaseBroker.vratiPoId(iu1);

        
        while (rs.next()) {
            Igrac_Utakmica igrac_utakmica = new Igrac_Utakmica();
            igrac_utakmica.setIgracId(rs.getInt(1));
            igrac_utakmica.setUtakmicaId(rs.getInt(2));
            if(igrac_utakmica.getUtakmicaId()==u.getUtakmicaId()){
                databaseBroker.obrisi(igrac_utakmica);
            }
        } 
        for(Igrac i:u.getIgraci()){
            Igrac_Utakmica iu=new Igrac_Utakmica(i.getIgracId(), u.getUtakmicaId());
            databaseBroker.sacuvajBezVracanja((Igrac_Utakmica) iu);
        }
        return u;
    }
}
