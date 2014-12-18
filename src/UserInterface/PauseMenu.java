package UserInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;

import GameManagement.GameEngine;
import GameManagement.MenuManager;

public class PauseMenu extends Menu
{
	// VARIABLES
	private JButton resumeButton;
	private JButton restartButton;
	private JButton helpButton;
	private JButton exitButton;
	// END OF VARIABLES
	
	// CONSTRUCTORS
	public PauseMenu()
	{
		super();
		
		BorderLayout outerLayout = new BorderLayout();
        GridLayout innerLayout = new GridLayout( 4, 1, 0, 40 );
        JPanel middlePanel = new JPanel();
		
        setLayout( outerLayout );
		middlePanel.setLayout( innerLayout );
		middlePanel.setOpaque( false );
		
		middlePanel.add( resumeButton );
		middlePanel.add( restartButton );
		middlePanel.add( helpButton );
		middlePanel.add( exitButton );
		
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
		resumeButton = new JButton( "RESUME" );
		restartButton = new JButton( "RESTART" );
		helpButton = new JButton( "HELP" );
		exitButton = new JButton( "EXIT" );
        
        // Set colors and font
        Font f = new Font( "Default", Font.BOLD, 23 );
        
        resumeButton.setFont( f );
        restartButton.setFont( f );
        helpButton.setFont( f );
        exitButton.setFont( f );
        
        resumeButton.setBackground( new Color( 125, 189, 102 ) );
        restartButton.setBackground( new Color( 251, 245, 136 ) );
        helpButton.setBackground( new Color( 117, 158, 236 ) );
        exitButton.setBackground( new Color( 234, 106, 113 ) );
        
        resumeButton.addActionListener( this );
        restartButton.addActionListener( this );
        helpButton.addActionListener( this );
        exitButton.addActionListener( this );
	}
	
	public void actionPerformed( ActionEvent e )
	{
		if( e.getSource() == resumeButton )
		{
			GameEngine.getInstance().resume();
		}
		else if( e.getSource() == restartButton )
		{
			GameEngine.getInstance().initializeLevel( GameEngine.getInstance().getEpisode(),
					GameEngine.getInstance().getLevelID() );
		}
		else if( e.getSource() == helpButton )
		{
			MenuManager.getInstance().changeMenu( new HelpMenu( true ) );
		}
		else if( e.getSource() == exitButton )
		{
			MenuManager.getInstance().changeMenu( new MainMenu() );
		}
	}
	// END OF OTHER METHODS
}