package tile_interactive;

import java.awt.Color;

import entity.Entity;
import main.GamePanel;

public class IT_DryTree extends InteractiveTile{
            
	GamePanel gp;
	
	public IT_DryTree(GamePanel gp,int col, int row) {
		super(gp, row, col);
		this.gp = gp;
		
		this.worldX = gp.tilesize*col;
		this.worldY = gp.tilesize*row;
		
		down1 = setup("/interactive_tiles/drytree",gp.tilesize,gp.tilesize);
		destructible = true;
		Life = 1;
	}
	
	public boolean isCorrectWeapon(Entity entity) {
		boolean isCorrectWeapon = false;
		
		if(entity.currentWeapon.type == type_axe) {
			isCorrectWeapon = true;
		}
		return isCorrectWeapon;
	}
	
    public void playSE() {
		gp.playSE(13);
	}
	
	public InteractiveTile getDestroyedForm() {
		InteractiveTile tile = new IT_Trunk(gp, worldX/gp.tilesize, worldY/gp.tilesize);
		return tile;
	}
	
	public Color getParticleColor() {
		Color color = new Color(65,50,35);//COLOR OF PARTICLES
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
