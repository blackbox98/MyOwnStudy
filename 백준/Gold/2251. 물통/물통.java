import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] bottle;
    static List<Integer> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        bottle = new int[]{A, B, C};
        list = new ArrayList<>();
        bfs();
        Collections.sort(list);
        StringBuilder sb = new StringBuilder();
        for (int n : list) {
            sb.append(n).append(" ");
        }
        System.out.println(sb);
    }

    private static void bfs() {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0, bottle[2]});
        boolean[][][] v = new boolean[bottle[0] + 1][bottle[1] + 1][bottle[2] + 1];
        int A, B, C;
        while (!queue.isEmpty()) {
            int[] water = queue.poll();
            A = water[0];
            B = water[1];
            C = water[2];
            if (v[A][B][C]) continue;
            v[A][B][C] = true;
            if (A == 0) list.add(C);
            // A -> B
            if (A + B < bottle[1]) queue.offer(new int[]{0, A + B, C});
            else queue.offer(new int[]{A - (bottle[1] - B), bottle[1], C});
            // A -> C
            if (A + C < bottle[2]) queue.offer(new int[]{0, B, A + C});
            else queue.offer(new int[]{A - (bottle[2] - C), B, bottle[2]});
            // B -> A
            if (B + A < bottle[0]) queue.offer(new int[]{B + A, 0, C});
            else queue.offer(new int[]{bottle[0], B - (bottle[0] - A), C});
            // B -> C
            if (B + C < bottle[2]) queue.offer(new int[]{A, 0, B + C});
            else queue.offer(new int[]{A, B - (bottle[2] - C), bottle[2]});
            // C -> A
            if (C + A < bottle[0]) queue.offer(new int[]{C + A, B, 0});
            else queue.offer(new int[]{bottle[0], B, C - (bottle[0] - A)});
            // C -> B
            if (C + B < bottle[1]) queue.offer(new int[]{A, C + B, 0});
            else queue.offer(new int[]{A, bottle[1], C - (bottle[1] - B)});
        }
    }
}