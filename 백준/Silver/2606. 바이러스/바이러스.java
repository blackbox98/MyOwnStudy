import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int answer;
    static List<Integer>[] edge;
    static boolean[] v;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        answer = 0;
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        edge = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            edge[i] = new ArrayList<>();
        }
        StringTokenizer st;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            edge[start].add(end);
            edge[end].add(start);
        }
        v = new boolean[N + 1];
        dfs(1);
        System.out.println(answer);
    }

    private static void dfs(int start) {
        v[start] = true;
        for (int i = 0; i < edge[start].size(); i++) {
            int num = edge[start].get(i);
            if (!v[num]) {
                answer++;
                dfs(num);
            }
        }
    }
}