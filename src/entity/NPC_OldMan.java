package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;

import main.GamePanel;
import main.utilityTool;

public class NPC_OldMan extends Entity{
	public NPC_OldMan(GamePanel gp) {
		super(gp);
		solidArea = new Rectangle();
		solidArea.x = 8;
		solidArea.y = 16;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 30;
		solidArea.height = 30;
		direction = "down";
		
		speed = 1;
		dialougeSet = -1;
		getImage();
		setDialouge();
		
		}
	
		public void getImage() {
		    up1 = setup("/npc/oldman_up_1", gp.tilesize, gp.tilesize);
			up2 = setup("/npc/oldman_up_2", gp.tilesize, gp.tilesize);
			down1 = setup("/npc/oldman_down_1", gp.tilesize, gp.tilesize);
			down2 = setup("/npc/oldman_down_2", gp.tilesize, gp.tilesize);
			left1 = setup("/npc/oldman_left_1", gp.tilesize, gp.tilesize);
			left2 = setup("/npc/oldman_left_2", gp.tilesize, gp.tilesize);
			right1 = setup("/npc/oldman_right_1", gp.tilesize, gp.tilesize);
			right2 = setup("/npc/oldman_right_2", gp.tilesize, gp.tilesize);
		}	
		public void setDialouge() {
			dialouge[0][0] = "Oh? You're approaching me?\nInstead of running away,\nyou're coming right to me?";
			dialouge[0][1] = "I can't beat the shit out of you\nwithout getting closer.";
			dialouge[0][2] = "Oh ho! Then come as close as you like.";
			dialouge[0][3] = "Ora!";
			
			dialouge[1][0] = "Too slow, too slow!\nThe World is the ultimate Stand.\nEven without his power to stop time";
			dialouge[1][1] = "So it's the same type of Stand as Star Platinum.\nNot much range, but immense power\nand precise movements.";
			dialouge[1][2] = "his speed and power far exceed\nthat of your Star Platinum";
			dialouge[1][3] = "*ZA WARUDO! TOKI WO TOMARE!*";
			
			dialouge[2][0] = "*MOANS*";
		}
		public void setAction() {
			
			if(onPath == true) {
				int goalCol = (gp.player.worldX + gp.player.solidArea.x)/gp.tilesize;
				int goalRow = (gp.player.worldY + gp.player.solidArea.y)/gp.tilesize;
				
				searchPath(goalCol, goalRow);
				
			}
			
			else {
				actionLockCounter++;
				
				if(actionLockCounter == 120) {
					Random random = new Random();
					int i = random.nextInt(100)+1;
					
					if(i <= 25) {
						direction = "up";
					}
					
					if(i > 25 && i <= 50) {
						direction = "down";
					}
					
					if(i > 50 && i <= 75) {
						direction = "left";
					}
					
					if(i > 75 && i <= 100) {
						direction = "right";
					}
					actionLockCounter = 0;
				}
			}
		}
		
		public void speak() {
			facePlayer();
			startDialouge(this, dialougeSet);
			dialougeSet++;
			
			if(dialouge[dialougeSet][0] == null) {
				dialougeSet = 0;
			
//				if(gp.player.Life < gp.player.maxLife) {
//					dialougeSet = 1;
//				}
			}
		}
		
	}


