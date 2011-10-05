package pl.wbaczynski.kata.term_freq;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;

import pl.wbaczynski.kata.tokenizer.Tokenizer;

/**
 * @author Sk (wpjtek@yahoo.com)
 *
 */
public class TermFreq implements ITermFreq {
	
	private String lowerCaseTerm;
	private int freq;
	private int allTokens;	
	
	public static void main(String[] args) {
		
		
		String text = "";
		String term  = "";
		
		if(args.length == 0) 
		{
			System.err.println( "Please provide a term and a text: java TermFreq <term> [text file] (ctr-z)");
			return;			
		}
		
		term = args[0]; 
		
		
		if(args.length == 1)
		{
		
			text = getUserInput();
		}
		else 
		{
			try {
				text = readFromFile(args[1]);				
			} catch (FileNotFoundException e) {
				System.err.println("File " + args[1] + " does not exist");
				return;
			}	
		}
		long start = System.currentTimeMillis();
		final TermFreq tf = new TermFreq();
		tf.setTerm(term);		
		tf.process(text);
		
		System.out.println("Term Frequency: " + tf.getFreq());
		System.out.printf("Percentage : %.2f \n",  tf.getPercentage());
		long end = System.currentTimeMillis();
		System.out.println("It took " +  (end - start) + " miliseconds") ;
	}
	
	@Override
	public void setTerm(String aTerm)
	{
		lowerCaseTerm = aTerm.toLowerCase();		
	}
	
	
	
	public void process(String aText)
	{
		Iterator<String> tokens = Tokenizer.parseText(aText);
		freq = 0;
		allTokens = 0;
	
		String token;
		while(tokens.hasNext())
		{
			token = tokens.next().toLowerCase();
			if(token.compareTo(lowerCaseTerm) == 0)
			{
				freq++;
			}
			allTokens++;
		}		
	}
	
	@Override
	public int getFreq() {
		return freq;
	}
	
	@Override
	public double getPercentage()
	{
		return ((double) freq /  (double) allTokens) * 100 ;
				
	}
	
	// works with control z
	protected static String getUserInput()
	{		
		return new Scanner(System.in).useDelimiter("\\A").next();
	}
	
	protected static String readFromFile(String aFileName) throws FileNotFoundException
	{
		if(! new File(aFileName).isFile() ) {throw new FileNotFoundException();}
		Scanner sc = null;
		try {
			 sc = new Scanner(new File(aFileName));
			return sc.useDelimiter("\\A").next();
		}
		finally {
			if(sc!=null) {sc.close();}
		}
	}
	
}
