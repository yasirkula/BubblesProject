/**
 * LevelLostMenu - Draws the level lost screen
 * 
 * @author CS319 - Section 2 - Group 9
 */

package UserInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import GameManagement.GameEngine;
import GameManagement.MenuManager;

public class LevelLostMenu extends Menu
{
	// VARIABLES
	private JButton restartButton;
	private JButton exitButton;
	// END OF VARIABLES
	
	// CONSTRUCTORS
	public LevelLostMenu()
	{
		super();
		
		BorderLayout outerLayout = new BorderLayout();
        GridLayout innerLayout = new GridLayout( 3, 1, 0, 50 );
        JPanel middlePanel = new JPanel();
        
        JTextField levelLostText = new JTextField( "Time's Up!" );
        Font f = new Font( "Default", Font.BOLD , 45 );
        levelLostText.setEditable( false );
        levelLostText.setBorder( BorderFactory.createLineBorder( Color.BLACK, 5 ) );
        levelLostText.setBackground( new Color( 208, 214, 5 ) );
        levelLostText.setForeground( Color.BLACK );
        levelLostText.setFont( f );
        levelLostText.setHorizontalAlignment( JTextField.CENTER );
        
        setLayout( outerLayout );
		middlePanel.setLayout( innerLayout );
		middlePanel.setOpaque( false );
		
		middlePanel.add( levelLostText );
		middlePanel.add( restartButton );
		middlePanel.add( exitButton );
		
		// put some white space at the edges so that interface
		// looks nicer
		Dimension gap = new Dimension( 250, 50 );
		add( new Box.Filler( gap, gap, gap ), BorderLayout.PAGE_START );
		add( new Box.Filler( gap, gap, gap ), BorderLayout.LINE_START );
		add( middlePanel, BorderLayout.CENTER );
		add( new Box.Filler( gap, gap, gap ), BorderLayout.LINE_END );
		add( new Box.Filler( gap, gap, gap ), BorderLayout.PAGE_END );
	}
	// END OF CONSTRUCTORS
	
	// MUTATOR - ACCESSOR METHODS
	// END OF MUTATOR - ACCESSOR METHODS
	
	// OTHER METHODS
	protected void initComponents()
	{
		// Initialize components
		restartButton = new JButton( "RESTART LEVEL" );
		exitButton = new JButton( "EXIT" );
        
        // Set colors and font
        Font f = new Font( "Default", Font.BOLD, 23 );
        
        restartButton.setFont( f );
        exitButton.setFont( f );
        
        restartButton.setBackground( new Color( 125, 189, 102 ) );
        exitButton.setBackground( new Color( 234, 106, 113 ) );
        
        restartButton.addActionListener( this );
        exitButton.addActionListener( this );
	}
	
	public void actionPerformed( ActionEvent e )
	{
		if( e.getSource() == restartButton )
		{
			GameEngine.getInstance().playSound( "sounds/buttonClick.wav" );
			
			// restart the current level
			GameEngine.getInstance().initializeLevel( GameEngine.getInstance().getEpisode(), 
					GameEngine.getInstance().getLevelID() );
		}
		else if( e.getSource() == exitButton )
		{
			GameEngine.getInstance().playSound( "sounds/buttonClick.wav" );
			
			MenuManager.getInstance().changeMenu( new MainMenu() );
		}
	}
	// END OF OTHER METHODS
}