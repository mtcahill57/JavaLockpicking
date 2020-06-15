package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import javax.swing.JFrame;
import gamecore.Game;
import gui.ControlPanel;
import gui.DisplayPanel;

public class GUI extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private ControlPanel controlPanel;
	private DisplayPanel displayPanel;
	private Game game;
	
	public GUI(Game g) 
	{
		super("Lockpicking Game");
		
		this.game = g;
		this.displayPanel = new DisplayPanel(Game.getCore().numPins());
		this.controlPanel = new ControlPanel(game, displayPanel);
		
		Container container = getContentPane();
	    container.setBackground(Color.WHITE);
	    container.setLayout(new BorderLayout());
	    container.add(controlPanel, BorderLayout.PAGE_END);
		container.add(displayPanel, BorderLayout.CENTER);
	}
}
