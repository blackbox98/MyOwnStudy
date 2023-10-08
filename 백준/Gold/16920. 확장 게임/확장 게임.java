import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, P;
    static int[] castles, sArr;
    static Queue<int[]>[] qArr;
    static char[][] map;
    static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 우 하 좌 상

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        castles = new int[P + 1];
        sArr = new int[P + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= P; i++) {
            sArr[i] = Integer.parseInt(st.nextToken());
        }
        qArr = new Queue[P + 1];
        for (int i = 1; i <= P; i++) {
            qArr[i] = new LinkedList<>();
        }
        map = new char[N][M];
        for (int r = 0; r < N; r++) {
            char[] tmp = br.readLine().toCharArray();
            for (int c = 0; c < M; c++) {
                map[r][c] = tmp[c];
                if (map[r][c] == '.' || map[r][c] == '#') continue;
                castles[map[r][c] - '0']++;
            }
        }
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (map[r][c] == '.' || map[r][c] == '#') continue;
                for (int[] d : dir) {
                    int nr = r + d[0];
                    int nc = c + d[1];
                    if (check(nr, nc) && map[nr][nc] == '.') {
                        qArr[map[r][c] - '0'].offer(new int[]{r, c});
                        break;
                    }
                }
            }
        }
        int done = 0;
        while (done < P) {
            done = 0;
            for (int n = 1; n <= P; n++) {
                if (expansion(n)) done++;
            }
        }
        StringBuilder answer = new StringBuilder();
        for (int n = 1; n <= P; n++) {
            answer.append(castles[n]).append(" ");
        }
        System.out.println(answer);
    }

    private static boolean expansion(int n) {
        int before = castles[n];
        char ch = (char) (n + '0');
        int dist = 1;
        while (!qArr[n].isEmpty() && dist++ <= sArr[n]) {
            int size = qArr[n].size();
            for (int i = 0; i < size; i++) {
                int[] point = qArr[n].poll();
                for (int[] d : dir) {
                    int nr = point[0] + d[0];
                    int nc = point[1] + d[1];
                    if (check(nr, nc) && map[nr][nc] == '.') {
                        map[nr][nc] = ch;
                        qArr[n].offer(new int[]{nr, nc});
                        castles[n]++;
                    }
                }
            }
        }
        return castles[n] == before;
    }

    private static boolean check(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }
}