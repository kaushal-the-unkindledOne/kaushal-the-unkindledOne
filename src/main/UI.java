package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.awt.BasicStroke;
import javax.swing.Spring;
import entity.Entity;
import objects.OBJ_Coin_Bronze;
import objects.OBJ_Heart;
import objects.OBJ_ManaCrystal;

public class UI {
	
	private static final String Ariel = null;
	GamePanel gp;
	Graphics2D g2;
	Font maruMonica, purisaB;
	
	//BufferedImage keyImage;
	BufferedImage heart_full, heart_half, heart_blank, crystal_full, crystal_blank, coin;
	public boolean messageOn = false;
	ArrayList<String> message = new ArrayList<>();
	ArrayList<Integer> messageCounter = new ArrayList<>();
	public boolean gameFinished = false;
	public String CurrentDialouge = "";
	public int commandNum = 0;
	public int titleScreenState = 0;
	public int playerSlotCol = 0;
	public int playerSlotRow = 0;
	public int npcSlotCol = 0;
	public int npcSlotRow = 0;
	public int subState = 0;
	int counter = 0;
	public  Entity npc;
	int charIndex = 0;
	String combinedText = "";
	
	public  UI(GamePanel gp) {
		this.gp = gp;
		try {
			InputStream is = getClass().getResourceAsStream("/font/x12y16pxMaruMonica.ttf");
			maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
			is = getClass().getResourceAsStream("/font/Purisa Bold.ttf");
			purisaB = Font.createFont(Font.TRUETYPE_FONT, is);
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		//CREATE HUD OBJECT
		Entity heart = new OBJ_Heart(gp);
		heart_full = heart.image;
		heart_half = heart.image2;
		heart_blank = heart.image3;
		Entity crystal = new OBJ_ManaCrystal(gp);
		crystal_full = crystal.image;
		crystal_blank = crystal.image2;
	    Entity bronzeCoin = new OBJ_Coin_Bronze(gp);
	    coin = bronzeCoin.down1;
	}
	
	 public void addMessage(String text) {
		 message.add(text);
		 messageCounter.add(0);
		 
	 }
     public void draw(Graphics2D g2) {
    	 this.g2 = g2;
    	 g2.setFont(maruMonica);
    	 g2.setColor(Color.white);
    	 
    	 //TITLE SCRREN
    	 if(gp.gameState == gp.titleState) {
    		 drawTitleScreen();
    	 }
    	  
    	 //PLAY STATE
    	 if(gp.gameState == gp.playState) {	 
    		 drawPlayerLife();
    		 drawMonsterLife();
    		 drawMessage();
    	 }
    	 
    	 //PAUSE STATE
    	 if(gp.gameState == gp.pauseState) {
    		 drawPlayerLife();
    		 drawPauseScreen();
    	 }
    	 
    	 //DIALOUGE STATE
    	 if(gp.gameState == gp.dialougeState){
    		 drawPlayerLife();
    		 drawDialougeScreen();
    	 }
    	 
    	 //CHARACTER SCREEN STATE
    	 if(gp.gameState == gp.characterScreenState){
    		 drawCharacterScreen();
    		 drawInventory(gp.player, true);
    	 }
    	 
    	 //OPTIONS STATE
    	 if(gp.gameState == gp.optionState){
    		 drawOptionScreen();
    	 }
    	 
    	//GAMEOVER STATE
    	 if(gp.gameState == gp.gameOverState){
    		 drawGameOverScreenScreen();
    	 }
    	 
    	 //TRANSITION STATE
    	 if(gp.gameState == gp.transitionState){
    		 drawTransitionScreen();
    	 }
    	 
    	//TRADE STATE
    	 if(gp.gameState == gp.tradeState){
    		 drawTradeScreen();
    	 }
    	 
    	//SLEEP STATE
    	 if(gp.gameState == gp.sleepState){
    		 drawSleepScreen();
    	 }
    }
     
     public void drawPlayerLife() {
    	 int x = gp.tilesize/2;
    	 int y = gp.tilesize/2;
    	 int i = 0;
    	 
    	 //PLAYER MAX LIFE
    	 while(i < gp.player.maxLife/2) {
    		 g2.drawImage(heart_blank, x, y, null);
    		 i++;
    		 x += gp.tilesize;
    	 }
    	 
    	 //RESET
    	  x = gp.tilesize/2;
    	  y = gp.tilesize/2;
    	  i = 0;
    	  
    	  //CURRENT LIFE
    	  while(i < gp.player.Life) {
    		  g2.drawImage(heart_half, x, y, null);
    		  i++;
    		  if(i < gp.player.Life) {
    			  g2.drawImage(heart_full, x, y, null);
    			  
    		  }
    		  i++;
    		  x += gp.tilesize;
    	  }
    	  
    	  //DRAW MAX MANA
    	  x = (gp.tilesize/2)-5;
    	  y = (int)(gp.tilesize*1.5);
    	  i = 0;
    	  while(i < gp.player.maxMana) {
    		  g2.drawImage(crystal_blank, x, y, null);
    		  i++;
    		  x += 35;
          }
    	  //DRAW MANA
    	  x = (gp.tilesize/2)-5;
    	  y = (int)(gp.tilesize*1.5);
    	  i = 0;
    	  while(i < gp.player.mana) {
    		  g2.drawImage(crystal_full, x, y, null);
    		  i++;
    		  x += 35;
          }
     }
     
     public void drawMonsterLife() {
    	 
    	 for(int i = 0; i < gp.monster[1].length; i++) {
    		 
    		 if(gp.monster[gp.currentMap][i] != null && gp.monster[gp.currentMap][i].inCamera() == true) {
    			 
    			 if(gp.monster[gp.currentMap][i].hpBarOn == true && gp.monster[gp.currentMap][i].boss == false){
    					double oneScale = (double)gp.tilesize/gp.monster[gp.currentMap][i].maxLife;
    					double hpBarValue = oneScale*gp.monster[gp.currentMap][i].Life;
    					
    				    g2.setColor(new Color(35,35,35));
    					g2.fillRect(gp.monster[gp.currentMap][i].getScreenX()-1, gp.monster[gp.currentMap][i].getScreenY()-16, gp.tilesize+2, 12);
    					g2.setColor(new Color(255,0,30));
    		 			g2.fillRect(gp.monster[gp.currentMap][i].getScreenX(), gp.monster[gp.currentMap][i].getScreenY() - 15, (int)hpBarValue, 10);
    		 			
    		 			gp.monster[gp.currentMap][i].hpBarCounter++;
    		 			if(gp.monster[gp.currentMap][i].hpBarCounter > 420) {
    		 				gp.monster[gp.currentMap][i].hpBarCounter = 0;
    		 				gp.monster[gp.currentMap][i].hpBarOn = false;
    		 		}
    			}
    			 else if(gp.monster[gp.currentMap][i].boss == true) {
    				 double oneScale = (double)gp.tilesize*8/gp.monster[gp.currentMap][i].maxLife;
 					double hpBarValue = oneScale*gp.monster[gp.currentMap][i].Life;
 					
 					int x = gp.screenWidth/2 - gp.tilesize*4;
 					int y = gp.tilesize*10;
 					
 				    g2.setColor(new Color(35,35,35));
 					g2.fillRect(x-1, y-1, gp.tilesize*8+2, 22);
 					g2.setColor(new Color(255,0,30));
 		 			g2.fillRect(x, y, (int)hpBarValue, 20);
 		 			g2.setFont(g2.getFont().deriveFont(Font.BOLD,24f));
 		 			g2.setColor(Color.WHITE);
 		 			g2.drawString(gp.monster[gp.currentMap][i].name, x+4, y-10);
 		 			
    			 }
    		 }
           }
       }
     
     public void drawMessage() {
    	 int messageX = gp.tilesize;
    	 int messageY = gp.tilesize*4;
    	 g2.setFont(g2.getFont().deriveFont(Font.BOLD , 32f));
    	 
    	 for(int i = 0; i < message.size(); i++) {
    		 if(message.get(i) != null) {
    			 g2.setColor(Color.black);
    			 g2.drawString(message.get(i), messageX+3, messageY+3);
    			 
    			 g2.setColor(Color.WHITE);
    			 g2.drawString(message.get(i), messageX, messageY);
    			 
    			 int counter = messageCounter.get(i) + 1; //messagecounter++
    			 messageCounter.set(i, counter);
    			 messageY+= 50;
    			 
    			 if(messageCounter.get(i) > 180) {
    				 message.remove(i);
    				 messageCounter.remove(i);
    			 }
    		 }
    	 }
     }
     
     public void drawTitleScreen() {
    	 
    	 if(titleScreenState == 0) {
    		 
    		 //BACKGROUND COLOR
        	 g2.setColor(new Color(32,32,32));
        	 g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        	 
        	 //TITLE NAME
        	 g2.setFont(g2.getFont().deriveFont(Font.BOLD,76F)); 
        	 String text = "ADVENTURE GAME";
        	 int x = getXforCenteredText(text);
        	 int y = gp.tilesize*3;
        	 
        	 //SHADOW
        	 g2.setColor(Color.black);
        	 g2.drawString(text, x+7, y+7);
        	 
        	//MAIN COLOR
        	 g2.setColor(Color.white);
        	 g2.drawString(text, x, y);
        	 
        	 //PLAYER IMAGE
        	 x = gp.screenWidth/2 - (gp.tilesize*2)/2;
        	 y += gp.tilesize*2;
        	 g2.drawImage(gp.player.down1, x, y, gp.tilesize*2, gp.tilesize*2, null);
        	 
        	 //MENU
        	 g2.setFont(g2.getFont().deriveFont(Font.BOLD,38F));
        	 
        	 text = "NEW GAME";
        	 x = getXforCenteredText(text);
        	 y += gp.tilesize*3.5;
        	 g2.drawString(text, x, y);
        	 if(commandNum == 0) {
        		 g2.drawString("→", x-gp.tilesize, y);
        	 }
        	 
        	 text = "LOAD GAME";
        	 x = getXforCenteredText(text);
        	 y += gp.tilesize;
        	 g2.drawString(text, x, y);
        	 if(commandNum == 1) {
        		 g2.drawString("→", x-gp.tilesize, y);
        	 }
        	 
        	 text = "QUIT";
        	 x = getXforCenteredText(text);
        	 y += gp.tilesize;
        	 g2.drawString(text, x, y);
        	 if(commandNum == 2) {
        		 g2.drawString("→", x-gp.tilesize, y);
        	 }
    	 }
    	 else if(titleScreenState == 1) {
    		 
    		 g2.setColor(new Color(0,0,0));
    		 g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
    		 
    		 // CLASS SELECTION
    		 g2.setColor(Color.white);
    		 g2.setFont(g2.getFont().deriveFont(35F));
    		 
    		 String text ="ARISE NOW YE TARNISHED!";
    		 int x = getXforCenteredText(text);
        	 int y = gp.tilesize*3;
        	 g2.drawString(text, x, y);
        	 
        	 text = "WARRIOR";
        	  x = getXforCenteredText(text);
        	  y += gp.tilesize*3;
        	 g2.drawString(text, x, y);
        	 if(commandNum == 0) {
        		 g2.drawString("→", x-gp.tilesize, y);
        	 }
        	 
        	 text = "MAGE";
        	  x = getXforCenteredText(text);
        	  y += gp.tilesize;
        	 g2.drawString(text, x, y);
        	 if(commandNum == 1) {
        		 g2.drawString("→", x-gp.tilesize, y);
        	 }

        	 text = "ASSASSIAN";
        	  x = getXforCenteredText(text);
        	  y += gp.tilesize;
        	 g2.drawString(text, x, y);
        	 if(commandNum == 2) {
        		 g2.drawString("→", x-gp.tilesize, y);
        	 }

        	 text = "BACK";
        	  x = getXforCenteredText(text);
        	  y += gp.tilesize*2;
        	 g2.drawString(text, x, y);
        	 if(commandNum == 3) {
        		 g2.drawString("→", x-gp.tilesize, y);
        	 }
        
    	 }
     
     }
     
     
     public void drawPauseScreen() {
    	 g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
    	 String text = "Paused";
    	 int x = getXforCenteredText(text);
    	 
    	 int y = gp.screenHeight/2;
    	 g2.drawString(text, x, y);
    	 
     }
     
     public void drawDialougeScreen() {
    	 
    	 //WINDOW
    	 int x = gp.tilesize*3;
    	 int y = gp.tilesize/2;
    	 int width = gp.screenWidth - (gp.tilesize*6);
    	 int height = gp.tilesize*4;
    	 drawSubWindow(x, y, width, height);
    	 
    	 g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32f));
    	 x += gp.tilesize;
    	 y += gp.tilesize;
    	 
