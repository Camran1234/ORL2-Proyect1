/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valiente.orl2.webconnection;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import valiente.orl2.central.Central;
import valiente.orl2.reproduccion.ListaReproduccion;
import valiente.orl2.reproduccion.PistaReproduccion;
import valiente.orl2.reproduccion.PlayList;
import valiente.orl2.reproduccion.Reproduccion;
import valiente.orl2.reproductor.Nota;

/**
 *
 * @author camran1234
 */
public class Escritor {
    private Central central = new Central();
    
    
    /*
    Formato:
    < listas >
        < lista nombre = "tranquilas" pistas = 5>
        < lista nombre = "fiesta" pistas = 10>
    </ listas >
    */
    /**
     * Devuelve una cadena con todas las listas disponible
     * @return 
     */
    public String escribirListas(){
        try {
            StringBuilder string = new StringBuilder();
            PlayList playList = central.getPlayList();
            ArrayList<ListaReproduccion> listas = playList.getlistas();
            string.append("<listas>\n");
            for(ListaReproduccion lista:listas){
                string.append("\t<lista nombre = ").append("\"").append(lista.getNombre()).append("\"").append(" pistas = ");
                string.append(lista.getPistas().size()).append(">\n");
            }   
            string.append("</listas>\n");
            return string.toString();
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, "No se pudo escribir las listas disponible", "Error Servidor", JOptionPane.ERROR);
            e.printStackTrace();
        }
        return "Listas no encontradas";
    }
    
    
    /*
    Formato:
    < lista nombre = "tranquilas" aleatoria ="SI/NO">
        < pista nombre = "pista1" duracion = 14500 >
        < pista nombre = "pista2" duracion = 60000 >    
    </ lista >
    */
    /**
     * Escribe las pistas que contiene una lista
     * @param lista
     * @return 
     */
    public String escribirDatosLista(String nombreLista){
        try {
            ListaReproduccion lista = central.getListaReproduccion(nombreLista);
            ArrayList<PistaReproduccion> pistas = central.getPistas();
            StringBuilder string = new StringBuilder();
            string.append("< lista nombre = ").append("\"").append(lista.getNombre()).append("\" ");
            if(lista.getCircular()){
                string.append("circular = ").append("\"").append("Si").append("\"");
            }else{
                string.append("circular= ").append("\"").append("No").append("\"");
            }
            if(lista.getRandom()){
                string.append("aleatoria = ").append("\"").append("Si").append("\"");
            }else{
                string.append("aleatoria = ").append("\"").append("No").append("\"");
            }
            string.append(" >\n");
            for(PistaReproduccion pista:pistas){
                string.append("\t< pista nombre = ").append("\"").append(pista.getName()).append("\"").append(" duracion = ").
                        append(pista.getDuracionTotal()).append(" >\n");
            }
            string.append("</lista>");
            return string.toString();
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, "No se pudo escribir la lista disponible", "Error Servidor", JOptionPane.ERROR);
            e.printStackTrace();
        }
        return "Lista no encontrada";
    }
    
    /*
    Formato:
    < pistas >
    < pista nombre = "pista1" duracion = 14500 >
    < pista nombre = "one minute" duracion = 60000 >
    </ pistas >
    */
    /**
     * Devuelve una cadena con todas las pistas disponibles
     * @return 
     */
    public String escribirPistas(){
        try {
            StringBuilder string = new StringBuilder();
            ArrayList<PistaReproduccion> pistas = central.getPistas();
            
            string.append("<pistas>\n");
            for(PistaReproduccion pista:pistas){
                string.append("\t<pista nombre = ").append("\"").append(pista.getName()).append("\"").append(" duracion = ").append(pista.getDuracionTotal()).append(">\n");
            }
            string.append("</pistas>\n");
            return string.toString();
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, "No se pudo escribir las pistas disponible", "Error Servidor", JOptionPane.ERROR);
            e.printStackTrace();
        }
        return "Pistas no encontradas";
    }
    
    
    /**
     * Genera la nota en Communication Language
     * @param reproduccion
     * @return 
     */
    private String generateNota(Reproduccion reproduccion){
        StringBuilder string = new StringBuilder();                
        Nota nota = new Nota(Nota.getNota(reproduccion.getNotaMusical()), reproduccion.getOctava());        
        string.append("\t\t< nota duracion = ").append(reproduccion.getDuracion()).append(" frecuencia = ").append(nota.frequency()).append(" >\n");
        return string.toString();
    }
    
    /**
     * Busca generar las notas
     * @param auxiliar
     * @param reproduccion 
     */
    private void addData(ArrayList<EscritorAuxiliar> auxiliar, Reproduccion reproduccion){
        boolean added=false;
        for(int index=0; index<auxiliar.size(); index++){
            EscritorAuxiliar escritor = auxiliar.get(index);
            if(escritor.getChannel() == reproduccion.getCanal()){                
                escritor.addNote(generateNota(reproduccion));
                added=true;
                break;
            }
        }
        //Agregamoe el nuevo escritor
        if(!added){
            EscritorAuxiliar escritor = new EscritorAuxiliar(reproduccion.getCanal());
            escritor.addNote(generateNota(reproduccion));
            auxiliar.add(escritor);
            //agregar un nuevo escritor auxiliar
        }
                
    }
    
    /*
    Formato:
        < pista nombre = "pista1" >
            < canal numero = 1 >
                < nota duracion = 4000 frecuencia = 32.7 > 
                < nota duracion = 3500 frecuencia = 73.4 > 
                < nota duracion = 7000 frecuencia = 0 > 
            </ canal >
            < canal numero = 8 >
                < nota duracion = 3500 frecuencia = 73.4 >
                < nota duracion = 7000 frecuencia = 0 >
                < nota duracion = 7000 frecuencia = 0 >
                < nota duracion = 4000 frecuencia = 32.7 >
            </ canal >
        </ pista>
    */
    public String escribirDatosPista(String nombrePista){
        try {
            PistaReproduccion pista = central.getPistaReproduccion(nombrePista);
            StringBuilder string = new StringBuilder();
            
            ArrayList<Reproduccion> reproducciones = pista.getSonidos();
            ArrayList<EscritorAuxiliar> auxiliar = new ArrayList();
            
            for(Reproduccion reproduccion:reproducciones){
                addData(auxiliar, reproduccion);
            }
            string.append("< pista nombre = ").append("\"").append(nombrePista).append("\"").append(">\n");
            for(EscritorAuxiliar escritor:auxiliar){
                string.append("\t < canal numero = ").append(escritor.getChannel()).append(" >\n");
                for(int index=0; index<escritor.getNotas().size(); index++){
                    string.append(escritor.getNotas().get(index));
                }
                string.append("\t</canal>\n");
            }
            string.append("</pista>");
            return string.toString();
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, "No se pudo escribir las pistas disponible", "Error Servidor", JOptionPane.ERROR);
            e.printStackTrace();
        }
        return "Pista no encontrada";
    }
    
}
