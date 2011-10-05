package pl.wbaczynski.kata.term_freq;

import java.io.FileNotFoundException;
import java.net.URL;

import junit.framework.Assert;

import org.junit.Test;

import pl.wbaczynski.kata.term_freq.TermFreq;
import pl.wbaczynski.kata.term_freq.tokenizer.TokenizerTest;

/** 
 * @author wpjtek@yahoo.com
 */
public class TermFreqTest extends TermFreq{
	
	private static final String TEST_TEXT = TokenizerTest.TEST_TEXT_A;
	
	@Test
	public void testForTokenCat()
	{
		TermFreq tf = new TermFreq();
		tf.setTerm("cat");
		tf.process(TEST_TEXT);
		Assert.assertEquals(2, tf.getFreq());		
		
	}
	
	@Test
	public void testReadingFile() throws FileNotFoundException
	{		
		final URL url = new TermFreq().getClass().getClassLoader().getResource("myTestFile.txt");		
		TermFreq.readFromFile(url.getPath());		
	}
	

}
