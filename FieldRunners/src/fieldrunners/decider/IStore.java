/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.decider;

/**
 *
 * @author MrKupi
 */
public interface IStore {
    
    String getTowerName();
    int getValue();
    
    static final String tagTowerName = "TowerName";
    static final String tagValue = "Value";
}
