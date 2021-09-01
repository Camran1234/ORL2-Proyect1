/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valiente.orl2.phyton.table;

import java.util.ArrayList;
import valiente.orl2.phyton.instructions.Instruction;

/**
 *
 * @author camran1234
 */
public class Type {
    private Object value="";
    private String id="";
    private String base = "";
    private String father="";
    private ArrayList<Integer> dimension = new ArrayList();
    private int min=0;
    private int max=0;
    private int ambit=0;
    
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFather() {
        return father;
    }

    public void setFather(String father) {
        this.father = father;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public ArrayList<Integer> getDimension() {
        return dimension;
    }

    public void setDimension(ArrayList<Integer> dimension) {
        this.dimension = dimension;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getAmbit() {
        return ambit;
    }

    public void setAmbit(int ambit) {
        this.ambit = ambit;
    }
    
    
    
}
