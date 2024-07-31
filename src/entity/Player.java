package entity;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import main.GamePanel;
import main.keyHandler;
import objects.OBJ_Sword_Normal;
import objects.OBJ_key;
import objects.OBJ_FireBall;
import objects.OBJ_Shield_Wood;

import java.awt.Font;

public class Player extends Entity{

	keyHandler KeyH;
	
	public final int screenX;
	public final int screenY;
	public boolean attackCancel = false;
	public boolean lightUpdated = false;
	
	public Player (GamePanel gp, keyHandler KeyH){
		super(gp);
		this.KeyH = KeyH;
		
		screenX = gp.screenWidth/2 - (gp.tilesize/2);
		screenY = gp.screenHeight/2 - (gp.tilesize/2);
		
		solidArea = new Rectangle();
		solidArea.x = 8;
		solidArea.y = 16;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 32;
		solidArea.height = 32;
		
		setDefaultValues();
		
	}
	
	public void setDefaultValues() {
		
		worldX = gp.tilesize * 23;
		worldY = gp.tilesize * 21;
//		worldX = gp.tilesize * 10;
//		worldY = gp.tilesize * 9;
		defaultSpeed = 4;
		speed = defaultSpeed;
		direction = "down";
		
		//PLAYER STATUS
		level = 1;
		maxLife = 6;
		Life = maxLife;
		exp = 0;
		nextLevelExp = 5;
	    strength = 1;
		dexterity = 1;
		maxMana = 4;
		mana = maxMana;
		coin = 350;
		currentWeapon = new OBJ_Sword_Normal(gp);
		currentShield = new OBJ_Shield_Wood(gp);
		currentLight = null;
		projectile = new OBJ_FireBall(gp);
		
		attack = getAttack();
		defense = getDefense();
		
		getPlayerImage();
		getPlayerAttackImage();	
		setItems();
		setDialouge();
		getGuardImage();
	}
	
	public void setDefaultPosition() {
		
		gp.currentMap = 0;
		worldX = gp.tilesize * 23;
		worldY = gp.tilesize * 21;
		direction = "down";
	}
	
	public void setDialouge() {
		dialouge[0][0] = "You are now Level " + level +"\nCongratulations";
	}
	
	public void restoreStatus() {
		Life = maxLife;
		mana = maxMana;
		speed = defaultSpeed;
		invincible = false;
		transparent = false;
		attacking = false;
		guarding = false;
		knockBack = false;
		lightUpdated = true;
	}
	
	public void setItems() {
		
		inventory.clear();
		inventory.add(currentWeapon);
		inventory.add(currentShield);
		inventory.add(new OBJ_key(gp));
		
	}
	
	public int getAttack() {
		attackArea = currentWeapon.attackArea;
		motion1_duration = currentWeapon.motion1_duration;
		motion2_duration = currentWeapon.motion2_duration;
		return attack = strength * currentWeapon.attackValue;
	}
	
	public int getDefense() {
		return defense = dexterity * currentShield.defenseValue;
	}
	
	public int getCurrentWeaponSlot() {
		int currentWeaponSlot = 0;
		for(int i = 0; i < inventory.size(); i++) {
			if(inventory.get(i) == currentWeapon) {
				currentWeaponSlot = i;
			}
		}
		return currentWeaponSlot;
	}
	
	public int getCurrentShieldSlot() {
		int currentShieldSlot = 0;
		for(int i = 0; i < inventory.size(); i++) {
			if(inventory.get(i) == currentShield) {
				currentShieldSlot = i;
			}
		}
		return currentShieldSlot;
	}
	
	public void getPlayerImage() {
	    up1 = setup("/player/boy_up_1", gp.tilesize, gp.tilesize);
		up2 = setup("/player/boy_up_2", gp.tilesize, gp.tilesize);
		down1 = setup("/player/boy_down_1", gp.tilesize, gp.tilesize);
		down2 = setup("/player/boy_down_2", gp.tilesize, gp.tilesize);
		left1 = setup("/player/boy_left_1", gp.tilesize, gp.tilesize);
		left2 = setup("/player/boy_left_2", gp.tilesize, gp.tilesize);
		right1 = setup("/player/boy_right_1", gp.tilesize, gp.tilesize);
		right2 = setup("/player/boy_right_2", gp.tilesize, gp.tilesize);
	}
	
	public void getSleepingImage(BufferedImage image) {
		    up1 = image;
			up2 = image;
			down1 = image;
			down2 = image;
			left1 = image;
			left2 = image;
			right1 = image;
			right2 = image;
	}
	
