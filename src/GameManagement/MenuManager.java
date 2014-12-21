/**
 * GameEngine - When user is strolling the menus
 * 				it is MenuManager's role to keep track of
 * 				menu transitions
 * 
 * @author CS319 - Section 2 - Group 9
 */

package GameManagement;

import Driver.Driver;
import Database.SettingsManager;
import Database.HighScores;
import UserInterface.Menu;

public class MenuManager
{
	// CONSTANTS
	public static final int LEVEL_COUNT = 10;
	// END OF CONSTANTS
	
	// VARIABLES
	// Singleton instance
	private static MenuManager instance = null;
	
	private SettingsManager settings;
	private HighScores scores;
	// END OF VARIABLES
	
	// CONSTRUCTORS
	public MenuManager()
	{
		instance = this;
		
		// initialize the databases
		settings = new SettingsManager();
		scores = new HighScores();
	}
	// END OF CONSTRUCTORS
	
	// MUTATOR - ACCESSOR METHODS
	public static MenuManager getInstance()
	{
		// return the singleton instance
		// if it does not exist, first initialize the instance
		if( instance == null )
			instance = new MenuManager();
		
		return instance;
	}
	
	public SettingsManager getSettings()
	{
		return settings;
	}
	
	public HighScores getScores()
	{
		return scores;
	}
	// END OF MUTATOR - ACCESSOR METHODS
	
	// OTHER METHODS
	public void changeMenu( Menu m )
	{
		// transition between menus
		Driver.changeActivePanel( m );
	}
	
	public void exitGame()
	{
		// exit the game
		System.exit(0);
	}
	// END OF OTHER METHODS
}