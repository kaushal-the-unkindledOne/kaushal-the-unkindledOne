package environment;

import java.awt.Graphics2D;

import main.GamePanel;

public class environmentManager {
	
	GamePanel gp;
	public Lighting lighting;
	
	public environmentManager(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setUp() {
		//INSTANCE LIGHTING
		lighting = new Lighting(gp);
	}
	
	public void update() {
		lighting.update();
	}
	
	public void draw(Graphics2D g2){
		
		lighting.draw(g2);
		
	}
}
