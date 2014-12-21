/**
 * GameEngine - When user is not strolling the menus
 * 				but playing the game, it is GameEngine's
 * 				role to keep track of every important detail
 * 
 * @author CS319 - Section 2 - Group 9
 */

package GameManagement;

import java.util.ArrayList;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Timer;

import GameAssets.Bubble;
import GameAssets.EpisodeType;
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
	// Singleton instance
	private static GameEngine instance = null;

	private Timer timer;
	private int time;
	private int score;
	private int levelID;
	private EpisodeType episode;
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
		// Initialize the objects that only needs to be
		// initialized once
		settings = MenuManager.getInstance().getSettings();
		bubbleCollection = new BubbleCollection();
		bubblePositionFactory = new PointFactory();
		instance = this;
	}
	// END OF CONSTRUCTORS

	// MUTATOR - ACCESSOR METHODS
	public static GameEngine getInstance()
	{
		// return the singleton instance
		// if it does not exist, first initialize the instance
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

	public EpisodeType getEpisode()
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
	
	public ArrayList<Bubble> getMatchingBubbles()
	{
		return matchingBubbles;
	}

	public ArrayList<Bubble> getTrapBubbles()
	{
		return trapBubbles;
	}

	public Bubble getSelectedBubble()
	{
		return selectedBubble;
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
	public void initializeLevel( EpisodeType episode, int levelID )
	{
		// if we are restarting the level, make sure that
		// previous timer is stopped so that it no more generates
		// actions on this ActionListener (i.e. GameEngine)
		if( timer != null )
			timer.stop();

		// initialize the timer and have it generate an action
		// once a second (1000 miliseconds)
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

		switch( episode )
		{
			case VOCABULARY:
				// note that levelID starts from 1, not 0!
				bubbleCollection.getVocabBubbles( 
						MINIMUM_BUBBLES + ( levelID - 1 ) * BUBBLE_INCREMENT_AMOUNT, 
						MINIMUM_TRAP_BUBBLES + ( levelID - 1 ) * TRAP_BUBBLE_INCREMENT_AMOUNT,
						bubbles, matchingBubbles, trapBubbles );
				break;
			
			case BIOLOGY:
				// note that levelID starts from 1, not 0!
				bubbleCollection.getBioBubbles( 
						MINIMUM_BUBBLES + ( levelID - 1 ) * BUBBLE_INCREMENT_AMOUNT, 
						MINIMUM_TRAP_BUBBLES + ( levelID - 1 ) * TRAP_BUBBLE_INCREMENT_AMOUNT,
						bubbles, matchingBubbles, trapBubbles );
				break;
			case CHEMISTRY:
				// note that levelID starts from 1, not 0!
				bubbleCollection.getChemBubbles( 
						MINIMUM_BUBBLES + ( levelID - 1 ) * BUBBLE_INCREMENT_AMOUNT, 
						MINIMUM_TRAP_BUBBLES + ( levelID - 1 ) * TRAP_BUBBLE_INCREMENT_AMOUNT,
						bubbles, matchingBubbles, trapBubbles );
				break;
		}

		// initialize the cell factory so that each cell (grid)
		// is considered free
		// (to understand how random positioning works, read
		// the description on PointFactory class)
		bubblePositionFactory.initialize();

		// randomly place the bubbles on screen
		randomizeBubblePositions();
		
		// Tick tack!
		timer.start();
	}

	private void randomizeBubblePositions()
	{
		// reposition all the bubbles inside the bubbles ArrayList randomly
		for( Bubble b : bubbles )
		{
			Point p = bubblePositionFactory.getRandomBubblePosition();
			b.setLocation( p.x, p.y );
		}

		// only one matching bubble will be on screen at the beginning
		// others will pop as player match bubbles
		// randomIndex: index of the first matching bubble that will
		// appear on screen when game starts
		// offset: the amount of pixels to move the other matching bubbles
		// so that they are outside of the game screen
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

		// reposition all the bubbles inside the trapBubbles ArrayList randomly
		for( Bubble b : trapBubbles )
		{
			Point p = bubblePositionFactory.getRandomBubblePosition();
			b.setLocation( p.x, p.y );
		}
	}

	public void pause()
	{
		// pause the timer and go to pause menu
		timer.stop();
		Driver.changeActivePanel( new PauseMenu() );
	}

	public void resume()
	{
		// resume the timer and return to game screen
		timer.start();
		Driver.changeActivePanel( UI );
	}

	public void gameWon()
	{
		// There is no valid bubble left on the screen, game is won
		timer.stop();

		// increment the score proportional to remaining time
		score += time * SCORE_INCREMENT_AMOUNT_BY_REMAINING_TIME;

		if( episode == EpisodeType.VOCABULARY )
		{
			if( settings.getLockedLevelNumber( EpisodeType.VOCABULARY ) > 0 )
			{
				settings.setLockedLevelNumber( EpisodeType.VOCABULARY, 
						settings.getLockedLevelNumber( EpisodeType.VOCABULARY ) - 1 );
			}
		}
		else if( episode == EpisodeType.BIOLOGY )
		{
			if( settings.getLockedLevelNumber( EpisodeType.BIOLOGY ) > 0 )
			{
				settings.setLockedLevelNumber( EpisodeType.BIOLOGY, 
						settings.getLockedLevelNumber( EpisodeType.BIOLOGY ) - 1 );
			}
		}
		else
		{
			if( settings.getLockedLevelNumber( EpisodeType.CHEMISTRY ) > 0 )
			{
				settings.setLockedLevelNumber( EpisodeType.CHEMISTRY, 
						settings.getLockedLevelNumber( EpisodeType.CHEMISTRY ) - 1 );
			}
		}
		MenuManager.getInstance().getSettings().writeSettings();
		
		// go to win screen
		MenuManager.getInstance().changeMenu( new LevelCompleteMenu() );
	}

	public void gameLost()
	{
		// Timer reached 0, game is lost
		timer.stop();

		// go to game lost screen
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
							// otherwise pop a new matching bubble
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
							// otherwise pop a new matching bubble
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

				// a match (either correct or wrong) is made, make selectedBubble null so
				// that a new match can be made between any other bubbles
				selectedBubble = null;
			}
		}
	}
	// END OF OTHER METHODS

	// INTERFACE METHODS
	public void actionPerformed( ActionEvent e )
	{
		if( e.getSource() == timer )
		{
			// called once a second by the Timer
			time -= 1;
	
			if( time <= 0 )
			{
				// Ouch!
				gameLost();
			}
			else
			{
				// repaint the game screen so that time on screen is refreshed
				UI.repaint();
			}
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
				// if mouse position is inside this bubble
				bubbleFound = true;
				clickedBubble( bubbles.get( i ) );
			}
		}

		for( int i = 0; i < matchingBubbles.size() && !bubbleFound; i++ )
		{
			if( matchingBubbles.get( i ).contains( e.getPoint() ) )
			{
				// if mouse position is inside this bubble
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

		if( !bubbleFound )
		{
			// If user did not press on a bubble, deselect
			// the currently selected bubble
			selectedBubble = null;
		}

		// repaint the UI
		UI.setMousePos( e.getPoint() );
		UI.repaint();
	}

	public void mouseClicked( MouseEvent e ){}
	public void mouseEntered( MouseEvent e ){}
	public void mouseExited( MouseEvent e ){}
	public void mouseReleased( MouseEvent e ){}
	// END OF INTERFACE METHODS
}
