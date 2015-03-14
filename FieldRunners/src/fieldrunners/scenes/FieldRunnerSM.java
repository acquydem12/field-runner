/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.scenes;

import fieldrunners.map.FRMapBuilder;
import fieldrunners.map.FRMapBuilderFactory;
import fieldrunners.map.FRMapData;
import fieldrunners.map.FRMapDataFactory;
import fieldrunners.xml.datas.FRXmlRootData;
import java.awt.Point;
import java.util.Collection;
import java.util.Vector;
import tbgameframework.scene.Scene;
import tbgameframework.scene.SceneManager;
import tbgameframework.utils.factory.FactoryAdapter;
import tbgameframework.xml.xmldata.XmlData;
import tbgameframework.xml.xmldata.XmlInstance;

/**
 *
 * @author Uchiha Salm
 */
public class FieldRunnerSM
    extends SceneManager
{
    XmlData mapsData = new FRXmlRootData("Map", FactoryAdapter.<XmlInstance>adapt(FRMapDataFactory.getInst()));
    Vector<String> mapList = new Vector<>();
    int currentLevel = 0;
    
    public FieldRunnerSM()
    {
        super();
        
        (new FieldRunnerSMResources()).LoadingResource();
        
        this.LoadScene();
    }
    
    private void LoadScene()
    {
        mapsData.readFromFile("maps.xml");
        Collection<FRMapData> frMD = mapsData.getAllInstances();
        for (FRMapData frmd : frMD)
        {
            this.mapList.add(frmd.getName());
        }
        
        FROptionScene option = new FROptionScene(this, "Option");
        option.Start();
        this.addScene(option);
        
        Scene mainMenu = new FRMainMenu(this, "MainMenu");
        mainMenu.Start();
        this.addScene(mainMenu);
        
        WinGame wg = new WinGame(this, "WinGame", "a_4");
        this.addScene(wg);
        
        LostGame lg = new LostGame(this, "LostGame", "a_4");
        this.addScene(lg);
        
        this.getScene("MainMenu").Show();
    }
    
    private Scene createFieldRunnerMap(String mapName)
    {
        FRMapData mapData = (FRMapData) this.mapsData.queryFirstInstances(FRMapData.tagName, mapName);
        
        if (mapData != null)
        {
            FRMapBuilder mapBuilder = FRMapBuilderFactory.getInst().createMapBuilder();
            mapBuilder.setName(mapName);
            mapBuilder.setSceneManager(this);
            mapBuilder.setMapBackground(mapData.getMapBackground());
            mapBuilder.setPlayMode(mapData.getPlayMode());
            mapBuilder.setMapBeginPosition(mapData.getMapBeginPosition());
            mapBuilder.setStart(mapData.getStart());
            mapBuilder.setGoal(mapData.getGoal());
            mapBuilder.setGridSize(mapData.getGridSize());
            mapBuilder.setTotalMapSize(mapData.getTotalMapSize());
            mapBuilder.setCellSize(mapData.getCellSize());
            
            Collection<Point> lockedCells = mapData.getLockedPositions();
            for (Point lockedCell : lockedCells)
            {
                mapBuilder.Lock(lockedCell);
            }
            
            mapBuilder.setRounds(mapData.getRoundDatas());
            
            return mapBuilder.createMap();
        }
        
        return null;
    }
    
    public void newGame()
    {
        this.currentLevel = 0;
        
        this.resume();
    }
    
    public void resume()
    {
        String level = this.mapList.get(currentLevel);
        this.removeScene(level);
        
        Scene cLevel = this.createFieldRunnerMap(level);
        this.addScene(cLevel);
        cLevel.Show();
    }
    
    public void nextLevel()
    {
        ++this.currentLevel;
        if (this.currentLevel < this.mapList.size())
        {
            this.resume();
        }
        else
        {
            this.getScene("MainMenu").Show();
        }
    }
}
