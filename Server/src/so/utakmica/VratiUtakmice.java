/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.utakmica;

import domen.Igrac;
import domen.Klub;
import domen.Pozicija;
import domen.Igrac_Utakmica;
import domen.Takmicenje;
import domen.Utakmica;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import logika.Kontroler;
import so.AbstractGenericOperation;

/**
 *
 * @author Windows HD
 */
public class VratiUtakmice extends AbstractGenericOperation {

    @Override
    protected void preconditions(Object entity) throws Exception {
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected Object executeOperation(Object entity) throws Exception {
        ResultSet rs = databaseBroker.vratiSve((Utakmica) entity);
        ArrayList<Utakmica> utakmice = new ArrayList<>();
        ArrayList<Igrac_Utakmica> iul = new ArrayList<>();
        

        ResultSet rs1 = databaseBroker.vratiSve(new Igrac_Utakmica());
        while (rs1.next()) {
            Igrac_Utakmica iu = new Igrac_Utakmica();
            iu.setIgracId(rs1.getInt(1));
            iu.setUtakmicaId(rs1.getInt(2));
            iul.add(iu);
        }
        while (rs.next()) {
            Utakmica utakmica = new Utakmica();
            utakmica.setUtakmicaId(rs.getInt(1));
            utakmica.setBrojGolova(rs.getInt(2));
            utakmica.setBrojGolovaGost(rs.getInt(3));
            utakmica.setDatumOdigravanja(new Date(rs.getDate(4).getTime()));
            utakmica.setBrojBodova(rs.getInt(5));
            Takmicenje takmicenje = new Takmicenje();
            ArrayList<Takmicenje> takmicanja = Kontroler.getInstanca().vratiTakmicenje(takmicenje);
            for (Takmicenje t : takmicanja) {
                if (t.getTakmicenjeId() == rs.getInt(6)) {
                    takmicenje = t;
                }
            }
            utakmica.setTakmicenje(takmicenje);
            Klub klub = new Klub();
            ArrayList<Klub> klubovi = Kontroler.getInstanca().vratiKlubove(klub);
            for (Klub k : klubovi) {
                if (k.getKlubId() == rs.getInt(7)) {
                    klub = k;
                }
            }
            utakmica.setKlub(klub);
            ArrayList<Igrac> igraciZaUbaci = new ArrayList<>();
            ArrayList<Igrac> igraci = Kontroler.getInstanca().vratiIgrace(new Igrac());
            for (Igrac i : igraci) {
                for (Igrac_Utakmica igracUtakmica : iul) {
                    if (igracUtakmica.getIgracId() == i.getIgracId() && igracUtakmica.getUtakmicaId() == utakmica.getUtakmicaId()) {
                        igraciZaUbaci.add(i);
                        //System.out.println("ubacio"+i.getIme()+"u utakmicu"+utakmica.getUtakmicaId());
                    }
                }
            }
            for(Igrac i:igraciZaUbaci){
                //System.out.println(i.getIme()+"u utakmicu"+utakmica.getUtakmicaId());
            }
            utakmica.setIgraci(igraciZaUbaci);
            utakmice.add(utakmica);
//            igraciZaUbaci.clear();
        }
        return utakmice;
    }
}
