import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.Queue;

public class Main {
    static final int R = 10, C = 9;
    static int[][][] dir = {{{0, -1}, {-1, -1}, {-1, -1}},
                            {{-1, 0}, {-1, -1}, {-1, -1}},
                            {{-1, 0}, {-1, 1}, {-1, 1}},
                            {{0, 1}, {-1, 1}, {-1, 1}},
                            {{0, 1}, {1, 1}, {1, 1}},
                            {{1, 0}, {1, 1}, {1, 1}},
                            {{1, 0}, {1, -1}, {1, -1}},
                            {{0, -1}, {1, -1}, {1, -1}}};
    /*
        1               2
    0                       3
    
                X
    
    7                       4
        6               5
    */
    
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int R1 = Integer.parseInt(st.nextToken());
        int C1 = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int R2 = Integer.parseInt(st.nextToken());
        int C2 = Integer.parseInt(st.nextToken());
        System.out.println(bfs(R1, C1, R2, C2));
    }
    
    private static int bfs(int R1, int C1, int R2, int C2) {
        int result = Integer.MAX_VALUE;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{R1, C1, 0});
        int[][] v = new int[R][C];
        v[R1][C1] = 0;
        int pr, pc, pm, nr, nc;
        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            pr = point[0];
            pc = point[1];
            pm = point[2];
            if (pr == R2 && pc == C2) {
                result = Math.min(result, pm);
                continue;
            }
            L:
            for (int[][] d : dir) {
                nr = pr;
                nc = pc;
                for (int i = 0; i < 3; i++) {
                    nr += d[i][0];
                    nc += d[i][1];
                    if (check(nr, nc) || (i < 2 && nr == R2 && nc == C2)) continue L;
                }
                if (v[nr][nc] > 0 && v[nr][nc] <= pm + 1) continue;
                queue.offer(new int[]{nr, nc, pm + 1});
                v[nr][nc] = pm + 1;
            }
        }
        return result == Integer.MAX_VALUE ? -1 : result;
    }
    
    private static boolean check(int r, int c) {
        return r < 0 || r >= R || c < 0 || c >= C;
    }
}