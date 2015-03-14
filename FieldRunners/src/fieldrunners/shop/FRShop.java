/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.shop;

import fieldrunners.controls.FRButtonBank;
import fieldrunners.controls.RectangleButton;
import fieldrunners.decider.WikiValueTower;
import fieldrunners.scenes.Survival;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import tbgameframework.Board;
import tbgameframework.IDrawable;
import tbgameframework.IUpdateable;
import tbgameframework.control.ButtonImp;
import tbgameframework.control.ButtonState;
import tbgameframework.control.IButton;
import tbgameframework.control.ImageDrawObject;
import tbgameframework.control.buttons.ButtonBank;
import tbgameframework.math.Vector2D;
import tbgameframework.sprite.SpriteManager;
import tbgameframework.sprite.imagesprite.ImageSprite;

/**
 *
 * @author MrKupi
 */
public class FRShop implements IUpdateable, IDrawable{
    
    // Inheritance
    private boolean isEnable = true;
    private boolean isVisible = true;
    private float depth = 1;
    public Survival currentScene;
    
    private boolean isSell = false;
    protected String towerName;
    protected String shineName;
    protected ImageSprite towerShine;
    protected Vector2D towerShinePosition = new Vector2D();
    protected Vector2D towerShineScale = new Vector2D(1.0, 1.0);
            
    protected ArrayList<ButtonImp> buttons = new ArrayList();
    protected Vector2D position;
    
    public FRShop(Survival currentScene, Vector2D shopPosition)
    {
        this.position = shopPosition;
        this.currentScene = currentScene;
    }
    
    public void InitShop()
    {    
        LinkedList<IShopImpl> listShop = (LinkedList)ShopLoader.getInst().getShops(currentScene.getName());
        for(IShopImpl shop : listShop)
        {
            ButtonImp button = (ButtonImp)FRButtonBank.getInst().CreateProduct(this, 
                    ButtonBank.getInst().getButton(shop.getButtonName()), position);
            ((RectangleButton)button).changeText(Integer.toString(WikiValueTower.getInst().getTowerValue(shop.getTowerName())));
            this.Add(button);
        }
    }
    
    @Override
    public void Update()
    {
        if(Board.getInstance().GameInput.IsRightButtonClicked()){
            this.GiveBack();
        }
        
        for(ButtonImp bt : buttons)
        {
            if( !"".equals(bt.getText()) && Integer.parseInt(bt.getText()) > currentScene.gameScore.getMoney() ){
                bt.setButtonState(ButtonState.DISABLE);
            }
            else{
                bt.setButtonState(ButtonState.RELEASE);
            }
            bt.Update();
        }
        if(towerShine != null && towerShine.isVisibled()){
            Vector2D pos = Board.getInstance().GameInput.MousePosition();
            towerShinePosition.x = pos.x - towerShine.getWidth()/2;
            towerShinePosition.y = pos.y - towerShine.getHeight()/2;
        }
    }
    
    @Override
    public void Draw(Graphics2D g2D) 
    {
        for(IButton bt : buttons){
            ((ButtonImp)bt).Draw(g2D);
        }
        if(towerShine != null){
            towerShine.Draw(g2D, towerShinePosition, 0.0f, towerShineScale);
        }
    }
    
    /*
     * Add new Store to shop
     */
    public void Add(ButtonImp button)
    {
        buttons.add(button);
        
        Vector2D currentPos = new Vector2D(this.position);
        ImageDrawObject drawObj = new ImageDrawObject(currentPos, 
                button.getDrawObject().getScale(), button.getDrawObject().getAngle());
        button.setDrawObject(drawObj);
        
        this.position.x += button.Width();
    }
    
    /*
     * Add collection store to shop
     */
    public void Add(Collection<ButtonImp> buttons)
    {
        this.buttons.addAll(buttons);
    }
    
    /*
     * Remove store from shop
     */
    public void Remove(IButton button)
    {
        buttons.remove(button);
    }
    
    /*
     * Get tower's name with button correctively
     */
    public String getTowerName()
    {
        return this.towerName;
    }   
    
    // Return true when button be clicked
    public boolean isAgreeSell()
    {
        return this.isSell;
    }
    
    // Most of set shop from wait for sale to supended
    public void sale()
    {
        this.isSell = false;
        if(towerShine != null){
            towerShine.setVisibled(false);
        }
    }
    
    public void GiveBack(){
        if(towerName != null)
        {
            isSell = false;
            towerShine.setVisibled(false);
        }
    }
    
    public void Click()
    {
        if(isEnable)
        {
            for(IButton bt : buttons)
            {
                if(bt.getButtonState() == ButtonState.CLICK)
                {
                    // get store's tower name
                    towerName = ShopLoader.getInst().getTower(bt.getName(), currentScene.getName());
                    // If has enough money
                    if(towerName != null)
                    {
                        int money = WikiValueTower.getInst().getTowerValue(towerName);
                        if(currentScene.gameScore.getMoney() >= money)
                        {
                            isSell = true;
                            shineName = ShopLoader.getInst().getShineName(bt.getName(), currentScene.getName());
                            towerShine = (ImageSprite)SpriteManager.getInstance().getSprite(shineName);
                            towerShine.setVisibled(true);
                        }
                    }
                }
            }
        }
    }
    
    public void Over()
    {
    }
    
    public void Release()
    {
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
    public boolean isVisibled()
    {
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
}
