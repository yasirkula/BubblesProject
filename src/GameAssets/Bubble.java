/**
 * Bubble - Stores all properties of a bubble like position
 * 			and content
 * 
 * @author CS319 - Section 2 - Group 9
 */

package GameAssets;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Ellipse2D;

public class Bubble
{
	// CONSTANTS
	private static final int RADIUS = 32;
	private final int DIAMETER = 64;
	// END OF CONSTANTS
	
	// VARIABLES
	private Color bubbleColor;
	private int xPos; // leftmost point
	private int yPos; // topmost point
	private Content content;
	// END OF VARIABLES
	
	// CONSTRUCTORS
	public Bubble( Content c )
	{
		// initialize variables to default values
		content = c;
		xPos = 0;
		yPos = 0;
		
		// give bubble a random light color
		bubbleColor = new Color( 170 + (int)( Math.random() * 85 ),
    			170 + (int)( Math.random() * 85 ), 170 + (int)( Math.random() * 85 ) );
	}
	// END OF CONSTRUCTORS
	
	// MUTATOR - ACCESSOR METHODS
	public Content getContent()
	{
		return content;
	}
	
	public void setContent( Content c )
	{
		content = c;
	}
	
	public static int getRadius()
	{
		return RADIUS;
	}
	
	public int getDiameter()
	{
		return DIAMETER;
	}
	
	public int getX()
	{
		return xPos;
	}
	
	public int getY()
	{
		return yPos;
	}
	
	public Point getCenterPoint()
	{
		// returns the center point of the bubble on screen
		return new Point( xPos + RADIUS, yPos + RADIUS );
	}
	
	public void setLocation( int x, int y )
	{
		xPos = x;
		yPos = y;
	}
	
	public Color getColor()
	{
		return bubbleColor;
	}
	
	public void setColor( Color c )
	{
		bubbleColor = c;
	}
	// END OF MUTATOR - ACCESSOR METHODS
	
	// OTHER METHODS
	public void draw( Graphics g )
	{
		g.setColor( bubbleColor );
		content.draw( g, this );
	}
	
	public boolean contains( Point mousePos )
	{
		// returns whether Bubble contains the position of the mouse inside or not
		Ellipse2D.Float shape = new Ellipse2D.Float( xPos, yPos, DIAMETER, DIAMETER );

		if( shape.contains( mousePos ) )
			return true;

		return false;
	}
	// END OF OTHER METHODS
}