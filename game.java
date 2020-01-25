import java.awt.event.*;
import java.util.Scanner;
import java.awt.*;
import javax.swing.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import javax.swing.JFrame;
public class game extends JComponent implements KeyListener{
	static Scanner scanner= new Scanner(System.in);
	static Random random= new Random();
	static int rowNo1,rowNo2,columnNo1, columnNo2;
	static Object[][] array=null;
	static int[] fill= {2,2,2,2,2,2,2,2,4,4};
	static int nextMove=0;
	static String s;
	static int moveCount=0;

	public game() {
		setFocusable(true);//indicates whether this Component is focusable
		addKeyListener(this);//adds key listener to itself
	}


	public static void main(String[] args) {
		game game= new game();
		//to generate a matrix		
		JFrame fr= new JFrame();
		fr.setVisible(true);
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fr.add(game);
		array = new Object[4][4];
		
		start();
		//		array[0][0]=2;
		//		array[1][0]=2;
		//		array[2][0]=2;
		//		array[3][0]=2;
		System.out.println();
	}

	//to print the matrix
	public static void printArray(Object[][] array) {
		System.out.println("\t\t\tMoves:"+ moveCount);
		maximum();
		cls();
		
		for(int i=0; i<4; i++) {
			System.out.println("\t\t  ________________________");
			System.out.print("\t\t");
		
			for(int j=0; j<4; j++) {
				System.out.print("    " + array[i][j]+" ");
			}
	
			System.out.println();
		}
		System.out.println("\t\t  ________________________");
		cls();
		cls();
	}

	//starting game
	public static void start() {
		//trying to generate 2 and 4 in one of the places in the matrix
		System.out.println("\t\t\tNEW GAME");
		for (Object[] row  : array) 
			Arrays.fill(row,"*"); 
		rowNo1= random.nextInt(4);
		rowNo2= random.nextInt(4);
		columnNo1= random.nextInt(4);
		columnNo2= random.nextInt(4);
		if(rowNo1==rowNo2 && columnNo1==columnNo2 ) {
			start();
		}
		int No1=random.nextInt(10);
		int No2=random.nextInt(10);
		//System.out.println(rowNo1+"  "+ columnNo1+ " "+ rowNo2  +" "+  columnNo2);
		array[rowNo1][columnNo1] = fill[No1];
		array[rowNo2][columnNo2] = fill[No2];
		printArray(array);
	
	}

	public static void generate() {
		nextMove=0;
		rowNo1= random.nextInt(4);
		columnNo1= random.nextInt(4);

		if(array[rowNo1][columnNo1].equals("*")){
			int No1=random.nextInt(10);
			array[rowNo1][columnNo1] = fill[No1];
		}else {
			generate();
		}
	}

	public static void left() {
		int count=0;
		for (int i=0; i<4;i++) {
			for (int j=1; j<4; j++) {				
				if( !array[i][j].equals("*")) {
					if(array[i][j-1].equals("*")) {
						nextMove++;
						count++;
						array[i][j-1]=array[i][j];
						array[i][j]="*";
					}
				}	
			}
		}
		if(count>0) {
			left();
		}	
	}

	public static void right() {
		int count=0;
		for (int i=0; i<4;i++) {
			for (int j=2; j>=0; j--) {				
				if(!array[i][j].equals("*")) {
					if(array[i][j+1].equals("*")) {
						nextMove++;
						count++;
						array[i][j+1]=array[i][j];
						array[i][j]="*";
					}
				}	
			}
		}
		if(count>0) {
			right();
		}	
	}

	public static void up() {
		int count=0;
		for (int i=1; i<4;i++) {
			for (int j=0; j<4; j++) {				
				if( !array[i][j].equals("*")) {
					if(array[i-1][j].equals("*")) {
						nextMove++;
						count++;
						array[i-1][j]=array[i][j];
						array[i][j]="*";
					}
				}	
			}
		}
		if(count>0) {
			up();
		}	
	}

