/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tbgameframework;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 *
 * @author MrKupi
 */
public abstract class Canvas extends JPanel{
    
    public Canvas(){
        
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.setBackground(Color.RED);
        
    }
    
    public abstract void Draw(Graphics2D g2D);
    
    @Override 
    public synchronized void paintComponent(Graphics g)
    {
        Graphics2D g2D = (Graphics2D)g;
        super.paintComponent(g2D);
        
        Draw(g2D);
    }
}
