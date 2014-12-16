package UserInterface;

import java.awt.event.ActionEvent;

public class PauseHelpMenu extends Menu
{
	// VARIABLES
	private String helpText;
	// END OF VARIABLES
	
	// CONSTRUCTORS
	public PauseHelpMenu()
	{
		super();
	}
	// END OF CONSTRUCTORS
	
	// MUTATOR - ACCESSOR METHODS
	public void setHelpText( String h )
	{
		helpText = h;
	}
	// END OF MUTATOR - ACCESSOR METHODS
	
	// OTHER METHODS
	protected void initComponents() {}
	public void actionPerformed( ActionEvent e ) {}
	// END OF OTHER METHODS
}