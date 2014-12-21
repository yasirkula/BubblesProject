/**
 * TextContent - A content that stores a String and draws it 
 * 				 on screen
 * 
 * @author CS319 - Section 2 - Group 9
 */

package GameAssets;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class TextContent implements Content
{
	// VARIABLES
	private String text;
	// END OF VARIABLES
	
	// CONSTRUCTORS
	public TextContent()
	{
		text = "";
	}
	
	public TextContent( String text )
	{
		this.text = text;
	}
	// END OF CONSTRUCTORS
	
	// MUTATOR - ACCESSOR METHODS
	public void setText( String text )
	{
		this.text = text;
	}
	// END OF MUTATOR - ACCESSOR METHODS
	
	// OTHER METHODS
	public void draw( Graphics g, Bubble b )
	{
		// draw the outline of the bubble
		g.fillOval( b.getX(), b.getY(), b.getDiameter(), b.getDiameter() );
		g.setColor( Color.black );
		( (Graphics2D) g ).setStroke( new BasicStroke( 3 ) );
		g.drawOval( b.getX(), b.getY(), b.getDiameter(), b.getDiameter() );
		
		// used a code segment from: http://www.coderanch.com/t
		//							 /336616/GUI/java/Center-Align-text-drawString
		// by kyle amburn
		// Draw the text at horizontally and vertically center of the Bubble
		Graphics2D g2D = (Graphics2D) g;
		Rectangle2D bounds = g2D.getFontMetrics().getStringBounds( text, g2D );
		int stringXPos = b.getX() + Bubble.getRadius() - (int)( bounds.getWidth() / 2 );
		int stringYPos = b.getY() + Bubble.getRadius() + (int)( bounds.getHeight() / 4 );
		
		g.drawString( text, stringXPos, stringYPos );
	}
	// END OF OTHER METHODS
}