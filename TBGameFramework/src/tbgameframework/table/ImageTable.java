/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tbgameframework.table;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author MrKupi
 */
public class ImageTable implements Itable{

    static ImageTable table = new ImageTable();
    Map<String, String> linkDict = new HashMap();
    
    private ImageTable(){
    }
    
    @Override
    public Object getAttribute(Object name) {
        if(linkDict.containsKey((String)name)){
            return linkDict.get((String)name);
        }
        return null;
    }

    @Override
    public void setAttribute(Object name, Object value) {
        linkDict.put((String)name, (String)value);
    }

    static public Itable getInst() {
        return table;
    }
}
