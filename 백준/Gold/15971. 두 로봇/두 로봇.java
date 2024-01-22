import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int R2, answer;
    static List<int[]>[] list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int R1 = Integer.parseInt(st.nextToken());
        R2 = Integer.parseInt(st.nextToken());
        list = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }
        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            list[s].add(new int[]{e, w});
            list[e].add(new int[]{s, w});
        }
        answer = 0;
        boolean[] v = new boolean[N + 1];
        v[R1] = true;
        dfs(v, R1, 0, 0);
        System.out.println(answer);
    }

    private static void dfs(boolean[] v, int cur, int totalDist, int maxDist) {
        if (answer != 0) return;
        if (cur == R2) answer = totalDist - maxDist;
        else {
            for (int[] next : list[cur]) {
                if (v[next[0]]) continue;
                v[next[0]] = true;
                dfs(v, next[0], totalDist + next[1], Math.max(maxDist, next[1]));
            }
        }
    }
}