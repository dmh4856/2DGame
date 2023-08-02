package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import object.SuperObject;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable
{
	//This class works as a game screen.
	
	//SCREEN SETTINGS
	final int originalTileSize = 16; //16x16 tile graphics. Default size for maps and characters.
	final int scale = 3; //Used to scale the game.
	
	public final int tileSize = originalTileSize * scale; //16 x 3 = 48 x 48 tile.
	public final int maxScreenCol = 16; //16 tiles horizontally
	public final int maxScreenRow = 12; //12 tiles vertically. Ratio is 4 x 3
	public final int screenWidth = tileSize * maxScreenCol; //768 pixels
	public final int screenHeight = tileSize * maxScreenRow; //576 pixels
	
	//WORLD MAP PARAMETERS
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
	public final int worldWidth = tileSize * maxWorldCol;
	public final int worldHeight = tileSize * maxWorldRow;
	
	
	//FPS
	int FPS = 60;
	
	//This keeps the program working until you close out or do something special.
	//Is also in charge of animations.
	
	//SYSTEM- FOR CLOSING OUT THE PROGRAM OR CONTROLLING ANIMATIONS.
	TileManager tileM = new TileManager(this);
	KeyHandler keyH = new KeyHandler();
	Sound music = new Sound();
	Sound se = new Sound();
	
	//Checks collision of objects.
	public CollisionChecker cChecker = new CollisionChecker(this);
	public AssetSetter aSetter = new AssetSetter(this);
	Thread gameThread;
	
	//Game UI
	public UI ui = new UI(this);
	
	//ENTITY AND OBJECT
	public Player player = new Player(this,keyH);
	public SuperObject obj[] = new SuperObject[10]; //10 slots of objects.
	
	
	
	//Set Player's default position
	//int playerX = 100;
	//int playerY = 100;
	//int playerSpeed = 4;
	
	public GamePanel () 
	{
		//Sets size of game panel.
		this.setPreferredSize(new Dimension (screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true); //For rendering.
		this.addKeyListener(keyH); //Reorganizes key input.
		this.setFocusable(true); //GamePanel class is focused to recieve key input.
	}
	
	//Is called before game starts.
	public void setupGame() 
	{
		aSetter.setObject();
		playMusic(0);
	}

	public void startGameThread()
	{
		//Instantiates thread
		gameThread = new Thread(this);
		gameThread.start();
	}
	@Override
	public void run()
	{
			double drawInterval = 1000000000/FPS; //Dividing a billion by 60 FPS. 0.01666 seconds
			double nextDrawTime = System.nanoTime() + drawInterval; //Draw the screen when it hits this time.
			
		//Uses to create a thread. Gets automatically called.
		while (gameThread != null) 
		{
			//As long as gameThread exists, the code here will be repeated.
			//System.out.println("Game loop is running");
			
			
			//1 UPDATE: Update information like character position
			update();
			//2 DRAW: Draw the screen with the updated information
			repaint();
			
			
			
			try {
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime = remainingTime/1000000; //Uses milliseconds.
				
				if(remainingTime < 0) 
				{
					remainingTime = 0;
				}
				
				//Sleep pauses game loop until the time is over. 
				Thread.sleep((long) remainingTime);
				
				nextDrawTime += drawInterval;
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	public void update() 
	{
		//Changing player position
		
		player.update();
		
	}
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		//We should draw the tile first, then the character. Order is important. 
		
		//TILES
		tileM.draw(g2);
		
		//DEBUG
		long drawStart = 0;
		if(keyH.checkDrawTime == true) 
		{
			drawStart = System.nanoTime();
		}
		
		
		//OBJECT
		for(int i = 0; i < obj.length; i++)
		{
			if(obj[i] != null)
			{
				obj[i].draw(g2, this);
			}
		}
		
		//PLAYER
		player.draw(g2);
		
		//UI 
		ui.draw(g2);
		
		//DEBUG CONTINUE AT 4:15 IN VIDEO
		if(keyH.checkDrawTime == true) 
		{
		long drawEnd = System.nanoTime();
		long passed = drawEnd - drawStart;
		g2.setColor(Color.white);
		g2.drawString("Draw Time: " + passed, 10, 400);
		System.out.println("Draw Time: " + passed);
		}
		
		
		g2.dispose();
	}
	
	//Calls methods in Sound class to play music.
	public void playMusic(int i) 
	{
		music.setFile(i);
		music.play();
		music.loop();
	}
	public void stopMusic() 
	{
		music.stop();
	}
	public void playSE(int i) 
	{
		se.setFile(i);
		se.play();
	}
}
