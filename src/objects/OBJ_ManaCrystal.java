package objects;

import entity.Entity;
import main.GamePanel;

public class OBJ_ManaCrystal extends Entity{
	
	public static final String objName = "Mana Crystal";

	 GamePanel gp;
	public OBJ_ManaCrystal(GamePanel gp){
		super(gp);
		this.gp = gp;
		
		type = type_pickUpOnly;
		name = objName;
		value = 1;
		down1 = setup("/objects/manacrystal_full", gp.tilesize, gp.tilesize);
		image = setup("/objects/manacrystal_full", gp.tilesize, gp.tilesize);
		image2 = setup("/objects/manacrystal_blank", gp.tilesize, gp.tilesize);
	}
	public boolean use(Entity entity) {
		gp.playSE(10);
		gp.ui.addMessage("Mana +" + value);
		entity.mana += value;
		return true;
	}
}

