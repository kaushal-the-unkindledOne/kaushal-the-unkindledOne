package objects;

import java.awt.Color;

import entity.Entity;
import main.GamePanel;

public class OBJ_Sword_Golden extends Entity{
	
	public static final String objName = "Golden Sword";

	public OBJ_Sword_Golden(GamePanel gp) {
		super(gp);
		
		type = type_swordGolden;
		name = objName;
		down1 = setup("/objects/sword_golden", gp.tilesize, gp.tilesize);
		attackValue = 3;
		attackArea.width = 36;
		attackArea.height = 36;
		description = "[" + name + "]\nA Majestic \nGolden sword.";
		price = 100;
		knockBackPower = 10;
		motion1_duration = 20;
		motion2_duration = 35;
	}


	public Color getParticleColor() {
		Color color = new Color(102,0,0);//COLOR OF PARTICLES
		return color;
	}
	
	public int getParticleSize() {
		int size = 6;//NO. OF PARTICLES
		return size;
	}
	
	public int getParticleSpeed() {
		int speed = 1;//SPEED OF PARTICLES
		return speed;
	}
	
	public int getParticleMaxLife() {
		int maxLife = 20;//FOR 30 FRAMES,PARTICLES WILL STAY
		return maxLife;
	}
}
