import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static final int limit = 100001;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        System.out.println(bfs(N, K));
    }
    
    private static int bfs(int N, int K) {
        int result = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(N);
        int[] v = new int[limit];
        v[N] = 1;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            if (cur == K) {
                result = v[cur] - 1;
                break;
            }
            int next = cur - 1;
            if (next >= 0 && v[next] == 0) {
                queue.offer(next);
                v[next] = v[cur] + 1;
            }
            next = cur + 1;
            if (next < limit && v[next] == 0) {
                queue.offer(next);
                v[next] = v[cur] + 1;
            }
            next = 2 * cur;
            if (next < limit && v[next] == 0) {
                queue.offer(next);
                v[next] = v[cur] + 1;
            }
        }
        return result;
    }
}