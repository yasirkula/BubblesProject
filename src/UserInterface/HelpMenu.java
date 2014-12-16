package UserInterface;

import java.awt.event.ActionEvent;

public class HelpMenu extends Menu
{
	// VARIABLES
	private String helpText;
	// END OF VARIABLES
	
	// CONSTRUCTORS
	public HelpMenu()
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