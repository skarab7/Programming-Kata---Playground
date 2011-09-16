import java.util.*;
import java.lang.Math;
import java.util.Collections;

public class KMeans {
	
	public static void main(String[] args)
	{
		int[][] points = { {1,4}, {4,5}, {10,15}, {10,15}, {30,40}, {100,40}, {-10,20}, {49,50}, {-30,40} };

		KMeans km = new KMeans();
		km.setPoints(points); 
		km.setCentroids(4);
		km.setMaxIteration(40);
		km.run();
		Set<Cluster> claster = km.getClusters();
	
	}

	private	int[][] points;
	private int numOfCent;
	private int[][] centroids;	
	private int[][] oldCentroids;
	private Map<Integer, Set<Integer> > cents2points;	
	private int maxIter;

	public void setMaxIteration(int aValue)
	{
		maxIter = aValue;
	}

	public KMeans()
	{
		cents2points = new HashMap<Integer, Set<Integer> >();
	}
	
	public void setPoints(int[][] aPoints)
	{
		points = aPoints;
	}

	public void setCentroids(int aNumOfCent)
	{
		numOfCent = aNumOfCent;
	}

	public Set<Cluster> getClusters()
	{
		return null;
	}

	private void randCentroids()
	{	
		if(numOfCent > points.length) { throw new RuntimeException("Cent num: " + numOfCent + " is greater than number of points: " + points.length); }	
		centroids = new int[numOfCent][2];
		
		Set<Integer> wasAlready = new HashSet<Integer>(); 
		int pIdx;
		for(int i=0; i < numOfCent; i++)
		{
			pIdx =  (int) ( Math.random()  * (  points.length ) );
			System.out.println("" + pIdx);
			if( wasAlready.contains(pIdx) )
			{
				i--;
			}
			else {
				wasAlready.add(pIdx);
				centroids[i] = points[pIdx];
				System.out.println(i + " : " + array2String( points[pIdx] ) );
			}
		}
	}
	
	private static String array2String(int[] aArr)
	{
		StringBuilder sb = new StringBuilder("[");
		boolean isFirst = true;
		for(int i=0; i<aArr.length; i++)
		{
			if(isFirst)
			{
				isFirst = false;
			}
			else {
				sb.append(",");

			}	
			sb.append(aArr[i]);
		}
		sb.append("]");
		return sb.toString() ;
	} 	

	public void run()
	{	
		randCentroids();
		
		for(int iter=0; iter < maxIter; iter++) {
			System.out.println("=== Iteration: " + iter + " ===");
			Map<Integer, Set<CentDistance> > points2CentDist = new HashMap<Integer, Set<CentDistance> >();
			for(int centIdx=0; centIdx < centroids.length; centIdx++)
			{
				for(int pIdx = 0; pIdx < points.length; pIdx++)
				{
					double distance = getDistance( centroids[centIdx], points[pIdx] );	
					Set<CentDistance> pds = points2CentDist.get(pIdx);

					if(pds==null) {
						pds = new HashSet<CentDistance>(); 
						points2CentDist.put(pIdx, pds);
					}
					pds.add(new CentDistance(centIdx, distance));
				}						
			}
	
			assignPoints2Centroids(points2CentDist);
			calculateNewCentroids();
			boolean isStable = areCentroidsStable();		
			if(isStable) {System.out.println("Stable!"); break;}
		}
	}	

	private void assignPoints2Centroids(Map<Integer, Set<CentDistance> > aPoints2Cent)
	{
		cents2points.clear();
		CentDistance centDistance;
		for(Map.Entry<Integer, Set<CentDistance>> entry :  aPoints2Cent.entrySet())
		{
			centDistance = Collections.min( entry.getValue(), comparator);
			Set<Integer> points =  cents2points.get(centDistance.centIdx);		
			if(points == null)
			{
				points = new HashSet<Integer>();
				cents2points.put(centDistance.centIdx, points);
			}
			points.add( entry.getKey() );	
		} 
	}


	// sets would be better
	private boolean areCentroidsStable()
	{
		for(int i=0; i < oldCentroids.length; i++)  
		{
			for(int j=0; j<2; j++)
			{
				if(centroids[i][j] != oldCentroids[i][j])
				{
					return false;
				}
			}
		}
		return true;
	}

	private void calculateNewCentroids()
	{
		oldCentroids = new int[centroids.length][2];
		System.arraycopy(centroids, 0, oldCentroids, 0, centroids.length);

		for(Map.Entry<Integer, Set<Integer> > entry : cents2points.entrySet() )	
		{	
			int centx = 0;
			int centy = 0;
			StringBuilder sb = new StringBuilder("{");
			for(Integer pIdx : entry.getValue() )
			{
				sb.append(array2String(points[pIdx]));
				sb.append(",");
				centx += points[pIdx][0];
				centy += points[pIdx][1];	
			}
			sb.append("}");
			centx = centx / entry.getValue().size();
			centy = centy / entry.getValue().size();
			
			int[] newCent =  new int[] {centx, centy}; 	
			System.out.println ( " Centroid " + entry.getKey() + " old : " + array2String(centroids[entry.getKey() ] ) + " new: " + array2String(newCent)  + " points: " + sb.toString() ) ; 	
			centroids [entry.getKey() ]  = newCent;
		}
	} 
	

	
	public double getDistance(int[] aCent, int[] aPoint)
	{
		return Math.pow(aCent[0] - aPoint[0], 2.0) + Math.pow(aCent[1] - aPoint[1], 2);

	}

	
	private Comparator<CentDistance> comparator =  new Comparator<CentDistance>() {
			public int compare(CentDistance aDist1, CentDistance aDist2)
			{
				if(aDist1.distance > aDist2.distance) return 1;
				else if(aDist1.distance == aDist2.distance) return 0;	
				return -1;
		
			}
		};	
 
	private class CentDistance {
		public int centIdx;
		public double distance;

		public CentDistance(int aCentIdx, double aDistance)
		{
			centIdx = aCentIdx;
			distance = aDistance;
			
		}		

		

	}

	private interface Cluster 
	{


	}
}
