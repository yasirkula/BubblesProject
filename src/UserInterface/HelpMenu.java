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

public class HelpMenu extends Menu
{
	// CONSTANTS
	private final String HELP_TEXT = "\nSuch help document\n\n" +
			 "many content\n\n" +
			 "so helpful\n\n" +
			 "wow\n";
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

        backButton.setIcon( new ImageIcon( getClass().getResource("/images/BackButton.png") ) );
        backButton.setBorderPainted( false );
        backButton.setContentAreaFilled( false );
        backButton.setFocusPainted( false );
        backButton.addActionListener( this );
	}
	
	public void actionPerformed( ActionEvent e ) 
	{
		if( e.getSource() == backButton )
		{
			if( !isGamePaused )
				MenuManager.getInstance().changeMenu( new MainMenu() );
			else
				MenuManager.getInstance().changeMenu( new PauseMenu() );
		}
	}
	// END OF OTHER METHODS
}