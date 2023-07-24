import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] times;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        times = new int[M];
        int max = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            times[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, times[i]);
        }
        long answer = 0;
        if (N > M) {
            long t = binarySearch(N, M, 0, (N / M) * max);
            N -= M;
            for (int i = 0; i < M; i++) {
                N -= (t - 1) / times[i];
            }
            for (int i = 0; i < M; i++) {
                if (t % times[i] == 0) N--;
                if (N == 0) {
                    answer = i + 1;
                    break;
                }
            }
        } else answer = N;
        System.out.println(answer);
    }

    private static long binarySearch(long N, int M, long left, long right) {
        long mid, cnt;
        while (left <= right) {
            mid = (left + right) / 2;
            cnt = M;
            for (int i = 0; i < M; i++) {
                cnt += mid / times[i];
            }
            if (cnt < N) left = mid + 1;
            else right = mid - 1;
        }
        return left;
    }
}