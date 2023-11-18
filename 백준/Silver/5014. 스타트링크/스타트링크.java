import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int F, S, G, U, D;
    
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        F = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        U = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        System.out.println(bfs());
    }
    
    private static String bfs() {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(S);
        int[] v = new int[F + 1];
        Arrays.fill(v, -1);
        v[S] = 0;
        while (queue.size() > 0) {
            int cur = queue.poll();
            if (cur == G) {
                return String.valueOf(v[cur]);
            }
            int next = cur + U;
            if (next <= F && v[next] == -1) {
                queue.offer(next);
                v[next] = v[cur] + 1;
            }
            next = cur - D;
            if (next >= 1 && v[next] == -1) {
                queue.offer(next);
                v[next] = v[cur] + 1;
            }
        }
        return "use the stairs";
    }
}