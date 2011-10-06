package pl.wbaczynski.kata.term_freq;

import java.io.Closeable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;

import pl.wbaczynski.kata.tokenizer.StreamTokenizer;


public class TermFreqStream implements ITermFreq {
	
	
	public static void main(String[] args) 
	{
		InputStreamReader isr = null;
		String term;
		if(args.length == 0) {
			System.err.println("java TermFreqStream <term> [text] or java TermFreqStream <term> <text file name>");
			return;
		}
		else if(args.length == 1)
		{
			term = args[0];
			isr = new InputStreamReader(System.in );
		}
		else 
		{
			term = args[0];

			//if(! new File(args[1]).isFile())
			
			try {
				isr =  new InputStreamReader(new FileInputStream(args[1]));
			} catch (FileNotFoundException e) {
				System.err.println("Text file not found");
				return;
			}
		}
		final TermFreqStream termFreq = new TermFreqStream();
		try {	
			termFreq.setTerm( term );
			termFreq.process( isr );
		
			System.out.printf("Freq of term: %s is %d\n", args[0], termFreq.getFreq() );
			System.out.printf("Percentage: %.2f\n", termFreq.getPercentage());
		} catch (IOException e) {
			// TODO add logging ERROR
			e.printStackTrace();
			System.err.println("Internal error.");
		} finally 
		{
			termFreq.closeAttempt(isr);
		}
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
	
	protected void closeAttempt(Closeable aCloseable)
	{
		if(aCloseable != null)
		{
			try 
			{	
				aCloseable.close();
			} catch (Exception e) {
				// TODO add logging
				e.printStackTrace();
			}
		}
	}
		

}
