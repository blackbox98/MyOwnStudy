package SWEA;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class SWEA_1228 {

	public static void main(String[] args) throws IOException{
		System.setIn(new FileInputStream("InOutFiles/SWEA_1228_input.txt"));
		Scanner sc = new Scanner(System.in);
		LinkedList<Integer> myList;
		int N;
		int comN;
		String s;
		int x, y;
		for (int tc = 1; tc <= 10; tc++) {
			myList = new LinkedList<Integer>();
			N = sc.nextInt();
			for (int i = 0; i < N; i++) {
				myList.add(sc.nextInt());
			}
			comN = sc.nextInt();
			for (int j = 0; j < comN; j++) {
				s = sc.next();
				x = sc.nextInt();
				y = sc.nextInt();
				for (int k = 0; k < y; k++) {
					myList.add(x + k, sc.nextInt());
				}
			}
			System.out.printf("#%d ", tc);
			for (int i = 0; i < 10; i++) {
				System.out.print(myList.get(i) + " ");
			}
			System.out.println();
		}
	}
}