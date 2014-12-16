package UserInterface;

import java.awt.event.ActionListener;
import javax.swing.JPanel;

public abstract class Menu extends JPanel implements ActionListener
{
	// VARIABLES
	// END OF VARIABLES
	
	// CONSTRUCTORS
	public Menu()
	{
		initComponents();
	}
	// END OF CONSTRUCTORS
	
	// OTHER METHODS
	protected abstract void initComponents(); 
	// END OF OTHER METHODS
}