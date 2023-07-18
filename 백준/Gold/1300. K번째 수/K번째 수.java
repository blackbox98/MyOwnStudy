import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());
        int left = 1;
        int right = k;
        int cnt;
        int mid;
        while (left < right) {
            cnt = 0;
            mid = (left + right) / 2;
            for (int i = 1; i <= N; i++) {
                cnt += Math.min(mid / i, N);
            }
            if (cnt < k) left = mid + 1;
            else right = mid;
        }
        System.out.println(left);
    }
}