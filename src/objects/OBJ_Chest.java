package objects;


import entity.Entity;
import main.GamePanel;

public class OBJ_Chest extends Entity{
	
	public static final String objName = "chest";
	
	GamePanel gp;
	
	public OBJ_Chest(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
	    type = type_Obstacle;
		name = objName;
		image = setup("/objects/chest", gp.tilesize, gp.tilesize);
		image2 = setup("/objects/chest_opened", gp.tilesize, gp.tilesize);
		down1 = image;
		
		collision = true;
		
		solidArea.x = 4;
		solidArea.y = 16;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 40;
		solidArea.height = 32;
	}
	
	public void setLoot(Entity loot) {
		this.loot = loot;
		setDialouge();
	}
	
	public void setDialouge() {
		dialouge[0][0] = "You opened the chest and found " + loot.name + "!\n..but You cannot carry anymore item!";
		dialouge[1][0] = "You opened the chest and found " + loot.name + "!";
		dialouge[2][0] = "its Empty bro!";
		}
	
	public void interact() {
		
		if(opened == false) {
			gp.playSE(4);
			
			if(gp.player.canObtainItem(loot) == false){
				startDialouge(this, 0);
			}
			else {
				startDialouge(this, 1);
				down1 = image2;
				opened = true;
			}
		}
		else {
			startDialouge(this, 2);
		}
	}

}
