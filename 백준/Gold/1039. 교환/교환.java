import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, K, M, answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        String s = st.nextToken();
        N = Integer.parseInt(s);
        K = Integer.parseInt(st.nextToken());
        M = s.length() - 1;
        answer = -1;
        bfs();
        System.out.println(answer);
    }

    private static void bfs() {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(N);
        int[] v = new int[1000001];
        int size, cur, next;
        while (!queue.isEmpty() && K > 0) {
            size = queue.size();
            for (int n = 0; n < size; n++) {
                cur = queue.poll();
                for (int i = 0; i < M; i++) {
                    for (int j = i + 1; j <= M; j++) {
                        next = swap(cur, i, j);
                        if (next == -1 || v[next] == K) continue;
                        queue.offer(next);
                        v[next] = K;
                    }
                }
            }
            K--;
        }
        if (!queue.isEmpty()) {
            while (!queue.isEmpty()) answer = Math.max(answer, queue.poll());
        }
    }

    private static int swap(int cur, int i, int j) {
        char[] arr = (cur + "").toCharArray();
        if (i == 0 && arr[j] == '0') return -1;
        char tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
        return Integer.parseInt(new String(arr));
    }
}