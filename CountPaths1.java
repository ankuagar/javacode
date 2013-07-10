// Dynamic programming based solution ( faster than non dynamic programming based solution.
//Interview question at 621  

/* 

Problem statement: Given a grid ( with r rows and c cols) find the number 
of unique paths to go from a given cell to cell r-1, c-1

*/


public class CountPaths1 {

	private int[][] arr; 
	private int rows; 
	private int cols;

	//rows and cols are the number of rows and cols in the grid	
	
	public CountPaths1(int rows, int cols) {
			
		this.rows = rows;
		this.cols = cols;
		
		arr = new int[rows][cols];
		
		arr[rows-1][cols-1] = 0; // set the end point to 0
		
		if( rows == cols) {
			for(int i = 0 ; i < rows -1 ; i++) {
				arr[i][rows-1] = 1;
				arr[rows-1][i] = 1;
			}				
		}
		
		else {
		
			for(int i = 0 ; i < rows -1 ; i++) {
				arr[i][cols-1] = 1;
			}
			
			for(int i = 0 ; i < cols -1 ; i++) {
			
				arr[rows-1][i] = 1;	
			}				
		}				
	}
	
	public int countPaths(int r, int c) {
		
		if( (r == this.rows - 1) && (c == this.cols - 1) )
			return arr[r][c];

		else if ( r == this.rows - 1)
			return arr[r][c];

		else if ( c == this.cols -1 )
			return arr[r][c];			
	
	
		else {
		
		
			if( (arr[r+1][c] != 0)  && ( arr[r][c+1] != 0 ) )
				return arr[r+1][c] + arr[r][c+1];


			else {

				arr[r+1][c] = countPaths(r+1, c);
				arr[r][c+1] = countPaths(r,c+1);
				
				return (arr[r+1][c] + arr[r][c+1]) ;
			
			}				
		
		}	
	
	}
	
	public static void main(String[] args){

		CountPaths1 cp = new CountPaths1(10,10);

		long startTime = System.nanoTime();
		System.out.println(cp.countPaths(0,0));
		System.out.println(System.nanoTime() - startTime);	
		
	}

}
