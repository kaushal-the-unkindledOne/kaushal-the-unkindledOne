package tile_interactive;

import java.awt.Graphics2D;

import entity.Entity;
import main.GamePanel;

public class InteractiveTile extends Entity{

	    GamePanel gp;
	public boolean destructible = false;
	     
	public InteractiveTile(GamePanel gp, int col, int row) {
		super(gp);
		this.gp = gp;
		
	}
     
	public boolean isCorrectWeapon(Entity entity) {
		boolean isCorrectWeapon = false;
		return isCorrectWeapon;
	}
	
	public void playSE() {}
	
	public InteractiveTile getDestroyedForm() {
		InteractiveTile tile = null;
		return tile;
	}
	
	public void update() {
		if(invincible == true) {
			invincibleCounter ++;
			if(invincibleCounter > 20) {
				invincible = false;
				invincibleCounter = 0;
			}
		}
	}
	
	public void draw(Graphics2D g2) {
    	 int ScreenX = worldX - gp.player.worldX + gp.player.screenX;
 		int ScreenY = worldY - gp.player.worldY + gp.player.screenY;
 		
 		if (worldX + gp.tilesize > gp.player.worldX - gp.player.screenX &&
 		    worldX - gp.tilesize < gp.player.worldX + gp.player.screenX &&
 			worldY + gp.tilesize > gp.player.worldY - gp.player.screenY &&
 			worldY - gp.tilesize < gp.player.worldY + gp.player.screenY) 
 		{
 			g2.drawImage(down1,ScreenX,ScreenY,null);
 			
 		}
	}
	
}
