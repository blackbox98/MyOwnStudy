import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] beads;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        beads = new int[N];
        st = new StringTokenizer(br.readLine());
        int left = 0;
        int right = 0;
        for (int i = 0; i < N; i++) {
            beads[i] = Integer.parseInt(st.nextToken());
            left = Math.max(left, beads[i]);
            right += beads[i];
        }
        StringBuilder answer = new StringBuilder();
        int mid;
        while (left < right) {
            mid = (left + right) / 2;
            if (division(mid)) left = mid + 1;
            else right = mid;
        }
        answer.append(left).append("\n");
        int cnt = 0;
        int sum = 0;
        for (int i = 0; i < N; i++) {
            sum += beads[i];
            if (sum > left) {
                answer.append(cnt).append(" ");
                cnt = 1;
                sum = beads[i];
                M--;
            } else cnt++;
            if (M == N - i) break;
        }
        if (M-- > 0) answer.append(cnt).append(" ");
        while (M-- > 0) answer.append(1).append(" ");
        answer.deleteCharAt(answer.length() - 1);
        System.out.println(answer);
    }

    private static boolean division(int mid) {
        int cnt = 1;
        int sum = 0;
        for (int i = 0; i < N; i++) {
            sum += beads[i];
            if (sum > mid) {
                cnt++;
                sum = beads[i];
            }
        }
        return cnt > M;
    }
}