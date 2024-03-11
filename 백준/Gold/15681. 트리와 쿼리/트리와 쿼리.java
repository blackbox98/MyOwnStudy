import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static List<Integer>[] list;
    static int[] subTrees;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        list = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int U = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());
            list[U].add(V);
            list[V].add(U);
        }
        subTrees = new int[N + 1];
        getSubTree(R);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            int U = Integer.parseInt(br.readLine());
            sb.append(subTrees[U]).append("\n");
        }
        System.out.println(sb);
    }

    private static int getSubTree(int cur) {
        subTrees[cur] = 1;
        for (int next : list[cur]) {
            if (subTrees[next] > 0) continue;
            subTrees[cur] += getSubTree(next);
        }
        return subTrees[cur];
    }
}