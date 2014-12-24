/**
 * PointFactory - A factory class to create random positions
 * 				  for bubbles very easily and efficiently
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

import java.awt.Point;

public class PointFactory extends Point2DFactory
{
	// CONSTRUCTORS
	public PointFactory()
	{
		super();
	}
	// END OF CONSTRUCTORS
	
	// OTHER METHODS
	public Point getRandomBubblePosition()
	{
		// selects a random cell and returns it position as Point
		// also removes the selected cell from the list of free cells
		// so that this cell is not selected again until this object
		// is initialized again via initialize() method
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
			// If the selected cell is one of the rightmost or 
			// bottommost cells, do not reposition the bubble inside
			// the cell because it sometimes leads to bubble being
			// outside of the game frame
			xPos = col * alignmentSpace + (int)( Math.random() * 
					( ( col == horizCellCount - 1 ) ? 0 : diameter ) );
			yPos = row * alignmentSpace + (int)( Math.random() * 
					( ( row == vertCellCount - 1 ) ? 0 : diameter ) );
		}
		
		return new Point( xPos, yPos );
	}
	// END OF OTHER METHODS
}