	public void getPlayerAttackImage() {
		if(currentWeapon.type == type_sword) {
			attackup1 = setup("/player/boy_attack_up_1", gp.tilesize, gp.tilesize*2);
			attackup2 =  setup("/player/boy_attack_up_2", gp.tilesize, gp.tilesize*2);
			attackdown1 = setup("/player/boy_attack_down_1", gp.tilesize, gp.tilesize*2);
			attackdown2 = setup("/player/boy_attack_down_2", gp.tilesize, gp.tilesize*2);
			attackleft1 = setup("/player/boy_attack_left_1", gp.tilesize*2, gp.tilesize);
			attackleft2 = setup("/player/boy_attack_left_2", gp.tilesize*2, gp.tilesize);
			attackright1 = setup("/player/boy_attack_right_1", gp.tilesize*2, gp.tilesize);
			attackright2 =setup("/player/boy_attack_right_2", gp.tilesize*2, gp.tilesize);
		}
		if(currentWeapon.type == type_axe) {
			attackup1 = setup("/player/boy_axe_up_1", gp.tilesize, gp.tilesize*2);
			attackup2 =  setup("/player/boy_axe_up_2", gp.tilesize, gp.tilesize*2);
			attackdown1 = setup("/player/boy_axe_down_1", gp.tilesize, gp.tilesize*2);
			attackdown2 = setup("/player/boy_axe_down_2", gp.tilesize, gp.tilesize*2);
			attackleft1 = setup("/player/boy_axe_left_1", gp.tilesize*2, gp.tilesize);
			attackleft2 = setup("/player/boy_axe_left_2", gp.tilesize*2, gp.tilesize);
			attackright1 = setup("/player/boy_axe_right_1", gp.tilesize*2, gp.tilesize);
			attackright2 =setup("/player/boy_axe_right_2", gp.tilesize*2, gp.tilesize);
		}
		if(currentWeapon.type == type_swordGolden) {
			attackup1 = setup("/gAttack/boy_goldenAttack_up_1", gp.tilesize, gp.tilesize*2);
			attackup2 =  setup("/gAttack/boy_goldenAttack_up_2", gp.tilesize, gp.tilesize*2);
			attackdown1 = setup("/gAttack/boy_goldenAttack_down_1", gp.tilesize, gp.tilesize*2);
			attackdown2 = setup("/gAttack/boy_goldenAttack_down_2", gp.tilesize, gp.tilesize*2);
			attackleft1 = setup("/gAttack/boy_goldenAttack_left_1", gp.tilesize*2, gp.tilesize);
			attackleft2 = setup("/gAttack/boy_goldenAttack_left_2", gp.tilesize*2, gp.tilesize);
			attackright1 = setup("/gAttack/boy_goldenAttack_right_1", gp.tilesize*2, gp.tilesize);
			attackright2 =setup("/gAttack/boy_goldenAttack_right_2", gp.tilesize*2, gp.tilesize);
		}
		if(currentWeapon.type == type_pickAxe) {
			attackup1 = setup("/player/boy_pick_up_1", gp.tilesize, gp.tilesize*2);
			attackup2 =  setup("/player/boy_pick_up_2", gp.tilesize, gp.tilesize*2);
			attackdown1 = setup("/player/boy_pick_down_1", gp.tilesize, gp.tilesize*2);
			attackdown2 = setup("/player/boy_pick_down_2", gp.tilesize, gp.tilesize*2);
			attackleft1 = setup("/player/boy_pick_left_1", gp.tilesize*2, gp.tilesize);
			attackleft2 = setup("/player/boy_pick_left_2", gp.tilesize*2, gp.tilesize);
			attackright1 = setup("/player/boy_pick_right_1", gp.tilesize*2, gp.tilesize);
			attackright2 =setup("/player/boy_pick_right_2", gp.tilesize*2, gp.tilesize);
		}
	    
	}
	
	public void getGuardImage() {
		
		    guardUp = setup("/player/boy_guard_up", gp.tilesize, gp.tilesize);
			guardDown = setup("/player/boy_guard_down", gp.tilesize, gp.tilesize);
			guardLeft = setup("/player/boy_guard_left", gp.tilesize, gp.tilesize);
			guardRight = setup("/player/boy_guard_right", gp.tilesize, gp.tilesize);
	}
	
