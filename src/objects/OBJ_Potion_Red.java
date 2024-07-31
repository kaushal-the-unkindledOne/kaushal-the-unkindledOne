package objects;

import entity.Entity;
import main.GamePanel;

public class OBJ_Potion_Red extends Entity{
   
	public static final String objName = "Red Potion";         
	    GamePanel gp;
	          
public OBJ_Potion_Red(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		
		type = type_consumable;
		name = objName;
	    value = 5;  
		down1 = setup("/objects/potion_red", gp.tilesize, gp.tilesize);
		description = "[" + name + "]\nA Potion to heal \nyour wounds by " + value + ".";
		price = 25;
		stackable = true;
		setDialouge();
	}

public void setDialouge() {
	dialouge[0][0] =  "You drank the " + name + " regain your health";
	dialouge[1][0] = "You already have max health";;
}

public boolean use(Entity entity) {
	 if(gp.player.Life < gp.player.maxLife) {
			startDialouge(this,0);
			gp.playSE(11);
			entity.Life += value;
			return true;
		}
		
      else 
    	  startDialouge(this,0);
		return false;
	}
}
	

  

 