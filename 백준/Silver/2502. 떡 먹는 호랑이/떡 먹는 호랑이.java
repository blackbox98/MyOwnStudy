import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static boolean flag;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int D = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        flag = false;
        int[] riceCakes = new int[D + 1];
        for (int i = K / 2; !flag && i >= 1; i--) {
            riceCakes[D - 1] = K - i;
            riceCakes[D - 2] = i;
            dfs(riceCakes, D - 1);
        }
        dfs(riceCakes, D);
    }

    private static void dfs(int[] riceCakes, int day) {
        if (flag) return;
        if (day == 2) {
            if (riceCakes[1] > 0 && riceCakes[1] < riceCakes[2]) {
                flag = true;
                System.out.printf("%d\n%d\n", riceCakes[1], riceCakes[2]);
            }
            return;
        }
        riceCakes[day - 2] = riceCakes[day] - riceCakes[day - 1];
        if (riceCakes[day - 1] <= riceCakes[day - 2] || riceCakes[day - 2] < 0) return;
        dfs(riceCakes, day - 1);
    }
}