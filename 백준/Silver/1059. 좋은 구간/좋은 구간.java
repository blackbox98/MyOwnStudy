import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int ans, n;
    static int[] target, result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int L = Integer.parseInt(br.readLine());
        int[] S = new int[L];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < L; i++) {
            S[i] = Integer.parseInt(st.nextToken());
        }
        n = Integer.parseInt(br.readLine());
        Arrays.sort(S);
        ans = 0;
        if (1 <= n && 2 < S[0] && n < S[0]) {
            target = new int[S[0] - 1];
            for (int i = 0; i < target.length; i++) {
                target[i] = i + 1;
            }
            result = new int[2];
            combination(0, 0);
        } else {
            for (int i = 1; i < L; i++) {
                if (S[i] - S[i - 1] > 2 && S[i - 1] < n && n < S[i]) {
                    target = new int[S[i] - S[i - 1] - 1];
                    for (int j = 0; j < target.length; j++) {
                        target[j] = S[i - 1] + j + 1;
                    }
                    result = new int[2];
                    combination(0, 0);
                }
            }
        }
        System.out.println(ans);
    }

    private static void combination(int cnt, int idx) {
        if (cnt == 2) {
            if (result[0] <= n && n <= result[1]) ans++;
            return;
        }

        for (int i = idx; i < target.length; i++) {
            result[cnt] = target[i];
            combination(cnt + 1, i + 1);
        }
    }
}