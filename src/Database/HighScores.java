package Database;

import java.util.ArrayList;

public class HighScores
{
	// VARIABLES
	private ArrayList<Integer> bioScores;
	private ArrayList<String> bioNames;
	private ArrayList<Integer> chemScores;
	private ArrayList<String> chemNames;
	private ArrayList<Integer> vocabScores;
	private ArrayList<String> vocabNames;
	// END OF VARIABLES
	
	// CONSTRUCTORS
	public HighScores()
	{
	
	}
	// END OF CONSTRUCTORS
	
	// MUTATOR - ACCESSOR METHODS
	public void setScores( String episode, ArrayList<Integer> scores )
	{
	
	}
	
	public ArrayList<Integer> getScores( String episode )
	{
		return null;
	}
	
	public void setNames( String episode, ArrayList<String> names )
	{
	
	}
	
	public ArrayList<String> getNames( String episode )
	{
		return null;
	}
	
	public int getTotalScore( String episode )
	{
		return -1;
	}
	// END OF MUTATOR - ACCESSOR METHODS
	
	// OTHER METHODS
	public void loadHighScores()
	{
	
	}
	
	public void saveHighScores()
	{
	
	}
	// END OF OTHER METHODS
}