package main;

import object.OBJ_Chest;
import object.OBJ_Doll;
import object.OBJ_Door;
import object.OBJ_Key;

public class AssetSetter
{
	GamePanel gp;
	
	public AssetSetter(GamePanel gp)
	{
		this.gp = gp;
	}
	//Instantiates objects on map.
	public void setObject()
	{
		gp.obj[0] = new OBJ_Key(gp);
		gp.obj[0].worldX = 23 * gp.tileSize;
		gp.obj[0].worldY = 10 * gp.tileSize;
		
		gp.obj[1] = new OBJ_Key(gp);
		gp.obj[1].worldX = 23 * gp.tileSize;
		gp.obj[1].worldY = 22 * gp.tileSize;
		
		gp.obj[2] = new OBJ_Door(gp);
		gp.obj[2].worldX = 22 * gp.tileSize;
		gp.obj[2].worldY = 11 * gp.tileSize;
		
		gp.obj[3] = new OBJ_Door(gp);
		gp.obj[3].worldX = 23 * gp.tileSize;
		gp.obj[3].worldY = 11 * gp.tileSize;
		
		gp.obj[4] = new OBJ_Chest(gp);
		gp.obj[4].worldX = 20 * gp.tileSize;
		gp.obj[4].worldY = 6 * gp.tileSize;
		
		gp.obj[5] = new OBJ_Doll(gp);
		gp.obj[5].worldX = 25 * gp.tileSize;
		gp.obj[5].worldY = 8 * gp.tileSize;
		
		
		//Continue watching at 10:45 in RyiSnow Video
		//Also check for map coordinates before watching. 
	}
}
