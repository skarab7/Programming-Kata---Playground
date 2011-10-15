package pl.wbarczynski.kata.bloom_filter;

import java.security.MessageDigest;
import java.util.BitSet;

public class BloomFilter  {
	
	private final BitSet filter;
	private final int k;
	private final MessageDigest digest;
	private final int n;
	
	private int instCount;
	private final double maxFalsePositive;
	private final int m;
	

	public BloomFilter(int m, int k, int n, MessageDigest digest, double aMaxFalsePositive) {
		
		this.filter = new BitSet(m);
		this.m = m;
		this.k = k;
		this.digest = digest;
		this.n = n;
		this.instCount = 0;
		this.maxFalsePositive = aMaxFalsePositive;
	}
	
	public void add(String aElem)
	{
		if(instCount+1 > n) {
			if(maxFalsePositive < Math.pow( (1 - Math.pow(Math.E, -k * ( instCount + 1  + 0.5) / (m - 1) )), k))
			{
				throw new MaxPostRateViolationException("Can not add one more element, because max false positive rate: " + maxFalsePositive + " : reached by bloom filter");
			}
		}
		
		for(int i=0; i< k; i++)
		{
			int pos = getPos(aElem, (byte)i);	
			filter.set(Math.abs(pos % m), true);
		}
		
		
		instCount++;
	}
	
	private int getPos(String aValue, byte aSalt)
	{	
		digest.reset();
		digest.update(aSalt);
		byte[] hash = digest.digest(aValue.getBytes());
		 
		int result = 0x00;
		int myInt;
		for(int i=0; i < hash.length; i++)
		{
			if( i % 4 == 0)
			{
				myInt =	asInt( hash[i], hash[i+1], hash[i+2], hash[i+3] );	
				result = result + myInt; 
			}
		}
		return result;
	}		

	protected int asInt(byte... bytes)
	{
		int result = 0x00;
		for(int i=0; i < bytes.length; i++)
		{
			result<<=8;
			result |=  0xFF & bytes[i]; 
		}			
		return result;	
	}

	public boolean contains(String aElem)
	{
		for(int i=0;  i < k; i++)
		{
			int hash = getPos( aElem, (byte) i);
			if( !filter.get( Math.abs(hash % m)) ) {
				return false;
			}
		}			
		return true;
	}
	
	public int size()
	{
		return instCount;
	}
	
	public int getSizeLimit()
	{
		return n;
	}
}
