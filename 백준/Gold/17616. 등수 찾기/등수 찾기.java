import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        List<Integer>[] higherList = new List[N + 1];
        List<Integer>[] lowerList = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            higherList[i] = new ArrayList<>();
            lowerList[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int high = Integer.parseInt(st.nextToken());
            int low = Integer.parseInt(st.nextToken());
            higherList[low].add(high);
            lowerList[high].add(low);
        }
        System.out.println(bfs(higherList, N, X) + " " + (N + 1 - bfs(lowerList, N, X)));
    }

    private static int bfs(List<Integer>[] list, int N, int X) {
        int cnt = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(X);
        boolean[] v = new boolean[N + 1];
        v[X] = true;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            cnt++;
            for (int next : list[cur]) {
                if (v[next]) continue;
                queue.offer(next);
                v[next] = true;
            }
        }
        return cnt;
    }
}