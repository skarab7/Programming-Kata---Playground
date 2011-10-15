package pl.wbarczynski.kata.bloom_filter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

///:! javac -classpath ~/.m2/repository/junit/junit/4.9/junit-4.9.jar BloomFilter.java  
//   java  -classpath ~/.m2/repository/junit/junit/4.9/junit-4.9.jar:. org.junit.runner.JUnitCore BloomFilterTest  

public class SpellcheckTest 
{
	private static final String DICT_FILE ="./words";

	// not a test 
	@Test
	public void testPositive() throws IOException	
	{
		final Spellcheck bf = new Spellcheck();
		Spellcheck.importFile(bf, DICT_FILE);
		
		forEachLine(new Visitor() {
			public void process(String aWord)
			{
				boolean result = bf.isValid( aWord.trim() );
				Assert.assertTrue(aWord + " is not in BloomFilter" ,  result);
			}
		});
	}

	@Test
	public void testNegative() throws IOException
	{
		final Spellcheck bf = new Spellcheck();
		
		Spellcheck.importFile(bf, DICT_FILE);
		
		forEachLine(new Visitor() {
			public void process(String aWord)
			{
				String word = "wwww22222" + aWord + "xwwwwww";
				boolean result = bf.isValid(word);
				Assert.assertFalse( result + " Failed for " + word,  result);
			}

		});
	}
	
	@Test
	public void testSmall()
	{
		String[] in = {"www", "lll", "eeee"};
		final Spellcheck bf = new Spellcheck();
		
		for(String s : in)
		{
			bf.add(s);
		}
		Assert.assertTrue( bf.isValid(in[0]) );
		
		Assert.assertFalse( bf.isValid( "1122") );
		
	}

		
	private interface Visitor {
		
		public void process(String aWord);		
	}

		
	public void forEachLine(Visitor aVis) throws IOException
	{
			BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader( DICT_FILE));	
			String line;
			while( ( line = in.readLine() ) !=null )
			{
				aVis.process(line);
			}
			
		}
		catch(IOException ioe)
		{	
			System.err.println("Failed to import file to BloomFilter " + ioe);
		}
		finally {
			if(in !=null) { in.close();}
		}	

	}
	
	
}
