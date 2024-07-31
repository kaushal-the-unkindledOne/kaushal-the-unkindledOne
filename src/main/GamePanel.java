package main;

import java.awt.Color;
import entity.Entity;
import tile.Tile;
import tile.TileManager;
import tile_interactive.InteractiveTile;
import ai.PathFinder;
import environment.environmentManager;
import data.SaveLoad;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JFrame;
import javax.swing.JPanel;

import entity.Player;
import tile.Map;

public class GamePanel extends JPanel implements Runnable{

	//SCREEN SETTINGS
    final int originalTileSize = 16; //16x16 tile
    final int scale = 3;
    public final int tilesize = originalTileSize * scale; //48x48 tile
    public final int maxScreenCol = 20;
    public final int maxScreenRow = 12;
    public final int screenWidth = tilesize * maxScreenCol; //768 tiles
    public final int screenHeight= tilesize * maxScreenRow; //432 tiles
    
    //World settings
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int maxMap = 10;
    public int currentMap = 0;
    
    //FULL SCREEN
    int screenWidth2 = screenWidth;
    int screenHeight2 = screenHeight;
    BufferedImage tempScreen;
    Graphics2D g2;
    public boolean fullScreenOn = false;
    
     //FPS FOR THE GAME
    int FPS = 60;
    
    //SYSTEM
     public TileManager tileM = new TileManager(this);
     public keyHandler keyH = new keyHandler(this);
     Sound music = new Sound(); 
     Sound se = new Sound(); 
     public CollisionChecker cChecker = new CollisionChecker(this);
     public ItemSetter iSetter = new ItemSetter(this);
     public UI ui = new UI(this);
     public EventHandler eHandler = new EventHandler(this);
     Config config = new Config(this);
     public PathFinder pFinder = new PathFinder(this);
     Map map = new Map(this);
     SaveLoad saveLoad = new SaveLoad(this);
     public EntityGenerator eGenerator = new EntityGenerator(this);
     Thread gamThread;
     environmentManager eManager = new environmentManager(this);
     public cutsceneManager cManager = new cutsceneManager(this);
     
     //ENTITY FOR PLAYER AND OBJECT
     public Player player = new Player(this, keyH);
     public Entity obj[][] = new Entity[maxMap][20];
	 public Entity npc[][] = new Entity[maxMap][10];
	 public Entity monster[][]= new Entity[maxMap][20];
	 public InteractiveTile iTile[][] = new InteractiveTile[maxMap][50];
	 public ArrayList<Entity> particleList = new ArrayList<>();
	 ArrayList<Entity> entitylist = new ArrayList<>();
	 //public ArrayList<Entity> projectileList = new ArrayList<>();
	 public Entity projectile[][] = new Entity[maxMap][20];
	 
	//GAME STATE
	 public int gameState;
	 public final int titleState = 0;
	 public final int playState = 1;
	 public final int pauseState = 2;
	 public final int dialougeState = 3;
	 public final int characterScreenState = 4;
	 public final int optionState = 5;
	 public final int gameOverState = 6;
	 public final int transitionState = 7;
	 public final int tradeState = 8;
	 public final int sleepState = 9;
	 public final int mapState = 10;
	 public final int cutsceneState = 11;
    
	 //OTHERS
	 public boolean bossBattleOn = false;
	 
	 //AREA EFFECT
	 public int currentArea;
	 public int nextArea;
	 public final int outside = 50;
	 public final int indoor = 51;
	 public final int dungeon = 52;
	 
