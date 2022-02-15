package Jungol;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Jungol_1828 {

	static class Chemical implements Comparable<Chemical> {
		int low;
		int high;
		public Chemical(int low, int high) {
			this.low = low;
			this.high = high;
		}
		
		@Override
		public int compareTo(Chemical o) {
			return this.high != o.high ? this.high - o.high : this.low - o.low;
		}
	}
	
	static int Ans = 0;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("InOutFiles/Jungol_1828_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Chemical[] chemi = new Chemical[N];
		StringTokenizer st;
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			chemi[n] = new Chemical(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		Arrays.sort(chemi);
		Ans++;
		
		int idx = 0;
		for (int i = 1; i < chemi.length; i++) {
			if (chemi[idx].high < chemi[i].low) {
				idx = i;
				Ans++;
			}
		}
		System.out.println(Ans);
	}
}