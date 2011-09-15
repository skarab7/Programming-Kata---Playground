import java.util.*;

/**
* The aim of this exercise is to provide a function which generate all the subsets of a given set:
* Example: 
*   x = {a, b,c} -> {a,b,c}, {a,b}, {b,c}, {a,c}, {a}, {b}, {c}, {}
*      R:         combn(x,3)      combn(x,2)       combn(x,1)   combn(x,0)     
*/
class SubsetGen {


  public static void main(String[] args)
  {
	char[] mySet = new char[] {'a', 'b', 'c'};

	SubsetGen subsetGen = new SubsetGen();
	List<char[]> subsets = subsetGen.getSets(mySet);
	for(char[] set : subsets )
	{
		printSet(set);
	}
	// R: choose (3,1 ) + choose(3,2) + choose(3,3) + empty set 
	assertEquals(6 + 2, subsets.size()); 

	char[] mySecSet = new char[] {'x', 'y', 'z', 'v'};
	List<char[]> secSubset = subsetGen.getSets(mySecSet);
	for(char[] set: secSubset)
	{
		printSet(set);
	}	
	// R: choose(4,1) + choose(4,2) + choose(4,3) + choose(4,4) + empty set	
	assertEquals(15 + 1, secSubset.size());

  }

  private static void assertEquals(int aExpected, int aActual)
  {	
	if(aExpected != aActual) throw new RuntimeException("Expected: " + aExpected + " but actual value was: " + aActual );
  }
 
  private static void printSet(char[] aSet)
 {
	StringBuilder sb = new StringBuilder("(");
	for(int i =0; i< aSet.length; i++)
	{
		if(i!=0)
		{	
			sb.append(",");
		}
		sb.append(aSet[i]);
	}
	sb.append(")");
	System.out.println(sb.toString() );
 }
  
  private List<char[]> getSets(char[] array)
  {
	List<char[]> results = new ArrayList<char[]>();
	if(array.length ==  0) { results.add(new char[0]); return results; }
	
	results.add(array); //  <- a, b, c, d
	
	char curElem = array[0]; // <- a 

	char[] arrCopy = new char[array.length-1];


	System.arraycopy(array, 1, arrCopy, 0, array.length - 1 );

	List<char[]> lowResults = getSets(arrCopy);
	results.addAll( lowResults );

	for(char[] lowRes : lowResults)
	{
		if(lowRes.length != arrCopy.length )
		{	
			char[] newRes = new char[lowRes.length + 1];
			newRes[0] = curElem;
			System.arraycopy(lowRes, 0, newRes, 1, lowRes.length);
			results.add ( newRes );
		}
	}
	return results;
   }	   

}
