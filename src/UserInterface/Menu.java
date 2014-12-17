package UserInterface;

import java.awt.event.ActionListener;
import javax.swing.JPanel;

import GameManagement.MenuManager;

public abstract class Menu extends JPanel implements ActionListener
{
	// VARIABLES
	// END OF VARIABLES
	
	// CONSTRUCTORS
	public Menu()
	{
		initComponents();
		
		setBackground( MenuManager.getInstance().getSettings().getBackgroundColor() );
	}
	// END OF CONSTRUCTORS
	
	// OTHER METHODS
	protected abstract void initComponents(); 
	// END OF OTHER METHODS
}