/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.scenes;

import fieldrunners.decider.WikiValueTower;
import fieldrunners.gameLogic.GameRuleBank;
import fieldrunners.gameobjects.frimp.FROFBank;
import fieldrunners.shop.ShopLoader;
import fieldrunners.states.statesprite.StateSpriteFactory;
import tbgameframework.control.buttons.ButtonBank;
import tbgameframework.image.ImageFactory;
import tbgameframework.image.ImageResourceContent;
import tbgameframework.sound.load.SoundLoader;
import tbgameframework.sprite.fontsprite.FontFactory;
import tbgameframework.sprite.imagesprite.SpriteFactory;

/**
 *
 * @author Uchiha Salm
 */
public class FieldRunnerSMResources
    extends ImageResourceContent
{
    @Override
    public void InputString()
    {
        this.AddString("src/resources/xmls/Image.xml", "ImageContent", ImageFactory.getInstance());
        this.AddString("src/resources/xmls/sprites.xml", "SpriteInfo", SpriteFactory.getInstance());
        this.AddString("src/resources/xmls/Font.xml", "FontInfo", FontFactory.getInstance());
        
        GameRuleBank.getInst().readFromFile("src/resources/xmls/gamerules.xml");
        ShopLoader.getInst().readFromFile("src/resources/xmls/shop.xml");
        ButtonBank.getInst().readFromFile("src/resources/xmls/buttons.xml");
        WikiValueTower.getInst().readFromFile("src/resources/xmls/store.xml");
        
        FROFBank.getInst().addRunnerFromFile("runners.xml");
        FROFBank.getInst().addTowerFromFile("towers.xml");
        FROFBank.getInst().addMissileFromFile("missiles.xml");
        
        this.AddString("statesprites.xml", "StateSprite", StateSpriteFactory.getInst());
        
        SoundLoader.getInst().readFromFile("src/resources/xmls/sounds.xml");
    }
}