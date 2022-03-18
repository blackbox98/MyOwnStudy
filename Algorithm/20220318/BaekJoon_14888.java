package BaekJoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BaekJoon_14888 {

	static int N, min, max;
	static ArrayList<Integer> numList;
	static ArrayList<Character> opList;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("InOutFiles/BaekJoon_14888_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		min = Integer.MAX_VALUE;
		max = Integer.MIN_VALUE;
		N = Integer.parseInt(br.readLine());
		numList = new ArrayList<>();
		opList = new ArrayList<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			numList.add(Integer.parseInt(st.nextToken()));
		}
		st = new StringTokenizer(br.readLine());
		int opSum = 0;
		for (int i = 0; i < 4; i++) {
			int n = Integer.parseInt(st.nextToken());
			opSum += n;
			if (n == 0)
				continue;
			if (i == 0) {
				for (int j = 0; j < n; j++) {
					opList.add('+');
				}
			} else if (i == 1) {
				for (int j = 0; j < n; j++) {
					opList.add('-');
				}
			} else if (i == 2) {
				for (int j = 0; j < n; j++) {
					opList.add('*');
				}
			} else {
				for (int j = 0; j < n; j++) {
					opList.add('/');
				}
			}
		}

		dfs(new char[opSum], new boolean[opSum], 0);

		System.out.println(max);
		System.out.println(min);
	}

	private static void dfs(char[] sel, boolean[] v, int k) {
		if (k == sel.length) {
			calc(numList, sel);
			return;
		}

		for (int i = 0; i < v.length; i++) {
			if (!v[i]) {
				v[i] = true;
				sel[k] = opList.get(i);
				dfs(sel, v, k + 1);
				v[i] = false;
			}
		}
	}

	private static void calc(ArrayList<Integer> num, char[] sel) {
		int sum = num.get(0);
		for (int i = 0; i < sel.length; i++) {
			switch (sel[i]) {
			case '+':
				sum += num.get(i + 1);
				break;
			case '-':
				sum -= num.get(i + 1);
				break;
			case '*':
				sum *= num.get(i + 1);
				break;
			case '/':
				sum /= num.get(i + 1);
				break;
			}
		}
		min = Math.min(min, sum);
		max = Math.max(max, sum);
	}
}