package pl.wbaczynski.kata.term_freq;

import java.net.URL;

import org.junit.Test;

public class TermFreqStreamTest {
	
	@Test
	public void run4Test()
	{
		final URL url = new TermFreq().getClass().getClassLoader().getResource("myTestFile.txt");
		TermFreqStream.main(new String[] {"cat", url.getPath()} );		
	}
}
