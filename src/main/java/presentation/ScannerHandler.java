package presentation;

import java.util.Scanner;



public class ScannerHandler {
	
	private Scanner scanner;
	
	public ScannerHandler() {
		// TODO Auto-generated constructor stub
		scanner = new Scanner(System.in);
	}
	
	public float getInputFloat() {
		float newFloat = scanner.nextFloat();
		scanner.nextLine();
		return newFloat;
	}
	
	public int getInputInt() {
		int newInt = scanner.nextInt();
		scanner.nextLine();
		return newInt;
	}
	
	public String getInputString() {
		String newString = scanner.nextLine();
		return newString;
	}
	
	public char getInputChar() {
		char newChar = scanner.nextLine().charAt(0);
		return newChar;
	}
	
	public void close() {
		scanner.close();
	}
}
