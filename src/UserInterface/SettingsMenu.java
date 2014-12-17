package UserInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.Hashtable;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import GameManagement.MenuManager;

public class SettingsMenu extends Menu implements ChangeListener
{
	// VARIABLES
	private JColorChooser colorPicker;
	private JSlider soundLevel;
	private JButton backButton;
	// END OF VARIABLES
	
	// CONSTRUCTORS
	public SettingsMenu()
	{
		super();
		
		BorderLayout layout = new BorderLayout( 0, 25 );
		setLayout( layout );

		JPanel centerPanel = new JPanel();
		JPanel centerTopPanel = new JPanel();
		JPanel centerBottomPanel = new JPanel();
		JLabel bgColorLabel = new JLabel( "BACKGROUND COLOR", SwingConstants.CENTER );
		JLabel soundLevelLabel = new JLabel( "SOUND LEVEL", SwingConstants.CENTER );
		
		// Set font
        Font f = new Font( "Default", Font.PLAIN, 23 );
        bgColorLabel.setFont( f );
        soundLevelLabel.setFont( f );
        
		GridLayout centerLayout = new GridLayout( 2, 1, 0, 25 );
		BorderLayout centerTopLayout =  new BorderLayout( 0, 15 );
		BorderLayout centerBottomLayout =  new BorderLayout();
		
		centerPanel.setLayout( centerLayout );
		centerTopPanel.setLayout( centerTopLayout );
		centerBottomPanel.setLayout( centerBottomLayout );
		
		centerTopPanel.add( bgColorLabel, BorderLayout.PAGE_START );
		centerTopPanel.add( colorPicker, BorderLayout.CENTER );
		centerBottomPanel.add( soundLevelLabel, BorderLayout.PAGE_START );
		centerBottomPanel.add( soundLevel, BorderLayout.CENTER );
		
		centerPanel.add( centerBottomPanel );
		centerPanel.add( centerTopPanel );
		
		centerPanel.setBorder( BorderFactory.createEtchedBorder( EtchedBorder.LOWERED ) );
		
		Dimension gap = new Dimension( 25, 25 );
		add( new Box.Filler( gap, gap, gap ), BorderLayout.PAGE_START );
		add( new Box.Filler( gap, gap, gap ), BorderLayout.LINE_START );
		add( centerPanel, BorderLayout.CENTER );
		add( new Box.Filler( gap, gap, gap ), BorderLayout.LINE_END );
		add( backButton, BorderLayout.PAGE_END );
		
		setBackground( MenuManager.getInstance().getSettings().getBackgroundColor() );
	}
	// END OF CONSTRUCTORS
	
	// MUTATOR - ACCESSOR METHODS
	// END OF MUTATOR - ACCESSOR METHODS
	
	// OTHER METHODS
	protected void initComponents()
	{
		// Initialize components
		colorPicker = new JColorChooser( MenuManager.getInstance().getSettings().getBackgroundColor() );
		soundLevel = new JSlider( JSlider.HORIZONTAL, 0, 100, 100 ); // min, max, initial
		backButton = new JButton();
		
		colorPicker.setPreviewPanel( new JPanel() );
		colorPicker.getSelectionModel().addChangeListener( this );
		AbstractColorChooserPanel[] colorPickerPanels = new AbstractColorChooserPanel[1];
		for( int i = 0; i < colorPicker.getChooserPanels().length; i++ )
		{
			if( colorPicker.getChooserPanels()[i].getClass().getName().equals("javax.swing.colorchooser.DefaultSwatchChooserPanel") )
				colorPickerPanels[0] = colorPicker.getChooserPanels()[i];
		}
		colorPicker.setChooserPanels( colorPickerPanels );
		
		
		Hashtable<Integer, JLabel> labelTable = new Hashtable<Integer, JLabel>();
		labelTable.put( new Integer( 0 ), new JLabel( "SILENT" ) );
		labelTable.put( new Integer( 100 ), new JLabel( "LOUD" ) );
		soundLevel.setLabelTable( labelTable );
		soundLevel.setMajorTickSpacing( 25 );
		soundLevel.setMinorTickSpacing( 5 );
		soundLevel.setPaintTicks( true );
		soundLevel.setPaintLabels( true );
		soundLevel.addChangeListener( this );
		
		backButton.setIcon( new ImageIcon( getClass().getResource("/images/BackButton.png") ) );
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
	
	public void stateChanged( ChangeEvent e ) 
	{
		if( e.getSource() == soundLevel )
		{
			System.out.println( soundLevel.getValue() / 100f );
		}
		else if( e.getSource() == colorPicker.getSelectionModel() )
		{
			Color c = colorPicker.getColor();
			System.out.println( c );
			MenuManager.getInstance().getSettings().setBackgroundColor( c );
			setBackground( c );
		}
	}
	// END OF OTHER METHODS
}