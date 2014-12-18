package GameAssets;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class TextContent implements Content
{
	// VARIABLES
	private String text;
	// END OF VARIABLES
	
	// CONSTRUCTORS
	public TextContent( String text )
	{
		this.text = text;
	}
	// END OF CONSTRUCTORS
	
	// MUTATOR - ACCESSOR METHODS
	// END OF MUTATOR - ACCESSOR METHODS
	
	// OTHER METHODS
	public void draw( Graphics g, Bubble b )
	{
		// used a code segment from: http://www.coderanch.com/t
		//							 /336616/GUI/java/Center-Align-text-drawString
		// by kyle amburn
		Graphics2D g2D = (Graphics2D) g;
		Rectangle2D bounds = g2D.getFontMetrics().getStringBounds( text, g2D );
		int stringXPos = b.getX() + Bubble.getRadius() - (int)( bounds.getWidth() / 2 );
		int stringYPos = b.getY() + Bubble.getRadius() + (int)( bounds.getHeight() / 4 );
		
		g.drawString( text, stringXPos, stringYPos );
	}
	// END OF OTHER METHODS
}