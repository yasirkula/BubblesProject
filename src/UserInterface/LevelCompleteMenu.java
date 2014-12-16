package UserInterface;

import java.awt.event.ActionEvent;

public class LevelCompleteMenu extends Menu
{
	// VARIABLES
	private boolean isGameWon;
	// END OF VARIABLES
	
	// CONSTRUCTORS
	public LevelCompleteMenu( boolean isGameWon )
	{
		super();
		this.isGameWon = isGameWon;
	}
	// END OF CONSTRUCTORS
	
	// MUTATOR - ACCESSOR METHODS
	// END OF MUTATOR - ACCESSOR METHODS
	
	// OTHER METHODS
	protected void initComponents() {}
	public void actionPerformed( ActionEvent e ) {}
	// END OF OTHER METHODS
}