	public void update() {
		
     if(knockBack == true) {
    		
    	   //check tile collision
		   CollisionOn = false;
		   gp.cChecker.checkTile(this);
		   //check object collision
		  gp.cChecker.checkObject(this, true);
		  //CHECK NPC COLLISION
		  gp.cChecker.checkEntity(this, gp.npc);
         //CHECK MONSTER COLLISION
		 gp.cChecker.checkEntity(this, gp.monster);
		 //CHECK INTERACTIVE TILE COLLISION
		   gp.cChecker.checkEntity(this, gp.iTile);
		  
    		if(CollisionOn == true) {
    			knockBackCounter = 0;
    			knockBack = false;
    			speed = defaultSpeed;
    		}
    		else if(CollisionOn == false) {
    			switch(knockBackDiection) {
    			 case "up": worldY -= speed; break;
      	         case "down": worldY += speed;break;
      	         case "left":  worldX -= speed; break;
      	         case "right": worldX += speed; break;
    			}
    		}
    		knockBackCounter++;
    		if(knockBackCounter == 10) {
    			knockBackCounter = 0;
    			knockBack = false;
    			speed = defaultSpeed;
    		}
    	}

     else if(attacking == true) {
			attacking();
		}
		
		else if(gp.keyH.spacePressed == true) {
			guarding = true;
			guardCounter++;
		}
		
	else if(KeyH.upPressed == true || KeyH.downPressed == true || 
	        KeyH.leftPressed == true || KeyH.rightPressed == true || 
	        KeyH.enterPressed == true || KeyH.spacePressed == true) {
			
		
			   if(KeyH.upPressed == true) {
				   direction = "up";		          
		            }

		            else if(KeyH.downPressed == true) {
		            	direction = "down";		                
		            }
 
		            else if(KeyH.leftPressed == true) {
		            	direction = "left";		               
		            }

		            else if(KeyH.rightPressed == true) {
		            	direction = "right";		                
		            }
			   
			   
			   //check tile collision
			   CollisionOn = false;
			   gp.cChecker.checkTile(this);
			   
			   //check object collision
			  int objIndex =  gp.cChecker.checkObject(this, true);
			  pickupObject(objIndex);
			  
			  //CHECK NPC COLLISION
			  int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
			  InteractNpc(npcIndex); 
			  
			  //CHECK MONSTER COLLISION
			  int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
			  contactMonster(monsterIndex);
			  
			  //CHECK INTERACTIVE TILE COLLISION
			   gp.cChecker.checkEntity(this, gp.iTile);
			  
			  //CHECK EVENT
			  gp.eHandler.checkEvent();
			  
			  //if collision is false then player can move
			   if(CollisionOn == false && KeyH.enterPressed == false) {
				   switch(direction) {
				   case "up": worldY -= speed;
					   break;
				   case "down": worldY += speed;
					   break;
				   case "left":  worldX -= speed;
					   break;
				   case "right": worldX += speed;
					   break;
				   }
			   }
			   
			   if(KeyH.enterPressed == true && attackCancel == false) {
				   gp.playSE(7);
				   attacking = true;
				   spriteCounter = 0;
			   }
			   
			  attackCancel = false;
			  gp.keyH.enterPressed = false;
			  guarding = false;
			  guardCounter = 0;
			   
			   spriteCounter++;
			   if(spriteCounter > 12) {
				   if(spriteNum == 1) {
					   spriteNum = 2;
				   }
				   else if(spriteNum == 2) {
					   spriteNum = 1;
				   }
				   spriteCounter = 0;	
				   guarding = false;
				   guardCounter = 0;
	}
		}
		
	if(gp.keyH.shotKeyPressed == true && projectile.alive == false 
			&& shotAvailableCounter == 30 && projectile.haveResource(this) == true) {
			projectile.set(worldX, worldY, direction, true, this);
			
			//SUBTRACT COST
			projectile.subtractResource(this);
			
			//ADD PROJECTILE TO ARRAYLIST
			for(int i = 0; i < gp.projectile[1].length; i++) {
	        	if(gp.projectile[gp.currentMap][i] == null) {
	        		gp.projectile[gp.currentMap][i] = projectile;
	        		break;
	        	}
	        }
			
			shotAvailableCounter = 0;
			gp.playSE(12);
		}
		
	if(invincible == true) {
			invincibleCounter ++;
			if(invincibleCounter > 60) {
				invincible = false;
				transparent = false;
			 	invincibleCounter = 0;
			}
		}
		
	if(shotAvailableCounter < 30) {
			shotAvailableCounter++;
		}
	
	if(Life  > maxLife) {
		Life = maxLife;
	  }
	
	if(mana > maxMana) {
		mana = maxMana;
	  }
	
	if(KeyH.godModeOn == false) {
		 if(Life <= 0) {
				gp.gameState = gp.gameOverState;
				gp.ui.commandNum  = -1;
				gp.stopMusic();
				gp.playSE(14);
			}
	    }
	}
	
