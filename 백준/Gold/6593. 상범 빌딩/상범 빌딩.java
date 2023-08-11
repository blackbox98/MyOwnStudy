import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] dir = {{0, 0, 1}, {0, 0, -1}, {0, 1, 0}, {0, -1, 0}, {-1, 0, 0}, {1, 0, 0}}; // 동 서 남 북 상 하
        String s;
        while ((s = br.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(s);
            int L = Integer.parseInt(st.nextToken());
            int R = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            if (L == 0 && R == 0 && C == 0) break;
            char[][][] map = new char[L][R][C];
            int[] start = new int[3];
            for (int l = 0; l < L; l++) {
                for (int r = 0; r < R; r++) {
                    char[] tmp = br.readLine().toCharArray();
                    for (int c = 0; c < C; c++) {
                        map[l][r][c] = tmp[c];
                        if (map[l][r][c] == 'S') start = new int[]{l, r, c};
                    }
                }
                br.readLine();
            }
            int answer = Integer.MAX_VALUE;
            boolean flag = false;
            Queue<int[]> queue = new LinkedList<>();
            queue.offer(new int[]{start[0], start[1], start[2], 0});
            boolean[][][] v = new boolean[L][R][C];
            v[start[0]][start[1]][start[2]] = true;
            while (!queue.isEmpty()) {
                int[] point = queue.poll();
                for (int[] d : dir) {
                    int nl = point[0] + d[0];
                    int nr = point[1] + d[1];
                    int nc = point[2] + d[2];
                    int nd = point[3] + 1;
                    if (nl >= 0 && nl < L && nr >= 0 && nr < R && nc >= 0 && nc < C && !v[nl][nr][nc]) {
                        if (map[nl][nr][nc] == '.') {
                            queue.offer(new int[]{nl, nr, nc, nd});
                            v[nl][nr][nc] = true;
                        } else if (map[nl][nr][nc] == 'E') {
                            flag = true;
                            answer = Math.min(answer, nd);
                        }
                    }
                }
            }
            if (flag) System.out.printf("Escaped in %d minute(s).\n", answer);
            else System.out.println("Trapped!");
        }
    }
}