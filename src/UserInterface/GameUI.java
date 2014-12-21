/**
 * GameUI - Draws the game screen
 * 
 * @author CS319 - Section 2 - Group 9
 */

package UserInterface;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

import GameAssets.Bubble;
import GameManagement.GameEngine;

public class GameUI extends JPanel implements MouseMotionListener, ActionListener
{
	// VARIABLES
	private GameEngine engine;
	
	private JButton pauseButton;
	private Point mousePos;
	// END OF VARIABLES
	
	// CONSTRUCTORS
	public GameUI()
	{
		// GameEngine is accessed a lot by this class
		// store the reference in a variable so that
		// accessing the engine becomes slightly faster
		engine = GameEngine.getInstance();
		
		// get the background color from SettingsManager
		setBackground( GameEngine.getInstance().getSettings().getBackgroundColor() );
		mousePos = new Point();
		
		// Set layout to null so that we can position components manually
		setLayout( null );
		
		pauseButton = new JButton( "PAUSE" );
		pauseButton.setSize( 95, 50 );
		pauseButton.setBackground( new Color( 190, 205, 210 ) );
		pauseButton.setFocusable( false );
		pauseButton.addActionListener( this );
		
		add( pauseButton );
		
		// UI should watch mouse motion so that it can draw line from
		// selected bubble to mouse position on mouse movement
		addMouseMotionListener( this );
	}
	// END OF CONSTRUCTORS
	
	// MUTATOR - ACCESSOR METHODS
	public void setMousePos( Point p )
	{
		mousePos = p;
	}
	// END OF MUTATOR - ACCESSOR METHODS
	
	// OTHER METHODS
	public void paintComponent( Graphics g )
	{
		// Draw gradient background
		// Inspired from: http://stackoverflow.com/questions/12220853/how
		//				  -to-make-the-background-gradient-of-a-jpanel
		// answered by: trashgod
		super.paintComponent( g );
        Graphics2D g2d = ( Graphics2D ) g;
        Color colorTop = getBackground();
        Color colorBottom = colorTop.darker();
        GradientPaint gra = new GradientPaint( 0, 0, colorTop, 0, getHeight(), colorBottom );
        g2d.setPaint( gra );
        g2d.fillRect( 0, 0, getWidth(), getHeight() );
        
        // if user already selected their first bubble,
 		// draw a line from the bubble's center to the mouse position
 		if( engine.getSelectedBubble() != null )
 		{
 			g2d.setStroke( new BasicStroke( 3 ) );
 			
 			GradientPaint gradientLine = new GradientPaint( engine.getSelectedBubble().getCenterPoint(),
 							Color.BLACK, mousePos, new Color( 150, 0, 0 ) );
 			g2d.setPaint( gradientLine );
 			g.drawLine( engine.getSelectedBubble().getCenterPoint().x,
 						engine.getSelectedBubble().getCenterPoint().y,
 						mousePos.x, mousePos.y );
 		}
		
        // draw bubbles on screen
		ArrayList<Bubble> bubbles;
		
		bubbles = engine.getBubbles();
		for( Bubble b : bubbles )
		{
			b.draw( g );
		}
		
		bubbles = engine.getMatchingBubbles();
		for( Bubble b : bubbles )
		{
			b.draw( g );
		}
		
		bubbles = engine.getTrapBubbles();
		for( Bubble b : bubbles )
		{
			b.draw( g );
		}
		
		// Draw remaining time and score with black outline
		g.setFont( new Font( "Default", Font.PLAIN, 23 ) );
		
		g.setColor( Color.black );
		g.drawString( "Time left: " + engine.getTime(), 12, 25 );
		g.drawString( "Time left: " + engine.getTime(), 8, 25 );
		g.drawString( "Time left: " + engine.getTime(), 10, 23 );
		g.drawString( "Time left: " + engine.getTime(), 10, 27 );
		g.drawString( "Score: " + engine.getScore(), 12, 55 );
		g.drawString( "Score: " + engine.getScore(), 8, 55 );
		g.drawString( "Score: " + engine.getScore(), 10, 53 );
		g.drawString( "Score: " + engine.getScore(), 10, 57 );
		
		g.setColor( Color.white );
		g.drawString( "Time left: " + engine.getTime(), 10, 25 );
		g.drawString( "Score: " + engine.getScore(), 10, 55 );
		
		// position pauseButton correctly so that it is always
		// at top-right even when the frame is resized
		pauseButton.setLocation( getWidth() - 100, 5 );
	}
	// END OF OTHER METHODS

	// INTERFACE METHODS
	public void mouseMoved( MouseEvent e ) 
	{
		if( engine.getSelectedBubble() != null )
		{
			// if user selected a bubble, update the mouse position
			// so that the line from the bubble's center to mouse
			// position is drawn correctly
			mousePos = e.getPoint();
			repaint();
		}
	}
	
	public void actionPerformed( ActionEvent e ) 
	{
		if( e.getSource() == pauseButton )
		{
			GameEngine.getInstance().playSound( "sounds/buttonClick.wav" );
			engine.pause();
		}
	}
	
	public void mouseDragged( MouseEvent e ){}
	// END OF INTERFACE METHODS
}