package monster;

import java.util.Random;

import entity.Entity;
import main.GamePanel;
import objects.OBJ_Coin_Bronze;
import objects.OBJ_Heart;
import objects.OBJ_ManaCrystal;
import objects.OBJ_Rock;
import objects.OBJ_Shield_Blue;

public class MON_RedSlime extends Entity{
	
	GamePanel gp;
	
   public MON_RedSlime(GamePanel gp) {
	   super(gp);
	   this.gp = gp;
	   
	   type = type_monster;
	   name = "Red Slime";
	   defaultSpeed = 1;
	   speed = defaultSpeed;
	   maxLife = 8;
	   Life = maxLife;
	   attack = 5;
	   defense = 0;
	   exp = 3;
	   projectile = new OBJ_Rock(gp);
	   
	   solidArea.x = 3;
	   solidArea.y = 18;
	   solidArea.width = 42;
	   solidArea.height = 30;
	   solidAreaDefaultX =  solidArea.x;
	   solidAreaDefaultY =  solidArea.y;
	   getImage();
	}
   
   public void getImage() {
	   
	   up1 = setup("/monster/redslime_down_1", gp.tilesize, gp.tilesize); 
	   up2 = setup("/monster/redslime_down_2", gp.tilesize, gp.tilesize);
	   down1 = setup("/monster/redslime_down_1", gp.tilesize, gp.tilesize); 
	   down2 = setup("/monster/redslime_down_2", gp.tilesize, gp.tilesize); 
	   left1 = setup("/monster/redslime_down_1", gp.tilesize, gp.tilesize); 
	   left2 = setup("/monster/redslime_down_2", gp.tilesize, gp.tilesize); 
	   right1 = setup("/monster/redslime_down_1", gp.tilesize, gp.tilesize); 
	   right2 = setup("/monster/redslime_down_2", gp.tilesize, gp.tilesize); 
   }
   
public void setAction() {
	   
	   if(onPath == true) {
		   //CHECK IF ITS STOP FOLLOWING
		   checkStopChasingOrNot(gp.player, 8, 100);
		   
		   //CHECK THE DIRECTION
			searchPath(getGoalCol(gp.player), getGoalRow(gp.player));
			
			//CHECK IF IT SHOOTS PROJECTILE
			checkShootOrNot(200,30);
	   }
	   
	   else {
		   //CHECKS IF IT STARTS CHASING
		   checkStartChasingOrNot(gp.player, 5, 100);
		   
		   //GET RANDOM DIRECTION
		   getRandomDirection(120);
	   }
	}
   
   public void attackReaction(){
	   
	   actionLockCounter = 0;
	   //direction = gp.player.direction;
	   onPath = true;
	 }
   
   public void checkDrop() {
	   
	   int i = new Random().nextInt(100)+1;
	   if(i < 50) {
			dropItem(new OBJ_Heart(gp));
		}
		
		if(i > 50 && i <= 75) {
			dropItem(new OBJ_Coin_Bronze(gp));
		}
		
		if(i > 75 && i <= 100) {
			dropItem(new OBJ_ManaCrystal(gp));
		}
	     
   }
}

