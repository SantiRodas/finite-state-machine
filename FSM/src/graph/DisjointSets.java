package graph;

public class DisjointSets {
	
	private int[] arr;
	
	public DisjointSets(int n) {
		arr = new int[n];
		
		for (int i = 0; i < arr.length; i++) {
			arr[i] = i;
		}
	}
	
	public int findSet(int i) {
		if(arr[i] == i) {
			return i;
		}else {
			arr[i] = findSet(arr[i]);
			return arr[i];
		}
	}
	
	public boolean isSame(int i, int j) {
		return findSet(i) == findSet(j);
	}
	
	public void join(int i, int j) {
		if(!isSame(i, j)) {
			int x = findSet(i);
			int y = findSet(j);
			
			arr[x] = y;
		}
	}

}