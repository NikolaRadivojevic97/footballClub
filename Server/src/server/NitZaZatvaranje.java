/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Windows HD
 */
public class NitZaZatvaranje extends Thread {

    ServerSocket ss;
    PokretanjeServera ps;
    boolean kraj = false;

    public NitZaZatvaranje(ServerSocket ss, PokretanjeServera ps) {
        this.ss = ss;
        this.ps = ps;
    }

    @Override
    public void run() {
        while(!kraj) {
            if(ps.isInterrupted()) {
                try {
                    ss.close();
                    kraj = true;
                } catch (IOException ex) {
                    Logger.getLogger(NitZaZatvaranje.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

}
