package UserInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
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
		
		add( scrollPane, BorderLayout.CENTER );
		add( backButton, BorderLayout.PAGE_END );
		
		setBackground( new Color( 255, 255, 255 ) );
	}
	// END OF CONSTRUCTORS
	
	// MUTATOR - ACCESSOR METHODS
	public void setCreditsText( String c )
	{
		textPane.setText( c );
		textPane.repaint();
	}
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
    		MenuManager.instance.changeMenu( (Menu) new MainMenu() );
    }
    // END OF OTHER METHODS                            
}