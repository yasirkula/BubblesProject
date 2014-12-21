/**
 * CreditsMenu - Draws the credits text in a pane
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

import GameManagement.MenuManager;

public class CreditsMenu extends Menu
{
	// CONSTANTS
	private final String CREDITS_TEXT = "\n(-(-_(-_-)_-)-)\n\n" +
			 "Four brilliant students worked on this project\n\n" +
			 "Esra Zeynep Kurt / 21101384\nRana Kösterit / 21100715\n" +
			 "Süleyman Yasir Kula / 21200823\nTan Küçükoðlu / 21201893\n" +
			 "\n(>'.')>   <('.'<)";
	// END OF CONSTANTS
	
	// VARIABLES
	private JButton backButton;
    private JScrollPane scrollPane;
    private JTextPane textPane;
	// END OF VARIABLES
	
	// CONSTRUCTORS
	public CreditsMenu()
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
        textPane.setText( CREDITS_TEXT );
        
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
    		MenuManager.getInstance().changeMenu( (Menu) new MainMenu() );
    }
    // END OF OTHER METHODS                            
}