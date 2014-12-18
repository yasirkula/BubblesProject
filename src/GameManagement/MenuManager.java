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
	private static MenuManager instance = null;
	
	private SettingsManager settings;
	private GameEngine engine;
	private HighScores scores;
	// END OF VARIABLES
	
	// CONSTRUCTORS
	public MenuManager()
	{
		instance = this;
		
		settings = new SettingsManager();
		scores = new HighScores();
	}
	// END OF CONSTRUCTORS
	
	// MUTATOR - ACCESSOR METHODS
	public static MenuManager getInstance()
	{
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
		Driver.changeActivePanel( m );
	}
	
	public void exitGame()
	{
		System.exit(0);
	}
	// END OF OTHER METHODS
}