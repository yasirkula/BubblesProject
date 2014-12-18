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

public class LevelCompleteMenu extends Menu
{
	// VARIABLES
	private JButton nextLevelButton;
	private JButton exitButton;
	private JTextField nameInput;
	// END OF VARIABLES
	
	// CONSTRUCTORS
	public LevelCompleteMenu()
	{
		super();
		
		BorderLayout outerLayout = new BorderLayout();
        GridLayout innerLayout = new GridLayout( 4, 1, 0, 38 );
        GridLayout innermostLayout = new GridLayout( 2, 1, 0, 10 );
        JPanel middlePanel = new JPanel();
        JPanel innermostPanel = new JPanel();
        
        JTextField levelCompleteText = new JTextField( "Level Completed!" );
        Font f = new Font( "Default", Font.BOLD , 45 );
        levelCompleteText.setEditable( false );
        levelCompleteText.setBorder( BorderFactory.createLineBorder( Color.BLACK, 5 ) );
        levelCompleteText.setBackground( new Color( 208, 214, 5 ) );
        levelCompleteText.setForeground( Color.BLACK );
        levelCompleteText.setFont( f );
        levelCompleteText.setHorizontalAlignment( JTextField.CENTER );
        
        JTextField scoreText = new JTextField( "Score: " + GameEngine.getInstance().getScore() );
        f = new Font( "Default", Font.BOLD , 25 );
        scoreText.setEditable( false );
        scoreText.setBorder( BorderFactory.createLineBorder( Color.BLACK, 5 ) );
        scoreText.setBackground( new Color( 0, 204, 238 ) );
        scoreText.setForeground( Color.BLACK );
        scoreText.setFont( f );
        scoreText.setHorizontalAlignment( JTextField.CENTER );
        
        setLayout( outerLayout );
        
		middlePanel.setLayout( innerLayout );
		middlePanel.setOpaque( false );
		
		innermostPanel.setLayout( innermostLayout );
		innermostPanel.setOpaque( false );
		
		innermostPanel.add( scoreText );
		innermostPanel.add( nameInput );
		
		middlePanel.add( levelCompleteText );
		middlePanel.add( innermostPanel );
		middlePanel.add( nextLevelButton );
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
		nextLevelButton = new JButton( "NEXT LEVEL" );
		exitButton = new JButton( "EXIT" );
		nameInput = new JTextField( "ENTER YOUR NAME HERE" );
        
        // Set colors and font
        Font f = new Font( "Default", Font.BOLD, 23 );
        
        nextLevelButton.setFont( f );
        exitButton.setFont( f );
        nameInput.setFont( f );
        
        nameInput.setHorizontalAlignment( JTextField.CENTER );
        
        nextLevelButton.setBackground( new Color( 125, 189, 102 ) );
        exitButton.setBackground( new Color( 234, 106, 113 ) );
        
        nextLevelButton.addActionListener( this );
        exitButton.addActionListener( this );
	}
	
	public void actionPerformed( ActionEvent e )
	{
		if( e.getSource() == nextLevelButton )
		{
			if( GameEngine.getInstance().getLevelID() >= MenuManager.LEVEL_COUNT )
			{
				MenuManager.getInstance().changeMenu( new MainMenu() );
			}
			else
			{
				GameEngine.getInstance().initializeLevel( GameEngine.getInstance().getEpisode(), 
						GameEngine.getInstance().getLevelID() + 1 );
			}
		}
		else if( e.getSource() == exitButton )
		{
			MenuManager.getInstance().changeMenu( new MainMenu() );
		}
	}
	// END OF OTHER METHODS
}