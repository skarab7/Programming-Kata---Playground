package pl.wbarczynski.kata.bloom_filter;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.FileReader;
import java.io.IOException;

public class Spellcheck {
	
	private final static int NUM_OF_WORDS = 235886;
	private final static double MAX_FALSE_POS = 0.001d;
	private final static int NUM_OF_HASHES = 10;
	private final static int FILTER_SIZE = 9000000;
	
	private final BloomFilter bloomFilter;	
	
	public Spellcheck() {
		BloomFilterBuilder bfbuilder = new BloomFilterBuilder();
		
		bfbuilder.setFilterSize(FILTER_SIZE)
		.setNumOfHashes(NUM_OF_HASHES)
		.setHashAlgorithm("SHA1")
		.setInputNumber(NUM_OF_WORDS);	
		bfbuilder.setMaxFalsePositive(MAX_FALSE_POS);
		
		bloomFilter = bfbuilder.build();
	}
	
	public void add(String aWord)
	{
		bloomFilter.add(aWord);
	}

	public boolean isValid(String aWord)
	{
		return bloomFilter.contains(aWord);
	}
	
	public static void importFile(Spellcheck aSpellCheck, String aFilePath)
	{
		
		BufferedReader in = null;
		try {
			int i=0;
			in = new BufferedReader(new FileReader(aFilePath));	
			String line;
			while( ( line = in.readLine() ) !=null )
			{
				aSpellCheck.add( line.trim() );
				i++;
			}
			System.out.println("Imported input from a File: " + i);
		}
		catch(IOException ioe)
		{	
			System.err.println("Failed to import file to BloomFilter " + ioe);
		}
		finally {
			closeAttempt(in);
		}	

	}
	
	private static void closeAttempt(Closeable aCloseable)
	{
		if(aCloseable!=null)
		{
			try {
				aCloseable.close();
			} catch (IOException ioe)
			{	
				System.err.println ("Ignoring " + ioe);
			}
		}
	}
}
