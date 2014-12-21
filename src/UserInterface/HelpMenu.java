/**
 * HelpMenu - Draws the help text in a pane
 * 
 * @author CS319 - Section 2 - Group 9
 */

package UserInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import GameManagement.GameEngine;
import GameManagement.MenuManager;

public class HelpMenu extends Menu
{
	// CONSTANTS
	private final String HELP_TEXT = "Your aim is to match correct bubble pairs. " +
			 "When you do a correct match, these two bubbles will vanish and a new bubble will pop up. " +
			 "Each false match will result in losing 1 second from your remaining time. If you cannot finish the game " +
			 "in time, you will lose.\n\n" +
			 "These are the objectives for each episode:\n\n" +
			 "- BIOLOGY -\n\n" +
			 "Match animal pictures with their name.\n\n" +
			 "- CHEMISTRY -\n\n" +
			 "Match elements' name with their symbol in the periodic table.\n\n" +
			 "- VOCABULARY -\n\n" +
			 "Match antonym words.";
	
	
	// END OF CONSTANTS
	
	// VARIABLES
	private JButton backButton;
	private JScrollPane scrollPane;
    private JTextPane textPane;
    
    private boolean isGamePaused;
	// END OF VARIABLES
	
	// CONSTRUCTORS
	public HelpMenu( boolean isGamePaused )
	{
		super();
		
		BorderLayout layout = new BorderLayout( 0, 25 );
		setLayout( layout );
		
		// put some white space at the edges so that interface
		// looks nicer
		Dimension gap = new Dimension( 25, 25 );
		add( new Box.Filler( gap, gap, gap ), BorderLayout.PAGE_START );
		add( new Box.Filler( gap, gap, gap ), BorderLayout.LINE_START );
		add( scrollPane, BorderLayout.CENTER );
		add( new Box.Filler( gap, gap, gap ), BorderLayout.LINE_END );
		add( backButton, BorderLayout.PAGE_END );
		
		this.isGamePaused = isGamePaused;
	}
	// END OF CONSTRUCTORS
	
	// MUTATOR - ACCESSOR METHODS
	// END OF MUTATOR - ACCESSOR METHODS
	
	// OTHER METHODS
	protected void initComponents()
	{
		// Initialize components
        scrollPane = new JScrollPane();
        textPane = new JTextPane();
        backButton = new JButton();

        // Set colors and font
        Font f = new Font( "Default", Font.PLAIN, 23 );
        textPane.setEditable( false );
        textPane.setBackground( new Color( 237, 237, 237 ) );
        textPane.setForeground( Color.BLACK );
        textPane.setFont( f );
        textPane.setText( HELP_TEXT );
        
        // Center the text
        StyledDocument doc = textPane.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment( center, StyleConstants.ALIGN_CENTER );
		doc.setParagraphAttributes( 0, doc.getLength(), center, false );
		
        scrollPane.setViewportView( textPane );

        backButton.setIcon( new ImageIcon( "buttons/BackButton.png" ) );
        backButton.setBorderPainted( false );
        backButton.setContentAreaFilled( false );
        backButton.setFocusPainted( false );
        backButton.addActionListener( this );
	}
	
	public void actionPerformed( ActionEvent e ) 
	{
		if( e.getSource() == backButton )
		{
			GameEngine.getInstance().playSound( "sounds/buttonClick.wav" );
			
			// if the game was paused, return to pause menu,
			// otherwise return to main menu
			if( !isGamePaused )
				MenuManager.getInstance().changeMenu( new MainMenu() );
			else
				MenuManager.getInstance().changeMenu( new PauseMenu() );
		}
	}
	// END OF OTHER METHODS
}
