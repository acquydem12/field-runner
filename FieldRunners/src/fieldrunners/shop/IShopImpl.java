/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.shop;


public class IShopImpl implements IShop {

    String towerName;
    String buttonName;
    String mapName;
    String shineName;
    
    public IShopImpl(String mapName, String buttonName, String towerName, String shineName){
        this.mapName = mapName;
        this.buttonName = buttonName;
        this.towerName = towerName;
        this.shineName = shineName;
    }
    
    @Override
    public String getTowerName() {
        return this.towerName;
    }

    @Override
    public String getButtonName() {
        return this.buttonName;
    }

    @Override
    public String getMapName() {
        return this.mapName;
    }
    
    @Override
    public String getShineName() {
        return this.shineName;
    }
}
