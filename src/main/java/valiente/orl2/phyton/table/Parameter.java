/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valiente.orl2.phyton.table;

/**
 *
 * @author camran1234
 */
public class Parameter {
    private String type="";
    private String id="";

    public Parameter(String type, String id){
        this.type = type;
        this.id = id;
    }
    
    public boolean compareType(String type){
        if(this.type.equalsIgnoreCase(type)){
            return true;
        }else{
            return false;
        }
    }
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
   
    
}
