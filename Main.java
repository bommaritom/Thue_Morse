import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args){
		System.out.print(
				"This program allows you to visualize the Thue-Morse \n"
				+ "sequence and understand how it compares to a \n"
				+ "random sequence.  Enter \"FAIR\" \n"
				+ "to visualize the fair (Thue-Morse) sequence, or enter \n"
				+ "\"UNFAIR\" to see a random, unfair sequence. \n"
				+ "Enter \"QUIT\" to quit. \n"
				);
		prompt();
	}
		
	public static void prompt(){
		
		Scanner scanner = new Scanner(System.in);
		String input = (String)scanner.next();
		
		if ( input.equals("FAIR") ){
			fair();
		}else if ( input.equals("UNFAIR")){
			unfair();
		}else if ( input.equals("QUIT")){
			Runtime runtime = Runtime.getRuntime();
			scanner.close();
			runtime.halt(1);
		}else{
			System.out.println("You have to choose \"FAIR\" or \"UNFAIR\", or enter \"QUIT\" to quit.\n>");
			prompt();
		}
	}
	
	public static void fair(){
		ArrayList<Integer> fairSequence = generateFairSequence(1000);
		visualize(fairSequence);
		prompt();
	}
	
	public static void unfair(){
		ArrayList<Integer> unfairSequence = generateUnfairSequence(1000);
		visualize(unfairSequence);
		prompt();
	}
	
	public static ArrayList<Integer> generateFairSequence(int k){
		int p = (int)Math.round(Math.log(k)/Math.log(2));
		ArrayList<Integer> fairSequence = new ArrayList<Integer>();
		
		fairSequence.add(0);
		for ( int i = 0; i < p; i++ ){
			ArrayList<Integer> temp = opposite(fairSequence);
			fairSequence.addAll(temp);
		}
		return new ArrayList<Integer>(fairSequence.subList(0, k));
	}
	
	public static ArrayList<Integer> generateUnfairSequence(int k){
		ArrayList<Integer> unfairSequence = new ArrayList<Integer>();
		
		for ( int i = 0; i < k; i++ ){
			int r = (int) Math.round(Math.random());
			unfairSequence.add(r);
		}
		return unfairSequence;
	}
	
	public static ArrayList<Integer> opposite(ArrayList<Integer> sequence){
		ArrayList<Integer> opposite = new ArrayList<Integer>();
		for ( int i : sequence ){
			if ( i == 0 ){
				opposite.add(1);
			}
			if ( i == 1 ){
				opposite.add(0);
			}
		}
		return opposite;
	}
	
	public static void visualize(ArrayList<Integer> sequence){
		ArrayList<Integer> points = toPlot(sequence);
		
		String space = " ";
		for ( int i = 0; i < points.size()-1; i++){
			String line = Integer.toString(sequence.get(i));
			int a = points.get(i);
			int b = points.get(i+1);
			if ( a > b ){
				for ( int s = 0; s < b; s++ ){
					line = line + space;
				}
				line = line + "/";
			}
			if ( a < b ){
				for ( int s = 0; s < a; s++ ){
					line = line + space;
				}
				line = line + "\\";
			}
			if ( a == b ){
				for ( int s = 0; s < a; s++ ){
					line = line + space;
				}
				line = line + "|";
			}
			System.out.println(line);
		}
	}
	
	public static ArrayList<Integer> toPlot(ArrayList<Integer> sequence){
		ArrayList<Integer> points = new ArrayList<Integer>();
		int cur = 50;
		
		points.add(cur);
		for ( int i : sequence ){
			if ( i == 0 ){
				cur--;
			}
			if ( i == 1 ){
				cur++;
			}
			points.add(cur);
		}
		return points;
	}
}
