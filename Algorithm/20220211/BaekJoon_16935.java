package BaekJoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_16935 {

	static int T, N, M, R;
	static int[][] arr;
	static int[][] newArr;
	static int[][] result;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("InOutFiles/BaekJoon_16935_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			arr = new int[N][M];
			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < M; c++) {
					arr[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			st = new StringTokenizer(br.readLine());
			result = arr;
			System.out.println("#" + tc);
			for (int i = 0; i < R; i++) {
				int op = Integer.parseInt(st.nextToken());
				rotation(op, result);

			}
			for (int r = 0; r < result.length; r++) {
				for (int c = 0; c < result[r].length; c++) {
					System.out.print(result[r][c] + " ");
				}
				System.out.println();
			}
		}
	}

	private static void rotation(int op, int[][] rotArr) {
		switch (op) {
		case 1:
			newArr = new int[N][M];
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					newArr[r][c] = rotArr[N - r - 1][c];
				}
			}
			break;
		case 2:
			newArr = new int[N][M];
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					newArr[r][c] = rotArr[r][M - c - 1];
				}
			}
			break;
		case 3:
			newArr = new int[M][N];
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					newArr[c][r] = rotArr[N - r - 1][c];
				}
			}
			break;
		case 4:
			newArr = new int[M][N];
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					newArr[c][r] = rotArr[r][M - c - 1];
				}
			}
			break;
		case 5:
			newArr = new int[N][M];
			for (int r = 0; r < N / 2; r++) {
				for (int c = 0; c < M / 2; c++) {
					newArr[r][c] = rotArr[r + N / 2][c];
				}
			}
			for (int r = 0; r < N / 2; r++) {
				for (int c = M / 2; c < M; c++) {
					newArr[r][c] = rotArr[r][c - M / 2];
				}
			}
			for (int r = N / 2; r < N; r++) {
				for (int c = M / 2; c < M; c++) {
					newArr[r][c] = rotArr[r - N / 2][c];
				}
			}
			for (int r = N / 2; r < N; r++) {
				for (int c = 0; c < M / 2; c++) {
					newArr[r][c] = rotArr[r][c + M / 2];
				}
			}
			break;
		case 6:
			newArr = new int[N][M];
			for (int r = 0; r < N / 2; r++) {
				for (int c = 0; c < M / 2; c++) {
					newArr[r][c] = rotArr[r][M / 2 + c];
				}
			}
			for (int r = 0; r < N / 2; r++) {
				for (int c = M / 2; c < M; c++) {
					newArr[r][c] = rotArr[N / 2 + r][c];
				}
			}
			for (int r = N / 2; r < N; r++) {
				for (int c = M / 2; c < M; c++) {
					newArr[r][c] = rotArr[r][c - M / 2];
				}
			}
			for (int r = N / 2; r < N; r++) {
				for (int c = 0; c < M / 2; c++) {
					newArr[r][c] = rotArr[r - N / 2][c];
				}
			}
			break;
		}
		result = newArr;
		N = result.length;
		M = result[0].length;
	}
}