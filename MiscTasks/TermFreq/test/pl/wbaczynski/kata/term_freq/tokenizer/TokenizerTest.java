package pl.wbaczynski.kata.term_freq.tokenizer;

import java.util.Iterator;

import junit.framework.Assert;

import org.junit.Test;

import pl.wbaczynski.kata.tokenizer.Tokenizer;

/**
 * @author wpjtek@yahoo.com
 */
public class TokenizerTest {
	
	public static final String TEST_TEXT_A = "The fat cat ate the rat for dinner. Then the cat took a nap  .";
	public static final String[] EXPECTED = new String[] {"The", "fat", "cat", "ate", "the", "rat", "for", "dinner", "Then", "the", "cat", "took", "a", "nap"};
	

	@Test
	public void testUseCaseText()
	{
		Iterator<String> tokens = Tokenizer.parseText(TEST_TEXT_A);
		
		String token;
		int idx = 0;
		while(tokens.hasNext())
		{
			token = tokens.next();	
			Assert.assertEquals(EXPECTED[idx], token );			
			idx++;
		}
		

	}
	
}
