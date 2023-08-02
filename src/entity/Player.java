package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;

public class Player extends Entity 
{
	GamePanel gp;
	KeyHandler keyH;
	
	//Indicates where player is drawn on the screen. Scrolls the background as player moves.
	public final int screenX;
	public final int screenY;
	
	//Indicates how many keys the player has.
	public int hasKey = 0;
	
	
	public Player(GamePanel gp, KeyHandler keyH) 
	{
		this.gp = gp;
		this.keyH = keyH; 
		
		//Returns halfway point of screen. Are final valuables. 
		screenX = gp.screenWidth/2 - (gp.tileSize/2);
		screenY = gp.screenHeight/2 - (gp.tileSize/2);
		
		//For rectangle collision.
		solidArea = new Rectangle();
		solidArea.x = 8;
		solidArea.y = 16;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 32;
		solidArea.height = 32;
		
		setDefaultValues();
		getPlayerImage();
	}
	
	//Is the same as in GamePanel. For testing and player information. 
	public void setDefaultValues() 
	{
		worldX = gp.tileSize * 23;
		worldY = gp.tileSize * 21;
		speed = 4;
		direction = "down";
	}
	
	//Accessor method that gets the sprites for the Player character.
	public void getPlayerImage()
	{
			up1 = setup("sara_up1");
			up2 = setup("sara_up2");
			down1 = setup("sara_down1");
			down2 = setup("sara_down2");
			left1 = setup("sara_left1");
			left2 = setup("sara_left2");
			right1 = setup("sara_right1");
			right2 = setup("sara_right2");
	}
	
	public BufferedImage setup(String imageName) 
	{
		UtilityTool uTool = new UtilityTool();
		BufferedImage image = null;
		
		try 
		{
			image = ImageIO.read(getClass().getResourceAsStream("/player/" + imageName + ".png"));
			image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
		}
		catch(IOException e) 
		{
			e.printStackTrace();
		}
		return image;
	}
	public void update() 
	{

		if(keyH.upPressed == true || keyH.downPressed == true || 
				keyH.leftPressed == true || keyH.rightPressed == true)
		{
			
		if(keyH.upPressed == true)
		{
			direction = "up";
		}
		else if (keyH.downPressed == true)
		{
			direction = "down";
		}
		else if (keyH.leftPressed == true) 
		{
			direction = "left";
		}
		else if (keyH.rightPressed == true)
		{
			direction = "right";
		}
		
		//CHECK TILE COLLISION
		collisionOn = false;
		gp.cChecker.checkTile(this);
		
		
		//CHECK OBJECT COLLISION
		int objIndex = gp.cChecker.checkObject(this, true);
		pickUpObject(objIndex);
		
		// IF COLLISION IS FALSE, PLAYER CAN MOVE
		if(collisionOn == false) 
		{
			switch(direction) 
			{
			case "up": worldY -= speed; break;
			case "down": worldY += speed; break;
			case "left":worldX -= speed; break;
			case "right": worldX += speed; break;
			}
		}
		
		//Counts the animations of the sprites. Gets called 60 times per second and counter is increased by 1.
		//Player image changes in every 10 frames. 
		spriteCounter++; 
		if(spriteCounter > 10) 
		{
			if(spriteNum == 1) 
			{
				spriteNum = 2;
			}
			else if (spriteNum == 2) 
			{
				spriteNum = 1;
			}
			spriteCounter = 0; //Sprite counter resets if spriteNum is neither 1 or 2. 
		}
		}
	}
	public void pickUpObject(int i) 
	{
		if(i != 999) 
		{
			String objectName = gp.obj[i].name;
			
			switch(objectName)
			{
			case "Key":
				gp.playSE(1);
				hasKey++;
				gp.obj[i] = null;
				gp.ui.showMessage("Key get!");
				break;
			
			case "Door":
				if(hasKey > 0)
				{
					gp.playSE(3);
					gp.obj[i] = null;
					hasKey--;
					gp.ui.showMessage("Door unlocked!");
				}
				else 
				{
					gp.ui.showMessage("It's locked!");
				}
				System.out.println("Key:"+hasKey);
				break;
			case "Doll":
				gp.playSE(2);
				speed += 2;
				gp.obj[i] = null;
				gp.ui.showMessage("Let's move!");
				break;
			case "Chest":
				gp.ui.gameFinished = true;
				gp.stopMusic();
				gp.playSE(4);
				break;
			}
		}
	}
	
	public void draw(Graphics2D g2) 
	{
		//g2.setColor(Color.white);
		//g2.fillRect(x, y, gp.tileSize, gp.tileSize);
		
		
		//Draws the sprites for the player directions. 
		BufferedImage image = null;
		switch(direction) 
		{
		case "up":
			if(spriteNum == 1) 
			{
				image = up1;
			}
			if(spriteNum == 2) 
			{
				image = up2;
			}
			break;
		case "down":
			if(spriteNum == 1)
			{
				image = down1;
			}
			if(spriteNum == 2)
			{
				image = down2;
			}
			break;
		case "left":
			if(spriteNum == 1)
			{
				image = left1;
			}
			if(spriteNum == 2) 
			{
				image = left2;
			}
			break;
		case "right": 
			if(spriteNum == 1)
			{
				image = right1;
			}
			if(spriteNum == 2)
			{
				image = right2;
			}
			break;
		}
		g2.drawImage(image, screenX, screenY, null);
	}  
}
