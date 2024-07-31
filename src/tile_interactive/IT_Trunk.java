package tile_interactive;

import main.GamePanel;

public class IT_Trunk extends InteractiveTile{
            
	GamePanel gp;
	
	public IT_Trunk(GamePanel gp,int col, int row) {
		super(gp, row, col);
		this.gp = gp;
		
		this.worldX = gp.tilesize*col;
		this.worldY = gp.tilesize*row;
		
		down1 = setup("/interactive_tiles/trunk",gp.tilesize,gp.tilesize);
		
		solidArea.x = 0;
		solidArea.y = 0;
		solidArea.width = 0;
		solidArea.height = 0;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
	}
}
	