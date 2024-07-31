package objects;

import entity.Entity;
import main.GamePanel;

public class OBJ_Coin_Bronze extends Entity{
	
	public static final String objName = "Bronze Coin";

	  GamePanel gp;
	  
	public OBJ_Coin_Bronze(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		type = type_pickUpOnly;
		name = objName;
		value = 1;
		down1 = setup("/objects/coin", gp.tilesize, gp.tilesize);
	}
	
	public boolean use(Entity entity) {
		gp.playSE(10);
		gp.ui.addMessage("Coin +" + value);
		gp.player.coin += value;
		return true;
		
		
	}

}
