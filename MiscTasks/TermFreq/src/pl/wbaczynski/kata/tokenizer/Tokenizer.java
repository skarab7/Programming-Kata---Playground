package pl.wbaczynski.kata.tokenizer;

import java.util.Iterator;


/**
 * Regular expression based tokenizer 
 * 
 * @author wpjtek@yahoo.com
 */
public class Tokenizer {
	
	private static final String WHITE_SP_OR_DOT = "(((\\s)+)|((\\.)+))+";
	
	public Iterator<String> parse(final String aValue)
	{
		if(aValue == null) {throw new  IllegalArgumentException();};			
		return new MyIterator( aValue.split(WHITE_SP_OR_DOT) );
	}
	
	public static Iterator<String> parseText(final String aValue)
	{
		final Tokenizer t = new Tokenizer();
		return t.parse(aValue);
	}
		
	private class MyIterator implements Iterator<String> {
			private final String[] tokens;
			private int idx;
			private String theNext;

			public MyIterator(String[] aTokens)
			{
				tokens = aTokens;
				idx = 0;
			}			
			
			@Override
			public boolean hasNext() {
				if(idx >= tokens.length)
				{
					return false;					
				}						
				theNext = tokens[idx];
				idx++;
				return true;
			}

			@Override
			public String next() {				
				return theNext;
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException("Token iterator does not support remove()");				
			}
	}
	
	



}	
