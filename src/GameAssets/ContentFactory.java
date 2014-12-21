/**
 * ContentFactory - A factory classes that creates Contents
 * 					for us to use
 * 
 * @author CS319 - Section 2 - Group 9
 */

package GameAssets;

public class ContentFactory
{
	public Content getContent( ContentType type )
	{
		Content result = null;
		
		switch( type )
		{
			case TEXT:
				result = new TextContent();
				break;
			case IMAGE:
				result = new ImageContent();
				break;
			default: break;
		}
		
		return result;
	}
}
