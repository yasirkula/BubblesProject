/**
 * Point2DFactory - An abstract factory class to create random positions
 * 				  	for bubbles very easily and efficiently
 * 
 * @author CS319 - Section 2 - Group 9
 */

/*   IMPORTANT INFORMATION ABOUT THIS CLASS
 * - HOW ARE THE RANDOM POSITIONS GENERATED -
 * 
 * We divide the game screen into square grids (cells) of
 * equal size. The dimension of each cell is 2 times
 * the diameter of a bubble. 
 * 
 * We put bubbles to top-left point of randomly selected
 * grids and then reposition them inside the grid randomly
 * (dimension of each cell is 2 times the diameter, so a
 * bubble can move "diameter" amount of pixels horizontally
 * and vertically at most)
 */

package GameAssets;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import Driver.Driver;

public abstract class Point2DFactory 
{
	// VARIABLES
	protected int diameter;
	protected int alignmentSpace;
	protected int horizCellCount;
	protected int vertCellCount;
	protected int cellCount;
	protected ArrayList<Integer> freeBubbleCells;
	// END OF VARIABLES
	
	// CONSTRUCTORS
	public Point2DFactory()
	{
		// Initialize the variables
		diameter = (int)( 2 * Bubble.getRadius() );
		alignmentSpace = 2 * diameter;
		horizCellCount = Driver.getFrameWidth() / alignmentSpace;
		vertCellCount = Driver.getFrameHeight() / alignmentSpace;
		cellCount = horizCellCount * vertCellCount;
	}
	// END OF CONSTRUCTORS
	
	// OTHER METHODS
	public void initialize()
	{
		// Initialize the grids. After this function is called,
		// each grid is considered empty. So, this function is
		// called each time a level is loaded
		freeBubbleCells = new ArrayList<Integer>();
		for( int i = 0; i < cellCount; i++ )
		{
			freeBubbleCells.add( i );
		}
	}
	
	// Abstract function that is to be filled by the child
	// (concrete) factory classes
	public abstract Point2D getRandomBubblePosition();
	
	public void freeCell( Point2D position )
	{
		// this function is called whenever a bubble is destroyed runtime
		// it frees the cell bubble was inside so that this cell can be
		// used again
		double x = position.getX();
		double y = position.getY();
		
		int row = (int)( y / alignmentSpace );
		int col = (int)( x / alignmentSpace );
		
		int cellIndex = row * horizCellCount + col;
		
		if( !freeBubbleCells.contains( cellIndex ) )
			freeBubbleCells.add( cellIndex );
	}
	// END OF OTHER METHODS
}
