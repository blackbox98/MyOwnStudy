import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int l;
    static int[][] knight;
    static int[][] dir = {{-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}};
    /*
     1   2
    0     3
       x
    7     4
     6   5
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for (int tc = 0; tc < T; tc++) {
            l = Integer.parseInt(br.readLine());
            knight = new int[2][2];
            for (int i = 0; i < 2; i++) {
                st = new StringTokenizer(br.readLine());
                int r = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                knight[i] = new int[]{r, c};
            }
            sb.append(moveKnight()).append("\n");
        }
        System.out.println(sb);
    }

    private static int moveKnight() {
        int result = Integer.MAX_VALUE;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{knight[0][0], knight[0][1], 0});
        int[][] v = new int[l][l];
        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            int pr = point[0];
            int pc = point[1];
            if (knight[1][0] == pr && knight[1][1] == pc) {
                result = point[2];
                break;
            }
            for (int[] d : dir) {
                int nr = pr + d[0];
                int nc = pc + d[1];
                if (check(nr, nc) || (v[nr][nc] > 0 && v[nr][nc] <= point[2] + 1)) continue;
                queue.offer(new int[]{nr, nc, point[2] + 1});
                v[nr][nc] = point[2] + 1;
            }
        }
        return result;
    }

    private static boolean check(int r, int c) {
        return r < 0 || r >= l || c < 0 || c >= l;
    }
}