/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valiente.orl2.phyton.table;
import java.util.ArrayList;
/**
 *
 * @author camran1234
 */
public class Symbol {
    private String id="";
    //variable, method, class, function...
    private String category="";
    private Type type = null;
    private String value="";
    private int numberParameters=0;
    private ArrayList<Parameter> parameters = new ArrayList();
    private Object direction = this;
    private int ambit = 0;

    public void setValue(String value){
        this.value = value;
    }
    
    public String getValue(){
        return value;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getNumberParameters() {
        return numberParameters;
    }

    public void setNumberParameters(int numberParameters) {
        this.numberParameters = numberParameters;
    }

    public ArrayList<Parameter> getParameters() {
        return parameters;
    }

    public void setParameters(ArrayList<Parameter> parameters) {
        this.parameters = parameters;
    }

    public Object getDirection() {
        return direction;
    }

    public void setDirection(Object direction) {
        this.direction = direction;
    }

    public int getAmbit() {
        return ambit;
    }

    public void setAmbit(int ambit) {
        this.ambit = ambit;
    }
    
    
}
