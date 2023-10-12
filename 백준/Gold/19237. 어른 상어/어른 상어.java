import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, k;
    static Queue<Shark> sharks;
    static SharkSmell[][] map;
    static int[][] direction = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 상 하 좌 우

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        sharks = new LinkedList<>();
        List<Shark> list = new ArrayList<>();
        map = new SharkSmell[N][N];
        int num;
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                num = Integer.parseInt(st.nextToken());
                if (num == 0) continue;
                map[r][c] = new SharkSmell(num, k);
                list.add(new Shark(r, c, num, -1));
            }
        }
        list.sort(Comparator.comparingInt(o -> o.n));
        for (Shark s : list) sharks.offer(s);
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            Shark s = sharks.poll();
            s.d = Integer.parseInt(st.nextToken()) - 1;
            sharks.offer(s);
        }
        for (Shark s : sharks) {
            for (int j = 0; j < 4; j++) {
                st = new StringTokenizer(br.readLine());
                for (int l = 0; l < 4; l++) {
                    s.priorityDir[j][l] = Integer.parseInt(st.nextToken()) - 1;
                }
            }
        }
        int answer = 0;
        while (sharks.size() > 1 && answer++ <= 1000) {
            moveShark();
            reducingSmell();
        }
        if (answer > 1000 || sharks.size() > 1) answer = -1;
        System.out.println(answer);
    }

    private static void moveShark() {
        int nr, nc;
        for (Shark s : sharks) {
            int cnt = 0;
            for (int dir : s.priorityDir[s.d]) {
                nr = s.r + direction[dir][0];
                nc = s.c + direction[dir][1];
                if (check(nr, nc) || map[nr][nc] != null) continue;
                cnt++;
            }
            s.canMove = cnt;
        }
        int size = sharks.size();
        for (int i = 0; i < size; i++) {
            Shark s = sharks.poll();
            if (s.canMove > 0) {
                for (int dir : s.priorityDir[s.d]) {
                    nr = s.r + direction[dir][0];
                    nc = s.c + direction[dir][1];
                    if (check(nr, nc) || map[nr][nc] != null) continue;
                    if (sharks.contains(new Shark(nr, nc, s.n, s.d))) break;
                    s.r = nr;
                    s.c = nc;
                    s.d = dir;
                    sharks.offer(s);
                    break;
                }
            } else {
                for (int dir : s.priorityDir[s.d]) {
                    nr = s.r + direction[dir][0];
                    nc = s.c + direction[dir][1];
                    if (check(nr, nc) || map[nr][nc] == null || map[nr][nc].n != s.n) continue;
                    s.r = nr;
                    s.c = nc;
                    s.d = dir;
                    sharks.offer(s);
                    break;
                }
            }
        }
    }

    private static void reducingSmell() {
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (map[r][c] == null) continue;
                if (--map[r][c].smell == 0) map[r][c] = null;
            }
        }
        for (Shark s : sharks) {
            map[s.r][s.c] = new SharkSmell(s.n, k);
        }
    }

    private static boolean check(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= N;
    }

    private static class Shark {
        int r;
        int c;
        int n;
        int d;
        int canMove = 0;
        int[][] priorityDir = new int[4][4];

        private Shark(int r, int c, int n, int d) {
            this.r = r;
            this.c = c;
            this.n = n;
            this.d = d;
        }

        @Override
        public boolean equals(Object obj) {
            Shark o = (Shark) obj;
            return this.r == o.r && this.c == o.c;
        }

        @Override
        public int hashCode() {
            return this.r * (N + 1) + this.c;
        }
    }

    private static class SharkSmell {
        int n;
        int smell;

        private SharkSmell(int n, int smell) {
            this.n = n;
            this.smell = smell;
        }
    }
}