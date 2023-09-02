import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int sum;
    static Queue<int[]> queue;
    static boolean[][] v;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int answer = 0;
        sum = A + B + C;
        if (sum % 3 == 0) answer = bfs(A, B, C);
        System.out.println(answer);
    }

    private static int bfs(int A, int B, int C) {
        queue = new LinkedList<>();
        queue.offer(new int[]{A, B, C});
        v = new boolean[sum + 1][sum + 1];
        v[A][B] = true;
        while (!queue.isEmpty()) {
            int[] stone = queue.poll();
            int a = stone[0];
            int b = stone[1];
            int c = stone[2];
            if (a == b && b == c) return 1;
            if (a != b) moveStone(a, b, 1);
            if (a != c) moveStone(a, c, 2);
            if (b != c) moveStone(b, c, 3);
        }
        return 0;
    }

    private static void moveStone(int s1, int s2, int type) {
        int X = s1 < s2 ? s1 + s1 : s1 - s2;
        int Y = s1 < s2 ? s2 - s1 : s2 + s2;
        if (!v[X][Y]) {
            int[] stone;
            int Z = sum - (X + Y);
            switch (type) {
                case 1:
                    stone = new int[]{X, Y, Z};
                    break;
                case 2:
                    stone = new int[]{X, Z, Y};
                    break;
                default:
                    stone = new int[]{Z, X, Y};
                    break;
            }
            queue.offer(stone);
            v[X][Y] = true;
        }
    }
}