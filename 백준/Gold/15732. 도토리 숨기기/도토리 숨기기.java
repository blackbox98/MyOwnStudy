import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] rules;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());
        rules = new int[K][3];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            rules[i][0] = Integer.parseInt(st.nextToken());
            rules[i][1] = Integer.parseInt(st.nextToken());
            rules[i][2] = Integer.parseInt(st.nextToken());
        }
        long answer = Long.MAX_VALUE;
        int left = 0;
        int right = N;
        int mid;
        long cnt;
        while (left <= right) {
            mid = (left + right) / 2;
            cnt = countAcorns(mid);
            if (cnt < D) left = mid + 1;
            else {
                answer = Math.min(answer, mid);
                right = mid - 1;
            }
        }
        System.out.println(answer);
    }

    private static long countAcorns(int target) {
        long cnt = 0;
        for (int[] rule : rules) {
            if (target < rule[0]) continue;
            cnt += (Math.min(target, rule[1]) - rule[0]) / rule[2] + 1;
        }
        return cnt;
    }
}