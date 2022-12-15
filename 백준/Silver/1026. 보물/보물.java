import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int ans = 0;
        Integer[] A = new Integer[N];
        Integer[] B = new Integer[N];
        StringTokenizer st;
        for (int i = 0; i < 2; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                if (i == 0) {
                    A[j] = Integer.parseInt(st.nextToken());
                } else {
                    B[j] = Integer.parseInt(st.nextToken());
                }
            }
        }
        Arrays.sort(A);
        Arrays.sort(B, Comparator.reverseOrder());
        for (int i = 0; i < N; i++) {
            ans += A[i] * B[i];
        }
        System.out.println(ans);
    }
}