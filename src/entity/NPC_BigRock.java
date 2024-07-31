package entity;

import java.awt.Rectangle;
import java.util.Random;
import java.util.ArrayList;
import main.GamePanel;
import objects.OBJ_door_Iron;
import tile_interactive.IT_MetalPlate;
import tile_interactive.InteractiveTile;

public class NPC_BigRock extends Entity{
	
	public static final String npcName = "Big Rock";

	public NPC_BigRock(GamePanel gp) {
		super(gp);
		
		name = npcName;
		speed = 4;
		direction = "down";
		solidArea = new Rectangle();
		solidArea.x = 2;
		solidArea.y = 6;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 44;
		solidArea.height = 40;
		
		dialougeSet = -1;
		getImage();
		setDialouge();
		}
	
		public void getImage() {
		    up1 = setup("/npc/bigrock", gp.tilesize, gp.tilesize);
			up2 = setup("/npc/bigrock", gp.tilesize, gp.tilesize);
			down1 = setup("/npc/bigrock", gp.tilesize, gp.tilesize);
			down2 = setup("/npc/bigrock", gp.tilesize, gp.tilesize);
			left1 = setup("/npc/bigrock", gp.tilesize, gp.tilesize);
			left2 = setup("/npc/bigrock", gp.tilesize, gp.tilesize);
			right1 = setup("/npc/bigrock", gp.tilesize, gp.tilesize);
			right2 = setup("/npc/bigrock", gp.tilesize, gp.tilesize);
		}	
		
		public void setDialouge() {
			dialouge[0][0] = "It's a gaint rock.";
		}
		
		public void setAction() {}
		public void update() {}
		
		public void speak() {
			facePlayer();
			startDialouge(this, dialougeSet);
			dialougeSet++;
			
			if(dialouge[dialougeSet][0] == null) {
				dialougeSet = 0;
			}
		}
		
		public void move(String d) {
			this.direction = d;
			checkCollision();
			
			if(CollisionOn == false) {
				switch(direction) {
				case "up": worldY -= speed; break;
				case "down": worldY += speed; break;
				case "left": worldX -= speed; break;
				case "rigth": worldX += speed; break;
				}
			}
			detectPlate();
		}
		
		public void detectPlate() {
			ArrayList<InteractiveTile>plateList = new ArrayList<>();
			ArrayList<Entity>rockList = new ArrayList<>();
			
			//CREATE PLATE LIST
			for(int i = 0; i < gp.iTile[1].length; i++) {
				if(gp.iTile[gp.currentMap][i] != null && gp.iTile[gp.currentMap][i].name != null &&
						gp.iTile[gp.currentMap][i].name.equals(IT_MetalPlate.itName)) {
					plateList.add(gp.iTile[gp.currentMap][i]);
				}
			}
			
			//CREATE ROCK LIST
			for(int i = 0; i < gp.npc[1].length; i++) {
				if(gp.npc[gp.currentMap][i] != null && 
						gp.npc[gp.currentMap][i].name.equals(NPC_BigRock.npcName)){
					rockList.add(gp.npc[gp.currentMap][i]);
				}
			}
			
			int count = 0;
			
			//SCAN PLATE LIST
			for(int i = 0; i < plateList.size(); i++) {
				
				int xDistance = Math.abs(worldX - plateList.get(i).worldX);
				int yDistance = Math.abs(worldY - plateList.get(i).worldY);
				int distance = Math.max(xDistance, yDistance);
				
				if(distance < 8) {
					
					if(linkedEntity == null) {
						linkedEntity = plateList.get(i);
						gp.playSE(4);
					}
				}
				else {
					if(linkedEntity == plateList.get(i)) {
						linkedEntity = null;
					}
				}
            }
			
			//SCAN ROCK LIST
			for(int i = 0; i < rockList.size(); i++) {
				
				//COUNT ROCL ON PLATE
				if(rockList.get(i).linkedEntity != null) {
					count++;
				}
			}
			
			//IF ALL ROCKS ARE ON PLATE THEN DOOR OPEN
			if(count == rockList.size()) {
				for(int i = 0; i < gp.obj[1].length; i++) {
					if(gp.obj[gp.currentMap][i] != null && 
						gp.obj[gp.currentMap][i].name.equals(OBJ_door_Iron.objName)){
						
						gp.obj[gp.currentMap][i] = null;
						gp.playSE(22);
					}
				}
			}
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
		}
	}
