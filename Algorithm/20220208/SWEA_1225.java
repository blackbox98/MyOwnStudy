package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_1225 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("InOutFiles/SWEA_1225_input.txt")));
		Queue<Integer> queue;
		int T;
		for (int tc = 0; tc < 10; tc++) {
			queue = new ArrayDeque<Integer>();
			T = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 8; i++) {
				queue.offer(Integer.parseInt(st.nextToken()));
			}
			int cnt = 1;
			int tmp = 0;
			while (true) {
				tmp = queue.poll() - cnt;
				if (tmp <= 0) {
					tmp = 0;
					queue.offer(tmp);
					break;
				}
				queue.offer(tmp);
				cnt++;
				if (cnt > 5) {
					cnt = 1;
				}
			}
			System.out.print("#" + T + " ");
			for (Integer n : queue) {
				System.out.print(n + " ");
			}
			System.out.println();
		}
	}
}