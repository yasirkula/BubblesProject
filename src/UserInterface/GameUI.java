package UserInterface;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import GameAssets.Bubble;
import GameManagement.GameEngine;

public class GameUI extends JPanel implements MouseMotionListener
{
	// VARIABLES
	private GameEngine engine;
	
	private Point mousePos;
	// END OF VARIABLES
	
	// CONSTRUCTORS
	public GameUI()
	{
		engine = GameEngine.getInstance();
		setBackground( GameEngine.getInstance().getSettings().getBackgroundColor() );
		mousePos = new Point();
		requestFocus();
		
		// UI should watch mouse motion so that it can draw line from
		// selected bubble to mouse position at mouse movement
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
		// Gradient background
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
 			
 			GradientPaint redtowhite = new GradientPaint( engine.getSelectedBubble().getCenterPoint(),
 							Color.GRAY, mousePos, Color.BLACK );
 			g2d.setPaint(redtowhite);
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
	}
	// END OF OTHER METHODS

	// INTERFACE METHODS
	public void mouseMoved( MouseEvent e ) 
	{
		if( engine.getSelectedBubble() != null )
		{
			mousePos = e.getPoint();
			repaint();
		}
	}
	
	public void mouseDragged( MouseEvent e ){}
	// END OF INTERFACE METHODS
}