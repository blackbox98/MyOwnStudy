// 미완성
/*
2
1 4
2
3 2
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Algo2_대전_7_전병찬 {

	static int cnum, total;
	static String ans;
	static int[] list;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // BufferedReader를 사용하여 입력값 받기
		cnum = Integer.parseInt(br.readLine());
		total = 0;
		StringTokenizer st = new StringTokenizer(br.readLine()); // StringTokenizer를 사용하여 입력값을 공백을 기준으로 자르기
		list = new int[cnum];
		for (int i = 0; i < cnum; i++) {
			int n = Integer.parseInt(st.nextToken());
			list[i] = n;
			total += n;
		}
		Arrays.sort(list);
		int gnum = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine()); // StringTokenizer를 사용하여 입력값을 공백을 기준으로 자르기
		L : for (int i = 0; i < gnum; i++) {
			int target = Integer.parseInt(st.nextToken());
			if(target > total) {
				System.out.print("N ");
				continue;
			}
			for (int j = cnum - 1; j >= 0; j--) {
				if (target == list[j]) {
					System.out.print("Y ");
					continue L;
				}
			}
			ans = "N";
			dfs(new boolean[cnum], target, cnum - 1, 0);
			System.out.println(ans);
		}
	}
	
	private static void dfs(boolean[] v, int target, int idx, int sum) {
		if (target == sum) {
			ans = "Y";
			return;
		}
		
		for (int i = idx; i >= 0; i--) {
			if (!v[i] && target <= total && idx - 1 >= 0) {
				v[i] = true;
//				dfs(v, target + list[i], idx - 1, check(idx - 1));
				v[i] = false;
			}
		}
	}

//	private static int check(int i) {
//		if () {
//			
//		}
//		return 0;
//	}
}