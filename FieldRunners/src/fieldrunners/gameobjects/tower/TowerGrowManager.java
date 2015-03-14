/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.gameobjects.tower;

import fieldrunners.decider.Actioner;
import fieldrunners.decider.WikiValueTower;
import fieldrunners.gameobjects.frimp.FROFBank;
import fieldrunners.gameobjects.frimp.FRObject;
import fieldrunners.gameobjects.frimp.FRObjectFactory;
import fieldrunners.map.frgrid.Grid2D;
import fieldrunners.scenes.Survival;
import fieldrunners.shop.FRShop;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.Collection;
import tbgameframework.Board;
import tbgameframework.IDrawable;
import tbgameframework.IUpdateable;
import tbgameframework.control.ButtonImp;
import tbgameframework.math.Vector2D;
import tbgameframework.scene.Scene;
import tbgameframework.sound.SoundManager;

/**
 * Manager grow tower
 * @author Uchiha Salm
 */
public class TowerGrowManager
        implements IUpdateable, IDrawable
{   
    private FRShop shop;
    private Actioner actioner;
    
    private Grid2D gameGrid;
    private Survival scene;
    private boolean isEn = true;
    private boolean isGrow = true;
    
    public TowerGrowManager(Grid2D grid, Survival s)
    {
        this.gameGrid = grid;
        this.scene = s;
        shop = new FRShop(scene, new Vector2D(500.0, 0.0));
        shop.InitShop();
        actioner = new Actioner(this.gameGrid, this.scene);
    }
    
    @Override
    public void Update()
    {
        ManageShop();
    }
    
    @Override
    public void Draw(Graphics2D g2D) 
    {
        // draw all store in shop
        shop.Draw(g2D);
        actioner.Draw(g2D);
    }
    
    /*
     * Manage all shop logic and button logic
     */
    private void ManageShop()
    {
        // Check shop and allow grow tower
        growTower();
        // Update actioner
        actioner.Update();
        // Update all store in shop
        shop.Update();
        // Check when mouse click on grid to upgrade or sell
        CheckActionOnGrid();
    }
    
    /*
     * Check when mouse click on grid
     * if has tower call action on this tower
     */
    public boolean CheckActionOnGrid()
    {
        Vector2D location = Board.getInstance().GameInput.MousePosition();
        if(Board.getInstance().GameInput.IsLeftButtonClicked() && gameGrid.getBound().contains(location))
        {
            Point point = this.gameGrid.getCellPositionOf((int)location.x, (int)location.y);
            if(!isGrow && this.gameGrid.isHasTowerAt(point))
            {
                actioner.Settle(this.gameGrid.getCellAt(point));
            }
            else
            {
                actioner.Deactive();
            }
        }
        return false;
    }
    
    /*
     * Check shop for agree with money to grow tower
     * If true, update shop have sale tower
     * else do nothing
     */
    private boolean growTower()
    {
        if(shop.isAgreeSell() && Board.getInstance().GameInput.IsLeftButtonClicked())
        {
            if(this.growTowerAt(shop.getTowerName(), Board.getInstance().GameInput.MousePosition()))
            {
                shop.sale();
                isGrow = true;
                this.scene.gameScore.AddMoney(-WikiValueTower.getInst().getTowerValue(shop.getTowerName()));
                SoundManager.play("UI_button2", false);
                return true;
            }
            else
            {
                SoundManager.play("invalid", false);
            }
        }
        isGrow = false;
        return false;
    }
    
    /*
     * Grow tower at cell
     */
    public boolean growTowerAt(String towerName, Vector2D position)
    {
        if(gameGrid.getBound().contains(position))
        {
            final Point cell = gameGrid.getCellPositionOf(position.asPoint());
            if (!gameGrid.isHasTowerAt(cell) && this.scene.canGrow(cell))
            {
                // get tower choosed :)
                FROFBank.getInst().setTowerDynamicData(new TowerDynamicData() {

                    @Override
                    public Vector2D getPosition()
                    {
                        return gameGrid.getCellAt(cell).getCorner();
                    }
                    

                    @Override
                    public Scene getScene() 
                    {
                        return scene;
                    }
                });

                
                FRObjectFactory objF = FROFBank.getInst().getFactory(towerName);
                FRObject tower = new FRObject(objF);
                scene.addComponent(tower);
                this.gameGrid.growTowerAt(cell, (TowerBYS) tower.getBYS());

                scene.runnerFindNewPath();
                
                return true;
            }
        }
        return false;
    }
    
    /*
     * Add a store to shop
     */
    public void AddStoreToShop(ButtonImp store)
    {
        if(shop != null){
            shop.Add(store);
        }
    }
    
    /*
     * Add a collection of store to shop
     */
    public void AddStoreToShop(Collection<ButtonImp> stores)
    {
        if(shop != null){
            shop.Add(stores);
        }
    }
    
    /*
     * Return current shop
     */
    public FRShop getShop()
    {
        return this.shop;
    }
    
    @Override
    public boolean isEnabled()
    {
        return isEn;
    }

    @Override
    public void setEnabled(boolean isEn)
    {
        this.isEn = isEn;
    }

    @Override
    public boolean isVisibled() 
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setVisibled(boolean isVs) 
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public float getDepth()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setDepth(float depth)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
