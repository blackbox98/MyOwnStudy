package hw_20220413;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SWEA_4013 {

	static int K, ans;
	static ArrayList<Integer>[] magnet;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("InOutFiles/SWEA_4013_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		for (int tc = 1; tc <= T; tc++) {
			K = Integer.parseInt(br.readLine());
			magnet = new ArrayList[4];
			for (int i = 0; i < 4; i++) {
				magnet[i] = new ArrayList<>();
				String[] s = br.readLine().split(" ");
				for (int j = 0; j < 8; j++) {
					magnet[i].add(Integer.parseInt(s[j]));
				}
			}
			for (int k = 0; k < K; k++) {
				st = new StringTokenizer(br.readLine());
				int num = Integer.parseInt(st.nextToken()) - 1;
				int dir = Integer.parseInt(st.nextToken());
				play(num, dir);
			}
			ans = 0;
			for (int i = 0; i < 4; i++) {
				if (magnet[i].get(0) == 1) {
					ans |= 1 << i;
				}
			}
			System.out.printf("#%d %d\n", tc, ans);
		}
	}

	private static void play(int num, int dir) {
		turn(num, dir);
		int d = dir;
		if (num > 0) {
			for (int i = num; i > 0; i--) {
				if (d == 1) {
					if (magnet[i].get(7) != magnet[i - 1].get(2)) {
						turn(i - 1, d * -1);
						d *= -1;
					} else {
						break;
					}
				} else {
					if (magnet[i].get(5) != magnet[i - 1].get(2)) {
						turn(i - 1, d * -1);
						d *= -1;
					} else {
						break;
					}
				}
			}
		}
		d = dir;
		if (num < 3) {
			for (int i = num; i < 3; i++) {
				if (d == 1) {
					if (magnet[i].get(3) != magnet[i + 1].get(6)) {
						turn(i + 1, d * -1);
						d *= -1;
					} else {
						break;
					}
				} else {
					if (magnet[i].get(1) != magnet[i + 1].get(6)) {
						turn(i + 1, d * -1);
						d *= -1;
					} else {
						break;
					}
				}
			}
		}
	}

	private static void turn(int num, int dir) {
		if (dir == 1) {
			int temp = magnet[num].get(magnet[num].size() - 1);
			magnet[num].remove(magnet[num].size() - 1);
			magnet[num].add(0, temp);
		} else {
			int temp = magnet[num].get(0);
			magnet[num].remove(0);
			magnet[num].add(temp);
		}
	}
}