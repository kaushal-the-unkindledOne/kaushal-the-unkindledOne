package entity;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;

import main.GamePanel;
import main.utilityTool;
import objects.OBJ_Axe;
import objects.OBJ_Potion_Red;
import objects.OBJ_Shield_Blue;
import objects.OBJ_Shield_Wood;
import objects.OBJ_Sword_Golden;
import objects.OBJ_Sword_Normal;
import objects.OBJ_key;

public class NPC_Merchant extends Entity{
	public NPC_Merchant(GamePanel gp) {
		super(gp);
		direction = "down";
		speed = 1;
		getImage();
		setDialouge();
		setInventory();
		}
	
		public void getImage() {
		    
			up1 = setup("/npc/merchant_down_1", gp.tilesize, gp.tilesize);
			up2 = setup("/npc/merchant_down_2", gp.tilesize, gp.tilesize);
			down1 = setup("/npc/merchant_down_1", gp.tilesize, gp.tilesize);
			down2 = setup("/npc/merchant_down_2", gp.tilesize, gp.tilesize);
			left1 = setup("/npc/merchant_down_1", gp.tilesize, gp.tilesize);
			left2 = setup("/npc/merchant_down_2", gp.tilesize, gp.tilesize);
			right1 = setup("/npc/merchant_down_1", gp.tilesize, gp.tilesize);
			right2 = setup("/npc/merchant_down_2", gp.tilesize, gp.tilesize);
			
		}
		
		public void setDialouge() {
		 dialouge[0][0] = "He He, so you found me.\nI have some good stuff. \nDo you want to trade";
		 dialouge[1][0] = "Come back any time";
		 dialouge[2][0] = "Broke Ass Nigga!";
		 dialouge[3][0] = "You cannot carry anymore!";
		 dialouge[4][0] = "You cannot sell equipped item!";
		}
		
		public void setInventory() {
			inventory.add(new OBJ_Potion_Red(gp));
			inventory.add(new OBJ_key(gp));
			inventory.add(new OBJ_Axe(gp));
			inventory.add(new OBJ_Sword_Normal(gp));
			inventory.add(new OBJ_Shield_Wood(gp));
			inventory.add(new OBJ_Shield_Blue(gp));
			inventory.add(new OBJ_Sword_Golden(gp));
			
		}
		
		public void speak() {
			facePlayer();
			gp.gameState = gp.tradeState;
			gp.ui.npc = this;
		}
		
	}

