/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valiente.orl2.phyton.specialInstructions;

import java.util.ArrayList;
import valiente.orl2.phyton.error.ValueException;
import valiente.orl2.phyton.table.Data;

/**
 *
 * @author camran1234
 */
public class HelperOrdenar {
    
    
    
    
    public  boolean ASCENDENTE(Data data) throws Exception{
        String type = data.getType();
        String[] array = data.getArray();
        
        if(type.equalsIgnoreCase("entero")){            
            int menor, pos, tmp;
            for(int i=0; i<array.length-1; i++){
                if(array[i] == null){
                    continue;
                }
                menor = Integer.parseInt(array[i]);
                pos = i;
                for(int j=i+1; j<array.length; j++){
                    if(Integer.parseInt(array[j])<menor){
                        menor = Integer.parseInt(array[j]);
                        pos = j;
                    }
                }
                if(pos != i){
                    tmp = Integer.parseInt(array[i]);
                    array[i] = array[pos];
                    array[pos] = Integer.toString(tmp);
                }
            }
            data.setArray(array);
            return true;
        }else if(type.equalsIgnoreCase("doble")){
            double menor, tmp;
            int  pos;
            for(int i=0; i<array.length-1; i++){
                if(array[i] == null){
                    continue;
                }
                menor = Double.parseDouble(array[i]);
                pos = i;
                for(int j=i+1; j<array.length; j++){
                    if(Double.parseDouble(array[j])<menor){
                        menor = Double.parseDouble(array[j]);
                        pos = j;
                    }
                }
                if(pos != i){
                    tmp = Double.parseDouble(array[i]);
                    array[i] = array[pos];
                    array[pos] = Double.toString(tmp);
                }
            }
            data.setArray(array);
            return true;
        }else if(type.equalsIgnoreCase("caracter")){
            int menor, pos, tmp;
            for(int i=0; i<array.length-1; i++){
                if(array[i] == null){
                    continue;
                }
                menor = (array[i].charAt(0));
                pos = i;
                for(int j=i+1; j<array.length; j++){
                    if((array[j].charAt(0))<menor){
                        menor = (array[j].charAt(0));
                        pos = j;
                    }
                }
                if(pos != i){
                    tmp = (array[i].charAt(0));
                    array[i] = array[pos];
                    array[pos] = (char)tmp + "";
                }
            }
            data.setArray(array);
            return true;
        }else if(type.equalsIgnoreCase("cadena")){
            int pos;
            String menor, tmp;
            for(int i=0; i<array.length-1; i++){
                if(array[i] == null){
                    continue;
                }
                menor = array[i];
                pos = i;
                for(int j=i+1; j<array.length; j++){
                    if((array[j].length())<menor.length()){
                        menor = array[j];
                        pos = j;
                    }
                }
                if(pos != i){
                    tmp = array[i];
                    array[i] = array[pos];
                    array[pos] = tmp;
                }
            }
            data.setArray(array);
            return true;
        }
        return false;
    }
    
    public  boolean DESCENDENTE(Data data) throws Exception{     
        String type = data.getType();
        String[] array = data.getArray();
        
        if(type.equalsIgnoreCase("entero")){            
            int menor, pos, tmp;
            for(int i=0; i<array.length-1; i++){
                if(array[i] == null){
                    continue;
                }
                menor = Integer.parseInt(array[i]);
                pos = i;
                for(int j=i+1; j<array.length; j++){
                    if(Integer.parseInt(array[j])>menor){
                        menor = Integer.parseInt(array[j]);
                        pos = j;
                    }
                }
                if(pos != i){
                    tmp = Integer.parseInt(array[i]);
                    array[i] = array[pos];
                    array[pos] = Integer.toString(tmp);
                }
            }
            data.setArray(array);
            return true;
        }else if(type.equalsIgnoreCase("doble")){
            double menor, tmp;
            int  pos;
            for(int i=0; i<array.length-1; i++){
                if(array[i] == null){
                    continue;
                }
                menor = Double.parseDouble(array[i]);
                pos = i;
                for(int j=i+1; j<array.length; j++){
                    if(Double.parseDouble(array[j])>menor){
                        menor = Double.parseDouble(array[j]);
                        pos = j;
                    }
                }
                if(pos != i){
                    tmp = Double.parseDouble(array[i]);
                    array[i] = array[pos];
                    array[pos] = Double.toString(tmp);
                }
            }
            data.setArray(array);
            return true;
        }else if(type.equalsIgnoreCase("caracter")){
            int menor, pos, tmp;
            for(int i=0; i<array.length-1; i++){
                if(array[i] == null){
                    continue;
                }
                menor = (array[i].charAt(0));
                pos = i;
                for(int j=i+1; j<array.length; j++){
                    if((array[j].charAt(0))>menor){
                        menor = (array[j].charAt(0));
                        pos = j;
                    }
                }
                if(pos != i){
                    tmp = (array[i].charAt(0));
                    array[i] = array[pos];
                    array[pos] = (char)tmp + "";
                }
            }
            data.setArray(array);
            return true;
        }else if(type.equalsIgnoreCase("cadena")){
            int pos;
            String menor, tmp;
            for(int i=0; i<array.length-1; i++){
                if(array[i] == null){
                    continue;
                }
                menor = array[i];
                pos = i;
                for(int j=i+1; j<array.length; j++){
                    if((array[j].length())>menor.length()){
                        menor = array[j];
                        pos = j;
                    }
                }
                if(pos != i){
                    tmp = array[i];
                    array[i] = array[pos];
                    array[pos] = tmp;
                }
            }
            data.setArray(array);
            return true;
        }
        return false;
    }
    
