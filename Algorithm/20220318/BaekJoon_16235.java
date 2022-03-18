package BaekJoon;
/*
 * 시간초과 -> 수정 필요
 */
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BaekJoon_16235 {

	static int N, M, K;
	static int[][] A, map;
	static ArrayList<Tree> trees, death;
	// 0 1 2
	// 7   3
	// 6 5 4
	static int[] dr = { -1, -1, -1, 0, 1, 1, 1, 0 };
	static int[] dc = { -1, 0, 1, 1, 1, 0, -1, -1 };
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("InOutFiles/BaekJoon_16235_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		trees = new ArrayList<Tree>();
		death = new ArrayList<Tree>();
		map = new int[N + 1][N + 1];
		A = new int[N + 1][N + 1];
		for (int r = 1; r <= N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 1; c <= N; c++) {
				A[r][c] = Integer.parseInt(st.nextToken());
				map[r][c] = 5;
			}
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int age = Integer.parseInt(st.nextToken());
			trees.add(new Tree(r, c, age));
		}
		for (int i = 0; i < K; i++) {
			Collections.sort(trees);
			oneYear();
		}
		System.out.println(trees.size());
	}

	private static void oneYear() {
		// 봄
		for (int idx = 0; idx < trees.size(); idx++) {
			Tree t = trees.get(idx);
			if(map[t.r][t.c] >= t.age) { // 양분을 먹을 수 있는 경우
				map[t.r][t.c] -= t.age;
				t.age++;
			} else { // 양분이 부족하여 먹지 못하는 경우
				death.add(t);
				trees.remove(idx);
				idx--;
			}
		}
		
		// 여름
		for (int idx = 0; idx < death.size(); idx++) {
			Tree t = death.get(idx);
			death.remove(idx);
			idx--;
			map[t.r][t.c] += t.age / 2;
		}
		
		// 가을
		for (int idx = 0; idx < trees.size(); idx++) {
			Tree t = trees.get(idx);
			if (t.age % 5 == 0) { // 번식 가능
				for (int d = 0; d < dr.length; d++) {
					int nr = t.r + dr[d];
					int nc = t.c + dc[d];
					if (nr > 0 && nr <= N && nc > 0 && nc <= N) {
						trees.add(new Tree(nr, nc, 1));
					}
				}
			}
		}
		// 겨울
		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= N; c++) {
				map[r][c] += A[r][c];
			}
		}
	}

	static class Tree implements Comparable<Tree>{
		int r, c, age;

		public Tree(int r, int c, int age) {
			this.r = r;
			this.c = c;
			this.age = age;
		}

		@Override
		public int compareTo(Tree o) {
			return Integer.compare(this.age, o.age);
		}
	}
}