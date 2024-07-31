package monster;

import java.util.Random;

import data.progress;
import entity.Entity;
import main.GamePanel;
import objects.OBJ_Coin_Bronze;
import objects.OBJ_Heart;
import objects.OBJ_ManaCrystal;
import objects.OBJ_Rock;
import objects.OBJ_door_Iron;

public class MON_SkeletonLord extends Entity{
	
	GamePanel gp;
	public static final String monName = "Skeleton Lord";
	
   public MON_SkeletonLord(GamePanel gp){
	   super(gp);
	   this.gp = gp;
	   
	   type = type_monster;
	   name = monName;
	   defaultSpeed = 1;
	   speed = defaultSpeed;
	   maxLife = 50;
	   Life = maxLife;
	   attack = 10;
	   defense = 2;
	   exp = 50;
	   knockBackPower = 3;
	   boss = true;
	   sleep = true;
	   
	   int size = gp.tilesize*5;
	   solidArea.x = 48;
	   solidArea.y = 48;
	   solidArea.width = size - 48*2;
	   solidArea.height = size - 48;
	   solidAreaDefaultX =  solidArea.x;
	   solidAreaDefaultY =  solidArea.y;
	   attackArea.width = 170;
	   attackArea.height = 170;
	   motion1_duration = 25;
	   motion2_duration = 50;
	   
	   getImage();
	   getAttackImage();
	   setDialouge();
	}
   
   public void getImage() {
	   
	   int i = 5;
	   
	   if(inRage == false) {
		   up1 = setup("/monster/skeletonlord_up_1", gp.tilesize*i, gp.tilesize*i); 
		   up2 = setup("/monster/skeletonlord_up_2", gp.tilesize*i, gp.tilesize*i);
		   down1 = setup("/monster/skeletonlord_down_1", gp.tilesize*i, gp.tilesize*i); 
		   down2 = setup("/monster/skeletonlord_down_2", gp.tilesize*i, gp.tilesize*i); 
		   left1 = setup("/monster/skeletonlord_left_1", gp.tilesize*i, gp.tilesize*i); 
		   left2 = setup("/monster/skeletonlord_left_2", gp.tilesize*i, gp.tilesize*i); 
		   right1 = setup("/monster/skeletonlord_right_1", gp.tilesize*i, gp.tilesize*i); 
		   right2 = setup("/monster/skeletonlord_right_2", gp.tilesize*i, gp.tilesize*i); 
	   }
	   
	   if(inRage == true) {
		   up1 = setup("/monster/skeletonlord_phase2_up_1", gp.tilesize*i, gp.tilesize*i); 
		   up2 = setup("/monster/skeletonlord_phase2_up_2", gp.tilesize*i, gp.tilesize*i);
		   down1 = setup("/monster/skeletonlord_phase2_down_1", gp.tilesize*i, gp.tilesize*i); 
		   down2 = setup("/monster/skeletonlord_phase2_down_2", gp.tilesize*i, gp.tilesize*i); 
		   left1 = setup("/monster/skeletonlord_phase2_left_1", gp.tilesize*i, gp.tilesize*i); 
		   left2 = setup("/monster/skeletonlord_phase2_left_2", gp.tilesize*i, gp.tilesize*i); 
		   right1 = setup("/monster/skeletonlord_phase2_right_1", gp.tilesize*i, gp.tilesize*i); 
		   right2 = setup("/monster/skeletonlord_phase2_right_2", gp.tilesize*i, gp.tilesize*i); 
	   }
	   
   }
   
   public void getAttackImage() {
	   
	   int i = 5;
	   
	    if(inRage == false) {
	    	attackup1 = setup("/monster/skeletonlord_attack_up_1", gp.tilesize*i, gp.tilesize*i*2);
			attackup2 =  setup("/monster/skeletonlord_attack_up_2", gp.tilesize*i, gp.tilesize*i*2);
			attackdown1 = setup("/monster/skeletonlord_attack_down_1", gp.tilesize*i, gp.tilesize*i*2);
			attackdown2 = setup("/monster/skeletonlord_attack_down_2", gp.tilesize*i, gp.tilesize*i*2);
			attackleft1 = setup("/monster/skeletonlord_attack_left_1", gp.tilesize*i*2, gp.tilesize*i);
			attackleft2 = setup("/monster/skeletonlord_attack_left_2", gp.tilesize*i*2, gp.tilesize*i);
			attackright1 = setup("/monster/skeletonlord_attack_right_1", gp.tilesize*i*2, gp.tilesize*i);
			attackright2 =setup("/monster/skeletonlord_attack_right_2", gp.tilesize*i*2, gp.tilesize*i);
	    }
	    
	    if(inRage == true) {
	    	attackup1 = setup("/monster/skeletonlord_phase2_attack_up_1", gp.tilesize*i, gp.tilesize*i*2);
			attackup2 =  setup("/monster/skeletonlord_phase2_attack_up_2", gp.tilesize*i, gp.tilesize*i*2);
			attackdown1 = setup("/monster/skeletonlord_phase2_attack_down_1", gp.tilesize*i, gp.tilesize*i*2);
			attackdown2 = setup("/monster/skeletonlord_phase2_attack_down_2", gp.tilesize*i, gp.tilesize*i*2);
			attackleft1 = setup("/monster/skeletonlord_phase2_attack_left_1", gp.tilesize*i*2, gp.tilesize*i);
			attackleft2 = setup("/monster/skeletonlord_phase2_attack_left_2", gp.tilesize*i*2, gp.tilesize*i);
			attackright1 = setup("/monster/skeletonlord_phase2_attack_right_1", gp.tilesize*i*2, gp.tilesize*i);
			attackright2 =setup("/monster/skeletonlord_phase2_attack_right_2", gp.tilesize*i*2, gp.tilesize*i);
	    }
		
	}
   
   public void setDialouge() {
		dialouge[0][0] = "No one can steal my treasure.";
		dialouge[0][1] = "You will die here!";
		dialouge[0][2] = "THIS IS MF DOOM.";
	}
   
   public void setAction() {
	   
	   if(inRage == false && Life < maxLife/2) {
		   inRage = true;
		   getImage();
		   getAttackImage();
		   attack *= 2;
		   defaultSpeed++;
		   speed = defaultSpeed;
	   }
	   
	   if(getTileDistance(gp.player) < 10) {
		  moveTowardsPlayer(60);
	   }
	   
	   else {
		   //GET RANDOM DIRECTION
		   getRandomDirection(120);
		   }
	   
	   //CHECK IF IT ATTACKS OR NOT
	   if(attacking == false) {
		   checkAttackOrNot(60, gp.tilesize*7, gp.tilesize*5);
	   }
	   
	}
   
   public void attackReaction(){
	   
	   actionLockCounter = 0;
	 }
   
   public void checkDrop() {
	   
	   gp.bossBattleOn = false;
	   progress.skeletonLordDefeated = true;
	   
	   gp.stopMusic();
	   gp.playSE(21);
	   
	   for(int i = 0; i < gp.obj[1].length; i++) {
			if(gp.obj[gp.currentMap][i] != null && 
					gp.obj[gp.currentMap][i].name.equals(OBJ_door_Iron.objName)) {
				gp.playSE(23);
				gp.obj[gp.currentMap][i] = null;
			}
		}
	}
}
