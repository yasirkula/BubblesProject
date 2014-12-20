package GameAssets;

import java.awt.Point;
import java.util.ArrayList;

import Driver.Driver;

public class PointFactory 
{
	private int diameter;
	private int alignmentSpace;
	private int horizCellCount;
	private int vertCellCount;
	private int cellCount;
	private ArrayList<Integer> freeBubbleCells;
	
	public PointFactory()
	{
		diameter = (int)( 2 * Bubble.getRadius() );
		alignmentSpace = 2 * diameter;
		horizCellCount = Driver.getFrameWidth() / alignmentSpace;
		vertCellCount = Driver.getFrameHeight() / alignmentSpace;
		cellCount = horizCellCount * vertCellCount;
	}
	
	public void initialize()
	{
		freeBubbleCells = new ArrayList<Integer>();
		for( int i = 0; i < cellCount; i++ )
		{
			freeBubbleCells.add( i );
		}
	}
	
	public Point getRandomBubblePosition()
	{
		int randomIndex = (int)( Math.random() * freeBubbleCells.size() );
		int randomNumber = freeBubbleCells.get( randomIndex );
		int row = randomNumber / horizCellCount;
		int col = randomNumber % horizCellCount;
		freeBubbleCells.remove( randomIndex );
		
		int xPos, yPos;
		if( row == 0 && col == 0 )
		{
			// if bubble is in the top-left cell, change bubble's
			// position manually to prevent it from becoming
			// invisible behind the time and score texts
			xPos = diameter;
			yPos = diameter;
		}
		else if( row == 0 && col == horizCellCount - 1 )
		{
			// if bubble is in the top-right cell, change bubble's
			// position manually to prevent it from becoming
			// invisible behind the pause button
			xPos = col * alignmentSpace;
			yPos = diameter;
		}
		else
		{
			xPos = col * alignmentSpace + (int)( Math.random() * 
					( ( col == horizCellCount - 1 ) ? 0 : diameter ) );
			yPos = row * alignmentSpace + (int)( Math.random() * 
					( ( row == vertCellCount - 1 ) ? 0 : diameter ) );
		}
		
		return new Point( xPos, yPos );
	}
	
	public void freeCell( Point position )
	{
		int x = position.x;
		int y = position.y;
		
		int row = y / alignmentSpace;
		int col = x / alignmentSpace;
		
		int cellIndex = row * horizCellCount + col;
		
		if( !freeBubbleCells.contains( cellIndex ) )
			freeBubbleCells.add( cellIndex );
	}
}
