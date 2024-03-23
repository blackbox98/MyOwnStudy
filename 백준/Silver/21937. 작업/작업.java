import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, M, answer;
    static List<Integer>[] list;
    static boolean[] v;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        list = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int e = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            list[s].add(e);
        }
        answer = 0;
        v = new boolean[N + 1];
        int X = Integer.parseInt(br.readLine());
        v[X] = true;
        dfs(X);
        System.out.println(answer);
    }

    private static void dfs(int cur) {
        for (int next : list[cur]) {
            if (v[next]) continue;
            v[next] = true;
            answer++;
            dfs(next);
        }
    }
}