package data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import entity.Entity;

import java.io.ObjectInputStream;

import main.GamePanel;
import objects.OBJ_Axe;
import objects.OBJ_Boots;
import objects.OBJ_Chest;
import objects.OBJ_Lantern;
import objects.OBJ_Potion_Red;
import objects.OBJ_Shield_Blue;
import objects.OBJ_Shield_Wood;
import objects.OBJ_Sword_Golden;
import objects.OBJ_Sword_Normal;
import objects.OBJ_Tent;
import objects.OBJ_door;
import objects.OBJ_key;

public class SaveLoad {
  
	GamePanel gp;
	
	public SaveLoad(GamePanel gp) {
		this.gp = gp;
	}
	
	public void save() {
		
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("save.dat")));
			
			DataStorage ds = new DataStorage();
			
			//PLAYER STATUS
			 ds.level = gp.player.level;
			 ds.maxLife = gp.player.maxLife; 
			 ds.life = gp.player.Life;
			 ds.maxMana = gp.player.maxMana;
			 ds.mana = gp.player.mana;
			 ds.strength = gp.player.strength;
			 ds.dexerity = gp.player.dexterity;
			 ds.exp = gp.player.exp;
			 ds.nextLevelExp = gp.player.nextLevelExp;
			 ds.coin = gp.player.coin;
			 
			 //PLAYER INVENTORY
			 for(int i = 0; i < gp.player.inventory.size(); i++) {
				 ds.itemNames.add(gp.player.inventory.get(i).name);
				 ds.itemAmount.add(gp.player.inventory.get(i).amount);
				 
			 }
			 
			 //PLAYER EQUIPMENT
			 ds.currentWeaponSlot = gp.player.getCurrentWeaponSlot();
			 ds.currentShieldSlot = gp.player.getCurrentShieldSlot();
			 
			 //OBJECTS IN MAP
			 ds.mapObjectsName = new String[gp.maxMap][gp.obj[1].length];
			 ds.mapObjectWorldX = new int[gp.maxMap][gp.obj[1].length];
			 ds.mapObjectWorldY = new int[gp.maxMap][gp.obj[1].length];
			 ds.mapObjectsLootName = new String[gp.maxMap][gp.obj[1].length];
			 ds.mapObjectsOpened = new boolean[gp.maxMap][gp.obj[1].length];
			 
			 for(int mapNum = 0; mapNum < gp.maxMap; mapNum++) {
				 
				 for(int i = 0; i < gp.obj[1].length; i++) {
					 
					 if(gp.obj[mapNum][i] == null) {
						 ds.mapObjectsName[mapNum][i] = "NA";
					 }
					 else {
						 ds.mapObjectsName[mapNum][i] = gp.obj[mapNum][i].name;
						 ds.mapObjectWorldX[mapNum][i] = gp.obj[mapNum][i].worldX;
						 ds.mapObjectWorldY[mapNum][i] = gp.obj[mapNum][i].worldY;
						 if(gp.obj[mapNum][i].loot != null) {
							 ds.mapObjectsLootName[mapNum][i] = gp.obj[mapNum][i].loot.name;
						 }
						 ds.mapObjectsOpened[mapNum][i] = gp.obj[mapNum][i].opened; 
					 }
				 }
			 }
		//WRITE THE DATASTORAGE OBJECT
			oos.writeObject(ds);
		} catch (IOException e) {
			System.out.println("Save Exception");
		}
	}

	public void load() {
		
		try {
			ObjectInputStream ois =new ObjectInputStream(new FileInputStream(new File("save.dat")));
			
			//READ DATASTORAGE OBJECT
			DataStorage ds = (DataStorage)ois.readObject();//read what is recorded in .dat and get it as ds object
			
			//PLAYER STATS
			 gp.player.level = ds.level;
			 gp.player.maxLife = ds.maxLife; 
			 gp.player.Life = ds.life;
			 gp.player.maxMana = ds.maxMana;
			 gp.player.mana = ds.mana;
			 gp.player.strength = ds.strength;
			 gp.player.dexterity = ds.dexerity;
			 gp.player.exp = ds.exp;
			 gp.player.nextLevelExp = ds.nextLevelExp;
			 gp.player.coin = ds.coin;
			 
			 //PLAYER INVENTORY
			 gp.player.inventory.clear();
			 for(int i = 0; i < ds.itemNames.size(); i++) {
				 gp.player.inventory.add(gp.eGenerator.getObject(ds.itemNames.get(i)));
				 gp.player.inventory.get(i).amount = ds.itemAmount.get(i);
			 }
			 
			 //PLAYER EQUIPMENT
			 gp.player.currentWeapon = gp.player.inventory.get(ds.currentWeaponSlot);
			 gp.player.currentShield = gp.player.inventory.get(ds.currentShieldSlot);
			 gp.player.getAttack();
			 gp.player.getDefense();
			 gp.player.getPlayerAttackImage();
			 
			 //OBJECTS IN MAP
          for(int mapNum = 0; mapNum < gp.maxMap; mapNum++) {
				 
				 for(int i = 0; i < gp.obj[1].length; i++) {
					 
					 if(ds.mapObjectsName[mapNum][i].equals("NA")) {
						 gp.obj[mapNum][i] = null;
					 }
					 else {
						 gp.obj[mapNum][i] = gp.eGenerator.getObject(ds.mapObjectsName[mapNum][i]);
						 gp.obj[mapNum][i].worldX = ds.mapObjectWorldX[mapNum][i];
						 gp.obj[mapNum][i].worldY = ds.mapObjectWorldY[mapNum][i];
						 
						 if(ds.mapObjectsLootName[mapNum][i] != null) {
							 gp.obj[mapNum][i].setLoot(gp.eGenerator.getObject(ds.mapObjectsLootName[mapNum][i]));
						}
						 gp.obj[mapNum][i].opened = ds.mapObjectsOpened[mapNum][i];
						 if( gp.obj[mapNum][i].opened == true) {
							 gp.obj[mapNum][i].down1 =  gp.obj[mapNum][i].image2;
						 }
					 }
			    }
           }
		}
		catch(Exception e){
			System.out.println("Load Exception");
		}
    }
}
