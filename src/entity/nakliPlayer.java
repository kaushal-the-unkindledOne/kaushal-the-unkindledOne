package entity;

import main.GamePanel;

public class nakliPlayer extends Entity{
	
	public static final String dummyName = "Nakli";

	public nakliPlayer(GamePanel gp) {
		super(gp);
		
		name = dummyName;
		getPlayerImage();
	}
		
	public void getPlayerImage() {
		    up1 = setup("/player/boy_up_1", gp.tilesize, gp.tilesize);
			up2 = setup("/player/boy_up_2", gp.tilesize, gp.tilesize);
			down1 = setup("/player/boy_down_1", gp.tilesize, gp.tilesize);
			down2 = setup("/player/boy_down_2", gp.tilesize, gp.tilesize);
			left1 = setup("/player/boy_left_1", gp.tilesize, gp.tilesize);
			left2 = setup("/player/boy_left_2", gp.tilesize, gp.tilesize);
			right1 = setup("/player/boy_right_1", gp.tilesize, gp.tilesize);
			right2 = setup("/player/boy_right_2", gp.tilesize, gp.tilesize);
		}
		
	}

