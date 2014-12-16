package UserInterface;

import java.awt.event.ActionEvent;

public class LevelsMenu extends Menu
{
	// VARIABLES
	private int levelsCount;
	private int unlockedLevels;
	// END OF VARIABLES
	
	// CONSTRUCTORS
	public LevelsMenu( int count, int unlocked )
	{
		super();
		levelsCount = count;
		unlockedLevels = unlocked;
	}
	// END OF CONSTRUCTORS
	
	// MUTATOR - ACCESSOR METHODS
	public int getUnlockedLevels( String selectedEpisode )
	{
		return unlockedLevels;
	}
	// END OF MUTATOR - ACCESSOR METHODS
	
	// OTHER METHODS
	protected void initComponents() {}
	public void actionPerformed( ActionEvent e ) {}
	// END OF OTHER METHODS
}