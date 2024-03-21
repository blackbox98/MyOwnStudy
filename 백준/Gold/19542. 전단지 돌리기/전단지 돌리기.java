import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int answer, S, D;
    static List<Integer>[] list;
    static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        list = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }
        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            list[s].add(e);
            list[e].add(s);
        }
        answer = 0;
        dist = new int[N + 1];
        dfs(S, -1);
        System.out.println(answer * 2);
    }

    private static int dfs(int cur, int parent) {
        for (int next : list[cur]) {
            if (next == parent) continue;
            dist[cur] = Math.max(dist[cur], dfs(next, cur) + 1);
        }
        if (cur != S && dist[cur] >= D) answer++;
        return dist[cur];
    }
}