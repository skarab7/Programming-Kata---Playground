package pl.wbaczynski.kata.term_freq;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Scanner;

import pl.wbaczynski.kata.tokenizer.StreamTokenizer;


public class TermFreqStream implements ITermFreq {
	
	
	public static void main(String[] args) throws IOException
	{
		if(args.length == 0) {
			System.err.println("java TermFreqStream <term> [text]");
			return;
		}
		
		final InputStreamReader isr = new InputStreamReader( new ByteArrayInputStream(new Scanner(System.in).useDelimiter("\\A").next().getBytes("UTF8")) );
		
		
		final TermFreqStream termFreq = new TermFreqStream();
		termFreq.setTerm(args[0]);
		termFreq.process(isr);
		
		System.out.printf("Freq of term: %s is %d\n", args[0], termFreq.getFreq() );
		System.out.printf("Percentage: %.2f\n", termFreq.getPercentage());
	}

	

	private String term;
	private int freq;
	private int numAllTokens;
	
	public void setTerm(String aTerm)
	{
		term = aTerm.toLowerCase();
	}
	
	public void process(InputStreamReader aIsr ) throws IOException
	{
		final StreamTokenizer st = new StreamTokenizer();
		Iterator<String> tokens = st.parse(aIsr);
		freq = 0;
		numAllTokens = 0;
		String token;
		while(tokens.hasNext())
		{
			token = tokens.next().toLowerCase();
			if(token.compareTo(term) == 0 )
			{
				freq++;
			}
			numAllTokens++;
		}	
	}
	
	@Override
	public int getFreq() {
		return freq;
	}


	@Override
	public double getPercentage() {
		return ((double) freq /  (double) numAllTokens) * 100 ;
	}
	
		

}
