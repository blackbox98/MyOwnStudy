package BaekJoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BaekJoon_14891 {

	static ArrayList<Integer>[] TList, tmpList;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("InOutFiles/BaekJoon_14891_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		TList = new ArrayList[4];
		tmpList = new ArrayList[4];
		for (int n = 0; n < 4; n++) {
			TList[n] = new ArrayList<>();
			String str = br.readLine();
			for (int t = 0; t < 8; t++) {
				TList[n].add(str.charAt(t) - '0');
			}
		}

		arrCopy1();

		StringTokenizer st = null;
		int K = Integer.parseInt(br.readLine());
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int turn = Integer.parseInt(st.nextToken()) - 1;
			int dir = Integer.parseInt(st.nextToken());
			turningSide(turn, dir);
			turningSelf(turn, dir);
		}
		int ans = 0;
		for (int i = 0; i < 4; i++) {
			if (TList[i].get(0) == 1) {
				ans += Math.pow(2, i);
			}
		}
		System.out.println(ans);
	}

	private static void arrCopy1() {
		for (int i = 0; i < 4; i++) {
			tmpList[i] = (ArrayList<Integer>) TList[i].clone();
		}
	}

	private static void arrCopy2() {
		for (int i = 0; i < 4; i++) {
			TList[i] = (ArrayList<Integer>) tmpList[i].clone();
		}
	}

	private static void turningSide(int turn, int dir) {
		int d = dir;
		for (int i = turn; i > 0; i--) {
			if ((TList[i - 1].get(2) != TList[i].get(6))) {
				if (d == 1) {
					tmpList[i - 1].add(tmpList[i - 1].get(0));
					tmpList[i - 1].remove(0);
				} else {
					tmpList[i - 1].add(0, tmpList[i - 1].get(tmpList[i - 1].size() - 1));
					tmpList[i - 1].remove(tmpList[i - 1].size() - 1);
				}
				d *= -1;
			} else {
				break;
			}
		}

		for (int i = turn; i < 3; i++) {
			if ((TList[i + 1].get(6) != TList[i].get(2))) {
				if (dir == 1) {
					tmpList[i + 1].add(tmpList[i + 1].get(0));
					tmpList[i + 1].remove(0);
				} else {
					tmpList[i + 1].add(0, tmpList[i + 1].get(tmpList[i + 1].size() - 1));
					tmpList[i + 1].remove(tmpList[i + 1].size() - 1);
				}
				dir *= -1;
			} else {
				break;
			}
		}
		arrCopy2();
	}

	private static void turningSelf(int turn, int dir) {
		if (dir == 1) {
			tmpList[turn].add(0, tmpList[turn].get(tmpList[turn].size() - 1));
			tmpList[turn].remove(tmpList[turn].size() - 1);
		} else {
			tmpList[turn].add(tmpList[turn].get(0));
			tmpList[turn].remove(0);
		}
		arrCopy2();
	}
}