package davestack;

import java.io.*;
import java.util.Scanner;
import davestack.GameThread;

public class daveStack{
	

	public static void main(String [] args){
		
		Scanner inScanner = new Scanner(System.in);
		GameThread game = new GameThread(20,10);
		game.start();
		while(!game.gameOver){
			inScanner.nextLine();
			game.drop = true;
		}
	}
}
