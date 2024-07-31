package objects;

import java.awt.Color;

import entity.Entity;
import entity.Projectile;
import main.GamePanel;

public class OBJ_FireBall extends Projectile{
	
	public static final String objName = "Fireball";
	
	GamePanel gp;

	public OBJ_FireBall(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		name = objName;
		speed = 6;
		maxLife = 100;
		Life = maxLife;
		attack  = 2;
		knockBackPower = 0;
		useCost = 1;
		alive = false;
		getImage();
		
	}
	public void getImage() {
		    up1 = setup("/projectile/fireball_up_1", gp.tilesize, gp.tilesize);
			up2 = setup("/projectile/fireball_up_2", gp.tilesize, gp.tilesize);
			down1 = setup("/projectile/fireball_down_1", gp.tilesize, gp.tilesize);
			down2 = setup("/projectile/fireball_down_2", gp.tilesize, gp.tilesize);
			left1 = setup("/projectile/fireball_left_1", gp.tilesize, gp.tilesize);
			left2 = setup("/projectile/fireball_left_2", gp.tilesize, gp.tilesize);
			right1 = setup("/projectile/fireball_right_1", gp.tilesize, gp.tilesize);
			right2 = setup("/projectile/fireball_right_2", gp.tilesize, gp.tilesize);
	}
    
	public boolean haveResource(Entity user) {
		  boolean haveResource = false;
		  if(user.mana >= useCost) {
			  haveResource = true;
		 }
		  return haveResource;
	}
	
	public void subtractResource(Entity user) {
		user.mana -= useCost;
	}
	
	public Color getParticleColor() {
		Color color = new Color(240,50,0);//COLOR OF PARTICLES
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
		int maxLife = 20;//FOR 30 FRAMES,PARTICLES WILL STAY
		return maxLife;
	}
} 
