package GameManagement;

import java.util.ArrayList;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Timer;

import GameAssets.Bubble;
import GameAssets.PointFactory;
import Database.SettingsManager;
import Database.BubbleCollection;
import Driver.Driver;
import UserInterface.GameUI;
import UserInterface.LevelCompleteMenu;
import UserInterface.LevelLostMenu;
import UserInterface.PauseMenu;

public class GameEngine implements ActionListener, MouseListener
{
	// CONSTANTS
	private final int INITIAL_TIME = 60;
	private final int MINIMUM_BUBBLES = 10;
	private final int MINIMUM_TRAP_BUBBLES = 1;
	private final int BUBBLE_INCREMENT_AMOUNT = 2;
	private final int TRAP_BUBBLE_INCREMENT_AMOUNT = 1;
	private final int SCORE_INCREMENT_AMOUNT_BY_MATCH = 10;
	private final int SCORE_INCREMENT_AMOUNT_BY_REMAINING_TIME = 5;
	// END OF CONSTANTS
	
	// VARIABLES
	private static GameEngine instance = null;
	
	private Timer timer;
	private int time;
	private int score;
	private int levelID;
	private String episode;
	private ArrayList<Bubble> bubbles;
	private ArrayList<Bubble> matchingBubbles;
	private ArrayList<Bubble> trapBubbles;
	private Bubble selectedBubble;
	private SettingsManager settings;
	private BubbleCollection bubbleCollection;
	private PointFactory bubblePositionFactory;
	private GameUI UI;
	// END OF VARIABLES
	
