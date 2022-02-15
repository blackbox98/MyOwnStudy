package BaekJoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BaekJoon_1931 {

	static class Meeting implements Comparable<Meeting> {
		int start;
		int end;

		public Meeting(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Meeting o) {
			return this.end != o.end ? this.end - o.end : this.start - o.start;
		}
	}

	static int Ans = 0;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("InOutFiles/BaekJoon_1931_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		Meeting[] meetings = new Meeting[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			meetings[i] = new Meeting(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		Arrays.sort(meetings);
		Ans++; // 첫 회의는 기본으로 포함
		
		int idx = 0; // 비교를 시작할 인덱스
		for (int i = 1; i < meetings.length; i++) { // 첫 회의는 이미 포함되어 있으므로 2번째(index : 1) 회의부터 비교 시작
			if (meetings[idx].end <= meetings[i].start) {
				idx = i;
				Ans++;
			}
		}
		System.out.println(Ans);
	}
}