package objects;


import entity.Entity;
import main.GamePanel;

public class OBJ_Boots extends Entity{
	
	public static final String objName = "boots";
	
	public OBJ_Boots(GamePanel gp) {
		super(gp);
		
		name = objName;
		down1 = setup("/objects/loot01goblet", gp.tilesize, gp.tilesize);
		
	}
}