    	 if(npc.dialouge[npc.dialougeSet][npc.DialougeIndex] != null) {
//    		 CurrentDialouge = npc.dialouge[npc.dialougeSet][npc.DialougeIndex];
    		 char characters[] = npc.dialouge[npc.dialougeSet][npc.DialougeIndex].toCharArray();
    		 if(charIndex < characters.length) {
    			 gp.playSE(19);
    			 String s = String.valueOf(characters[charIndex]);
    			 combinedText = combinedText + s;
    			 CurrentDialouge = combinedText;
    			 charIndex++;
    		 }
    		 
    		 if(gp.keyH.enterPressed == true) {
    			 
    			 charIndex = 0;
    			 combinedText = "";
    			 
    			 if(gp.gameState == gp.dialougeState || gp.gameState == gp.cutsceneState) {
    				 npc.DialougeIndex++;
    				 gp.keyH.enterPressed = false;
    			 }
    		 }
    	 }
    	 else {
    		 npc.DialougeIndex = 0;
    		 
    		 if(gp.gameState == gp.dialougeState) {
    			 gp.gameState = gp.playState;
    		 }
    		 if(gp.gameState == gp.cutsceneState) {
    			 gp.cManager.scenePhase++;
    		 }
    	 }
    	 
    	 for(String line : CurrentDialouge.split("\n")) {
    		 g2.drawString(line, x, y);
    		 y += 40;
    	 }
     }
     
     public void drawCharacterScreen() {
    	 
    	 //FRAME
    	 final int frameX = gp.tilesize*2;
    	 final int frameY = gp.tilesize;
    	 final int frameWidth = gp.tilesize*5;
    	 final int frameHeight = gp.tilesize*10;
    	 
    	 drawSubWindow(frameX, frameY, frameWidth, frameHeight);
    	 
    	 //TEXT
    	 g2.setColor(Color.white);
    	 g2.setFont(g2.getFont().deriveFont(32f));
    	 
    	 int textX = frameX + gp.tilesize/2;
    	 int textY = frameY + gp.tilesize;
    	 final int lineHeight = 36;
    	 
    	 g2.drawString("Level", textX, textY);
    	 textY += lineHeight;
    	 g2.drawString("Life", textX, textY);
    	 textY += lineHeight;
    	 g2.drawString("Mana", textX, textY);
    	 textY += lineHeight;
    	 g2.drawString("strength", textX, textY);
    	 textY += lineHeight;
    	 g2.drawString("Dexterity", textX, textY);
    	 textY += lineHeight;
    	 g2.drawString("Attack", textX, textY);
    	 textY += lineHeight;
    	 g2.drawString("Defense", textX, textY);
    	 textY += lineHeight;
    	 g2.drawString("Exp", textX, textY);
    	 textY += lineHeight;
    	 g2.drawString("Next Level", textX, textY);
    	 textY += lineHeight;
    	 g2.drawString("Coins ", textX, textY);
    	 textY += lineHeight + 10;
    	 g2.drawString("Weapon", textX, textY);
    	 textY += lineHeight + 15;
    	 g2.drawString("Shield", textX, textY);
    	 textY += lineHeight;
    	 
    	 //VALUES
    	 int tailX = (frameX + frameWidth) - 20;
    	 
    	 //RESET TEXTY
    	 textY = frameY + gp.tilesize;
    	 String Value;
    	 
    	 Value = String.valueOf(gp.player.level);
    	 textX = getXforAllignToRhs(Value,tailX);
    	 g2.drawString(Value, textX, textY);
    	 textY += lineHeight;
    	 
    	 Value = String.valueOf(gp.player.Life + "/" + gp.player.maxLife);
    	 textX = getXforAllignToRhs(Value,tailX);
    	 g2.drawString(Value, textX, textY);
    	 textY += lineHeight;
    	 
    	 Value = String.valueOf(gp.player.mana + "/" + gp.player.maxMana);
    	 textX = getXforAllignToRhs(Value,tailX);
    	 g2.drawString(Value, textX, textY);
    	 textY += lineHeight;
    	 
    	 Value = String.valueOf(gp.player.strength);
    	 textX = getXforAllignToRhs(Value,tailX);
    	 g2.drawString(Value, textX, textY);
    	 textY += lineHeight;
    	 
    	 Value = String.valueOf(gp.player.dexterity);
    	 textX = getXforAllignToRhs(Value,tailX);
    	 g2.drawString(Value, textX, textY);
    	 textY += lineHeight;
    	 
    	 Value = String.valueOf(gp.player.attack);
    	 textX = getXforAllignToRhs(Value,tailX);
    	 g2.drawString(Value, textX, textY);
    	 textY += lineHeight;
    	 
    	 Value = String.valueOf(gp.player.defense);
    	 textX = getXforAllignToRhs(Value,tailX);
    	 g2.drawString(Value, textX, textY);
    	 textY += lineHeight;
    	 
    	 Value = String.valueOf(gp.player.exp);
    	 textX = getXforAllignToRhs(Value,tailX);
    	 g2.drawString(Value, textX, textY);
    	 textY += lineHeight;
    	 
    	 Value = String.valueOf(gp.player.nextLevelExp);
    	 textX = getXforAllignToRhs(Value,tailX);
    	 g2.drawString(Value, textX, textY);
    	 textY += lineHeight;
    	 
    	 Value = String.valueOf(gp.player.coin);
    	 textX = getXforAllignToRhs(Value,tailX);
    	 g2.drawString(Value, textX, textY);
    	 textY += lineHeight;
    	 
    	 g2.drawImage(gp.player.currentWeapon.down1, tailX - gp.tilesize, textY-30, null);
    	 textY += gp.tilesize;
    	 
    	 g2.drawImage(gp.player.currentShield.down1, tailX - gp.tilesize, textY-30, null);
    	 
     }
     
     public void drawInventory(Entity entity, boolean cursor){
    	 
    	 int frameX = 0;
    	 int frameY = 0;
    	 int frameWidth = 0;
    	 int frameHeight = 0;
    	 int slotCol = 0;
    	 int slotRow = 0;
    	 
    	 if(entity == gp.player) {
    	  frameX = gp.tilesize*12;
       	  frameY = gp.tilesize;
       	  frameWidth = gp.tilesize*6;
       	  frameHeight = gp.tilesize*5;
       	  slotCol = playerSlotCol;
       	  slotRow = playerSlotRow;
    	}
    	 else {
    		 frameX = gp.tilesize*2;
          	  frameY = gp.tilesize;
          	  frameWidth = gp.tilesize*6;
          	  frameHeight = gp.tilesize*5;
          	  slotCol = npcSlotCol;
          	  slotRow = npcSlotRow;
    	}
    	 //FRAME
    	 drawSubWindow(frameX, frameY, frameWidth, frameHeight);
    	 
    	 //SLOTS
    	 final int slotXstart = frameX + 20;
    	 final int slotYstart = frameY + 20;
    	 int slotX = slotXstart;
    	 int slotY = slotYstart;
    	 int slotSize = gp.tilesize+3;
    	 
    	 //PLAYER'S ITEMS
    	 for(int i = 0; i < entity.inventory.size(); i++) {
    		 
    		 //EQUIP ITEM
    		 if(entity.inventory.get(i) == entity.currentWeapon ||
    				 entity.inventory.get(i) == entity.currentShield ||
    				 entity.inventory.get(i) == entity.currentLight){
    					 g2.setColor(new Color(240,190,90));
    					 g2.fillRoundRect(slotX, slotY, gp.tilesize, gp.tilesize, 10, 10);
    				 }
    		 
    		 
    		 g2.drawImage(entity.inventory.get(i).down1, slotX, slotY, null);
    		 
    		 //DISPLAY AMOUNT
    		 if(entity == gp.player && entity.inventory.get(i).amount > 1) {
    			 g2.setFont(g2.getFont().deriveFont(32f));
    			 
    			 int amountX;
    			 int amountY;
    			 
    			 String s = " " + entity.inventory.get(i).amount;
    			 amountX = getXforAllignToRhs(s, slotX+44);
    			 amountY = slotY + gp.tilesize;
    			 
    			 //DRAW SHADOW
    			 g2.setColor(new Color(60,60,60));
    			 g2.drawString(s, amountX, amountY);
    			 
    			 //DRAW NUMBER
    			 g2.setColor(Color.WHITE);
    			 g2.drawString(s, amountX-3, amountY-3);
    			 
    		 }
    		 
    		 slotX += slotSize;
    		 
    		 if(i == 4 || i == 9 || i == 14) {
    			 slotX = slotXstart;
    			 slotY += slotSize;
    		 }
    	 }
    	 
    	 //CURSOR
    	 if(cursor == true) {
    		 int cursorX = slotXstart + (slotSize * slotCol);
        	 int cursorY = slotYstart + (slotSize * slotRow);
        	 int cursorWidth = gp.tilesize;
        	 int cursorHeight = gp.tilesize;
        	 
        	 //DRAW CURSOR
        	 g2.setColor(Color.white);
        	 g2.setStroke(new BasicStroke(3));
        	 g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);
        	 
        	 //DESCRIPTION FRAME
        	 int dframeX = frameX;
        	 int dframeY = frameY + frameHeight + 6;  
        	 int dframeWidth = frameWidth; 
        	 int dframeHeight = gp.tilesize*3;
        	
             
        	 //DRAW DESCRIPTION TEXT
        	 int textX = dframeX + 20;;
        	 int textY = dframeY + gp.tilesize;
        	 g2.setFont(g2.getFont().deriveFont(28f));
        	 int itemIndex = getItemIndexOnSlot(slotCol, slotRow);
        	 
        	 if(itemIndex < entity.inventory.size()) {
        		 
        		 drawSubWindow(dframeX, dframeY, dframeWidth, dframeHeight);
        		 
        		for(String line: entity.inventory.get(itemIndex).description.split("\n")){
        			 g2.drawString(line, textX, textY);
        			 textY += 38;
        		}
        	 }
    	 }
     }
     
     public void drawGameOverScreenScreen() {
    	 g2.setColor(new Color(0,0,0,150));
    	 g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
    	 
    	 int x;
    	 int y;
    	 String text;
    	 g2.setFont(g2.getFont().deriveFont(Font.ROMAN_BASELINE, 110f));
    	 
    	 text = "YOU DIED";
    	 
    	 
    	 //SHADOW
    	 g2.setColor(Color.BLACK);
    	 x = getXforCenteredText(text);
    	 y = gp.tilesize*4;
    	 g2.drawString(text, x, y);
    	 
    	 //MAIN STUFF
    	 g2.setColor(Color.red);
    	 g2.drawString(text, x-4, y-4);
    	 
    	 //RETRY
    	 g2.setColor(new Color(85,239,80));
    	 g2.setFont(g2.getFont().deriveFont(50f));
    	 text = "Resurrect";
    	 x = getXforCenteredText(text);
    	 y += gp.tilesize*4;
    	 g2.drawString(text, x, y);
    	 if(commandNum == 0) {
     		g2.drawString("→", x-40, y);
     		}
    	 
    	//RETURN TO TITLESCREEN
    	 g2.setColor(new Color(209,21,27));
    	 text = "Die";
    	 x = getXforCenteredText(text);
    	 y += 55;
    	 g2.drawString(text, x, y);
    	 if(commandNum == 1) {
      		g2.drawString("→", x-40, y);
      		}
     }
          
     public void drawOptionScreen() {
    	 g2.setColor(Color.white);
    	 g2.setFont(g2.getFont().deriveFont(32f));
    	 
    	 //SUB WINDOW
    	 int frameX = gp.tilesize*6;
    	 int frameY = gp.tilesize;
    	 int frameWidth = gp.tilesize*8;
    	 int frameHeight = gp.tilesize*10;
    	 
    	 drawSubWindow(frameX,frameY,frameWidth,frameHeight);
    	 
    	 switch(subState) {
    	 case 0: option_Top(frameX,frameY); break;
    	 case 1: option_FullScreenNotification(frameX, frameY); break;
    	 case 2: option_Control(frameX,frameY); break;
    	 case 3: option_EndGame(frameX,frameY); break;
    	 }
    	 gp.keyH.enterPressed = false;
    	 
     }
     
     public void option_Top(int frameX,int frameY) {
    	 
    	 int textX;
    	 int textY;
    	 
    	 //TITLE
    	String text = "Options";
    	textX = getXforCenteredText(text);
    	textY = frameY + gp.tilesize;
    	g2.drawString(text, textX, textY);
    	
    	//FULLSCREEN ON/OFF
    	textX = frameX + gp.tilesize;
    	textY += gp.tilesize*2;
    	g2.drawString("Full Screen", textX, textY);
    	if(commandNum == 0) {
    		g2.drawString("→", textX-25, textY);
    		if(gp.keyH.enterPressed == true) {
        		if(gp.fullScreenOn == false) {
        			gp.fullScreenOn = true;
        		}
        		else if(gp.fullScreenOn == true) {
        			gp.fullScreenOn = false;
        		}
        	subState = 1;
    	   }
    	}
    	
    	//MUSIC
    	textY += gp.tilesize;
    	g2.drawString("Music", textX, textY);
    	if(commandNum == 1) {
    		g2.drawString("→", textX-25, textY);
    	}
    	
    	//SOUND EFFECT
    	textY += gp.tilesize;
    	g2.drawString("SE", textX, textY);
    	if(commandNum == 2) {
    		g2.drawString("→", textX-25, textY);
    	}
    	
    	//CONTROLS
    	textY += gp.tilesize;
    	g2.drawString("Control", textX, textY);
    	if(commandNum == 3) {
    		g2.drawString("→", textX-25, textY);
    		if(gp.keyH.enterPressed == true) {
    			subState = 2;
    			commandNum = 0;
    		}
    	}
    	
    	//END GAME
    	textY += gp.tilesize;
    	g2.drawString("Quit Game", textX, textY);
    	if(commandNum == 4) {
    		g2.drawString("→", textX-25, textY);
    	if(gp.keyH.enterPressed == true) {
    		subState = 3;
    		commandNum = 0;
    	}
    }
    	
    	//BACK
    	textY += gp.tilesize*2;
    	g2.drawString("Back", textX, textY);
    	if(commandNum == 5) {
    		g2.drawString("→", textX-25, textY);
    		if(gp.keyH.enterPressed == true) {
    			gp.gameState = gp.playState;
    			commandNum = 0;
    		}
    	}
    	
    	//FULL SCREEN CHECK BOX
    	textX = frameX + (int)(gp.tilesize*4.5);//
    	textY = frameY + gp.tilesize*2 + 24;//
    	g2.setStroke(new BasicStroke(3));
    	g2.drawRect(textX, textY, 24, 24);
    	if(gp.fullScreenOn == true){
    		g2.fillRect(textX, textY, 24, 24);
    	}
    	
    	//MUSIC VOLUME
    	textY += gp.tilesize;
    	g2.drawRect(textX, textY, 120, 24);
    	int volumeWidth = 24 * gp.music.volumeScale;
    	g2.fillRect(textX, textY, volumeWidth, 24);
    	
    	//SE VOLUME
    	textY += gp.tilesize;
    	g2.drawRect(textX, textY, 120, 24);
    	volumeWidth = 24 * gp.se.volumeScale;
    	g2.fillRect(textX, textY, volumeWidth, 24);
    	
    	gp.config.saveConfig();
    	
     }
     
     public void option_FullScreenNotification(int frameX,int frameY) {
    	 
    	 int textX = frameX + gp.tilesize;
    	 int textY = frameY + gp.tilesize*3;
    	 
    	 CurrentDialouge = "The Change will take \neffect after restarting \nthe Game.";
    	 for(String line: CurrentDialouge.split("\n")){
    		 g2.drawString(line, textX, textY);
    		 textY += 40;
    	 }
    	 
    	 //BACK
    	 textY = frameY + gp.tilesize*9;
    	 g2.drawString("Back", textX, textY);
    	 if(commandNum == 0) {
    		 g2.drawString("→", textX-25, textY);
    		 if(gp.keyH.enterPressed == true) {
    			 subState = 0;
    		 }
    	 }
     }
     
    public void option_Control(int frameX,int frameY) {
    	 
    	 int textX;
    	 int textY;
    	 
    	 ///TEXT
    	 String text = "Control";
    	 textX = getXforCenteredText(text);
    	 textY = frameY + gp.tilesize;
    	 g2.drawString(text, textX, textY);
    	 g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 25F));
    	 
    	 textX = frameX + gp.tilesize;
    	 textY += gp.tilesize;
    	 g2.drawString("Move", textX, textY); textY += gp.tilesize;
    	 g2.drawString("Confirm/Attack", textX, textY); textY += gp.tilesize;
    	 g2.drawString("Cast", textX, textY); textY += gp.tilesize;
    	 g2.drawString("Character Screen", textX, textY); textY += gp.tilesize;
    	 g2.drawString("Pause", textX, textY); textY += gp.tilesize;
    	 g2.drawString("Options", textX, textY); textY += gp.tilesize;
    	 
    	 textX = frameX + gp.tilesize*6;
    	 textY = frameY + gp.tilesize*2;
    	 g2.drawString("WASD", textX, textY); textY += gp.tilesize;
    	 g2.drawString("ENTER", textX, textY); textY += gp.tilesize;
    	 g2.drawString("F", textX, textY); textY += gp.tilesize;
    	 g2.drawString("C", textX, textY); textY += gp.tilesize;
    	 g2.drawString("P", textX, textY); textY += gp.tilesize;
    	 g2.drawString("ESC", textX, textY); textY += gp.tilesize;
    	 
    	 //BACK
    	 textX = frameX + gp.tilesize;
    	 textY = frameY + gp.tilesize*9;
    	 g2.drawString("Back", textX, textY);
    	 if(commandNum == 0) {
    		 g2.drawString("→", textX-25, textY);
    		 if(gp.keyH.enterPressed == true) {
    			 subState = 0;
    			 commandNum = 3;
    		 }
    	 }
     }
    
    public void option_EndGame(int frameX,int frameY) {
    	
    	int textX = frameX + gp.tilesize;
    	int textY = frameY + gp.tilesize*3;
    	
    	CurrentDialouge = "Do You want to \nQuit the game and\nreturn to TitleScreen?";
    	for(String line: CurrentDialouge.split("\n")){
   		 g2.drawString(line, textX, textY);
   		 textY += 40;
   	   }
    	
    	//OPTION YES 
    	String text = "YES";
    	textX = getXforCenteredText(text);
    	textY += gp.tilesize*3;
    	g2.drawString(text, textX, textY);
    	 if(commandNum == 0) {
    		 g2.drawString("→", textX-25, textY);
    		 if(gp.keyH.enterPressed == true) {
    			 subState = 0;
    			 gp.gameState = gp.titleState;
    			 gp.resetGame(true);
    		 }
    	}
    	
    	//OPTION NO
        text = "NO";
     	textX = getXforCenteredText(text);
     	textY += gp.tilesize;
     	g2.drawString(text, textX, textY);
     	 if(commandNum == 1) {
     		 g2.drawString("→", textX-25, textY);
     		 if(gp.keyH.enterPressed == true) {
     			 subState = 0;
     			commandNum = 4;
     		 }
     	}
    }
    
    public void drawTransitionScreen() {
    	
    	 counter++;
    	 g2.setColor(new Color(0,0,0,counter*5));
    	 g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
    	 
    	 if(counter == 50) {
    		 counter = 0;
    		 gp.gameState = gp.playState;
    		 gp.currentMap = gp.eHandler.tempMap;
    		 gp.player.worldX = gp.tilesize * gp.eHandler.tempCol;
    		 gp.player.worldY = gp.tilesize * gp.eHandler.tempRow;
    		 gp.eHandler.previousEventX = gp.player.worldX;
 			 gp.eHandler.previousEventY = gp.player.worldY;
 			 gp.changeArea();
    	 }
    }
    
    public void drawTradeScreen() {
    	
    	switch(subState) {
    	case 0: trade_select(); break;
    	case 1: trade_buy(); break;
    	case 2: trade_sell(); break;
    	}
    	gp.keyH.enterPressed = false;
    }
    
    public void trade_select() {
    	
    	npc.dialougeSet = 0;
    	drawDialougeScreen();
    	
    	//DRAW MINIWINDOW
    	int x = gp.tilesize*15;
    	int y  = gp.tilesize*4;
    	int width  = gp.tilesize*3;
    	int height  = (int) (gp.tilesize*3.5);
    	drawSubWindow(x,y,width,height);
    	
    	//DRAW TEXT
    	x += gp.tilesize;
    	y += gp.tilesize;
    	g2.drawString("Buy", x, y); 
    	if(commandNum == 0) {
    		g2.drawString(">", x-24, y);
    		if(gp.keyH.enterPressed == true) {
    			subState = 1;
    		}
    	}
    	y+= gp.tilesize;
    	
    	g2.drawString("Sell", x, y); 
    	if(commandNum == 1) {
    		g2.drawString(">", x-24, y);
    		if(gp.keyH.enterPressed == true) {
    			subState = 2;
    		}
    	}
    	y+= gp.tilesize;
    	
        g2.drawString("Leave", x, y);
        if(commandNum == 2) {
    		g2.drawString(">", x-24, y);
    		if(gp.keyH.enterPressed == true) {
    			commandNum = 0;
    			npc.startDialouge(npc,1);
    		}
    	}
    	
    }
    
    public void trade_buy() {
    	
    	//DRAW PLAYER INVENTORY
    	drawInventory(gp.player, false);
    	
    	//DRAW NPC INVENTORY
    	drawInventory(npc, true);
    	
    	//DRAW HINT WINDOW
    	int x = gp.tilesize*2;
    	int y  = gp.tilesize*9;
    	int width  = gp.tilesize*6;
    	int height  = gp.tilesize*2;
    	drawSubWindow(x,y,width,height);
    	g2.drawString(" [ESC] Back ", x+24, y+60);
    	
    	//DRAW COIN WINDOW
    	 x = gp.tilesize*12;
    	 y  = gp.tilesize*9;
    	 width  = gp.tilesize*6;
    	 height  = gp.tilesize*2;
    	drawSubWindow(x,y,width,height);
    	g2.drawString("Your Coins: "+ gp.player.coin, x+24, y+60);
    	
    	//DRAW PRICE WINDOW
    	int itemIndex = getItemIndexOnSlot(npcSlotCol, npcSlotRow);
    	if(itemIndex < npc.inventory.size()) {
    		
    		//DRAW PRICE WINDOW
        	 x = (int) (gp.tilesize*5.5);
        	 y  = (int) (gp.tilesize*5.5);
        	 width  = (int) (gp.tilesize*2.5);
        	 height  = gp.tilesize;
        	 drawSubWindow(x,y,width,height);
        	 g2.drawImage(coin, x+10, y+8, 32, 32, null);
        	 
        	 int price = npc.inventory.get(itemIndex).price;
        	 String text = "" + price;
        	 x = getXforAllignToRhs(text, gp.tilesize*8-20);
        	 g2.drawString(text, x, y+34); 
        	 
        	 //BUY ITEM
        	 if(gp.keyH.enterPressed == true) {
        		 if(npc.inventory.get(itemIndex).price > gp.player.coin) {
        			 subState = 0;
        			 npc.startDialouge(npc,2);
        		 }
        		 else {
        			 if(gp.player.canObtainItem(npc.inventory.get(itemIndex)) == true) {
        				 gp.player.coin -= npc.inventory.get(itemIndex).price;
        			 }
        			 else {
        				 subState = 0;
        				 npc.startDialouge(npc,3);
        			 }
        		 }
        	 }
    	}
    }
 
    public void trade_sell() {
    	
    	drawInventory(gp.player, true);
    	
    	int x;
    	int y;
    	int width;
    	int height;
    	
    	//DRAW HINT WINDOW
    	 x = gp.tilesize*2;
    	 y  = gp.tilesize*9;
    	 width  = gp.tilesize*6;
    	 height  = gp.tilesize*2;
    	drawSubWindow(x,y,width,height);
    	g2.drawString(" [ESC] Back ", x+24, y+60);
    	
    	//DRAW COIN WINDOW
    	 x = gp.tilesize*12;
    	 y  = gp.tilesize*9;
    	 width  = gp.tilesize*6;
    	 height  = gp.tilesize*2;
    	drawSubWindow(x,y,width,height);
    	g2.drawString("Your Coins: "+ gp.player.coin, x+24, y+60);
    	
    	//DRAW PRICE WINDOW
    	int itemIndex = getItemIndexOnSlot(playerSlotCol, playerSlotRow);
    	if(itemIndex < gp.player.inventory.size()) {
    		
    		//DRAW PRICE WINDOW
        	 x = (int) (gp.tilesize*15.5);
        	 y  = (int) (gp.tilesize*5.5);
        	 width  = (int) (gp.tilesize*2.5);
        	 height  = gp.tilesize;
        	 drawSubWindow(x,y,width,height);
        	 g2.drawImage(coin, x+10, y+8, 32, 32, null);
        	 
        	 int price = gp.player.inventory.get(itemIndex).price/2;
        	 String text = "" + price;
        	 x = getXforAllignToRhs(text, gp.tilesize*18-20);
        	 g2.drawString(text, x, y+34); 
        	 
        	 //SELL ITEM
        	 if(gp.keyH.enterPressed == true) {
        		 if(gp.player.inventory.get(itemIndex) == gp.player.currentWeapon || 
        				 gp.player.inventory.get(itemIndex) == gp.player.currentShield) {
        			 commandNum = 0;
        			 subState = 0;
        			 npc.startDialouge(npc,4);
        		 }
        		 else {
        			 if(gp.player.inventory.get(itemIndex).amount > 1) {
        				 gp.player.inventory.get(itemIndex).amount--;
        			 }
        			 else {
        				 gp.player.inventory.remove(itemIndex);
        			 }
        			 gp.player.coin += price;
        		}
        	}
    	}
 	
     }
    
    public void drawSleepScreen() {
    	
    	counter++;
    	if(counter < 120) {
    		gp.eManager.lighting.filterAlpha += 0.01f;
    		if(gp.eManager.lighting.filterAlpha > 1f) {
    			gp.eManager.lighting.filterAlpha = 1f;
    		 }
    		}
    	if(counter >= 120) {
    		gp.eManager.lighting.filterAlpha -= 0.01f;
    		if(gp.eManager.lighting.filterAlpha < 0f) {
    			gp.eManager.lighting.filterAlpha = 0f;
    			counter = 0;
    			gp.eManager.lighting.dayState = gp.eManager.lighting.day;
    			gp.eManager.lighting.dayCounter = 0;
    			gp.gameState = gp.playState;
    			gp.player.getPlayerImage();
    		 }
    	   }
    	}
   
     public int getItemIndexOnSlot(int slotCol, int slotRow){
    	 int itemIndex = slotCol + (slotRow*5);
    	 return itemIndex;
     }
     
     public void drawSubWindow(int x, int y, int width, int height){
    	Color c = new Color(0,0,0,220);
    	g2.setColor(c);
    	g2.fillRoundRect(x, y, width, height, 36, 36);
        c = new Color(255,255,255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, width-10, height-10, 26, 26);
       
     }

    	public int getXforCenteredText(String text) {
    		int Length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth(); 
       	    int x = gp.screenWidth/2 - Length/2;
       	    return x;
    	}
    	
    	public int getXforAllignToRhs(String text , int tailX) {
    		int Length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth(); 
       	    int x = tailX - Length;
       	    return x;
    	}
    		 }
    	 

