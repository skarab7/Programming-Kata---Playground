package pl.wbarczynski.kata.bloom_filter;

public class MaxPostRateViolationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2664183194656775569L;
		
	public MaxPostRateViolationException(String aMsg)
	{
		super(aMsg);
	}
	
	public MaxPostRateViolationException()
	{
		super();
	}
}
