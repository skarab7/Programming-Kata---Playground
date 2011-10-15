package pl.wbarczynski.kata.bloom_filter;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.BitSet;

public class BloomFilterBuilder {
	private int n;
	private int k;
	private double maxFalsePos;
	private int m;
	private BitSet valid;
	private MessageDigest digest;
	
	public BloomFilterBuilder()
	{
		valid = new BitSet(3);
	}
	
	public BloomFilterBuilder setInputNumber(int aN)
	{
		n = aN;
		valid.set(0, true);
		return this;
	}
	
	public BloomFilterBuilder setNumOfHashes(int aK)
	{
		k = aK;
		valid.set(1, true);
		return this;
	}
	
	public BloomFilterBuilder setFilterSize(int aM)
	{
		m = aM;
		valid.set(2, true);
		return this;
	}
	
	public BloomFilterBuilder setMaxFalsePositive(double aValue)
	{
		if(maxFalsePos > 1) {
			throw new RuntimeException();
		}
		maxFalsePos = aValue;
		return this;
	}
	
//	public BloomFilterBuilder computeM()
//	{
//		if(!(valid.get(0) && valid.get(1)))
//		{
//			throw new RuntimeException();
//		}
//		m = (int) Math.ceil( 1.44 * Math.pow(1/maxFalsePos, -2));
//		valid.set(2, true);
//		return this;
//	}
	
	/** Throws RuntimeException if an algorithm was not found. The method uses
	 * {@link MessageDigest#getInstance(String)}.
	 * 
	 * @param aName
	 * @return
	 */
	public BloomFilterBuilder setHashAlgorithm(String aName)
	{
		try {
			digest = MessageDigest.getInstance(aName);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
		valid.set(3, true);
		return this;
	}
	
	public BloomFilter build()
	{
		if(valid.cardinality() != 4)
		{
			throw new RuntimeException("Not all fields are set!");
		}
		
		double fp = getActualMaxFalsePos();
		if( fp > maxFalsePos)
		{
			throw new MaxPostRateViolationException("The actaul max fale positive value is " + fp + ". It is larger than  " + maxFalsePos);
		}
		return new BloomFilter(m, k, n, digest, maxFalsePos);
	}
	
	private double getActualMaxFalsePos()
	{
		return Math.pow( (1 - Math.pow(Math.E, -k * (n + 0.5) / (m - 1) )), k);
	}
}
