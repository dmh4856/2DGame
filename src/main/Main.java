package main;

import javax.swing.*;

public class Main {

	public static void main(String[] args)
	{
		
		//Window settings for the game. It cannot be resized.

		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("Some 2D Thing");
		
		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel);
		
		window.pack();
		
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		gamePanel.setupGame();
		gamePanel.startGameThread();
		
		//Continue tomorrow on RyiSnow's tutorial at 20:51 

	}

}
