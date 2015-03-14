/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tbgameframework;

import java.awt.Cursor;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author MrKupi
 */
// Singleton pattern class
public class Window extends JFrame{
    
    static private Window window;
    
    private Board board = Board.getInstance();
    int m_Width = 0;
    int m_Height = 0;
    boolean isFullScreen = true;
    String m_WindowTitle = "";
    
    // ???
    JButton button;
    
    private Window()
    {
    }
    
    public void Initialize()
    {
        if(this.isFullScreen)
        {
            setUndecorated(true);
            GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice device = environment.getDefaultScreenDevice();
            device.setFullScreenWindow(this);
            
            // ???
            button = new JButton();
            button.setText("Start");
            button.setVisible(true);
        }
        else
        {
            setLocationRelativeTo(null);
            setResizable(false);
            setTitle(m_WindowTitle);
            setSize(m_Width, m_Height);
            setLocation(0, 0);
        }
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        
        setGameCursor();
//        setContentPane(board);
        this.setContentPane(board);
    }
    
    static public Window getInstance()
    {
        if(window == null){
            window = new Window();
        }
        return window;
    }
    
    public void setWindowsSize(int width, int height){
        m_Width = width;
        m_Height = height;
    }
    
    public void setWindowTitle(String title){
        m_WindowTitle = title;
    }
    
    public void setFullScreen(boolean isFullScreen){
        this.isFullScreen = isFullScreen;
    }
    
    public void setGameCursor()
    {
        // Transparent 16 x 16 pixel cursor image.
        BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);

        // Create a new blank cursor.
        Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
            cursorImg, new Point(0, 0), "blank cursor");

        // Set the blank cursor to the JFrame.
        //setCursor(blankCursor);
    }
    
    public void setGameWindow(Game game)
    {
        board.setGame(game);
    }
}