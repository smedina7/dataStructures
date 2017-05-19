package lab06;



public class DisjointSet {
	private int comparisons;
	
	public int[] initSets(int n){
		int[] S = new int[n];
		
		for(int i = 0; i < n; i++){
			S[i] = -1;
		}
		return S;
	}
	
	public int getComparisons() {
		return comparisons;
	}
	public void setComparisons(int comparisons) {
		this.comparisons = comparisons;
	}
	

	public int getNumberOfSets(int[] S){
		int count = 0;
		
		for (int i = 0; i < S.length; i++)
			if (S[i] < 0 )
				count++;
		
		return count;
	}

	public int find(int[] S, int x){
		if(S[x] < 0){
			return x;
		}
		comparisons++;
		return find(S, S[x]);
	}

	//find with path compression
	public int comFind(int[] S, int x){
		comparisons++;
		if(S[x]< 0){
			return x;
		}
		return S[x] = find(S, S[x]);
	}

	//Standard Union
	public void StdUnion(int[] S, int a, int b){
		int r1 = find(S, a);
		int r2 = find(S, b);

		if(r1==r2){
			return;
		}
		if(r1!=r2){
			S[r2] = r1;
		}
	}

	//Standard Union with path compression
	public void comUnion(int[] S, int a, int b){
		int r1 = comFind(S, a);
		int r2 = comFind(S, a);

		if(r1==r2){
			return;
		}
		if(r1!=r2){
			S[r2] = r1;
		}
	}
	
	//Union by height
	public void unionByHeight(int[] S, int a, int b){
		int r1 = find(S, a);
		int r2 = find(S, b);

		if(r1==r2){
			return;
		}
		if(S[r1] == S[r2]){
			S[r1]--;
			S[r2] = r1;
		}
		else if(S[r1]<S[r2]){
			S[r2] = r1;
		}
		else {
			S[r1] = r2;
		}
	}
	
	//Union by Size
	public void unionBySize(int[] S, int a, int b){
		int r1 = find(S, a);
		int r2 = find(S, b);

		if(r1==r2){
			return;
		}
		if(S[r1] <= S[r2]){
			S[r1] = S[r1] + S[r2];
			S[r2] = r1;
		}
		else {
			S[r2] = S[r1] + S[r2];
			S[r1] = r2;
		}
		
	}

}
