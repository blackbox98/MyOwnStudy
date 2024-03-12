import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int answer;
    static List<Integer>[] list;
    static boolean[] v;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int tc = 0; tc < T; tc++) {
            int N = Integer.parseInt(br.readLine());
            list = new List[N + 1];
            for (int i = 1; i <= N; i++) {
                list[i] = new ArrayList<>();
            }
            for (int i = 0; i < N - 1; i++) {
                st = new StringTokenizer(br.readLine());
                int A = Integer.parseInt(st.nextToken());
                int B = Integer.parseInt(st.nextToken());
                list[B].add(A);
            }
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            answer = 0;
            v = new boolean[N + 1];
            dfs(n1);
            dfs(n2);
            sb.append(answer).append("\n");
        }
        System.out.println(sb);
    }

    private static void dfs(int cur) {
        if (v[cur]) {
            answer = cur;
            return;
        }
        v[cur] = true;
        for (int next : list[cur]) {
            dfs(next);
        }
    }
}