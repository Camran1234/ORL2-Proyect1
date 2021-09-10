/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valiente.orl2.reproduccion;

import java.io.Serializable;
import valiente.orl2.phyton.error.ValueException;

/**
 *
 * @author camran1234
 */
public class Reproduccion implements Serializable{
    private String notaMusical = "";
    private int octava = 0;
    private int duracion = 0;
    private int canal =0;
    private int line,column =0;
    public Reproduccion(String notaMusical, int octava, int duracion, int canal, int line, int column) throws ValueException{
        if(!checkValues(notaMusical)){
            throw new ValueException("No es una nota musical lo asignado en reproducir","Tipos incompatibles", line, column);
        }
        this.notaMusical = notaMusical;
        this.octava = octava;
        this.duracion = duracion;
        this.canal = canal;
        this.line = line;
        this.column = column;
    }
    
    public boolean checkValues(String nota){
        if(nota.equalsIgnoreCase("do")){
            return true;
        }else if(nota.equalsIgnoreCase("re")){
            return true;
        }else if(nota.equalsIgnoreCase("mi")){
            return true;
        }else if(nota.equalsIgnoreCase("fa")){
            return true;
        }else if(nota.equalsIgnoreCase("sol")){
            return true;
        }else if(nota.equalsIgnoreCase("la")){
            return true;
        }else if(nota.equalsIgnoreCase("si")){            
            return true;
        }else if(nota.equalsIgnoreCase("do#")){
            return true;
        }else if(nota.equalsIgnoreCase("re#")){
            return true;
        }else if(nota.equalsIgnoreCase("mi#")){
            return true;
        }else if(nota.equalsIgnoreCase("fa#")){
            return true;
        }else if(nota.equalsIgnoreCase("sol#")){
            return true;
        }else if(nota.equalsIgnoreCase("la#")){
            return true;
        }else if(nota.equalsIgnoreCase("si#")){            
            return true;
        }else if(nota.equalsIgnoreCase("Rest")){
            return true;
        }
        return false;
    }

    public String getNotaMusical() {
        return notaMusical;
    }

    public void setNotaMusical(String notaMusical) {
        this.notaMusical = notaMusical;
    }

    public int getOctava() {
        return octava;
    }

    public void setOctava(int octava) {
        this.octava = octava;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public int getCanal() {
        return canal;
    }

    public void setCanal(int canal) {
        this.canal = canal;
    }
    
    
    
}
