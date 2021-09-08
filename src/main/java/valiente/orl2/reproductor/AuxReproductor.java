/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valiente.orl2.reproductor;

import java.util.ArrayList;
import valiente.orl2.reproduccion.PistaReproduccion;

/**
 *
 * @author camran1234
 */
public class AuxReproductor extends Thread{
    private boolean circular=false;
    private boolean random = false;
    private ArrayList<PistaReproduccion> pistas = new ArrayList();
    private ArrayList<Channel> channels = new ArrayList();
    
}
