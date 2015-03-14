/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.scenes;

import fieldrunners.controls.FRButtonBank;
import fieldrunners.controls.RectangleButton;
import fieldrunners.gameobjects.BackgroundObject;
import java.awt.Graphics2D;
import java.util.ArrayList;
import tbgameframework.control.buttons.ButtonBank;
import tbgameframework.math.Vector2D;
import tbgameframework.scene.Scene;
import tbgameframework.scene.SceneManager;
import tbgameframework.sound.SoundManager;
import tbgameframework.sprite.SpriteManager;
import tbgameframework.sprite.imagesprite.ImageSprite;

/**
 *
 * @author MrKupi
 */
public class FRMainMenu
        extends Scene {

    private ImageSprite background;
    private RectangleButton play;
    private RectangleButton resume;
    private RectangleButton scores;
    private RectangleButton option;
    private RectangleButton help;
    private RectangleButton quit;
    protected ArrayList<RectangleButton> UIs = new ArrayList<>();

    public FRMainMenu(SceneManager sm, String sName)
    {
        super(sm, sName);
    }

    @Override
    public void Start()
    {
        LoadContent();
    }

    @Override
    public void Reset() {
    }
    static int count = 0;

    @Override
    public void Update() {
        for (RectangleButton bt : UIs) {
            bt.Update();
        }
        super.Update();
    }

    @Override
    public void Draw(Graphics2D g2D) {
        background.Draw(g2D);
        for (RectangleButton bt : UIs) {
            bt.Draw(g2D);
        }
        super.Draw(g2D);
    }

    public void Over()
    {
    }

    public void Release()
    {
    }

    public void play_Clicked()
    {
        this.Hide();
        ((FieldRunnerSM) this.sceneManager).newGame();
    }

    public void resume_Clicked()
    {
        this.Hide();
        ((FieldRunnerSM) this.sceneManager).resume();
    }

    public void scores_Clicked()
    {
        System.exit(1);
    }

    public void option_Clicked()
    {
        this.Hide();
        sceneManager.getScene("Option").Start();
        sceneManager.getScene("Option").Show();
    }

    public void help_Clicked() {
        System.exit(1);
    }

    public void quit_Clicked() {
        System.exit(1);
    }

    private void LoadContent() {
        background = (ImageSprite) SpriteManager.getInstance().getSprite(menuName);
        background.setDrawObject(BackgroundObject.createObject(new Vector2D(1.0, 1.0), new Vector2D(1.0, 1.0)));
        background.setIstransformable(false);

        play = (RectangleButton) FRButtonBank.getInst().CreateProduct(this, ButtonBank.getInst().getButton(playName), playPos);
        //resume = (RectangleButton)FRButtonBank.getInst().CreateProduct(this, ButtonBank.getInst().getButton(resumeName), resumePos);
        //scores = (RectangleButton)FRButtonBank.getInst().CreateProduct(this, ButtonBank.getInst().getButton(scoresName), scoresPos);
        option = (RectangleButton) FRButtonBank.getInst().CreateProduct(this, ButtonBank.getInst().getButton(optionName), optionPos);
        help = (RectangleButton) FRButtonBank.getInst().CreateProduct(this, ButtonBank.getInst().getButton(helpName), helpPos);
        quit = (RectangleButton) FRButtonBank.getInst().CreateProduct(this, ButtonBank.getInst().getButton(quitName), quitPos);

        UIs.add(play);
        //UIs.add(resume);
        //UIs.add(scores);
        UIs.add(option);
        UIs.add(help);
        UIs.add(quit);
    }
    
    
    @Override
    public void Hide()
    {
        super.Hide();
        
        SoundManager.stop();
    }
    
    @Override
    public void Show()
    {
        super.Show();
        
        SoundManager.play("rainforest", true);
    }
    
    final private Vector2D playPos = new Vector2D(21.0, 44.0);
    final private Vector2D resumePos = new Vector2D(224.0, 16.0);
    final private Vector2D scoresPos = new Vector2D(422.0, 41.0);
    final private Vector2D optionPos = new Vector2D(615.0, 24.0);
    final private Vector2D helpPos = new Vector2D(825.0, 24.0);
    final private Vector2D quitPos = new Vector2D(0.0, 685.0);
    final private String menuName = "bg_mainmenu";
    final private String playName = "PLAY";
    final private String resumeName = "RESUME";
    final private String scoresName = "SCORES";
    final private String optionName = "OPTION";
    final private String helpName = "HELP";
    final private String quitName = "QUIT";
}
