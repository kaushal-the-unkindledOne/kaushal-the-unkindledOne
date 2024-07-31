package objects;

import entity.Entity;
import main.GamePanel;

     public class OBJ_door_Iron extends Entity{
    	 
    	 public static final String objName = "Iron door";
    	 
    	 GamePanel gp;
	
	public OBJ_door_Iron(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		type = type_Obstacle;
		name =objName;
		down1 = setup("/objects/door_iron", gp.tilesize, gp.tilesize);
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
		dialouge[0][0] = "It won't budge.";
	}
	
	public void interact() {
		startDialouge(this, 0);
		
	}

}
