/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.decider;

import tbgameframework.control.IButton;

/**
 *
 * @author MrKupi
 */
public class WikiMessage {
    
    // Message contain
    protected String message = "";
    // 
    protected IButton owner;
    
    public WikiMessage(IButton owner, String message){
        this.owner = owner;
        this.message = message;
    }
    
    public IButton getOwner(){
        return this.owner;
    }
    
    public String getMessage(){
        return this.message;
    }
    
    public void setOwner(IButton owner){
        this.owner = owner;
    }
    
    public void setMessage(String message){
        this.message = message;
    }
}