	// CONSTRUCTORS
	public GameEngine()
	{
		settings = MenuManager.getInstance().getSettings();
		bubbleCollection = new BubbleCollection();
		bubblePositionFactory = new PointFactory();
		instance = this;
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
	
	public int getLevelID()
	{
		return levelID;
	}
	
	public String getEpisode()
	{
		return episode;
	}
	
	public SettingsManager getSettings()
	{
		return settings;
	}
	
	public ArrayList<Bubble> getBubbles()
	{
		return bubbles;
	}
	
	public Bubble getSelectedBubble()
	{
		return selectedBubble;
	}
	
	public ArrayList<Bubble> getMatchingBubbles()
	{
		return matchingBubbles;
	}
	
	public ArrayList<Bubble> getTrapBubbles()
	{
		return trapBubbles;
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
	public void initializeLevel( String episode, int levelID )
	{
		if( timer != null )
			timer.stop();
		
		timer = new Timer( 1000, this );
		time = INITIAL_TIME;
		score = 0;
		selectedBubble = null;
		this.levelID = levelID;
		this.episode = episode;
		
		UI = new GameUI();
		UI.addMouseListener( this );
		Driver.changeActivePanel( UI );

		bubbles = new ArrayList<Bubble>();
		matchingBubbles = new ArrayList<Bubble>();
		trapBubbles = new ArrayList<Bubble>();
		
		if( episode.equalsIgnoreCase( "Vocabulary" ) )
		{
			// note that levelID starts from 1, not 0!
			bubbleCollection.getVocabBubbles( 
					MINIMUM_BUBBLES + ( levelID - 1 ) * BUBBLE_INCREMENT_AMOUNT, 
					MINIMUM_TRAP_BUBBLES + ( levelID - 1 ) * TRAP_BUBBLE_INCREMENT_AMOUNT,
					bubbles, matchingBubbles, trapBubbles );
		}
		else if( episode.equalsIgnoreCase( "Biology" ) )
		{
			// note that levelID starts from 1, not 0!
			bubbleCollection.getBioBubbles( 
					MINIMUM_BUBBLES + ( levelID - 1 ) * BUBBLE_INCREMENT_AMOUNT, 
					MINIMUM_TRAP_BUBBLES + ( levelID - 1 ) * TRAP_BUBBLE_INCREMENT_AMOUNT,
					bubbles, matchingBubbles, trapBubbles );
		}
		else
		{
			// note that levelID starts from 1, not 0!
			bubbleCollection.getChemBubbles( 
					MINIMUM_BUBBLES + ( levelID - 1 ) * BUBBLE_INCREMENT_AMOUNT, 
					MINIMUM_TRAP_BUBBLES + ( levelID - 1 ) * TRAP_BUBBLE_INCREMENT_AMOUNT,
					bubbles, matchingBubbles, trapBubbles );
		}
		
		bubblePositionFactory.initialize();
		
		randomizeBubblePositions();
		timer.start();
	}
	
	private void randomizeBubblePositions()
	{
		// reposition the bubbles so that they don't overlap
		// do the repositioning randomly
		for( Bubble b : bubbles )
		{
			Point p = bubblePositionFactory.getRandomBubblePosition();
			b.setLocation( p.x, p.y );
		}
		
		// only one matching bubble will be on screen at the beginning
		// others will pop as player match bubbles
		// randomIndex: index of the first matching bubble that will
		// appear on screen when game starts
		int randomIndex = (int)( Math.random() * matchingBubbles.size() );
		int offset = -2 * Bubble.getRadius() - 10;
		
		for( int i = 0; i < matchingBubbles.size(); i++ )
		{
			if( i == randomIndex )
			{
				Point p = bubblePositionFactory.getRandomBubblePosition();
				matchingBubbles.get( i ).setLocation( p.x, p.y );
			}
			else
			{
				matchingBubbles.get( i ).setLocation( offset, offset );
			}
		}
		
		for( Bubble b : trapBubbles )
		{
			Point p = bubblePositionFactory.getRandomBubblePosition();
			b.setLocation( p.x, p.y );
		}
	}
	
	public void pause()
	{
		timer.stop();
		Driver.changeActivePanel( new PauseMenu() );
	}
	
	public void resume()
	{
		timer.start();
		Driver.changeActivePanel( UI );
	}
	
	public void gameWon()
	{
		// There is no valid bubble left on the screen, game is won
		timer.stop();
		
		// increment the score proportional to remaining time
		score += time * SCORE_INCREMENT_AMOUNT_BY_REMAINING_TIME;
		
		MenuManager.getInstance().changeMenu( new LevelCompleteMenu() );
	}
	
	public void gameLost()
	{
		// Timer reached 0, game is lost
		timer.stop();
		
		MenuManager.getInstance().changeMenu( new LevelLostMenu() );
	}
	
	public void clickedBubble( Bubble b )
	{
		// user clicked on Bubble b
		if( selectedBubble == null )
		{
			// user clicked on their first bubble
			selectedBubble = b;
		}
		else
		{
			if( selectedBubble == b )
			{
				// user clicked on the same bubble
				// simply deselect the selected bubble
				selectedBubble = null;
			}
			else
			{
				// user clicked on their second bubble
				// check if bubbles match
				int bubbleIndex = bubbles.indexOf( selectedBubble );
				
				if( bubbleIndex != -1 )
				{
					// selected bubble is in the bubbles list
					if( matchingBubbles.indexOf( b ) == bubbleIndex )
					{
						// we have a match!
						// remove matching bubbles from game field
						bubblePositionFactory.freeCell( bubbles.get( bubbleIndex ).getCenterPoint() );
						bubblePositionFactory.freeCell( matchingBubbles.get( bubbleIndex ).getCenterPoint() );
						bubbles.remove( bubbleIndex );
						matchingBubbles.remove( bubbleIndex );
						
						// increment score
						score += SCORE_INCREMENT_AMOUNT_BY_MATCH;
						
						// if there is no valid bubble left, game is won
						if( bubbles.size() == 0 )
							gameWon();
						else
						{
							int randomBubbleIndex = (int)( Math.random() * matchingBubbles.size() );
							Point p = bubblePositionFactory.getRandomBubblePosition();
							matchingBubbles.get( randomBubbleIndex ).setLocation( p.x, p.y );
						}
					}
					else
					{
						// wrong match
						// decrement time by 1
						time--;
					}
				}
				else
				{
					// selected bubble is in the matchingBubbles list
					bubbleIndex = matchingBubbles.indexOf( selectedBubble );
					
					if( bubbles.indexOf( b ) == bubbleIndex )
					{
						// we have a match!
						// remove matching bubbles from game field
						bubblePositionFactory.freeCell( bubbles.get( bubbleIndex ).getCenterPoint() );
						bubblePositionFactory.freeCell( matchingBubbles.get( bubbleIndex ).getCenterPoint() );
						bubbles.remove( bubbleIndex );
						matchingBubbles.remove( bubbleIndex );
						
						// increment score
						score += SCORE_INCREMENT_AMOUNT_BY_MATCH;
						
						// if there is no valid bubble left, game is won
						if( bubbles.size() == 0 )
							gameWon();
						else
						{
							int randomBubbleIndex = (int)( Math.random() * matchingBubbles.size() );
							Point p = bubblePositionFactory.getRandomBubblePosition();
							matchingBubbles.get( randomBubbleIndex ).setLocation( p.x, p.y );
						}
					}
					else
					{
						// wrong match
						// decrement time by 1
						time--;
					}
				}
				
				selectedBubble = null;
			}
		}
	}
	// END OF OTHER METHODS
	
	// INTERFACE METHODS
	public void actionPerformed( ActionEvent e )
	{
		// called once a second by the Timer
		time -= 1;
		
		if( time <= 0 )
		{
			gameLost();
		}
		else
		{
			UI.repaint();
		}
	}
	
	public void mousePressed( MouseEvent e ) 
	{
		// Check if user pressed on a bubble
		boolean bubbleFound = false;

		for( int i = 0; i < bubbles.size() && !bubbleFound; i++ )
		{
			if( bubbles.get( i ).contains( e.getPoint() ) )
			{
				bubbleFound = true;
				clickedBubble( bubbles.get( i ) );
			}
		}
		
		for( int i = 0; i < matchingBubbles.size() && !bubbleFound; i++ )
		{
			if( matchingBubbles.get( i ).contains( e.getPoint() ) )
			{
				bubbleFound = true;
				clickedBubble( matchingBubbles.get( i ) );
			}
		}
		
		for( int i = 0; i < trapBubbles.size() && !bubbleFound; i++ )
		{
			if( trapBubbles.get( i ).contains( e.getPoint() ) )
			{
				bubbleFound = true;
				clickedBubble( trapBubbles.get( i ) );
			}
		}
		
		// If user did not press on a bubble
		if( !bubbleFound )
		{
			// deselect the currently selected bubble
			selectedBubble = null;
		}
		
		UI.setMousePos( e.getPoint() );
		UI.repaint();
	}
	
	public void mouseClicked( MouseEvent e ){}
	public void mouseEntered( MouseEvent e ){}
	public void mouseExited( MouseEvent e ){}
	public void mouseReleased( MouseEvent e ){}
	// END OF INTERFACE METHODS
}