package gui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class DisplayPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private int numPins;
	private int pinSelected;
	
	public DisplayPanel(int np) {
		this.numPins = np;
		
		setBackground(Color.WHITE);
	}
	
	
	
	// Redefines JPanel's paintComponent to draw the lock
	public void paintComponent(Graphics g)
	{
	    super.paintComponent(g);
	    
	    drawCore(g);
	    drawPins(g, numPins);
	    drawPick(g, pinSelected);
	}
	
	public void drawCore(Graphics g) {
		//rectangle
		
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(10, 10, 100, 50);
	}
	
	public void drawPins(Graphics g, int np) {
		// np rectangles
	}
	
	public void drawPick(Graphics g, int ps) {
		// two rectangles
	}
}