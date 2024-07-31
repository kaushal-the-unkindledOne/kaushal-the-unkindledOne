package objects;

import java.awt.Color;

import entity.Entity;
import main.GamePanel;

public class OBJ_Sword_Normal extends Entity{
	
	public static final String objName = "Normal Sword";

	public OBJ_Sword_Normal(GamePanel gp) {
		super(gp);
		
		type = type_sword;
		name = objName;
		down1 = setup("/objects/sword_normal", gp.tilesize, gp.tilesize);
		attackValue = 50;
		attackArea.width = 36;
		attackArea.height = 36;
		description = "[" + name + "]\nA Rusty sword.";
		price = 60;
		knockBackPower = 2;
		motion1_duration = 5;
		motion2_duration = 25;
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
