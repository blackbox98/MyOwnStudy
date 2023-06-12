import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int answer;
    static int N;
    static int[][] map;
    static int fishCnt;
    static Point shark;
    static int eatenFishCnt;
    static boolean leftToEat;
    static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 우 하 좌 상

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        answer = 0;
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        fishCnt = 0;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (1 <= map[i][j] && map[i][j] <= 6) fishCnt++;
                else if (map[i][j] == 9) {
                    shark = new Point(i, j, 2, 0);
                    eatenFishCnt = 0;
                    map[i][j] = 0;
                }
            }
        }
        leftToEat = true;
        while (fishCnt > 0 && leftToEat) {
            answer += eatFish();
        }
        System.out.println(answer);
    }

    private static int eatFish() {
        int dist = 0;
        PriorityQueue<Point> edibleFish = new PriorityQueue<>((o1, o2) -> {
            if (o1.level == o2.level) {
                if (o1.r == o2.r) return Integer.compare(o1.c, o2.c);
                else return Integer.compare(o1.r, o2.r);
            } else return Integer.compare(o1.level, o2.level);
        });
        Queue<Point> queue = new LinkedList<>();
        boolean[][] v = new boolean[N][N];
        queue.offer(shark);
        v[shark.r][shark.c] = true;
        while (!queue.isEmpty()) {
            Point point = queue.poll();
            int nLevel = point.level + 1;
            for (int[] d : dir) {
                int nr = point.r + d[0];
                int nc = point.c + d[1];
                if (nr >= 0 && nr < N && nc >= 0 && nc < N && !v[nr][nc] && map[nr][nc] <= shark.size) {
                    if (map[nr][nc] > 0 && map[nr][nc] < shark.size) {
                        edibleFish.offer(new Point(nr, nc, map[nr][nc], nLevel));
                    }
                    queue.offer(new Point(nr, nc, map[nr][nc], nLevel));
                    v[nr][nc] = true;
                }
            }
        }
        if (edibleFish.isEmpty()) leftToEat = false;
        else {
            Point fish = edibleFish.poll();
            map[fish.r][fish.c] = 0;
            shark.r = fish.r;
            shark.c = fish.c;
            dist = fish.level;
            eatenFishCnt++;
            if (shark.size == eatenFishCnt) {
                shark.size++;
                eatenFishCnt = 0;
            }
            fishCnt--;
        }
        return dist;
    }

    private static class Point {
        int r;
        int c;
        int size;
        int level;

        private Point(int r, int c, int size, int level) {
            this.r = r;
            this.c = c;
            this.size = size;
            this.level = level;
        }
    }
}