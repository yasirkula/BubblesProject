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

public class HighScoresMenu extends Menu
{
	// VARIABLES
	private JButton backButton;
	private JButton resetButton;
	private JButton bioScoresButton;
	private JButton chemScoresButton;
	private JButton vocabScoresButton;
	private JScrollPane scrollPane;
    private JTextPane textPane;
	// END OF VARIABLES
	
	// CONSTRUCTORS
	public HighScoresMenu()
	{
		super();
		
		BorderLayout layout = new BorderLayout( 0, 25 );
		setLayout( layout );
		
		Dimension gap = new Dimension( 25, 25 );
		add( new Box.Filler( gap, gap, gap ), BorderLayout.PAGE_START );
		add( new Box.Filler( gap, gap, gap ), BorderLayout.LINE_START );
		//add( bioScoresButton, BorderLayout.PAGE_END);
		//add( chemScoresButton, BorderLayout.NORTH);
		//add( vocabScoresButton, BorderLayout.NORTH);
		add( scrollPane, BorderLayout.CENTER );
		add( new Box.Filler( gap, gap, gap ), BorderLayout.LINE_END );
		//add( resetButton, BorderLayout.PAGE_END);
		add( backButton, BorderLayout.PAGE_END );
	}
	// END OF CONSTRUCTORS
	
	// MUTATOR - ACCESSOR METHODS
	// END OF MUTATOR - ACCESSOR METHODS
	
	// OTHER METHODS
	protected void initComponents() 
	{
		// Initialize components
        backButton = new JButton();
        resetButton = new JButton();
        bioScoresButton = new JButton( "Biology Scores" );
        chemScoresButton = new JButton( "Chemistry Scores" );
        vocabScoresButton = new JButton( "Vocabulary Scores" );
        scrollPane = new JScrollPane();
        textPane = new JTextPane();
        
        // Set colors and font
        Font f = new Font( "Default", Font.PLAIN, 23 );
        textPane.setEditable( false );
        textPane.setBackground( new Color( 237, 237, 237 ) );
        textPane.setForeground( Color.BLACK );
        textPane.setFont( f );
        
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
        
        resetButton.setIcon( new ImageIcon( getClass().getResource("/images/ResetButton.png") ) );
        resetButton.setBorderPainted( false );
        resetButton.setContentAreaFilled( false );
        resetButton.setFocusPainted( false );
        resetButton.addActionListener( this );
        
        bioScoresButton.setFont( f );
        chemScoresButton.setFont( f );
        vocabScoresButton.setFont( f );
        
        bioScoresButton.setBackground( new Color( 170, 157, 255 ) );
        chemScoresButton.setBackground( new Color( 174, 255, 157 ) );
        vocabScoresButton.setBackground( new Color( 240, 255, 157 ) );
        
        bioScoresButton.addActionListener( this );
        chemScoresButton.addActionListener( this );
        vocabScoresButton.addActionListener( this );
	}
	public void actionPerformed( ActionEvent e ) 
	{
		if( e.getSource() == backButton )
    		MenuManager.getInstance().changeMenu( (Menu) new MainMenu() );
	}
	// END OF OTHER METHODS
}