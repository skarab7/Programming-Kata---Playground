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
			state = State.DELIMITER;
		}
		
		@Override
		public boolean hasNext() {
			final StringBuilder nextTokBuilder = new StringBuilder();
			boolean isFound = false;
			try {
				if(idx<len)
				{
					isFound = readBuffer(nextTokBuilder);
				}
				
				if(! isFound)
				{
					idx = 0;
					while( (len = streamReader.read(chars)) !=-1)
					{
						isFound = readBuffer(nextTokBuilder);
						if(isFound) {break;}
					}
				}
				
				
				if(len == -1 && state == State.APPENDING)
				{
					state = gotoNextStateOnEof(state);
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
		
		private boolean readBuffer(final StringBuilder nextTokBuilder){
			boolean isFound = false;
			int i = idx;
			for(; i < len; i++)
			{
				state = gotoNextState(state, chars[i]);
				if(state == State.APPENDING)
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
	
	protected enum State
	{
		DELIMITER,
		APPENDING,
		READY;
	}
	
	/**
	 * Can be overwritten
	 * @param c
	 * @return
	 */
	protected boolean isWhiteSpace(char c)
	{
		return c == ' ' || c == '.' || c == '\n';	
	}
	
	/**
	 * @param aState
	 * @param c
	 * @return
	 */
	private State gotoNextState(State aState, char c)
	{
		State result;
		switch (aState) {
		case DELIMITER: 
			result = isWhiteSpace(c) ? State.DELIMITER  : State.APPENDING;
			break;
		case APPENDING:
			result = isWhiteSpace(c) ? State.READY 		: State.APPENDING;
			break;
		case READY:
			result = isWhiteSpace(c) ? State.DELIMITER  : State.APPENDING;
			break;
		default:
			throw new IllegalStateException();
		}
		return result;
		
	}
	
	protected State gotoNextStateOnEof(State aState)
	{
		return State.DELIMITER;
	}
	
	
}
