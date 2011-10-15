package pl.wbarczynski.kata.bloom_filter;

import org.junit.Test;

public class BloomFilterBuilderTest {
	
	@Test(expected=MaxPostRateViolationException.class)
	public void testCheckMaxPos()
	{
		final BloomFilterBuilder bfBuilder = new BloomFilterBuilder();
		
		bfBuilder.setFilterSize(3);
		bfBuilder.setHashAlgorithm("MD5");
		bfBuilder.setInputNumber(10000);
		bfBuilder.setMaxFalsePositive(0.01f);
		bfBuilder.setNumOfHashes(10);
		bfBuilder.build();
	}

	@Test
	public void testCheckMaxPos2()
	{
		final BloomFilterBuilder bfBuilder = new BloomFilterBuilder();
		bfBuilder.setFilterSize(900000);
		bfBuilder.setHashAlgorithm("MD5");
		bfBuilder.setInputNumber(9000);
		bfBuilder.setMaxFalsePositive(0.01f);
		bfBuilder.setNumOfHashes(10);
		bfBuilder.build();
	}
}
