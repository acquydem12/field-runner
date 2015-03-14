/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.decider;

import fieldrunners.controls.FRButtonBank;
import fieldrunners.controls.RectangleButton;
import fieldrunners.gameobject.MoneyShow;
import fieldrunners.gameobjects.tower.TowerBYS;
import fieldrunners.map.frgrid.Cell;
import fieldrunners.map.frgrid.Grid2D;
import fieldrunners.scenes.Survival;
import java.awt.Graphics2D;
import java.awt.Point;
import tbgameframework.IDrawable;
import tbgameframework.IUpdateable;
import tbgameframework.control.ButtonImp;
import tbgameframework.control.ButtonState;
import tbgameframework.control.ImageDrawObject;
import tbgameframework.control.buttons.ButtonBank;
import tbgameframework.math.Vector2D;
import tbgameframework.sprite.SpriteManager;
import tbgameframework.sprite.fontsprite.FontSprite;

/**
 *
 * @author MrKupi
 */
public class Actioner implements IUpdateable, IDrawable
{
    final private Vector2D fontPosition = new Vector2D(58.0, 8.0);
    // Radius when show two button
    protected int radius = 50;
    // Two button sell and upgrade
    protected RectangleButton upgrade;
    protected RectangleButton sell;
    // Flag on when active
    protected boolean isActive = false;
    
    // Store current Cell
    protected Cell currentCell;
    protected Grid2D grid;
    
    protected ImageDrawObject upgradeDrawObject;
    protected ImageDrawObject sellDrawObject;
    
    private boolean isEnable = false;
    private boolean isVisible = false;
    private float depth = 1.0f;
    
    private Survival scene;
    
    public Actioner(Grid2D grid, Survival s)
    {
        this.grid = grid;
        
        upgradeDrawObject = new ImageDrawObject(new Vector2D(0, 0), new Vector2D(1, 1), 0);
        sellDrawObject = new ImageDrawObject(new Vector2D(0, 0), new Vector2D(1, 1), 0);
        
        upgrade = (RectangleButton)FRButtonBank.getInst().CreateProduct(this, ButtonBank.getInst().getButton("Upgrade"), upgradeDrawObject.getPosition());
        sell = (RectangleButton)FRButtonBank.getInst().CreateProduct(this, ButtonBank.getInst().getButton("Sell"), sellDrawObject.getPosition());
        
        this.scene = s;
    }
    
    @Override
    public void Update()
    {
        if(isEnable)
        {
            if(!"".equals(upgrade.getText()) && Integer.parseInt(upgrade.getText()) > scene.gameScore.getMoney()){
                upgrade.setButtonState(ButtonState.DISABLE);
            }
            else{
                upgrade.setButtonState(ButtonState.RELEASE);
            }
            upgrade.Update();
            sell.Update();
        }
        else{
            this.isActive = false;
        }
    }

    @Override
    public void Draw(Graphics2D g2D)
    {
        if(isVisible){
            ((ButtonImp)upgrade).Draw(g2D);
            ((ButtonImp)sell).Draw(g2D);
        }
    }
    
    /*
     * Settle action when send input
     */
    public void Settle(Cell cell)
    {
        if(!isActive && cell.getCellTower() != null)
        {
            this.isActive = true;
            
            // Check if has tower in cell
            // get cell position and do anything
            this.currentCell = cell;

            // Get cell position
            Vector2D position = new Vector2D(cell.getPosition());

            // Add new button must be do here
            // Arrange position for two button
            position.x = cell.getBound().x + cell.getBound().width + radius;
            upgradeDrawObject.setPosition(position);
            
            position.x = cell.getPosition().x - (sell.Width() + radius);
            sellDrawObject.setPosition(position);

            // Set new position for two button
            upgradeDrawObject.setScale(cell.getScale());
            sellDrawObject.setScale(cell.getScale());

            // Set drawobject for button
            upgrade.setDrawObject(upgradeDrawObject);
            sell.setDrawObject(sellDrawObject);
            
            // Get && calc money upgrade and sell
            int priceBuy = currentCell.getCellTower().getTowerInShop().getPrice();
            int priceSell = (int)(priceBuy*0.33);
            int priceUpgrade = (int)(priceBuy*0.5);
            
            // Update money for all button
            upgrade.changeText(Integer.toString(priceUpgrade));
            sell.changeText(Integer.toString(priceSell));
            
            // Active two button to show at new position
            this.Active();
        }
        else
        {
            this.Deactive();
            this.isActive = false;
        }
    }

    /*
     * Click method for button event
     */
    public void Click()
    {
        // Deactive two button - hide it
        int priceBuy = currentCell.getCellTower().getTowerInShop().getPrice();
        int priceSell = (int)(priceBuy*0.33);
        int priceUpgrade = (int)(priceBuy*0.5);
        if (upgrade.getButtonState() == ButtonState.CLICK && 
                scene.gameScore.getMoney() >= priceUpgrade)
        {
            if(upgradeTower()){
                scene.gameScore.AddMoney(-priceUpgrade);
            }
        }
        
        if (sell.getButtonState() == ButtonState.CLICK)
        {
            sellTower();
            scene.addComponent(new MoneyShow("+" + Integer.toString(priceSell), (FontSprite)SpriteManager.getInstance().getSprite("font_gold"), 
                    currentCell.getPosition()));
            scene.gameScore.AddMoney(priceSell);
        }
        this.Deactive();
    }
    
    /*
     * Over method for button event
     */
    public void Over()
    {
    }
    
    /*
     * Release method for button event
     */
    public void Release()
    {
    }
    
    public boolean getActive()
    {
        return this.isActive;
    }
    
    /*
     * Set radius from button center to two button
     */
    public void setRadius(int radius)
    {
        this.radius = radius;
    }
    
    /*
     * active all state
     */
    public void Active()
    {
        this.setEnabled(true);
        this.setVisibled(true);
    }
    
    /*
     * deactive all state
     */
    public void Deactive()
    {
        this.setEnabled(false);
        this.setVisibled(false);
    }
    
    @Override
    public boolean isEnabled() 
    {
        return this.isEnable;
    }

    @Override
    public void setEnabled(boolean isEn)
    {
        this.isEnable = isEn;
    }

    @Override
    public boolean isVisibled() {
        return this.isVisible;
    }

    @Override
    public void setVisibled(boolean isVs)
    {
        this.isVisible = isVs;
    }

    @Override
    public float getDepth() 
    {
        return this.depth;
    }

    @Override
    public void setDepth(float depth) 
    {
        this.depth = depth;
    }

    private boolean upgradeTower()
    {
        TowerBYS tower = currentCell != null ? currentCell.getCellTower() : null;
        if (tower != null && tower.getLevel() < TowerBYS.LEVEL_MAX)
        {
            this.currentCell.getCellTower().upgrade();
            return true;
        }
        return false;
    }

    private void sellTower()
    {
        Point cellPos = grid.getCellPositionOf(currentCell.getCenter().asPoint());
        this.grid.removeTowerAt(cellPos);
        
        this.scene.runnerFindNewPath();
    }
}
