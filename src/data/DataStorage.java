package data;

import java.io.Serializable;
import java.util.ArrayList;

public class DataStorage implements Serializable{//enable to write and read the object

	//makes dataStorage readable and writable
	
	//PLAYER STATS
	int level;
	int maxLife;
	int life;
	int maxMana;
	int mana;
	int strength;
	int dexerity;
	int exp;
	int nextLevelExp;
	int coin;
	
	//PLAYER INVENTORY
	ArrayList<String> itemNames = new ArrayList<>();
	ArrayList<Integer> itemAmount = new ArrayList<>();
	int currentWeaponSlot;
	int currentShieldSlot;
	
	//OBJECTS IN MAP
	String mapObjectsName[][];
	int mapObjectWorldX[][];
	int mapObjectWorldY[][];
	String mapObjectsLootName[][];
	boolean mapObjectsOpened[][];
}
