import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static List<Integer>[] edge;
    static boolean[] v;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int answer = 0;
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        edge = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            edge[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            edge[start].add(end);
            edge[end].add(start);
        }
        v = new boolean[N + 1];
        for (int n = 1; n <= N; n++) {
            if (!v[n]) {
                dfs(n);
                answer++;
            }
        }
        System.out.println(answer);
    }

    private static void dfs(int n) {
        v[n] = true;
        for (int i = 0; i < edge[n].size(); i++) {
            int num = edge[n].get(i);
            if (!v[num]) dfs(num);
        }
    }
}