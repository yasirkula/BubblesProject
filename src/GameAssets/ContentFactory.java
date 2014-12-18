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
