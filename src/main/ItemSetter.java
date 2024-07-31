package main;

import data.progress;
import entity.NPC_BigRock;
import entity.NPC_Merchant;
import entity.NPC_OldMan;
import monster.MON_Bat;
import monster.MON_Greenslime;
import monster.MON_Orc;
import monster.MON_RedSlime;
import monster.MON_SkeletonLord;
import objects.OBJ_Axe;
import objects.OBJ_BlueCrystal;
import objects.OBJ_Chest;
import objects.OBJ_Coin_Bronze;
import objects.OBJ_Heart;
import objects.OBJ_Lantern;
import objects.OBJ_ManaCrystal;
import objects.OBJ_PickAxe;
import objects.OBJ_Potion_Red;
import objects.OBJ_Shield_Blue;
import objects.OBJ_Sword_Golden;
import objects.OBJ_Tent;
import objects.OBJ_door;
import objects.OBJ_door_Iron;
import objects.OBJ_key;
import tile_interactive.IT_DryTree;
import tile_interactive.IT_MetalPlate;
import tile_interactive.IT_destructibleWall;


public class ItemSetter {
	
	GamePanel gp;
	public ItemSetter(GamePanel gp ) {
		
		this.gp = gp;
		}
	
public void setObject() {
	
	int mapNum = 0;
	int i = 0;

	gp.obj[mapNum][i] = new OBJ_Tent(gp);
	gp.obj[mapNum][i].worldX =  gp.tilesize*19;
	gp.obj[mapNum][i].worldY =  gp.tilesize*20;
	i++;
	
	gp.obj[mapNum][i] = new OBJ_Coin_Bronze(gp);
	gp.obj[mapNum][i].worldX =  gp.tilesize*26;
	gp.obj[mapNum][i].worldY =  gp.tilesize*21;
	i++;
	
	gp.obj[mapNum][i] = new OBJ_Axe(gp);
	gp.obj[mapNum][i].worldX =  gp.tilesize*33;
	gp.obj[mapNum][i].worldY =  gp.tilesize*21;
	i++;
	
	gp.obj[mapNum][i] = new OBJ_Sword_Golden(gp);
	gp.obj[mapNum][i].worldX =  gp.tilesize*35;
	gp.obj[mapNum][i].worldY =  gp.tilesize*21;
	i++;
	
	gp.obj[mapNum][i] = new OBJ_Potion_Red(gp);
	gp.obj[mapNum][i].worldX =  gp.tilesize*22;
	gp.obj[mapNum][i].worldY =  gp.tilesize*27;
	i++;
	
	gp.obj[mapNum][i] = new OBJ_Potion_Red(gp);
	gp.obj[mapNum][i].worldX =  gp.tilesize*22;
	gp.obj[mapNum][i].worldY =  gp.tilesize*26;
	i++;
	
	gp.obj[mapNum][i] = new OBJ_Heart(gp);
	gp.obj[mapNum][i].worldX =  gp.tilesize*22;
	gp.obj[mapNum][i].worldY =  gp.tilesize*29;
	i++;
	
	gp.obj[mapNum][i] = new OBJ_ManaCrystal(gp);
	gp.obj[mapNum][i].worldX =  gp.tilesize*22;
	gp.obj[mapNum][i].worldY =  gp.tilesize*31;
	i++;
	
	gp.obj[mapNum][i] = new OBJ_door(gp);
	gp.obj[mapNum][i].worldX =  gp.tilesize*12;
	gp.obj[mapNum][i].worldY =  gp.tilesize*12;
	i++;
	
	gp.obj[mapNum][i] = new OBJ_door(gp);
	gp.obj[mapNum][i].worldX =  gp.tilesize*14;
	gp.obj[mapNum][i].worldY =  gp.tilesize*28;
	i++;
	
	gp.obj[mapNum][i] = new OBJ_Chest(gp);
	gp.obj[mapNum][i].setLoot(new OBJ_key(gp));
	gp.obj[mapNum][i].worldX =  gp.tilesize*25;
	gp.obj[mapNum][i].worldY =  gp.tilesize*23;
	i++;
	
	gp.obj[mapNum][i] = new OBJ_Chest(gp);
	gp.obj[mapNum][i].setLoot(new OBJ_Shield_Blue(gp));
	gp.obj[mapNum][i].worldX =  gp.tilesize*21;
	gp.obj[mapNum][i].worldY =  gp.tilesize*19;
	i++;
	
	gp.obj[mapNum][i] = new OBJ_Lantern(gp);
	gp.obj[mapNum][i].worldX =  gp.tilesize*18;
	gp.obj[mapNum][i].worldY =  gp.tilesize*20;
	i++;
	
	mapNum = 2;
	i = 0;
	gp.obj[mapNum][i] = new OBJ_Chest(gp);
	gp.obj[mapNum][i].setLoot(new OBJ_PickAxe(gp));
	gp.obj[mapNum][i].worldX =  gp.tilesize*40;
	gp.obj[mapNum][i].worldY =  gp.tilesize*41;
	i++;
	
	gp.obj[mapNum][i] = new OBJ_Chest(gp);
	gp.obj[mapNum][i].setLoot(new OBJ_Potion_Red(gp));
	gp.obj[mapNum][i].worldX =  gp.tilesize*13;
	gp.obj[mapNum][i].worldY =  gp.tilesize*16;
	i++;
	
	gp.obj[mapNum][i] = new OBJ_Chest(gp);
	gp.obj[mapNum][i].setLoot(new OBJ_Potion_Red(gp));
	gp.obj[mapNum][i].worldX =  gp.tilesize*26;
	gp.obj[mapNum][i].worldY =  gp.tilesize*34;
	i++;
	
//	gp.obj[mapNum][i] = new OBJ_door_Iron(gp);
//	gp.obj[mapNum][i].worldX =  gp.tilesize*18;
//	gp.obj[mapNum][i].worldY =  gp.tilesize*23;
//	i++;
	
	mapNum = 3;
	i = 0;
	gp.obj[mapNum][i] = new OBJ_door_Iron(gp);
	gp.obj[mapNum][i].worldX =  gp.tilesize*25;
	gp.obj[mapNum][i].worldY =  gp.tilesize*15;
	i++;
	
	gp.obj[mapNum][i] = new OBJ_BlueCrystal(gp);
	gp.obj[mapNum][i].worldX =  gp.tilesize*25;
	gp.obj[mapNum][i].worldY =  gp.tilesize*8;
	i++;
	}

public void setNPC() {
	
	int mapNum = 0;//FIRST MAP
	int i = 0;
	
	gp.npc[mapNum][i] = new NPC_OldMan(gp);
	gp.npc[mapNum][i].worldX =  gp.tilesize*21;
	gp.npc[mapNum][i].worldY =  gp.tilesize*21;
	
	mapNum = 1;//HUT
	i = 0;
	gp.npc[mapNum][i] = new NPC_Merchant(gp);
	gp.npc[mapNum][i].worldX =  gp.tilesize*12;
	gp.npc[mapNum][i].worldY =  gp.tilesize*7;
	
	mapNum = 2;//DUNGEON
	i = 0;
	gp.npc[mapNum][i] = new NPC_BigRock(gp);
	gp.npc[mapNum][i].worldX =  gp.tilesize*20;
	gp.npc[mapNum][i].worldY =  gp.tilesize*25;
	i++;
	
	gp.npc[mapNum][i] = new NPC_BigRock(gp);
	gp.npc[mapNum][i].worldX =  gp.tilesize*11;
	gp.npc[mapNum][i].worldY =  gp.tilesize*18;
	i++;
	
	gp.npc[mapNum][i] = new NPC_BigRock(gp);
	gp.npc[mapNum][i].worldX =  gp.tilesize*23;
	gp.npc[mapNum][i].worldY =  gp.tilesize*14;
	i++;
}

public void setMonster() {
	
	int mapNum = 0;
	int i = 0;

	gp.monster[mapNum][i] = new MON_Greenslime(gp);
	gp.monster[mapNum][i].worldX =  gp.tilesize*23;
	gp.monster[mapNum][i].worldY =  gp.tilesize*36;
	i++;
	
	gp.monster[mapNum][i] = new MON_Greenslime(gp);
	gp.monster[mapNum][i].worldX =  gp.tilesize*23;
	gp.monster[mapNum][i].worldY =  gp.tilesize*37;
	i++;
	
	gp.monster[mapNum][i] = new MON_RedSlime(gp);
	gp.monster[mapNum][i].worldX =  gp.tilesize*24;
	gp.monster[mapNum][i].worldY =  gp.tilesize*35;
	i++;
	
	gp.monster[mapNum][i] = new MON_Greenslime(gp);
	gp.monster[mapNum][i].worldX =  gp.tilesize*33;
	gp.monster[mapNum][i].worldY =  gp.tilesize*36;
	i++;
	
	gp.monster[mapNum][i] = new MON_RedSlime(gp);
	gp.monster[mapNum][i].worldX =  gp.tilesize*32;
	gp.monster[mapNum][i].worldY =  gp.tilesize*37;
	i++;
	
	gp.monster[mapNum][i] = new MON_RedSlime(gp);
	gp.monster[mapNum][i].worldX =  gp.tilesize*35;
	gp.monster[mapNum][i].worldY =  gp.tilesize*31;
	i++;
	
	gp.monster[mapNum][i] = new MON_Orc(gp);
	gp.monster[mapNum][i].worldX =  gp.tilesize*12;
	gp.monster[mapNum][i].worldY =  gp.tilesize*33;
	i++;
	
	mapNum = 2;
	i++;
	gp.monster[mapNum][i] = new MON_Bat(gp);
	gp.monster[mapNum][i].worldX =  gp.tilesize*34;
	gp.monster[mapNum][i].worldY =  gp.tilesize*39;
	i++;
//////	
	gp.monster[mapNum][i] = new MON_Bat(gp);
	gp.monster[mapNum][i].worldX =  gp.tilesize*36;
	gp.monster[mapNum][i].worldY =  gp.tilesize*25;
	i++;
//////	
	gp.monster[mapNum][i] = new MON_Bat(gp);
	gp.monster[mapNum][i].worldX =  gp.tilesize*39;
	gp.monster[mapNum][i].worldY =  gp.tilesize*26;
	i++;
//////	
	gp.monster[mapNum][i] = new MON_Bat(gp);
	gp.monster[mapNum][i].worldX =  gp.tilesize*28;
	gp.monster[mapNum][i].worldY =  gp.tilesize*11;
	i++;
	
	mapNum = 3;
	i++;
	if(progress.skeletonLordDefeated == false) {
		gp.monster[mapNum][i] = new MON_SkeletonLord(gp);
		gp.monster[mapNum][i].worldX =  gp.tilesize*23;
		gp.monster[mapNum][i].worldY =  gp.tilesize*16;
		i++;
	}
}

public void setInteractiveTile() {
	
	int mapNum = 0;
	int i = 0;
	
	gp.iTile[mapNum][i] = new IT_DryTree(gp,27,12);
	i++;
	
	gp.iTile[mapNum][i] = new IT_DryTree(gp,28,12);
	i++;
	
	gp.iTile[mapNum][i] = new IT_DryTree(gp,29,12);
	i++;
	
	gp.iTile[mapNum][i] = new IT_DryTree(gp,30,12);
	i++;
	
	gp.iTile[mapNum][i] = new IT_DryTree(gp,31,12);
	i++;
	
	gp.iTile[mapNum][i] = new IT_DryTree(gp,32,12);
	i++;
	
	gp.iTile[mapNum][i] = new IT_DryTree(gp,33,12);
	i++;
	
	gp.iTile[mapNum][i] = new IT_DryTree(gp,11,41);
	i++;
	
	gp.iTile[mapNum][i] = new IT_DryTree(gp,12,41);
	i++;
	
	gp.iTile[mapNum][i] = new IT_DryTree(gp,13,41);
	i++;
	
	gp.iTile[mapNum][i] = new IT_DryTree(gp,10,40);
	i++;
	
	gp.iTile[mapNum][i] = new IT_DryTree(gp,10,41);
	i++;
	
	gp.iTile[mapNum][i] = new IT_DryTree(gp,13,40);
	i++;
	
	gp.iTile[mapNum][i] = new IT_DryTree(gp,14,40);
	i++;
	
	gp.iTile[mapNum][i] = new IT_DryTree(gp,15,40);
	i++;
	
	gp.iTile[mapNum][i] = new IT_DryTree(gp,16,40);
	i++;
	
	gp.iTile[mapNum][i] = new IT_DryTree(gp,17,40);
	i++;
	
	gp.iTile[mapNum][i] = new IT_DryTree(gp,18,40);
	i++;
	
	mapNum = 2;
	i = 0;
	
	gp.iTile[mapNum][i] = new IT_destructibleWall(gp,18,30); i++;
	gp.iTile[mapNum][i] = new IT_destructibleWall(gp,17,31); i++;
	gp.iTile[mapNum][i] = new IT_destructibleWall(gp,17,32); i++;
	gp.iTile[mapNum][i] = new IT_destructibleWall(gp,17,34); i++;
	gp.iTile[mapNum][i] = new IT_destructibleWall(gp,18,34); i++;
	gp.iTile[mapNum][i] = new IT_destructibleWall(gp,18,33); i++;
	gp.iTile[mapNum][i] = new IT_destructibleWall(gp,10,22); i++;
	gp.iTile[mapNum][i] = new IT_destructibleWall(gp,10,24); i++;
	gp.iTile[mapNum][i] = new IT_destructibleWall(gp,38,18); i++;
	gp.iTile[mapNum][i] = new IT_destructibleWall(gp,38,19); i++;
	gp.iTile[mapNum][i] = new IT_destructibleWall(gp,38,20); i++;
	gp.iTile[mapNum][i] = new IT_destructibleWall(gp,38,21); i++;
	gp.iTile[mapNum][i] = new IT_destructibleWall(gp,18,13); i++;
	gp.iTile[mapNum][i] = new IT_destructibleWall(gp,18,14); i++;
	gp.iTile[mapNum][i] = new IT_destructibleWall(gp,22,28); i++;
	gp.iTile[mapNum][i] = new IT_destructibleWall(gp,30,28); i++;
	gp.iTile[mapNum][i] = new IT_destructibleWall(gp,32,28); i++;
	
	gp.iTile[mapNum][i] = new IT_MetalPlate(gp,20,22); i++;
	gp.iTile[mapNum][i] = new IT_MetalPlate(gp,8,17); i++;
	gp.iTile[mapNum][i] = new IT_MetalPlate(gp,39,31); i++;

}
			
}