	 public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
  }
    
    public void setupGame() {
    	
    	iSetter.setObject();
    	iSetter.setNPC();
    	iSetter.setMonster();
    	iSetter.setInteractiveTile();
    	eManager.setUp();
    	playMusic(0);
    	gameState = titleState;
    	currentArea = outside;
    	
    	tempScreen = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);
    	g2 = (Graphics2D)tempScreen.getGraphics();
    	
    	if(fullScreenOn == true) {
    		setFullScreen();
    	}
    }
    
    public void resetGame(boolean restart) {
    	
    	stopMusic();
    	currentArea = outside;
    	removeTempEntity();
    	bossBattleOn = false;
    	player.setDefaultPosition();
    	player.restoreStatus();
    	player.resetCounter();
    	iSetter.setNPC();
    	iSetter.setMonster();
    	
    	if(restart == true) {
    		player.setDefaultValues();
        	player.setItems();
        	iSetter.setObject();
        	iSetter.setInteractiveTile();
        	eManager.lighting.resetDay();
    	}
  }
    
    public void setFullScreen() {
    	
    	//GET MY DEVICE SCREEN
    	/*GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    	GraphicsDevice gd = ge.getDefaultScreenDevice();
    	gd.setFullScreenWindow(main.window);
    	
    	//GET FULLSCREEN WIDTH AND HEIGHT
    	screenWidth2 = main.window.getWidth();
    	screenHeight2 = main.window.getHeight();*/
    	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		Main.window.setExtendedState(JFrame.MAXIMIZED_BOTH);
		screenWidth2 = (int) width;
		screenHeight2 = (int) height;
		width = (float) screenWidth / (float) screenWidth2;		
    }
    
    public void startGameThread(){
    	gamThread = new Thread(this);
        gamThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000/FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while(gamThread != null){
        update();
        drawToTempScreen();
        drawToScreen();
        //repaint();
        
            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;

                if(remainingTime < 0){
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval; 

            } catch (InterruptedException e) {
               
                e.printStackTrace();
            }
        }
        
    }

    
	public void update(){
		
		if(gameState == playState) {
			player.update();
			
			for(int i = 0; i< npc[1].length; i++) {
				if(npc[currentMap][i] != null) {
				 npc[currentMap][i].update();	
				}
			}
			
			for(int i = 0; i < monster[1].length; i++) {
	        	if(monster[currentMap][i] != null) {
	        		if(monster[currentMap][i].alive == true && monster[currentMap][i].dying == false) {
	        			monster[currentMap][i].update();
	        		}
	        		if(monster[currentMap][i].alive == false) {
	        			monster[currentMap][i].checkDrop();
	        			monster[currentMap][i] = null;
	        		}
	        		
	        	}
	        }
			
			for(int i = 0; i < projectile[1].length; i++) {
	        	if(projectile[currentMap][i]!= null) {
	        		if(projectile[currentMap][i].alive == true) {
	        			projectile[currentMap][i].update();
	        		}
	        		if(projectile[currentMap][i].alive == false) {
	        			projectile[currentMap][i] = null;
	        		}
	        		
	        	}
	        }
			
			for(int i = 0; i < particleList.size(); i++) {
	        	if(particleList.get(i)!= null) {
	        		if(particleList.get(i).alive == true) {
	        			particleList.get(i).update();
	        		}
	        		if(particleList.get(i).alive == false) {
	        			particleList.remove(i);
	        		}
	        		
	        	}
	        }
			
			for(int i = 0; i < iTile[1].length; i++) {
				if(iTile[currentMap][i] != null) {
					iTile[currentMap][i].update();
				}
			}
			eManager.update();
	    }
		
      if(gameState == pauseState) {
		
	}
		
      }
	
	public void drawToTempScreen() {

	      //TITLE SCREEN
	    if(gameState == titleState) {
	    	ui.draw(g2);
	    }
	    
	    //MAP SCREEN
	    else if(gameState == mapState) {
	    	map.drawFullMapScreen(g2);
	    }
	    else {
	    	 //tiles
	        tileM.draw(g2);
	        
	        //interactive tiles
	        for(int i = 0; i < iTile[1].length; i++) {
				if(iTile[currentMap][i] != null) {
					iTile[currentMap][i].draw(g2);
				}
			}
	        
	       //ADD ENTITES TO THE LIST
	        entitylist.add(player);
	        
	        for(int i = 0; i < npc[1].length; i++) {
	        	if(npc[currentMap][i] != null) {
	        		entitylist.add(npc[currentMap][i]);
	        	}
	        }
	        
	        for(int i = 0; i < obj[1].length; i++) {
	        	if(obj[currentMap][i] != null) {
	        		entitylist.add(obj[currentMap][i]);
	        	}
	        }
	        
	        for(int i = 0; i < monster[1].length; i++) {
	        	if(monster[currentMap][i] != null) {
	        		entitylist.add(monster[currentMap][i]);
	        	}
	        }
	        

	        for(int i = 0; i < projectile[1].length; i++) {
	        	if(projectile[currentMap][i] != null) {
	        		entitylist.add(projectile[currentMap][i]);
	        	}
	        }
	        
	        for(int i = 0; i < particleList.size(); i++) {
	        	if(particleList.get(i) != null) {
	        		entitylist.add(particleList.get(i));
	        	}
	        }
	       
	        //SORT
	        Collections.sort(entitylist, new Comparator<Entity>(){

				@Override
				public int compare(Entity e1, Entity e2) {
					int result = Integer.compare(e1.worldY, e2.worldY);
					return result;
				}
	        	
	        });
	        //DRAW ENTITIES
	        for(int i = 0; i < entitylist.size(); i++) {
	        	entitylist.get(i).draw(g2);
	       }
	        
	        //EMPTY ENTITY LIST
	        entitylist.clear();
	       
	        //ENVIRONMENT
	        eManager.draw(g2);
	        
	        //MINIMAP
	        map.drawminiMap(g2);
	        
	        //CUTSCENE
	        cManager.draw(g2);
	        
	        //UI
	        ui.draw(g2);
	    }
	}
	
      public void drawToScreen() {
    	  
    	  Graphics g = getGraphics();
    	  g.drawImage(tempScreen,0,0,screenWidth2,screenHeight2,null);
    	  g.dispose();
      }
      
      public void playMusic(int i) {
    	  music.setFile(i);
    	  music.play();
    	  music.loop();
      }
      
      public void stopMusic() {
    	  music.stop();
      }
      
      public void playSE(int i) {
    	  se.setFile(i);
    	  se.play();
     }
      
      public void changeArea()  {
    	  
    	  if(nextArea != currentArea) { 
    		  stopMusic();
    		  
    		  if(nextArea == outside) {
        		  playMusic(0);
        	  }
        	  if(nextArea == indoor) {
        		  playMusic(20);
        	  }
        	  if(nextArea == dungeon) {
        		  playMusic(21);
        	  }
        	  
        	 iSetter.setNPC();
    	  }
    	   currentArea = nextArea;
    	  iSetter.setMonster();
     }
      
      public void removeTempEntity() {
    	  
    	  for(int mapNum = 0; mapNum < maxMap; mapNum++) {
    		  for(int i = 0; i < obj[1].length; i++) {
    			  if(obj[mapNum][i] != null && obj[mapNum][i].temp == true) {
    				  obj[mapNum][i] = null;
    			  }
    		  }
    	  }
      }
}