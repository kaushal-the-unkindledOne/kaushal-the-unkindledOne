package objects;

import java.awt.Color;

import entity.Entity;
import main.GamePanel;

public class OBJ_Axe extends Entity{
	
public static final String objName = "WoodCutter's Axe";
	
	public OBJ_Axe(GamePanel gp) {
		super(gp);
		
		type = type_axe;
		name = objName;
		down1 = setup("/objects/axe", gp.tilesize, gp.tilesize);
		attackValue = 2;
		attackArea.width = 30;
		attackArea.height = 30;
		description = "[" + name + "]\nAn Axe used for \ncutting wood.";
		price = 75;
		knockBackPower = 5;
		motion1_duration = 15;
		motion2_duration = 30;
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