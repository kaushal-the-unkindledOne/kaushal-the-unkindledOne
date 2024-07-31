package objects;

import entity.Entity;
import main.GamePanel;

public class OBJ_Shield_Wood extends Entity{
	
	public static final String objName = "Wood Shield";

	public OBJ_Shield_Wood(GamePanel gp) {
		super(gp);
		
		type = type_shield;
		name = objName;
		down1 = setup("/objects/shield_wood", gp.tilesize, gp.tilesize);
		defenseValue = 1;
		description = "[" + name + "]\nA Wooden shield.";
		price = 70;
	}

}