	public void pickupObject(int i) {
		
		if(i != 999) {	
            //PICK UP ONLY OBJECTS
			if(gp.obj[gp.currentMap][i].type == type_pickUpOnly) {
				gp.obj[gp.currentMap][i].use(this);
				gp.obj[gp.currentMap][i] = null;
			}
			else if(gp.obj[gp.currentMap][i].type == type_Obstacle) {
				if(gp.keyH.enterPressed == true) {
					attackCancel = true;
					gp.obj[gp.currentMap][i].interact();
				}
			}
			
			//INVENTORY
			else {
				String text;
				
				if(canObtainItem(gp.obj[gp.currentMap][i]) == true){
					//inventory.add(gp.obj[gp.currentMap][i]);
					gp.playSE(10);
					text = "Got a " + gp.obj[gp.currentMap][i].name + "!";
				}
				else {
					text = "You cannot carry anymore!";
				}
				gp.ui.addMessage(text);
				gp.obj[gp.currentMap][i] = null;
			}
		}
	}
	
	public void InteractNpc(int i) {
		
		if(i != 999) {
			if(gp.keyH.enterPressed == true) {
				attackCancel = true;
					gp.npc[gp.currentMap][i].speak();
			}
			gp.npc[gp.currentMap][i].move(direction);
		 }		
	}
	
	
	public void contactMonster(int i ) {
		if(i != 999) {
			
			if(invincible == false && gp.monster[gp.currentMap][i].dying == false) {
				gp.playSE(6);
				
				int damage = gp.monster[gp.currentMap][i].attack - defense;
				if(damage < 1) {
					damage = 1;
				}
				
				Life -= damage;
				invincible = true;
				transparent = true;
			}
		}
	}
	
	public void damageMonster(int i,Entity attacker, int attack, int knockBackPower){
		if(i != 999) {
			if(gp.monster[gp.currentMap][i].invincible == false) {
				generateParticle(gp.player.currentWeapon,gp.monster[gp.currentMap][i]);
				gp.playSE(5);
				if(knockBackPower > 0) {
					setknockBack(gp.monster[gp.currentMap][i],attacker, knockBackPower);
				}
				
				if(gp.monster[gp.currentMap][i].offBalance == true) {
					attack *= 4;
					
				}
				
				int damage = attack - gp.monster[gp.currentMap][i].defense;
				if(damage < 0) {
					damage = 0;
				}
				
				gp.monster[gp.currentMap][i].Life -= damage;
				gp.ui.addMessage(damage + " damage! ");
				
				gp.monster[gp.currentMap][i].invincible = true;
				gp.monster[gp.currentMap][i].attackReaction();
				
				if(gp.monster[gp.currentMap][i].Life <= 0) {
					gp.monster[gp.currentMap][i].dying = true;
					gp.ui.addMessage(" Killed the " + gp.monster[gp.currentMap][i].name +"!");
					gp.ui.addMessage(" Exp earned " + gp.monster[gp.currentMap][i].exp);
					exp += gp.monster[gp.currentMap][i].exp;
					checkLevelExp();
				}
			}
		}
	}
	
	public void damageInteractiveTile(int i) {
	if(i != 999 && gp.iTile[gp.currentMap][i].destructible == true && 
			gp.iTile[gp.currentMap][i].isCorrectWeapon(this) == true && gp.iTile[gp.currentMap][i].invincible == false){
		
		gp.iTile[gp.currentMap][i].playSE();
		gp.iTile[gp.currentMap][i].Life--;
		gp.iTile[gp.currentMap][i].invincible = true;
		
		generateParticle(gp.iTile[gp.currentMap][i], gp.iTile[gp.currentMap][i]);//GENERATE PARTICLE
		
		if(gp.iTile[gp.currentMap][i].Life < 0) {
			gp.iTile[gp.currentMap][i] = gp.iTile[gp.currentMap][i].getDestroyedForm();
		}
	}
}
	
	public void damageProjectile(int i) {
		if(i != 999) {
			Entity projectile = gp.projectile[gp.currentMap][i];
			projectile.alive = false;
			generateParticle(projectile, projectile);
		}
	}
	
