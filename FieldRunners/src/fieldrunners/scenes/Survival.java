/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.scenes;

import fieldrunners.gameLogic.GameScore;
import fieldrunners.gameobject.GameMessage;
import fieldrunners.gameobjects.*;
import fieldrunners.gameobjects.frimp.*;
import fieldrunners.gameobjects.runners.*;
import fieldrunners.gameobjects.tower.*;
import fieldrunners.map.frgrid.*;
import fieldrunners.map.round.*;
import fieldrunners.mapSystem.FRSystem;
import fieldrunners.pathfinding.astar.ASPathFinder;
import fieldrunners.pathfinding.astar.map2d.Map2D;
import fieldrunners.pathfinding.astar.map2d.Map2DFinderFactory;
import fieldrunners.pathfinding.astar.map2d.PointNode;
import fieldrunners.pathfinding.core.FinderFactory;
import fieldrunners.pathfinding.core.Node;
import fieldrunners.pathfinding.core.Path;
import fieldrunners.pathfinding.core.PathFinder;
import java.awt.*;
import java.util.*;
import tbgameframework.*;
import tbgameframework.math.*;
import tbgameframework.scene.*;
import tbgameframework.sound.SoundManager;
import tbgameframework.sprite.SpriteManager;
import tbgameframework.sprite.fontsprite.FontSprite;
import tbgameframework.sprite.imagesprite.*;
import tbgameframework.utils.factory.Factory;
 
/**
 *
 * @author Uchiha Salm
 */
