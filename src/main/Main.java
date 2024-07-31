package main;

import javax.swing.JFrame;

import javax.swing.ImageIcon;

public class Main {
	
	public static JFrame window;
	
    public static void main(String[] args) {

    window = new JFrame();
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.setResizable(false);
    window.setTitle("DING DONG DING");
    //new Main().setIcon();

    GamePanel gamePanel= new GamePanel();
    window.add(gamePanel);
    
    gamePanel.config.loadConfig();
    if(gamePanel.fullScreenOn == true){
    	window.setUndecorated(true);
    }
    
    window.pack();

    window.setLocationRelativeTo(null);
    window.setVisible(true);
    
    gamePanel.setupGame();
    gamePanel.startGameThread();
}
   
    public void setIcon() {
    	ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("/npc/bigrock.png")); 
    	window.setIconImage(icon.getImage());
    	
        }
    }