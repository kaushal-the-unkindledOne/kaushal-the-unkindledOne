package objects;

import entity.Entity;
import main.GamePanel;

public class OBJ_BlueCrystal extends Entity{
	
	GamePanel gp;
	public static final String objName = "Blue Crystal";
	
	public OBJ_BlueCrystal(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		name = objName;
		type = type_pickUpOnly;
		down1 = setup("/objects/blueheart", gp.tilesize, gp.tilesize);
		setDialouge();
	}
	
	public void setDialouge() {
		dialouge[0][0] = "After defeating the Skeleton Lord.";
		dialouge[0][1] = "You found the legendary chest.";
	}
	
	public boolean use(Entity entity) {
		
		gp.gameState = gp.cutsceneState;
		gp.cManager.sceneNum = gp.cManager.ending;
		return true;
	}
}