public class Survival
    extends Scene
{
    public static final int PLAYMODE_SURVIVAL = 0;
    
    private Grid2D grid;
    private ImageSprite background;
    private long timeCounter = 0;
    private long delay = 2000;
    private Vector2D totalSize = new Vector2D();
    boolean isPaused = false;
    
    private Point start = new Point();
    private Point goal = new Point();
    
    private TowerGrowManager tgm;
    public GameScore gameScore;
    
    private FRSystem system;
    
    private RoundManager roundMan;
    GameMessage gm;
    
    // test
    private int nObjs = 0;

    private void winLevel()
    {
        this.Hide();
        this.sceneManager.removeScene(this);
        
        this.sceneManager.getScene("WinGame").Start();
        this.sceneManager.getScene("WinGame").Show();
    }

    private void loseLevel()
    {
        this.Hide();
        this.sceneManager.removeScene(this);
        
        this.sceneManager.getScene("LostGame").Start();
        this.sceneManager.getScene("LostGame").Show();
    }
    
    private static class SurvivalRunnerDynData
        implements RunnerDynamicData
    {
        private Vector2D start;
        private Point goal;
        private Grid2D grid;
        private GameScore gs;
        private Scene s;
        
        public SurvivalRunnerDynData(Survival owner)
        {
            this.start = owner.grid.getCellAt(owner.start).getCorner();
            this.goal = owner.goal;
            this.grid = owner.grid;
            this.gs = owner.gameScore;
            this.s = owner;
        }
        
        @Override
        public Vector2D startPosition()
        {
            return this.start;
        }

        @Override
        public Grid2D Grid()
        {
            return this.grid;
        }

        @Override
        public Point goal()
        {
            return this.goal;
        }

        @Override
        public GameScore gameScore()
        {
            return this.gs;
        }

        @Override
        public Scene scene()
        {
            return s;
        }
        
    }     
    
    public Survival(SceneManager manager, String sName, Vector2D totalSz, Grid2D grid)
    {
        super(manager, sName);
        
        this.grid = grid;
        this.tgm = new TowerGrowManager(this.grid, this);
        
        this.totalSize.setValues(totalSz);
        this.CreateGameQuadTree(new Rectangle(0, 0, (int) this.totalSize.x, (int) this.totalSize.y));
        
        gameScore = GameScore.Factory.getInst().createProduct(this.getName());
        system = new FRSystem(this, new Vector2D(0.0, 0.0));
        system.InitShop();
        this.roundMan = new RoundManager(this, (new Factory<RunnerDynamicData>()    
        {
           private Survival owner;

           @Override
           public RunnerDynamicData createProduct()
           {
               return new SurvivalRunnerDynData(this.owner);
           }

           public Factory<RunnerDynamicData> init(Survival s)
           {
               this.owner = s;

               return this;
           }
        }).init(this));
        
        this.gm = new GameMessage((FontSprite) SpriteManager.getInstance().getSprite("font_gold"), new Vector2D(0, 650), new Vector2D(1024, 650));
        this.addComponent(gm);
        
//        gm.setText(this.roundMan.getCurrentRound().getName());
    }
    
    @Override
    public void Start()
    {
        // play sound
    }
    
    @Override
    public void Update()
    {
        synchronized (this.components)
        {
            system.Update();
            this.tgm.Update();
            this.roundMan.Update();
            if (this.roundMan.isRoundCompleted())
            {
                if (this.roundMan.isCompleted())
                {
                    this.winLevel();
                }
                else
                {
                    this.roundMan.nextRound();
                    gm.setText(this.roundMan.getCurrentRound().getName());
                    gm.start();
                }
            }
            else if (this.gameScore.getHeart() <= 0)
            {
                this.loseLevel();
            }

            this.removeCompletedComponents();

            gameScore.Update();
            
            if (!this.isPaused)
            {
                super.Update();
            }
        }
    }
    
    private void removeCompletedComponents()
    {
        Collection<GameObject> copy = new ArrayList<>(this.components);
        this.components.clear();
        
        for (GameObject o : copy)
        {
            if (o == null)
            {
                continue;
            }
            if (o instanceof ICompleteable)
            {
                ICompleteable cp = (ICompleteable) o;
                
                if (!cp.isCompleted())
                {
                    this.components.add(o);
                }
            }
            else
            {
                    this.components.add(o);
            }
        }
    }
    
    public void runnerFindNewPath()
    {
        Collection<RunnerBYS> Rbyss = new ArrayList<>();
        
        for (Object o : this.components)
        {
            if (o instanceof FRObject)
            {
                FRObject frO = (FRObject) o;
                FRBraceYourSelf bys = frO.getBYS();
                if (bys instanceof RunnerBYS)
                {
                    RunnerBYS Rbys = (RunnerBYS) bys;
                    Rbyss.add(Rbys);
                }
            }
        }
        
        for (RunnerBYS bys : Rbyss)
        {
            bys.mapChanged();
        }
    }
    
    public boolean canGrow(Point cellPos)
    {
        Collection<Point> runnerPos = new HashSet<>();
        
        runnerPos.add(start);
        for (Object o : this.components)
        {
            if (o instanceof FRObject)
            {
                FRObject frO = (FRObject) o;
                FRBraceYourSelf bys = frO.getBYS();
                if (bys instanceof RunnerBYS)
                {
                    RunnerBYS Rbys = (RunnerBYS) bys;
                    if (grid.getBound().contains(Rbys.getPosition()))
                    {
                        Point rCell = grid.getCellPositionOf(Rbys.getPosition().asPoint());
                        
                        if (!runnerPos.contains(rCell))
                        {
                            runnerPos.add(rCell);
                        }
                    }
                }
            }
        }
        
        Map2D checkMap = new Map2D(this.grid);
        checkMap.setState(new PointNode(cellPos), Map2D.LOCKED);
        PathFinder pf = new ASPathFinder();
        
        for (Point rPos : runnerPos)
        {
            try
            {
                FinderFactory ff = new Map2DFinderFactory(rPos, goal, checkMap, pf);
                Path rPath = pf.find(ff);
                if (rPath == null || rPath.isEmpty())
                {
                    return false;
                }
                
                Iterator<Node> node = rPath.iterator();
                PointNode pNode = (PointNode) node.next();

                if (!node.hasNext() && (pNode.getCoordinate().equals(cellPos) || pNode.getCoordinate().equals(goal)))
                {
                    return false;
                }
            }
            catch (Exception ex)
            {
                return false;
            }
        }
        
        return true;
    }
    
    @Override
    public void Draw(Graphics2D g2D)
    {
        synchronized (this.components)
        {
            background.Draw(g2D);
    //        grid.Draw(g2D);

            super.Draw(g2D);
            tgm.Draw(g2D);
            system.Draw(g2D);
            gameScore.Draw(g2D);
        }
    }
    
    public void setBackground(ImageSprite background)
    {
        this.background = background;
    }
    
    public void setTotalSize(Vector2D totalSize)
    {
        this.totalSize.setValues(totalSize);
        this.CreateGameQuadTree(new Rectangle(0, 0, (int) this.totalSize.x, (int) this.totalSize.y));
    }
    
    public void setStart(Point startPos)
    {
        this.start.setLocation(startPos);
    }
    
    public void setGoal(Point goalPos)
    {
        this.goal.setLocation(goalPos);
    }
    
    public void setRounds(Collection<RoundData> rnds)
    {
        this.roundMan.addRounds(rnds);
    }

    @Override
    public void Reset()
    {
    }
    
    @Override
    public void Hide()
    {
        super.Hide();
        
        SoundManager.stop();
        Board.getInstance().setFPS(45);
    }
    
    @Override
    public void Show()
    {
        super.Show();
        
        SoundManager.play("volcano", true);
    }
    
    public void setPaused(boolean val)
    {
        this.isPaused = val;
    }
    
    public boolean isPaused()
    {
        return this.isPaused;
    }
}
