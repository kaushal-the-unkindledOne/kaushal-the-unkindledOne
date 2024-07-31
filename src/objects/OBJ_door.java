package objects;

import entity.Entity;
import main.GamePanel;

     public class OBJ_door extends Entity{
    	 
    	 public static final String objName = "door";
    	 
    	 GamePanel gp;
	
	public OBJ_door(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		type = type_Obstacle;
		name =objName;
		down1 = setup("/objects/door", gp.tilesize, gp.tilesize);
		collision = true;
		
		   solidArea.x = 0;
		   solidArea.y = 16;
		   solidArea.width = 48;
		   solidArea.height = 32;
		   solidAreaDefaultX =  solidArea.x;
		   solidAreaDefaultY =  solidArea.y;
		   
		   setDialouge();
	}
	
	public void setDialouge() {
		dialouge[0][0] = "You need a key to open this door";
	}
	
	public void interact() {
		startDialouge(this, 0);
		
	}

}