    public  boolean PARES(Data data) throws Exception{
        String type = data.getType();
        String[] array = data.getArray();
        if(type.equalsIgnoreCase("entero") || type.equalsIgnoreCase("doble") ||
                type.equalsIgnoreCase("caracter")){
            ArrayList<String> pares = new ArrayList();
            ArrayList<String> impares = new ArrayList();                
            double doble;        
            int entero;
            char caracter;
            for(int index=0; index< array.length; index++){            
                if(array[index] == null){                
                    continue;                    
                }                
                
                if(type.equalsIgnoreCase("entero") || type.equalsIgnoreCase("doble")){
                    doble = Double.parseDouble(array[index]);
                    entero = (int)doble;
                }else{
                    caracter = array[index].charAt(0);
                    doble = (double)caracter;
                    entero = (int)doble;
                }
                if(entero%2==0){                
                    pares.add(array[index]);                    
                }else{                
                    impares.add(array[index]);                    
                }                
            }
            
            for(int index=0; index<impares.size(); index++){
                pares.add(impares.get(index));
            }
            array = new String[pares.size()];
            for(int index=0; index<pares.size(); index++){
                array[index] = pares.get(index);
            }
            data.setArray(array);
            return true;
        }
        
        return false;
    }
    
    public  boolean IMPARES(Data data)throws Exception {
        String type = data.getType();
        String[] array = data.getArray();
        if(type.equalsIgnoreCase("entero") || type.equalsIgnoreCase("doble") ||
                type.equalsIgnoreCase("caracter")){
            ArrayList<String> pares = new ArrayList();
            ArrayList<String> impares = new ArrayList();                
            double doble;        
            int entero;
            char caracter;
            for(int index=0; index< array.length; index++){            
                if(array[index] == null){                
                    continue;                    
                }                
                
                if(type.equalsIgnoreCase("entero") || type.equalsIgnoreCase("doble")){
                    doble = Double.parseDouble(array[index]);
                    entero = (int)doble;
                }else{
                    caracter = array[index].charAt(0);
                    doble = (double)caracter;
                    entero = (int)doble;
                }
                if(entero%2==0){                
                    pares.add(array[index]);                    
                }else{                
                    impares.add(array[index]);                    
                }                
            }
            
            for(int index=0; index<pares.size(); index++){
                impares.add(pares.get(index));
            }
            array = new String[impares.size()];
            for(int index=0; index<impares.size(); index++){
                array[index] = impares.get(index);
            }
            data.setArray(array);
            return true;
        }
        
        return false;
    }
    
    public  boolean PRIMOS(Data data) throws Exception{
        String type = data.getType();
        String[] array = data.getArray();
        if(type.equalsIgnoreCase("entero") || type.equalsIgnoreCase("doble") || type.equalsIgnoreCase("caracter")){
           ArrayList<String> primos = new ArrayList();
           ArrayList<String> noPrimos = new ArrayList();
           for(int index=0; index<array.length; index++){
               if(isPrimo(array[index],type)){
                   primos.add(array[index]);
               }else{
                   noPrimos.add(array[index]);
               }
           }
           for(int index=0; index<noPrimos.size(); index++){
               primos.add(noPrimos.get(index));
           }
           array = new String[primos.size()];
            for(int index=0; index<primos.size(); index++){
                array[index] = primos.get(index);
            }
           data.setArray(array);
           return true;
        }
        
        return false;
    }
    
    
    public boolean isPrimo(String value, String type) throws Exception{
        int ocurrencia = 0;
        char caracter;
        int entero;
        double doble;
        if(type.equalsIgnoreCase("caracter")){
            caracter = value.charAt(0);
            doble = (double) caracter;
            entero = (int) doble;
        }else{
            doble = Double.parseDouble(value);
            entero = (int) doble;
        }
        for(int index=1; index<entero; index++){            
            if(entero%index==0){            
                ocurrencia++;                
                if(ocurrencia>2){                
                    return false;                    
                }                
            }            
        }
        
        if(ocurrencia<=2){
            return true;
        }
            
        return false;
    }
}
