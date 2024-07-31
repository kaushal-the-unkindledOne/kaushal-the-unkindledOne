package objects;

import entity.Entity;
import main.GamePanel;

public class OBJ_Tent extends Entity{
	
	public static final String objName = "Tent";
	
	GamePanel gp;

	public OBJ_Tent(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		type = type_consumable;
		name = objName;
		down1 = setup("/objects/tent", gp.tilesize, gp.tilesize);
		description = "[Tent]]nYou can sleep \nuntil Next morning.";
		price = 250;
		stackable = true;
	}
	
    public boolean use(Entity entity) {
    	
    	gp.gameState = gp.sleepState;
    	gp.playSE(16);
    	gp.player.getSleepingImage(down1);
    	gp.player.Life = gp.player.maxLife;
    	gp.player.mana = gp.player.maxMana;
    	return true;
		
	}

}
