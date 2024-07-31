package objects;

import entity.Entity;
import main.GamePanel;

public class OBJ_key extends Entity{
	
	public static final String objName = "key";
	
	GamePanel gp;
	
	public OBJ_key(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		type = type_consumable;
		name = objName;
		down1 = setup("/objects/loot05key", gp.tilesize, gp.tilesize);
		description = "[" + name + "]\nA key to open Door.";
		price = 50;
		stackable = true;
		setDialouge();
	}
	
	public void setDialouge() {
		dialouge[0][0] = "You used the " + name + " and opened the door";
		
		dialouge[1][0] = "Are you trying to eat the Key?\n"
				+ "You donkey!";
	}
	
	public boolean use(Entity entity) {
		int objIndex = getDetected(entity, gp.obj, "door");
		
		if(objIndex != 999) {
			startDialouge(this,0);
			gp.playSE(4);
			gp.obj[gp.currentMap][objIndex] = null;
			return true;
		}
		else {
			startDialouge(this,1);
			return false;
		}
	}

}
