package Database;

import java.awt.Color;

public class SettingsManager
{
	// VARIABLES
	private float soundLevel;
	private Color backgroundColor;
	// END OF VARIABLES
	
	// CONSTRUCTORS
	public SettingsManager()
	{
		soundLevel = 1f;
		backgroundColor = Color.white;
	}
	// END OF CONSTRUCTORS
	
	// MUTATOR - ACCESSOR METHODS
	public float getSoundLevel()
	{
		return soundLevel;
	}
	
	public void setSoundLevel( float s )
	{
		soundLevel = s;
	}
	
	public Color getBackgroundColor()
	{
		return backgroundColor;
	}
	
	public void setBackgroundColor( Color c )
	{
		backgroundColor = c;
	}
	// END OF MUTATOR - ACCESSOR METHODS
	
	// OTHER METHODS
	public void loadSettings()
	{
	
	}
	
	public void writeSettings()
	{
	
	}
	// END OF OTHER METHODS
}