package pl.wbaczynski.kata.tokenizer;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;

/**
 * A simple white space tokenizer.
 *  
 * @author Sk (wpjtek@yahoo.com)
 */
public class StreamTokenizer {
	public Iterator<String> parse(InputStreamReader aStream) throws IOException
	{
		return new MyIterator(aStream);
	}
	// 21:28
	private class MyIterator implements Iterator<String>{

		private final char[] chars;
		private int len;
		private int idx;
		private State state;
		
		private final InputStreamReader streamReader;
		private String theNextToken;
		
		public MyIterator(InputStreamReader streamReader)
		{
			this.streamReader = streamReader;
			chars = new char[100];
			state = State.WHITE;
		}
		
		@Override
		public boolean hasNext() {
			final StringBuilder nextTokBuilder = new StringBuilder();
			boolean isFound = false;
			try {
				if(idx<len)
				{
					isFound = checkBuf(nextTokBuilder);
				}
				
				if(! isFound)
				{
					idx = 0;
					while( (len = streamReader.read(chars)) !=-1)
					{
						isFound = checkBuf(nextTokBuilder);
						if(isFound) {break;}
					}
				}
				
				if(len == -1 && state == State.TOKEN)
				{
					state = State.WHITE;
					isFound = true;
				}
				
				if(isFound)
				{				
					isFound = true;
					theNextToken = nextTokBuilder.toString();
				}
				
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			return isFound;
		}

		@Override
		public String next() {
			return theNextToken;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException("remove is not implemneted!");
			
		}
		
		private boolean checkBuf(final StringBuilder nextTokBuilder){
			boolean isFound = false;
			int i = idx;
			for(; i < len; i++)
			{
				state = state.nextStage(chars[i]);
				if(state == State.TOKEN)
				{
					nextTokBuilder.append(chars[i]);
				}
				else if(state == State.READY)
				{
					i++;
					isFound = true;
					break;
				}
			}
			idx = i;
			return isFound;
		}
		
	}
	
	private enum State 
	{
		WHITE {
			@Override
			public State nextStage(char c) {
				if(isWhiteSpace(c))
				{
					return WHITE;
				}
				return TOKEN; 
			}
		},
		TOKEN {
			@Override
			public State nextStage(char c) {
				if( isWhiteSpace(c))
				{
					return READY;
				}
				return TOKEN; 
			}
		},
		
		READY {

			@Override
			public State nextStage(char c) {
				if(isWhiteSpace(c))
				{
					return WHITE;
				}
				return TOKEN;
			}
			
		
		};
		
		public abstract State nextStage(char c);
		
		private static boolean isWhiteSpace(char c)
		{
			return c == ' ' || c == '.' || c == '\n';	
		}
		
	}
}
