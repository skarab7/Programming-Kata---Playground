import java.util.*;

class Kata2 
{

	private static List<Object[]> getTestInput()
	{
		final List<Object[]> testInput = new ArrayList<Object[]>();		
		testInput.add(new Object[] {-1, 3, new int[]{}  });
		testInput.add(new Object[] {-1, 3, new int[]{1} });
		testInput.add(new Object[] {-1, 3, new int[]{1} });
		testInput.add(new Object[] { 0, 1, new int[]{1} });
		testInput.add(new Object[] { 0, 1, new int[]{1, 3, 5}});
		testInput.add(new Object[] { 0, 1, new int[]{1, 3, 5}});
		testInput.add(new Object[] {-1, 3, new int[]{1, 4, 5}});		
		testInput.add(new Object[] { 2, 5, new int[]{1, 3, 5}});
		testInput.add(new Object[] {-1, 0, new int[]{1, 3, 5}});
		testInput.add(new Object[] {-1, 2, new int[]{1, 3, 5}});
		testInput.add(new Object[] {-1, 4, new int[]{1, 3, 5}});
		testInput.add(new Object[] {-1, 6, new int[]{1, 3, 5}});
		testInput.add(new Object[] { 0, 1, new int[]{1, 3, 5, 7}});
		testInput.add(new Object[] { 1, 3, new int[]{1, 3, 5, 7}});
		testInput.add(new Object[] { 2, 5, new int[]{1, 3, 5, 7}});
		testInput.add(new Object[] { 3, 7, new int[]{1, 3, 5, 7}});
		testInput.add(new Object[] {-1, 0, new int[]{1, 3, 5, 7}});
		testInput.add(new Object[] {-1, 2, new int[]{1, 3, 5, 7}});
		testInput.add(new Object[] {-1, 4, new int[]{1, 3, 5, 7}});
		testInput.add(new Object[] {-1, 6, new int[]{1, 3, 5, 7}});
		testInput.add(new Object[] {-1, 8, new int[]{1, 3, 5, 7}});	
		return testInput;
	}
	
	public static void main(String[] args)
	{
		List<Object[]> testInput = getTestInput();		
		int i=0;
		int expected;
		int num2find;
		int[] implRes = new int[2];
		for(Object[] uc : testInput)
		{		
			expected = (Integer) uc[0];
			num2find  = (Integer) uc[1];
			
			implRes[0] =  chop ( (Integer) num2find, (int[]) uc[2] );
			implRes[1] =  chop2( (Integer) num2find, (int[]) uc[2] );
			
			for(int implNum = 0; implNum < implRes.length; implNum++)
			{
				if(expected != implRes[implNum])
				{	
					String errMsg = "Impl "+ implNum+ " failed for test " + i+ ": expected " + expected + " actual : " + implRes[implNum];
					System.out.println(errMsg);

					throw new RuntimeException(errMsg);
				}
			}
			i++;
		}


	}



	public static int chop(int aInt, int[] array)
	{
		int result = -1;
		for(int i=0; i<array.length; i++)
		{
			if(aInt == array[i])
			{
				result = i;
			}
		}
		return result;
	}
	

	public static int chop2(final int aInt, final int[] array)
	{
		if(array.length == 0) {return -1;}
		
		int splitPos = array.length / 2;
		// Mistake 2: Forgot about the case, when array.size=0
		int size     = array.length / 2;
		int result   = -1;
		
		// Mistake 3: invalid while condition
		int prevSize = -1;
		while (prevSize != 0 || size != prevSize)
		{		
			System.out.println ("sp: " + splitPos + " s: " +size );
			prevSize = size;
			
			if( array[splitPos] < aInt )
			{
				splitPos = splitPos + (size/2 == 0 ? 1 : size/2);
				//  Mistake 4: splitPos must be bounded by the size of array
				if(splitPos >= array.length) { splitPos = array.length -1;}
				size = size/2;	
				
			}
			else if (array[splitPos] > aInt )
			{
				splitPos = splitPos - (size/2 == 0 ? 1 : size/2);
				if(splitPos < 0) { splitPos = 0;} 
				size = size/2;
			}
			else
	 		{
				result = splitPos;
				// Mistake 1: I forgot to add <i>break</i>
				break;
			}
		}
		return result;
	}		

}
