// Non dynamic programming based solution
//Interview question at 621  

/* 

Problem statement: Given a grid ( with r rows and c cols) find the number 
of unique paths to go from a given cell to cell r-1, c-1

*/

public class CountPaths {

	private int row;
	private int col;
	
	//row and col are the total number of rows and cols in the gird
	public CountPaths(int row, int col) {
	
		this.row = row;
		this.col = col;
	
	
	}
	
	//r and c are the starting locations from where the number of all possible paths need to be counted
	public int countPaths(int r, int c) {
	
		if(r == ( this.row -1 ) && c == (this.col -1 ) )
			return 0;

		else if ( ( r == this.row - 1 ) || ( c == this.col -1 )	)

			return 1;		
		
		else 
			return countPaths(r, c+1) + countPaths(r+1, c); 			
	
	}
	
	public static void main(String[] args) {
	
		CountPaths cp = new CountPaths(3,3);

		System.out.println(cp.countPaths(1,1));
	}



}
