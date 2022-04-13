package hw_20220413;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BaekJoon_20055 {

	static int N, K, ans;
	static boolean flag = true;
	static ArrayList<Integer> belt, robots;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("InOutFiles/BaekJoon_20055_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		belt = new ArrayList<>();
		robots = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 2 * N; i++) {
			belt.add(Integer.parseInt(st.nextToken()));
		}
		while (flag) {
			ans++;
			turn();
			move();
			put();
			count();
		}
		System.out.println(ans);
	}

	private static void turn() {
		if (robots.size() != 0) {
			for (int i = robots.size() - 1; i >= 0; i--) {
				if (robots.get(i) + 1 < N) {
					robots.set(i, robots.get(i) + 1);
				} else {
					robots.remove(i);
				}
			}
		}
		int tmp = belt.get(N * 2 - 1);
		belt.remove(N * 2 - 1);
		belt.add(0, tmp);
	}

	private static void move() {
		if (robots.size() != 0) {
			for (int i = robots.size() - 1; i >= 0; i--) {
				if (robots.size() != 0 && robots.get(i) == N - 1) {
					robots.remove(i);
					continue;
				}
				if (robots.size() != 0 && !robots.contains(robots.get(i) + 1) && robots.get(i) + 1 < N && belt.get(robots.get(i) + 1) > 0) {
					belt.set(robots.get(i) + 1, belt.get(robots.get(i) + 1) - 1);
					robots.set(i, robots.get(i) + 1);
				}
			}
		}
	}

	private static void put() {
		if (belt.get(0) > 0) {
			robots.add(0, 0);
			belt.set(0, belt.get(0) - 1);
		}
	}

	private static void count() {
		int cnt = 0;
		for (int i = 0; i < belt.size(); i++) {
			if (belt.get(i) == 0) {
				cnt++;
			}
		}
		if (cnt >= K) {
			flag = false;
		}
	}
}