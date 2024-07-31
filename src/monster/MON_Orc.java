package monster;

import java.util.Random;

import entity.Entity;
import main.GamePanel;
import objects.OBJ_Coin_Bronze;
import objects.OBJ_Heart;
import objects.OBJ_ManaCrystal;
import objects.OBJ_Rock;

public class MON_Orc extends Entity{
	
	GamePanel gp;
	
   public MON_Orc(GamePanel gp) {
	   super(gp);
	   this.gp = gp;
	   
	   type = type_monster;
	   name = "Orc";
	   defaultSpeed = 1;
	   speed = defaultSpeed;
	   maxLife = 10;
	   Life = maxLife;
	   attack = 7;
	   defense = 2;
	   exp = 10;
	   knockBackPower = 3;
	   
	   solidArea.x = 4;
	   solidArea.y = 4;
	   solidArea.width = 42;
	   solidArea.height = 44;
	   solidAreaDefaultX =  solidArea.x;
	   solidAreaDefaultY =  solidArea.y;
	   attackArea.width = 48;
	   attackArea.height = 48;
	   motion1_duration = 40;
	   motion2_duration = 85;
	   
	   getImage();
	   getOrcAttackImage();
	}
   
   public void getImage() {
	   
	   up1 = setup("/monster/orc_up_1", gp.tilesize, gp.tilesize); 
	   up2 = setup("/monster/orc_up_2", gp.tilesize, gp.tilesize);
	   down1 = setup("/monster/orc_down_1", gp.tilesize, gp.tilesize); 
	   down2 = setup("/monster/orc_down_2", gp.tilesize, gp.tilesize); 
	   left1 = setup("/monster/orc_left_1", gp.tilesize, gp.tilesize); 
	   left2 = setup("/monster/orc_left_2", gp.tilesize, gp.tilesize); 
	   right1 = setup("/monster/orc_right_1", gp.tilesize, gp.tilesize); 
	   right2 = setup("/monster/orc_right_2", gp.tilesize, gp.tilesize); 
   }
   
   public void getOrcAttackImage() {
	   
		attackup1 = setup("/monster/orc_attack_up_1", gp.tilesize, gp.tilesize*2);
		attackup2 =  setup("/monster/orc_attack_up_2", gp.tilesize, gp.tilesize*2);
		attackdown1 = setup("/monster/orc_attack_down_1", gp.tilesize, gp.tilesize*2);
		attackdown2 = setup("/monster/orc_attack_down_2", gp.tilesize, gp.tilesize*2);
		attackleft1 = setup("/monster/orc_attack_left_1", gp.tilesize*2, gp.tilesize);
		attackleft2 = setup("/monster/orc_attack_left_2", gp.tilesize*2, gp.tilesize);
		attackright1 = setup("/monster/orc_attack_right_1", gp.tilesize*2, gp.tilesize);
		attackright2 =setup("/monster/orc_attack_right_2", gp.tilesize*2, gp.tilesize);
	}
   
   public void setAction() {
	   
	   if(onPath == true) {
		   //CHECK IF ITS STOP FOLLOWING
		   checkStopChasingOrNot(gp.player, 7, 100);
		   
		   //CHECK THE DIRECTION
			searchPath(getGoalCol(gp.player), getGoalRow(gp.player));
	   }
	   
	   else {
		   //CHECKS IF IT STARTS CHASING
		   checkStartChasingOrNot(gp.player, 5, 100);
		   
		   //GET RANDOM DIRECTION
		   getRandomDirection(120);
		   }
	   
	   //CHECK IF IT ATTACKS OR NOT
	   if(attacking == false) {
		   checkAttackOrNot(25, gp.tilesize*4, gp.tilesize);
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
			dropItem(new OBJ_Coin_Bronze(gp));
		}
		
		if(i > 50 && i <= 75) {
			dropItem(new OBJ_Heart(gp));
		}
		
		if(i > 75 && i <= 100) {
			dropItem(new OBJ_ManaCrystal(gp));
		}
	}
}
