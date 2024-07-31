package objects;

import java.awt.Color;

import entity.Projectile;
import main.GamePanel;

public class OBJ_Rock extends Projectile {
	
	public static final String objName = "Rock";
  
	GamePanel gp;

	public OBJ_Rock(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		name = objName;
		speed = 6;
		maxLife = 100;
		Life = maxLife;
		attack  = 2;
		useCost = 1;
		alive = false;
		getImage();
		
	}
	public void getImage() {
		    up1 = setup("/projectile/rock_down_1", gp.tilesize, gp.tilesize);
			up2 = setup("/projectile/rock_down_1", gp.tilesize, gp.tilesize);
			down1 = setup("/projectile/rock_down_1", gp.tilesize, gp.tilesize);
			down2 = setup("/projectile/rock_down_1", gp.tilesize, gp.tilesize);
			left1 = setup("/projectile/rock_down_1", gp.tilesize, gp.tilesize);
			left2 = setup("/projectile/rock_down_1", gp.tilesize, gp.tilesize);
			right1 = setup("/projectile/rock_down_1", gp.tilesize, gp.tilesize);
			right2 = setup("/projectile/rock_down_1", gp.tilesize, gp.tilesize);
	}
	
	public Color getParticleColor() {
		Color color = new Color(40,50,0);//COLOR OF PARTICLES
		return color;
	}
	
	public int getParticleSize() {
		int size = 10;//SIZE OF PARTICLES IN PIXELS
		return size;
	}
	
	public int getParticleSpeed() {
		int speed = 1;//SPEED OF PARTICLES
		return speed;
	}
	
	public int getParticleMaxLife() {
		int maxLife = 20;//FOR 20 FRAMES,PARTICLES WILL STAY
		return maxLife;
	}

}


