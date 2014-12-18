package GameManagement;

import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Timer;

import GameAssets.Bubble;
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
	private GameUI UI;
	// END OF VARIABLES
	
	// CONSTRUCTORS
	public GameEngine()
	{
		settings = MenuManager.getInstance().getSettings();
		bubbleCollection = new BubbleCollection();
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
			bubbleCollection.getVocabBubbles( MINIMUM_BUBBLES + levelID - 1, 
					MINIMUM_TRAP_BUBBLES + levelID - 1, // levelID starts from 1, not 0!
					bubbles, matchingBubbles, trapBubbles );
		}
		else if( episode.equalsIgnoreCase( "Biology" ) )
		{
			bubbleCollection.getBioBubbles( MINIMUM_BUBBLES + levelID - 1, 
					MINIMUM_TRAP_BUBBLES + levelID - 1, // levelID starts from 1, not 0!
					bubbles, matchingBubbles, trapBubbles );
		}
		
		randomizeBubblePositions();
		timer.start();
	}
	
	private void randomizeBubblePositions()
	{
		// reposition the bubbles so that they don't overlap
		// do the repositioning randomly
		int diameter = (int)( 2 * Bubble.getRadius() );
		int alignmentSpace = 2 * diameter;
		int horizCellCount = Driver.getFrameWidth() / alignmentSpace;
		int vertCellCount = Driver.getFrameHeight() / alignmentSpace;
		int cellCount = horizCellCount * vertCellCount;
		
		ArrayList<Integer> availableCells = new ArrayList<Integer>();
		for( int i = 0; i < cellCount; i++ )
		{
			availableCells.add( i );
		}

		for( Bubble b : bubbles )
		{
			int randomIndex = (int)( Math.random() * availableCells.size() );
			int randomNumber = availableCells.get( randomIndex );
			int row = randomNumber / horizCellCount;
			int col = randomNumber % horizCellCount;
			availableCells.remove( randomIndex );
			
			int xPos, yPos;
			if( row == 0 && col == 0 )
			{
				// if bubble is in the top-left cell, change bubble's
				// position manually to prevent it from becoming
				// invisible behind the time and score texts
				xPos = diameter;
				yPos = diameter;
			}
			else if( row == 0 && col == horizCellCount - 1 )
			{
				// if bubble is in the top-right cell, change bubble's
				// position manually to prevent it from becoming
				// invisible behind the pause button
				xPos = col * alignmentSpace;
				yPos = diameter;
			}
			else
			{
				xPos = col * alignmentSpace + (int)( Math.random() * 
						( ( col == horizCellCount - 1 ) ? 0 : diameter ) );
				yPos = row * alignmentSpace + (int)( Math.random() * 
						( ( row == vertCellCount - 1 ) ? 0 : diameter ) );
			}
			b.setLocation( xPos, yPos );
		}
		
		for( Bubble b : matchingBubbles )
		{
			int randomIndex = (int)( Math.random() * availableCells.size() );
			int randomNumber = availableCells.get( randomIndex );
			int row = randomNumber / horizCellCount;
			int col = randomNumber % horizCellCount;
			availableCells.remove( randomIndex );
			
			int xPos, yPos;
			if( row == 0 && col == 0 )
			{
				// if bubble is in the top-left cell, change bubble's
				// position manually to prevent it from becoming
				// invisible behind the time and score texts
				xPos = diameter;
				yPos = diameter;
			}
			else if( row == 0 && col == horizCellCount - 1 )
			{
				// if bubble is in the top-right cell, change bubble's
				// position manually to prevent it from becoming
				// invisible behind the pause button
				xPos = col * alignmentSpace;
				yPos = diameter;
			}
			else
			{
				xPos = col * alignmentSpace + (int)( Math.random() * 
						( ( col == horizCellCount - 1 ) ? 0 : diameter ) );
				yPos = row * alignmentSpace + (int)( Math.random() * 
						( ( row == vertCellCount - 1 ) ? 0 : diameter ) );
			}
			b.setLocation( xPos, yPos );
		}
		
		for( Bubble b : trapBubbles )
		{
			int randomIndex = (int)( Math.random() * availableCells.size() );
			int randomNumber = availableCells.get( randomIndex );
			int row = randomNumber / horizCellCount;
			int col = randomNumber % horizCellCount;
			availableCells.remove( randomIndex );

			int xPos, yPos;
			if( row == 0 && col == 0 )
			{
				// if bubble is in the top-left cell, change bubble's
				// position manually to prevent it from becoming
				// invisible behind the time and score texts
				xPos = diameter;
				yPos = diameter;
			}
			else if( row == 0 && col == horizCellCount - 1 )
			{
				// if bubble is in the top-right cell, change bubble's
				// position manually to prevent it from becoming
				// invisible behind the pause button
				xPos = col * alignmentSpace;
				yPos = diameter;
			}
			else
			{
				xPos = col * alignmentSpace + (int)( Math.random() * 
						( ( col == horizCellCount - 1 ) ? 0 : diameter ) );
				yPos = row * alignmentSpace + (int)( Math.random() * 
						( ( row == vertCellCount - 1 ) ? 0 : diameter ) );
			}
			b.setLocation( xPos, yPos );
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
						bubbles.remove( bubbleIndex );
						matchingBubbles.remove( bubbleIndex );
						
						// increment score
						score += SCORE_INCREMENT_AMOUNT_BY_MATCH;
						
						// if there is no valid bubble left, game is won
						if( bubbles.size() == 0 )
							gameWon();
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
						bubbles.remove( bubbleIndex );
						matchingBubbles.remove( bubbleIndex );
						
						// increment score
						score += SCORE_INCREMENT_AMOUNT_BY_MATCH;
						
						// if there is no valid bubble left, game is won
						if( bubbles.size() == 0 )
							gameWon();
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