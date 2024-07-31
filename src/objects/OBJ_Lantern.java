package objects;

import entity.Entity;
import main.GamePanel;

public class OBJ_Lantern extends Entity {
	
	public static final String objName = "Lantern";

	public OBJ_Lantern(GamePanel gp) {
		super(gp);
		
		type = type_light;
		name = objName;
		down1 = setup("/objects/lantern",gp.tilesize,gp.tilesize);
		description = "[Lantern]\nIlluminate your\nSurroundings";
		price = 80;
		lightRadius = 200;
	}

}
