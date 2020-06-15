package gui;

import gamecore.Game;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import events.EventFactory;
import events.Event.EventType;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;

public class ControlPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private Game game;
	private ButtonGroup pins;
	private JButton lift, test, tension, untension, reset, exit;
	private JCheckBox hints;
	private JPanel pinPanel, buttonPanel;
	private DisplayPanel displayPanel;
	
	// ControlPanel object is based on the current game
	public ControlPanel(Game g, DisplayPanel dp)
	{
		this.game = g;
		this.displayPanel = dp;
		
		// panel for pin selection buttons
		int numPins = Game.getCore().numPins();
		pinPanel = new JPanel();
		pinPanel.setLayout(new GridLayout(1, numPins, 10, 10));
		
		// radio buttons for pin selection
		pins = new ButtonGroup();
		JRadioButton[] aPins = new JRadioButton[numPins];
		
		for(int i = 0; i < numPins; i++) {
			JRadioButton pin = new JRadioButton(Integer.toString(i));
			pins.add(pin);
			pinPanel.add(pin);
			aPins[i] = pin;
			pin.setActionCommand(Integer.toString(i));
			pin.setMnemonic((int)Integer.toString(i).charAt(0)); //set hotkey binding for pin selection
		}
		aPins[0].setSelected(true);
		add(pinPanel);
		
		// panel for buttons
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1, 7, 5, 10));
		
		// check box for hint status
		hints = new JCheckBox("Hints");
		hints.setPreferredSize(new Dimension(80, 30));
		hints.setToolTipText("Toggle hints");
		hints.addActionListener(this);
		hints.setActionCommand(hints.getText().toUpperCase());
		hints.setSelected(false);
		hints.setMnemonic('h');
		buttonPanel.add(hints);
		
		//lift action button
		lift = new JButton("Lift");
	    lift.setPreferredSize(new Dimension(80, 30));
	    lift.setToolTipText("Lift a pin");
	    lift.addActionListener(this);
	    lift.setActionCommand(lift.getText().toUpperCase());
	    lift.setMnemonic('l');
	    buttonPanel.add(lift);
	    
	    //test action button
	    test = new JButton("Test");
	    test.setPreferredSize(new Dimension(80, 30));
	    test.setToolTipText("Test a pin");
	    test.addActionListener(this);
	    test.setActionCommand(test.getText().toUpperCase());
	    test.setMnemonic('t');
	    buttonPanel.add(test);
	    
	    //tension action button
	    tension = new JButton("Tension");
	    tension.setPreferredSize(new Dimension(80, 30));
	    tension.setToolTipText("Tension the core");
	    tension.addActionListener(this);
	    tension.setActionCommand(tension.getText().toUpperCase());
	    buttonPanel.add(tension);
	    
	    //untension action button
	    untension = new JButton("Untension");
	    untension.setPreferredSize(new Dimension(80, 30));
	    untension.setToolTipText("Untension the core until a pin drops");
	    untension.addActionListener(this);
	    untension.setActionCommand(untension.getText().toUpperCase());
	    buttonPanel.add(untension);
	    
	    //reset action button
	    reset = new JButton("Reset");
	    reset.setPreferredSize(new Dimension(80, 30));
	    reset.setToolTipText("Reset the core");
	    reset.addActionListener(this);
	    reset.setActionCommand(reset.getText().toUpperCase());
	    buttonPanel.add(reset);
	    
	    //exit action button
	    exit = new JButton("Exit");
	    exit.setPreferredSize(new Dimension(80, 30));
	    exit.setToolTipText("Exit the game");
	    exit.addActionListener(this);
	    exit.setActionCommand(exit.getText().toUpperCase());
	    buttonPanel.add(exit);
	    
	    add(buttonPanel);
	}

	//action handling
	@Override
	public void actionPerformed(ActionEvent a) {
		
		int pin = pins.getSelection().getMnemonic();
		pin = Integer.parseInt(pins.getSelection().getActionCommand());
		
		switch(a.getActionCommand()) {
		case "LIFT": 		this.game.processEvent(EventFactory.newEvent(EventType.LIFT, pin));
							displayPanel.repaint();
						break;
		case "UNTENSION":	this.game.processEvent(EventFactory.newEvent(EventType.UNTENSIONUNTILFALL, pin));
							displayPanel.repaint();
						break;
		case "RESET":		this.game.processEvent(EventFactory.newEvent(EventType.RESET, pin));
							displayPanel.repaint();
						break;
		case "TEST":		this.game.processEvent(EventFactory.newEvent(EventType.TEST, pin));
						break;
		case "TENSION":		this.game.processEvent(EventFactory.newEvent(EventType.TENSION, pin));
						break;
		case "EXIT":		this.game.processEvent(EventFactory.newEvent(EventType.EXIT, pin));
						break;
		case "HINTS":		JCheckBox h = (JCheckBox)a.getSource();
							if (h.isSelected()) {
								this.game.processEvent(EventFactory.newEvent(EventType.ENABLEHINTS, pin));
							}
							else {
								this.game.processEvent(EventFactory.newEvent(EventType.DISABLEHINTS, pin));
							}
						break;
		default:			// other (unimplemented) event
						break;
		}		
	}
}