	public void checkLevelExp() {
		
		if(exp >= nextLevelExp) {
			level++;
			nextLevelExp = nextLevelExp*3;
			maxLife += 2;
			strength++;
			dexterity++;
			attack = getAttack();
			defense = getDefense();
			
			gp.playSE(8);
			gp.gameState = gp.dialougeState;
			
			setDialouge();
			startDialouge(this, 0);
		}
	}
	
	public void selectItem() {
		
		int itemIndex = gp.ui.getItemIndexOnSlot(gp.ui.playerSlotCol, gp.ui.playerSlotRow);
		
		if(itemIndex < inventory.size()) {
			
			Entity selectedItem = inventory.get(itemIndex);
			if(selectedItem.type == type_sword || selectedItem.type == type_axe ||
					selectedItem.type == type_swordGolden ||selectedItem.type == type_pickAxe) {
				currentWeapon = selectedItem;
				attack = getAttack();
				getPlayerAttackImage();
			}
		if(selectedItem.type == type_shield) {
			   currentShield = selectedItem;
			   defense = getDefense();
		    }
		if(selectedItem.type == type_light) {
			
			if(currentLight == selectedItem) {
				currentLight = null;
			}
			else {
				currentLight = selectedItem;
			}
			lightUpdated = true;
		}
		if(selectedItem.type == type_consumable) {
			   if( selectedItem.use(this)== true){
				   if(selectedItem.amount > 1) {
					   selectedItem.amount--;
				   }
				   else {
					   inventory.remove(itemIndex);
				   }
				 }
			}
		}
	}
	
	public int searchItemInInventory(String itemName) {
		//CAN BE USED FOR CHECKING QUEST ITEM
		int itemIndex = 999;
		for(int i = 0; i < inventory.size(); i++) {
			if(inventory.get(i).name.equals(itemName)) {
				itemIndex = i;
				break;
				
			}
		}
		return itemIndex;
	}
	
	public boolean canObtainItem(Entity item) {
		
		boolean canObtain = false;
		
		Entity newItem = gp.eGenerator.getObject(item.name);
		
		//CHECK IF STACKABLE
		if(newItem.stackable == true) {
			int index = searchItemInInventory(newItem.name);
			
			if(index != 999) {
				inventory.get(index).amount++;
				canObtain = true;
			}
			else {
				//NEW ITEM , CHECK VACANCY
				if(inventory.size() != maxInventorySize) {
					inventory.add(newItem);
					canObtain = true;
				}
			}
		}
		else {
			// NON STACKABLE ITEMS
			if(inventory.size() != maxInventorySize) {
				inventory.add(newItem);
				canObtain = true;
			}
		}
		return canObtain;
	}
	
	public void draw( Graphics2D g2 ) {
		 
		BufferedImage image = null;
		int tempScreenX = screenX;
		int tempScreenY = screenY;
		
		switch(direction) {
		case "up":
			if(attacking == false) {
				if(spriteNum == 1){ image = up1;}
				if(spriteNum == 2) { image = up2;}
			}
			if(attacking == true) {
				tempScreenY = screenY - gp.tilesize;
				if(spriteNum == 1){ image = attackup1;}
				if(spriteNum == 2) { image = attackup2;}
			}
			if(guarding == true) {
				image = guardUp;
			}
			break;
			
		case "down":
			if(attacking == false) {
				if(spriteNum == 1){ image = down1;}
				if(spriteNum == 2) { image = down2;}
			}
			if(attacking == true) {
				if(spriteNum == 1){ image = attackdown1;}
				if(spriteNum == 2) { image = attackdown2;}
			}
			if(guarding == true) {
				image = guardDown;
			}
			break;
			
		case "left":
			if(attacking == false) {
				if(spriteNum == 1){ image = left1;}
				if(spriteNum == 2) { image = left2;}
			}
			if(attacking == true) {
				tempScreenX = screenX - gp.tilesize;
				if(spriteNum == 1){ image = attackleft1;}
				if(spriteNum == 2) { image = attackleft2;}
			}
			if(guarding == true) {
				image = guardLeft;
			}
			break;
			
		case "right":
			if(attacking == false) {
				if(spriteNum == 1){ image = right1;}
				if(spriteNum == 2) { image = right2;}
			}
			if(attacking == true) {
				if(spriteNum == 1){ image = attackright1;}
				if(spriteNum == 2) { image = attackright2;}
			}
			if(guarding == true) {
				image = guardRight;
			}
			break;
		
		}
		
		if(transparent == true) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
		}
		
		if(drawing == true) {
			g2.drawImage(image, tempScreenX, tempScreenY, null);		
		}
		
		//RESET ALPHACOMPOSITE
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
	
	}
}
