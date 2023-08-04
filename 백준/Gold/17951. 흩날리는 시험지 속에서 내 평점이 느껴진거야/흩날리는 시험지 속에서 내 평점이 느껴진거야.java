import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] score = new int[N];
        int right = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            score[i] = Integer.parseInt(st.nextToken());
            right += score[i];
        }
        if (K == 1) {
            System.out.println(right);
            return;
        }
        int left = 0;
        int mid, sum, cnt, min;
        while (left <= right) {
            mid = (left + right) / 2;
            min = Integer.MAX_VALUE;
            sum = 0;
            cnt = 1;
            for (int i = 0; i < N; i++) {
                sum += score[i];
                if (sum >= mid) {
                    min = Math.min(min, sum);
                    sum = 0;
                    cnt++;
                }
            }
            if (cnt > K) left = mid + 1;
            else right = mid - 1;
        }
        System.out.println(right);
    }
}