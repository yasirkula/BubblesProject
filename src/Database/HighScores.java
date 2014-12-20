package Database;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class HighScores
{
	// VARIABLES
	private ArrayList<Integer> bioScores;
	private ArrayList<String> bioNames;
	private ArrayList<Integer> chemScores;
	private ArrayList<String> chemNames;
	private ArrayList<Integer> vocabScores;
	private ArrayList<String> vocabNames;
	// END OF VARIABLES
	
	// CONSTRUCTORS
	public HighScores()
	{
		bioScores = new ArrayList<Integer>();
		bioNames = new ArrayList<String>();
		chemScores = new ArrayList<Integer>();
		chemNames = new ArrayList<String>();
		vocabScores = new ArrayList<Integer>();
		vocabNames = new ArrayList<String>();
		
		loadHighScores();
	}
	// END OF CONSTRUCTORS
	
	// MUTATOR - ACCESSOR METHODS
	public void setScores( String episode, ArrayList<Integer> scores )
	{
		if( episode == "biology" )
			bioScores = scores;
		else if( episode == "chemistry" )
			chemScores = scores;
		else if( episode == "vocabulary" )
			vocabScores = scores;
	}
	public ArrayList<Integer> getScores( String episode )
	{
		if( episode == "biology" )
			return bioScores;
		else if( episode == "chemistry" )
			return chemScores;
		else if( episode == "vocabulary" )
			return vocabScores;
		else
			return null;
	}
	
	public void setNames( String episode, ArrayList<String> names )
	{
		if( episode == "biology" )
			bioNames = names;
		else if( episode == "chemistry" )
			chemNames = names;
		else if( episode == "vocabulary" )
			vocabNames = names;			
	}
	
	public ArrayList<String> getNames( String episode )
	{
		if( episode == "biology" )
			return bioNames;
		else if( episode == "chemistry" )
			return chemNames;
		else if( episode == "vocabulary" )
			return vocabNames;
		else
			return null;
	}
	
	public int getTotalScore( String episode )
	{
		return -1;
	}
	// END OF MUTATOR - ACCESSOR METHODS
	
	// OTHER METHODS	
	public void saveHighScores()
	{
		// Inspired from http://stackoverflow.com/questions/2885173/java
				// 				 -how-to-create-and-write-to-a-file
				// answered by: Bozho
						
				BufferedWriter buffer = null;

				try 
				{
				    buffer = new BufferedWriter( new OutputStreamWriter( new FileOutputStream( "highscores.dat" ), "UTF-8" ) );
				    buffer.write( "Biology Scores " );
				    for(int i = 0; i < bioNames.size(); i++)
				    {
				    	bioNames.get( i );
				    }
				} 
				catch( IOException ex ){} 
				finally 
				{
					try 
					{
						buffer.close();
					} 
					catch( Exception ex ){}
				}
	}
	public void loadHighScores()
	{
		
		// Inspired from http://docs.oracle.com/javase/tutorial/essential/io/file.html
		// and
		// http://stackoverflow.com/questions/14918188/reading
		// -text-file-with-utf-8-encoding-using-java
		// answered by: Shobit Sharma
		BufferedReader buffer = null;
				
		try
		{
			buffer = new BufferedReader( new InputStreamReader( new FileInputStream( "highscores.dat" ), "UTF-8" ) );
				    
			String line = null;
			while( ( line = buffer.readLine() ) != null ) 
			{
				String[] tokens = line.split( "\n" );
				        
				if( tokens[0].equals( "Biology Scores" ) )
				{
				   	try
				    {
				     	for( int i = 0; i < tokens.length; i++ )
				     	{
				     		bioNames.add( tokens[i] );
				     	}
				    }
				    catch( Exception e ){}
				}
				else if( tokens[0].equals( "Chemistry Scores" ) )
		        {
		        	try
				    {
		        		for( int i = 0; i < tokens.length; i++ )
						{
		        			chemNames.add( tokens[i] );
						}
				    }
		        	catch( Exception e ){}
		        }
				else if( tokens[0].equals( "Vocabulary Scores" ) )
				{
					try
					{
						for( int i = 0; i < tokens.length; i++ )
						{
							vocabNames.add( tokens[i] );
						}
					}
					catch( Exception e ){}
				}
			}
		} 
		catch( IOException x ){}
		finally 
		{
		   try 
		   {
			   buffer.close();
		   } 
		   catch( Exception ex ){}
		}	
	}
	// END OF OTHER METHODS
}