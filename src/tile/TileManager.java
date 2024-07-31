package tile;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.File;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.utilityTool;

import java.util.ArrayList;

public class TileManager {
	GamePanel gp;
	public Tile[] tile;
	public int mapTileNum[][][];
	boolean drawPath = true;

	public TileManager(GamePanel gp) {
		
		this.gp = gp;
		
		//INITILIAZE TILE ARRAY BASED ON FILENAME SIZE
		tile = new Tile[50];
		mapTileNum = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
		getTileImage();
		LoadMap("/maps/worldV3.txt", 0);
		LoadMap("/maps/interior01.txt", 1);
		LoadMap("/maps/dungeon01.txt", 2);
		LoadMap("/maps/dungeon02.txt", 3);
  }
	
	public void getTileImage() {
		  //PLACEHOLDER
		    setup(0, "black", false);
		    setup(1, "grass00", false);
		    setup(2, "grass00", false);
		    setup(3, "grass00", false);
		    setup(4, "grass00", false);
		    setup(5, "grass00", false);
		    setup(6, "grass00", false);
		    setup(7, "grass00", false);
		    setup(8, "grass00", false);
		    setup(9, "grass00", false);
		       
		       
		   setup(10, "grass00", false);
	       setup(11, "grass01", false);
	       setup(12, "water00", true);
	       setup(13, "water01", true);
	       setup(14, "water02", true);
	       setup(15, "water03", true);
	       setup(16, "water04", true);
	       setup(17, "water05", true);
	       setup(18, "water06", true);
	       setup(19, "water07", true);
	       setup(20, "water08", true);
	       setup(21, "water09", true);
	       setup(22, "water10", true);
	       setup(23, "water11", true);
	       setup(24, "water12", true);
	       setup(25, "water13", true);
	       setup(26, "road00", false);
	       setup(27, "road01", false);
	       setup(28, "road02", false);
	       setup(29, "road03", false);
	       setup(30, "road04", false);
	       setup(31, "road05", false);
	       setup(32, "road06", false);
	       setup(33, "road07", false);
	       setup(34, "road08", false);
	       setup(35, "road09", false);
	       setup(36, "road10", false);
	       setup(37, "road11", false);
	       setup(38, "road12", false);
	       setup(39, "earth", false);
	       setup(40, "wall", true);
	       setup(41, "tree", true);
	       setup(42, "hut", false);
	       setup(43, "floor01", false);
	       setup(44, "table01", true);
	       setup(45, "stairs01", false);
	       setup(46, "stairs02", false);
       }
	
	public void setup(int index, String imageName, boolean collision) {
		utilityTool utool = new utilityTool();
		try {
			tile[index] = new Tile();
			 tile[index].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/" + imageName + ".png"));
			 tile[index].image = utool.scaleImage(tile[index].image, gp.tilesize, gp.tilesize);
			 tile[index].collision = collision;
			
		}catch(IOException e) {
			e.printStackTrace();
		}	
	}
	public void LoadMap(String FilePath, int map){
		
		try {
			InputStream is = getClass().getResourceAsStream(FilePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col = 0;
			int row = 0;
			
			while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
				
				String line = br.readLine();
				
				while(col < gp.maxWorldCol) {
					
					String numbers[] = line.split(" ");
					
					int num = Integer.parseInt(numbers[col]);
					
					mapTileNum[map][col][row] = num;
					col++;
					}
				if(col ==  gp.maxWorldCol) {
					col = 0;
					row++;
				}
			}
			br.close();
			
			
		}catch(Exception e) {
			
		}
		
		
	}
	
	public void draw( Graphics2D g2 ) {
		
		int worldcol = 0;
		int worldrow = 0;
		
		
		while(worldcol < gp.maxWorldCol && worldrow < gp.maxWorldRow ) {
			
			int tileNum = mapTileNum[gp.currentMap][worldcol][worldrow];
			
			int WorldX = worldcol * gp.tilesize;
			int WorldY = worldrow * gp.tilesize;
			int ScreenX = WorldX - gp.player.worldX + gp.player.screenX;
			int ScreenY = WorldY - gp.player.worldY + gp.player.screenY;
			
			if (WorldX + gp.tilesize > gp.player.worldX - gp.player.screenX &&
			    WorldX - gp.tilesize < gp.player.worldX + gp.player.screenX &&
				WorldY + gp.tilesize > gp.player.worldY - gp.player.screenY &&
				WorldY - gp.tilesize < gp.player.worldY + gp.player.screenY) {
				g2.drawImage(tile[tileNum].image , ScreenX, ScreenY,  null);
				
			}
			
			worldcol++;
		
			
			if(worldcol == gp.maxWorldCol) {
				
				worldcol = 0;
				
				worldrow++;
			}
		}
		if(drawPath == true) {
			g2.setColor(new Color(223,255,0,70));
			
			for(int i = 0; i < gp.pFinder.pathList.size(); i++) {
				int WorldX = gp.pFinder.pathList.get(i).col * gp.tilesize;
				int WorldY = gp.pFinder.pathList.get(i).row * gp.tilesize;
				int ScreenX = WorldX - gp.player.worldX + gp.player.screenX;
				int ScreenY = WorldY - gp.player.worldY + gp.player.screenY;
				
				g2.fillRect(ScreenX, ScreenY, gp.tilesize, gp.tilesize);
			}
		}
	}
}
