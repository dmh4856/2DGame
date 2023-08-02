package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Doll extends SuperObject 
{
	GamePanel gp;
	
	public OBJ_Doll(GamePanel gp)
	{
		this.gp = gp;
		
		name = "Doll";
		try {
		
			image = ImageIO.read(getClass().getResourceAsStream("/objects/doll.png"));
			uTool.scaleImage(image, gp.tileSize, gp.tileSize);
			}
				catch(IOException e)
				{
				e.printStackTrace();
				}
		//Continue video at 4:40.
			}
	}