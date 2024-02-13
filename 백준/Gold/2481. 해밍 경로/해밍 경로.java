import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, K;
    static int[] parent;
    static Map<Integer, Integer> map;
    static StringBuilder sb;
    static Stack<Integer> stack;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        parent = new int[N + 1];
        map = new HashMap<>();
        int start = Integer.parseInt(br.readLine(), 2);
        map.put(start, 1);
        for (int idx = 2; idx <= N; idx++) {
            map.put(Integer.parseInt(br.readLine(), 2), idx);
        }
        getParent(start);
        StringBuilder answer = new StringBuilder();
        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            answer.append(getPath(Integer.parseInt(br.readLine()))).append("\n");
        }
        System.out.println(answer);
    }

    private static void getParent(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            int curIdx = map.get(cur);
            for (int i = 0; i < K; i++) {
                int next = cur ^ (1 << i);
                if (start == next) continue;
                if (map.containsKey(next) && parent[map.get(next)] == 0) {
                    parent[map.get(next)] = curIdx;
                    queue.offer(next);
                }
            }
        }
    }

    private static String getPath(int num) {
        if (parent[num] == 0) return -1 + " ";
        sb = new StringBuilder();
        stack = new Stack<>();
        while (parent[num] > 0) {
            stack.push(num);
            num = parent[num];
        }
        sb.append(1).append(" ");
        while (!stack.isEmpty()) sb.append(stack.pop()).append(" ");
        return sb.toString();
    }
}