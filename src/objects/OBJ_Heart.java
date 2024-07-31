package objects;

import entity.Entity;
import main.GamePanel;

public class OBJ_Heart extends Entity{
	
	public static final String objName = "Heart";

	  GamePanel gp;
	public OBJ_Heart(GamePanel gp){
		super(gp);
		this.gp = gp;
		
		type = type_pickUpOnly;
		name = objName;
		value = 2;
		down1 = setup("/objects/heart_full", gp.tilesize, gp.tilesize);
		image = setup("/objects/heart_full", gp.tilesize, gp.tilesize);
		image2 = setup("/objects/heart_half", gp.tilesize, gp.tilesize);
		image3 = setup("/objects/heart_blank", gp.tilesize, gp.tilesize);
	}
	
	public boolean use(Entity entity) {
		gp.playSE(10);
		gp.ui.addMessage("Life +" + value);
		entity.Life += value;
		return true;
	}
}


