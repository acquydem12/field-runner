/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners;

import fieldrunners.gameobjects.frimp.state.FRStateFactory;
import fieldrunners.gameobjects.tower.TowerState;
import fieldrunners.scenes.FieldRunnerSM;
import fieldrunners.states.State;
import fieldrunners.states.StateBank;
import fieldrunners.states.StateFactoryPool;
import java.awt.Rectangle;
import tbgameframework.Board;
import tbgameframework.math.Vector2D;
import tbgameframework.utils.factory.FactoryAdapter;

/**
 *
 * @author Uchiha Salm
 */
public class Game
    extends tbgameframework.Game
{
    private static final float cameraRate = 1.0f;
    private static final float cameraZoomRate = 0.09f;
    private static final float cameraMaxZoom = 3.0f;
    private static final float cameraMinZoom = 0.2f;
    private static final int cameraLeftEdge = 0;
    private static final int cameraBottomEdge = 0;
    private static final int cameraRightEdge = 2275;    // ???
    private static final int cameraTopEdge = 1707;  // ???
    private static final Vector2D cameraMovement = new Vector2D(3, 3);
    
    public static class Factory
        implements tbgameframework.utils.factory.Factory<Game>
    {
        @Override
        public Game createProduct()
        {
            return new Game();
        }
        
        private Factory()
        {
            
        }
        
        private static Factory inst = new Factory();
        
        public static Factory getInst()
        {
            return inst;
        }
    }
    
    private Game()
    {
        
    }
            
    private void cameraInit()
    {
        Board.getInstance().camera.setFixRectangleArea(new Rectangle(cameraLeftEdge, cameraBottomEdge, 
                                                            cameraRightEdge, cameraTopEdge));
        Board.getInstance().camera.setMinZoom(cameraMinZoom);
        Board.getInstance().camera.setMaxZoom(cameraMaxZoom);
        Board.getInstance().camera.setRate(cameraRate);
        Board.getInstance().camera.setCameraZoomRate(cameraZoomRate);
        Board.getInstance().camera.setCameraMoveDistance(cameraMovement);
    }
    
    private void smInit()
    {
        this.sceneManager = new FieldRunnerSM();
    }
    
    private void stInit()
    {
        this.cameraInit();
        StateFactoryPool.getInst().addFactory("FRState", FactoryAdapter.<State>adapt(FRStateFactory.getInst()));
        StateFactoryPool.getInst().addFactory("TowerState", new tbgameframework.utils.factory.Factory<State>()
        {

            @Override
            public State createProduct()
            {
                return new TowerState();
            }
        });
        
        StateBank.getInst().readFromFile("frstates.xml");
        StateBank.getInst().readFromFile("towerstates.xml");
    }
    
    @Override
    protected void Initialize()
    {
        this.cameraInit();
        this.stInit();
        this.smInit();
        
        Board.getInstance().setFPS(60);
    }

    @Override
    protected void LoadContent()
    {
    }
}