	public static void down() {
		int count=0;
		for (int i=2; i>=0;i--) {
			for (int j=0; j<4; j++) {				
				if( !array[i][j].equals("*")) {
					if(array[i+1][j].equals("*")) {
						nextMove++;
						count++;
						array[i+1][j]=array[i][j];
						array[i][j]="*";
					}
				}	
			}
		}
		if(count>0) {
			down();
		}	
	}

	public static void sumL() {
		System.out.println("SUMMING LEFT");
		for (int i=0; i<4;i++) {
			for (int j=1; j<4; j++) {
				if( !array[i][j].equals("*")) {
					if(array[i][j].equals(array[i][j-1])) {
						nextMove++;
						int x= (int)array[i][j];
						array[i][j-1]=2*x;
						array[i][j]="*";	
					}
				}
			}
		}
		left();
		if(nextMove>0) {
			generate();
			moveCount++;
			
		}else {
			System.out.println("No move!");
		}
		printArray(array);
	}

	public static void sumR() {
		System.out.println("SUMMING RIGHT ");
		for (int i=0; i<4;i++) {
			for (int j=2; j>=0; j--) {	
				if( !array[i][j].equals("*")) {

					if(array[i][j].equals(array[i][j+1])) {
						nextMove++;
						int x= (int)array[i][j];
						array[i][j+1]=2*x;
						array[i][j]="*";	
					}
				}
			}
		}
		right();
		if(nextMove>0) {
			generate();
			moveCount++;
		}else {
			System.out.println("No move!");
		}
		printArray(array);
	}

	public static void sumU() {
		System.out.println("SUMMING UP");
		for (int i=1; i<4;i++) {
			for (int j=0; j<4; j++) {	
				if( !array[i][j].equals("*")) {
					if(array[i][j].equals(array[i-1][j])) {
						nextMove++;
						int x= (int)array[i][j];
						array[i-1][j]=2*x;
						array[i][j]="*";	
					}
				}
			}
		}
		up();
		if(nextMove>0) {
			generate();
			moveCount++;
		}else {
			System.out.println("No move!");
		}
		printArray(array);
	}

	public static void sumD() {
		System.out.println("SUMMING DOWN");
		for (int i=2; i>=0;i--) {
			for (int j=0; j<4; j++) {		
				if( !array[i][j].equals("*")) {
					if(array[i][j].equals(array[i+1][j])) {
						nextMove++;
						int x= (int)array[i][j];
						array[i+1][j]=2*x;
						array[i][j]="*";	
					}
				}
			}
		}
		down();
		if(nextMove>0) {
			generate();
			moveCount++;
		}else {
			System.out.println("No move!");
		}
		printArray(array);
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		char c= arg0.getKeyChar();	
		s= String.valueOf(c); //parses character into string type
		System.out.println(s);
		if(s.equals("w")) {
			
			up();
			sumU();
		}
		if(s.equals("s")) {
			down();
			sumD();
		}
		
		if(s.equals("a")) {
			left();
			sumL();
		}
		
		if(s.equals("d")) {
			right();
			sumR();
		}
		
		if(s.equals("r")) {
			restart();	
		}
		
		if(s.equals("q")) {
			quit();	
		}
		
	
	}
	
	public static void restart() {
		System.out.println("Do you want to restart(Y/N)?");
		String input = scanner.next();
		if (input.equals("y")||input.equals("Y")) {
			start();
		}else {
			printArray(array);
		}
		
		
	}
	public static void quit() {
		System.out.println("Do you want to quit (Y/N)?");
		String input = scanner.next();
		if (input.equals("y")||input.equals("Y")) {
			cls();
			System.out.println(" Thanks for Playing");
			cls();
		}else {
			printArray(array);
		}
		
	}
	
	public static void maximum() {
		int Max=2;
		int temp;
		for(int i=0; i<4;i++) {
			for(int j=0; j<4; j++) {
				
				if(!array[i][j].equals("*")) {
					temp=(int)array[i][j];
					if(temp>Max)
						Max=temp;
					
				}
			}
		}
		System.out.println("\t\t   Max # on board: "+Max);
	
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public static void cls() {
		System.out.println("\n\n\n\n\n");
	}
	
}
