package davestack;

import java.lang.*;
import java.util.concurrent.TimeUnit;




public class GameThread extends Thread {

	public boolean[][] grid;
	public boolean drop;
	public int score;
	public boolean gameOver;
	public int correct;

	GameThread(int length, int width){
		this.grid = new boolean[length][width];
		this.drop = false;
		this.score = 0;
		this.gameOver = false;
		this.correct = 0;
	}



	public void printGrid(){
		System.out.print("\033\143");
		System.out.println("Score: $" + this.score);
		System.out.println("Correct: " + this.correct);
		System.out.println("Press enter to drop block: ");
		int target = this.grid[0].length / 2;
		int colIndex = 0;
		int rowIndex= 0;
		this.score = 0;
		this.correct = 0;		
		for(boolean[] col: this.grid){
			for(boolean gridItem: col){
				colIndex++;
				if(gridItem){
					if(colIndex == target && rowIndex != 0){
			            System.out.print("[]");						
						this.score++;						this.correct++;
						if(this.correct >= this.grid.length - 1){
							this.gameOver = true;						
						}					
					}
					else if(rowIndex != 0) {
						System.out.print("XX");
						this.score--;					
					}
					else{
						System.out.print("[]");					
					}
				}
				else if(colIndex == target){
					System.out.print("$$");
				} else {
					System.out.print("::");
				}
			}
			System.out.println();
			colIndex = 0;
			rowIndex++;
		}
	}

	public void gravity(){
		for(int col = 0; col < this.grid[0].length; col++){
			for(int row = this.grid.length - 1; row > 0; row--){
				if(this.grid[row][col] && row < this.grid.length - 1 && System.nanoTime() % 100 == 0){
					if(!this.grid[row + 1][col]){
						this.grid[row][col] = false;
						this.grid[row + 1][col] = true;
					}
				
				}			
			}		
		}
	}

	public void loopBlock(){
		while(!this.gameOver){
			long x = System.nanoTime() / 100000000;
			if(System.nanoTime() % 100000 == 0){
				this.printGrid();
				this.gravity();
				int sec = Math.abs((int) x % this.grid[0].length);
				int prev = (sec - 1);
                        	if(prev < 0){
					prev += this.grid[0].length;			
				}
				this.grid[0][sec] = true;
				this.grid[0][prev] = false;
				if(this.drop){
					this.grid[1][sec] = true;
					this.drop = false;				
				}
				if(this.gameOver){
					System.out.println("-Game Over-");
					System.out.println("Final score: $" + this.score);
					System.out.println("-Press Enter to quit-");
					return;
				}

			}

		}
	}



	public void run(){
		this.loopBlock();
	}
	


}
