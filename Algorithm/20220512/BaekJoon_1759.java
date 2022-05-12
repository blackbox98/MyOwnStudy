package BaekJoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class BaekJoon_1759 {

	static int L, C;
	static String[] alpha;
	static ArrayList<String> cons, result;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("InOutFiles/BaekJoon_1759_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		cons = new ArrayList<String>();
		result = new ArrayList<String>();
		alpha = br.readLine().split(" ");
		for (int i = 0; i < alpha.length; i++) {
			switch (alpha[i]) {
			case "a":
			case "e":
			case "i":
			case "o":
			case "u":
				break;
			default:
				cons.add(alpha[i]);
				break;
			}
		}
		dfs(new String[L], new boolean[C], 0, 0);
		Collections.sort(result);
		for (int i = 0; i < result.size(); i++) {
			System.out.println(result.get(i));
		}
	}
	
	private static void dfs(String[] sel, boolean[] v, int idx, int k) {
		if (idx == v.length) {
			if (k == L) {
				String str = "";
				for (int i = 0; i < v.length; i++) {
					if (v[i]) {
						str += alpha[i];
					}
				}
				if (checkV(str) && checkC(str)) {
					result.add(set(str));
				}
			}
			return;
		}

		v[idx] = true;
		dfs(sel, v, idx + 1, k + 1);
		v[idx] = false;
		dfs(sel, v, idx + 1, k);
	}

	private static boolean checkV(String str) {
		if (str.contains("a") || str.contains("e") || str.contains("i") || str.contains("o") || str.contains("u")) {
			return true;
		}
		return false;
	}
	
	private static boolean checkC(String str) {
		int cnt = 0;
		for (int i = 0; i < cons.size(); i++) {
			if(str.contains(cons.get(i))) {
				cnt++;
			}
		}
		if (cnt >= 2) {
			return true;
		}
		return false;
	}

	private static String set(String res) {
		char[] arr = res.toCharArray();
		Arrays.sort(arr);
		return new String(arr);
	}
}