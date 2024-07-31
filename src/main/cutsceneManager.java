package main;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;

import entity.nakliPlayer;
import monster.MON_SkeletonLord;
import objects.OBJ_BlueCrystal;
import objects.OBJ_door_Iron;

public class cutsceneManager {
	
	GamePanel gp;
	Graphics2D g2;
	public int sceneNum;
	public int scenePhase;
	int counter;
	float alpha = 0f;
	int y;
	String endCredit;
	
	//SCENE NUMBER
	public final int NA = 0;
	public final int skeletonLord = 1;
	public final int ending = 2;
	
	public cutsceneManager(GamePanel gp) {
		this.gp = gp;
		
		endCredit = "Program/Music/Art\n"
				+ "Kaushal/CSE-B"
				+ "\n\n\n\n\n\n\n\n\n\n\n\n\n"
				+ "Game Project using Java\n"
				+ "Special thanks to myself for creating this.\n"
				+ "Thank you for Playing!\n"
				+ "Gai Hamari Maata hai.";
	}
	
	public void draw(Graphics2D g2) {
		this.g2 = g2;
		
		switch(sceneNum) {
		case skeletonLord: scene_skeletonLord(); break;
		case ending: scene_ending(); break;
		}
	}
	
	public void scene_skeletonLord() {
		
		if(scenePhase == 0) {
			gp.bossBattleOn = true;
			
			//SHUT THE IRON DOOR
			for(int i = 0; i < gp.obj[1].length; i++) {
				if(gp.obj[gp.currentMap][i] == null) {
					gp.obj[gp.currentMap][i] = new OBJ_door_Iron(gp);
					gp.obj[gp.currentMap][i].worldX =  gp.tilesize*25;
					gp.obj[gp.currentMap][i].worldY =  gp.tilesize*28;
					gp.obj[gp.currentMap][i].temp = true;
					gp.playSE(22);
					break;
				}
			}
			
			//SERACH VACANT SLOT FOR DUMMY
			for(int i = 0; i < gp.npc[1].length; i++) {
				if(gp.npc[gp.currentMap][i] == null) {
					gp.npc[gp.currentMap][i] = new nakliPlayer(gp);
 				    gp.npc[gp.currentMap][i].worldX =  gp.player.worldX;
					gp.npc[gp.currentMap][i].worldY =  gp.player.worldY;
					gp.npc[gp.currentMap][i].direction = gp.player.direction;
					break;
				}
			}
			
		gp.player.drawing = false;
			scenePhase++;
		}
		
		if(scenePhase == 1) {
			gp.player.worldY -= 2;
			
			if(gp.player.worldY < gp.tilesize*16) {
				scenePhase++;
			}
	 }
		
		if(scenePhase == 2) {
			
			//SEARCH THE BOSS
			for(int i = 0; i < gp.monster[1].length; i++) {
				if(gp.monster[gp.currentMap][i] != null  && 
					gp.monster[gp.currentMap][i].name == MON_SkeletonLord.monName){
					gp.monster[gp.currentMap][i].sleep = false;
					gp.ui.npc = gp.monster[gp.currentMap][i];
					scenePhase++;
					break;
					
				}
			}
		}
		if(scenePhase == 3) {
			
			//THE BOSS SPEAKS
			gp.ui.drawDialougeScreen();
		}
		
		if(scenePhase == 4) {
			for(int i = 0; i < gp.npc[1].length; i++) {
				if(gp.npc[gp.currentMap][i] != null &&
						gp.npc[gp.currentMap][i].name == nakliPlayer.dummyName) {
					//RESTORE PLAYER POSITION
					gp.player.worldX =  gp.npc[gp.currentMap][i].worldX;
					gp.player.worldY =  gp.npc[gp.currentMap][i].worldY;
					
					//DELETE NAKLI
					gp.npc[gp.currentMap][i] = null;
					break;
				}
		}
			//DRAW PALYER
			gp.player.drawing = true;	
			sceneNum = NA;
			scenePhase = 0;
			gp.gameState = gp.playState;
			
			gp.stopMusic();
			gp.playMusic(24);
		}
	}
	
	public void scene_ending() {
		
		if(scenePhase == 0) {
			
			gp.stopMusic();
			gp.ui.npc = new OBJ_BlueCrystal(gp);
			scenePhase++;
		}
		
		if(scenePhase == 1) {
			//DISPLAY DIALOUGE
			gp.ui.drawDialougeScreen();
		}
		
		if(scenePhase == 2) {
			//PLAY FANAFARE
			gp.playSE(25);
			scenePhase++;
		}
		
		if(scenePhase == 3) {
			//WAIT FOR MUSIC
			if(counterReached(300) == true) {
				scenePhase++;
			}
		}
		
		if(scenePhase == 4) {
			//THE SCREEEN GETS DARKER
			alpha += 0.005f;
			if(alpha > 1f) {
				alpha = 1f;
			}
			drawBlackBg(alpha);
			if(alpha == 1f) {
				alpha = 0;
				scenePhase++;
			}
		}
		
		if(scenePhase == 5) {
			drawBlackBg(1f);
			alpha += 0.005f;
			if(alpha > 1f) {
				alpha = 1f;
			}
			
			String text = "After the fierce battle with the Skeleton lord, \n"
					+ "the Boy finally found the legendary item. \n"
					+ "But this is not the end of his journey. \n"
					+ "the Blue Boy Adventure has just begun.";
			drawString(alpha, 38f, 200, text, 70);
			
			if(counterReached(600) == true) {
				gp.stopMusic();
				gp.playMusic(26);
				scenePhase++;
			}
		}
		
		if(scenePhase == 6) {
			drawBlackBg(1f);
			drawString(1f, 90f, gp.screenHeight/2, "Blue Boy Adventure", 30);
			if(counterReached(480) == true){
				scenePhase++;
			}
		}
		
		if(scenePhase == 7) {
		    drawBlackBg(1f);
			y = gp.screenHeight/2;
			drawString(1f, 38f, y, endCredit, 40);
			if(counterReached(480) == true) {
				scenePhase++;
			}
			
		}
		
		if(scenePhase == 8) {
			drawBlackBg(1f);
			//SCROLLING CREDITS
			y--;
			drawString(1f, 38f, y, endCredit, 40);
		}
	}
	
	public boolean counterReached(int target) {
		
		boolean counterReached = false;
		counter++;
		
		if(counter > target) {
			counterReached = true;
			counter = 0;
		}
		return counterReached;
	}
	
	public void drawBlackBg(float alpha) {
		
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
		g2.setColor(Color.black);
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
	}
	
	public void drawString(float alpha, float fontSize, int y, String text, int lineHeight) {
		
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(fontSize));
		
		for(String line: text.split("\n")){
			int x = gp.ui.getXforCenteredText(line);
	   		 g2.drawString(line, x, y);
	   		 y += lineHeight;
	   	}
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }
}
