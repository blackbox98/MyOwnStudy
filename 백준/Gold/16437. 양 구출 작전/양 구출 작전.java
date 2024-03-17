import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static long[] islandInfo;
    static List<Integer>[] list;
    static boolean[] v;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        islandInfo = new long[N + 1];
        list = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }
        StringTokenizer st;
        for (int i = 2; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            String type = st.nextToken();
            int a = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            list[i].add(p);
            list[p].add(i);
            islandInfo[i] = type.equals("S") ? a : -a;
        }
        v = new boolean[N + 1];
        dfs(1, 0);
        System.out.println(islandInfo[1]);
    }

    private static void dfs(int cur, int parent) {
        v[cur] = true;
        for (int next : list[cur]) {
            if (v[next]) continue;
            dfs(next, cur);
        }
        if (parent != 0 && islandInfo[cur] > 0) islandInfo[parent] += islandInfo[cur];
    }
}