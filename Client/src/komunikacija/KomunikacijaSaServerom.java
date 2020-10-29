/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package komunikacija;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import transfer.KlijentskiZahtev;
import transfer.ServerskiOdgovor;

/**
 *
 * @author Windows HD
 */
public class KomunikacijaSaServerom {
    private static KomunikacijaSaServerom instanca;
    Socket socket;

    private KomunikacijaSaServerom() {
    }

    public static KomunikacijaSaServerom getInstanca() {
        if(instanca == null)
            instanca = new KomunikacijaSaServerom();
        return instanca;
    }
    
    public void poveziSe(String adresa, int port) {
        try {
            socket = new Socket(adresa, port);
        } catch (IOException ex) {
            Logger.getLogger(KomunikacijaSaServerom.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void posaljiZahtev(KlijentskiZahtev kz) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(kz);
        } catch (IOException ex) {
            //Logger.getLogger(KomunikacijaSaServerom.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Postoji problem sa serverom", "", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }
    
    public ServerskiOdgovor primiOdgovor() {
        ServerskiOdgovor so = new ServerskiOdgovor();
        try {
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            so = (ServerskiOdgovor) ois.readObject();
        } catch (IOException ex) {
            //Logger.getLogger(KomunikacijaSaServerom.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Postoji problem sa serverom", "", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(KomunikacijaSaServerom.class.getName()).log(Level.SEVERE, null, ex);
        }
        return so;
    }
}
