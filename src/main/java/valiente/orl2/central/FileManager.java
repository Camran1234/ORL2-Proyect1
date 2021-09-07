/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valiente.orl2.central;

import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import javax.swing.JOptionPane;
/**
 *
 * @author camran1234
 */
public class FileManager {
    
    public void createFile(String path, String text){
        try{
            File file = new File(path);
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(text);
            bw.close();
        }
        catch (IOException e){
            JOptionPane.showMessageDialog(null, "No se pudo crear el archivo: "+e.getMessage(),"Error de archivo", JOptionPane.ERROR);
        }
    }
    
    public String downloadFile(String path){ 
        String text="";
        StringBuffer fileContent = new StringBuffer();
        try(BufferedReader br = new BufferedReader(new FileReader(path))){
            //Reading file
            String sCurrentLine;
            while((sCurrentLine=br.readLine())!=null){
                fileContent.append(sCurrentLine).append("\n");
            }
            text = fileContent.toString();
        }catch(Throwable e){
            JOptionPane.showMessageDialog(null, "No se pudo obtener el archivo: "+e.getMessage(),"Error de archivo", JOptionPane.ERROR);
        }
        return text;
    }
    
}
