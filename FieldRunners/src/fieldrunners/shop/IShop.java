/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.shop;

/**
 *
 * @author MrKupi
 */
public interface IShop {
    
    String getTowerName();
    String getButtonName();
    String getMapName();
    String getShineName();
    
    static final String tagTowerName = "TowerName";
    static final String tagButtonName = "ButtonName";
    static final String tagMapName = "MapName";
    static final String tagShineName = "ShineName";
}
