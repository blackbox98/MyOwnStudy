import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int M, N;
    static char[][] map;
    static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 동 남 서 북

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new char[M][N];
        for (int r = 0; r < M; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                map[r][c] = st.nextToken().charAt(0);
            }
        }
        Robot start = setRobot(br.readLine());
        Robot end = setRobot(br.readLine());
        System.out.println(bfs(start, end));
    }

    private static Robot setRobot(String robotInfo) {
        StringTokenizer st = new StringTokenizer(robotInfo);
        int r = Integer.parseInt(st.nextToken()) - 1;
        int c = Integer.parseInt(st.nextToken()) - 1;
        int d = Integer.parseInt(st.nextToken()) - 1;
        if (d == 1) d = 2;
        else if (d == 2) d = 1;
        return new Robot(r, c, d, 0);
    }

    private static int bfs(Robot start, Robot end) {
        PriorityQueue<Robot> queue = new PriorityQueue<>();
        queue.offer(start);
        boolean[][][][] v = new boolean[M][N][dir.length][4];
        v[start.r][start.c][start.d][0] = true;
        while (!queue.isEmpty()) {
            Robot robot = queue.poll();
            if (robot.r == end.r && robot.c == end.c && robot.d == end.d) return robot.moveCnt;
            int jumpIdx = robot.d + 2 > 3 ? robot.d - 2 : robot.d + 2;
            for (int i = 0; i < dir.length; i++) {
                if (i == jumpIdx) continue;
                int k = -1;
                while (++k <= 3) {
                    int nr = robot.r + dir[i][0] * k;
                    int nc = robot.c + dir[i][1] * k;
                    if (nr < 0 || nr >= M || nc < 0 || nc >= N || v[robot.r][robot.c][i][k]) continue;
                    if (map[nr][nc] == '1') break;
                    int nm = robot.moveCnt;
                    if (i != robot.d) nm++;
                    if (k > 0) nm++;
                    queue.offer(new Robot(nr, nc, i, nm));
                    v[robot.r][robot.c][i][k] = true;
                }
            }
        }
        return 0;
    }

    private static class Robot implements Comparable<Robot> {
        int r;
        int c;
        int d;
        int moveCnt;

        private Robot(int r, int c, int d, int moveCnt) {
            this.r = r;
            this.c = c;
            this.d = d;
            this.moveCnt = moveCnt;
        }

        @Override
        public int compareTo(Robot o) {
            return Integer.compare(this.moveCnt, o.moveCnt);
        }
    }
}