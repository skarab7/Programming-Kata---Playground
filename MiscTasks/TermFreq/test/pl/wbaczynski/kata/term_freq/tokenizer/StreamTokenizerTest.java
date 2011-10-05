package pl.wbaczynski.kata.term_freq.tokenizer;

import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;

import junit.framework.Assert;

import org.junit.Test;

import pl.wbaczynski.kata.tokenizer.StreamTokenizer;

/**
 * @author wpjtek@yahoo.com
 */
public class StreamTokenizerTest {

	private static final String TEST_TEXT = TokenizerTest.TEST_TEXT_A;
	private static final String[] EXPECTED = TokenizerTest.EXPECTED;
	
	@Test
	public void testInput()
	{
		StreamTokenizer st = new StreamTokenizer();
		InputStreamReader isr = null;
		try {
			isr = new  InputStreamReader(new ByteArrayInputStream(TEST_TEXT.getBytes("UTF8")));
			Iterator<String> toks = st.parse( isr );
			String token;
			int idx = 0;
			while(toks.hasNext())
			{
				token = toks.next();
				System.out.println(token);
				Assert.assertEquals("", EXPECTED[idx], token);
				idx++;
			}
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			closeAttempt(isr);
		}
		
	}
	
	private void closeAttempt(Closeable aCloseable)
	{
		if(aCloseable != null)
		{
			try {
				aCloseable.close();
			} catch (IOException e) {
				// TODO logg
				e.printStackTrace();
			}
		}
	}
}
