package GameManagement;

import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Timer;

import GameAssets.Bubble;
import Database.SettingsManager;
import Database.BubbleCollection;
import UserInterface.GameUI;

public class GameEngine implements ActionListener
{
	// CONSTANTS
	private final int INITIAL_TIME = 60;
	// END OF CONSTANTS
	
	// VARIABLES
	private static GameEngine instance = null;
	
	private Timer timer;
	private int time;
	private int score;
	private ArrayList<Bubble> matchingBubbles;
	private ArrayList<Bubble> trapBubbles;
	private Bubble selectedBubble;
	private int levelID;
	private SettingsManager settings;
	private GameUI UI;
	// END OF VARIABLES
	
	// CONSTRUCTORS
	public GameEngine()
	{
		settings = MenuManager.getInstance().getSettings();
		instance = this;
		initializeEngine();
	}
	// END OF CONSTRUCTORS
	
	// MUTATOR - ACCESSOR METHODS
	public static GameEngine getInstance()
	{
		if( instance == null )
			instance = new GameEngine();
		
		return instance;
	}
	
	public int getTime()
	{
		return time;
	}
	
	public int getScore()
	{
		return score;
	}
	
	public void changeScore( int deltaValue )
	{
		score += deltaValue;
	}
	
	public void changeTime( int deltaSeconds )
	{
		time += deltaSeconds;
	}
	// END OF MUTATOR - ACCESSOR METHODS
	
	// OTHER METHODS
	public void initializeEngine()
	{
		timer = new Timer( 1000, this );
		time = INITIAL_TIME;
		score = 0;
		selectedBubble = null;
		UI = new GameUI();
	}
	
	public void initializeLevel( String episode, int levelID,
							BubbleCollection bubbleContainer )
	{
	
	}
	
	public void pause()
	{
		timer.stop();
	}
	
	public void resume()
	{
		timer.start();
	}
	
	public void draw( Graphics g )
	{
		UI.draw( g );
	}
	
	public void clickedBubble( Bubble b )
	{
	
	}
	
	public void gameWon()
	{
		timer.stop();
	}
	
	public void gameLost()
	{
		timer.stop();
	}
	// END OF OTHER METHODS
	
	// INTERFACE METHODS
	public void actionPerformed( ActionEvent e )
	{
		time -= 1;
		
		if( time <= 0 )
		{
			gameLost();
		}
	}
	// END OF INTERFACE METHODS
}