package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity
{
	//Universal speed for character, NPC, etc.
	public int worldX, worldY;
	public int speed;
	
	//Starting sprite for character and their directions.
	public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
	public String direction;
	
	//Animations for player character.
	public int spriteCounter = 0;
	public int spriteNum = 1;
	
	//Creates invisible rectangle and stores data for character collision
	public Rectangle solidArea;
	public int solidAreaDefaultX, solidAreaDefaultY;
	public boolean collisionOn = false;
}
