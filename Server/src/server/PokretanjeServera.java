/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import forme.formaServer;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Windows HD
 */
//public class PokretanjeServera extends Thread {
//    
//    formaServer forma;
//
//    public PokretanjeServera(formaServer forma) {
//        this.forma = forma;
//    }
//
//    @Override
//    public void run() {
//        try {
//            ServerSocket ss = new ServerSocket(9000);
//            forma.serverPokrenut();
//            System.out.println("Server je pokrenut");
//            NitZaZatvaranje nz = new NitZaZatvaranje(ss, this);
//            nz.start();
//            while(!isInterrupted()) {
//                Socket s = ss.accept();
//                System.out.println("Klijent se nakacio");
//                ObradaKlijentskihZahteva okz = new ObradaKlijentskihZahteva(s);
//                okz.start();
//            }
//        } catch (IOException ex) {
//            forma.serverNijePokrenut();
//            System.out.println("Server je zaustavljen");
//        }
//    }
//    
//}
public class PokretanjeServera extends Thread{

    formaServer forma;
    Socket s = new Socket();

    public PokretanjeServera(formaServer forma) {
        this.forma = forma;
    }

    @Override
    public void run() {
        try {
            ServerSocket ss = new ServerSocket(9000);
            forma.serverPokrenut();
            System.out.println("Server je pokrenut");
            NitZaZatvaranje nz = new NitZaZatvaranje(ss, this);
            nz.start();
            while(!isInterrupted()) {
                s = ss.accept();
                System.out.println("Klijent se nakacio");
                ObradaKlijentskihZahteva okz = new ObradaKlijentskihZahteva(s);
                okz.start();
            }
        } catch (IOException ex) {
            forma.serverNijePokrenut();
            try {
                s.close();
            } catch (IOException ex1) {

            }

            System.out.println("Server je zaustavljen");
        }
    }
}
