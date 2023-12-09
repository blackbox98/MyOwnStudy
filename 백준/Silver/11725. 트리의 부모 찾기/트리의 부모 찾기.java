import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] answer;
    static int N;
    static List<Integer>[] adjList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        adjList = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }
        StringTokenizer st;
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            adjList[s].add(e);
            adjList[e].add(s);
        }
        answer = new int[N + 1];
        findParent();
        StringBuilder sb = new StringBuilder();
        for (int i = 2; i <= N; i++) {
            sb.append(answer[i]).append("\n");
        }
        System.out.println(sb);
    }

    private static void findParent() {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        boolean[] v = new boolean[N + 1];
        v[1] = true;
        while (!queue.isEmpty()) {
            int parent = queue.poll();
            for (int child : adjList[parent]) {
                if (v[child]) continue;
                answer[child] = parent;
                queue.offer(child);
                v[child] = true;
            }
        }
    }
}