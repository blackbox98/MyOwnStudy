import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] map;
    static Queue<int[]> queue;
    static boolean[][] v;
    static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 우 하 좌 상
    static Map<Integer, Integer> numMap;
    
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        int[][] answer = new int[N][M];
        for (int r = 0; r < N; r++) {
            char[] tmp = br.readLine().toCharArray();
            for (int c = 0; c < M; c++) {
                map[r][c] = tmp[c] - '0';
                answer[r][c] = map[r][c];
            }
        }
        queue = new LinkedList<>();
        v = new boolean[N][M];
        numMap = new HashMap<>();
        int num = 2;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (map[r][c] == 0 && !v[r][c]) numMap.put(num, bfs(r, c, num++));
            }
        }
        Set<Integer> set;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (answer[r][c] == 1) {
                    set = new HashSet<>();
                    for (int[] d : dir) {
                        int nr = r + d[0];
                        int nc = c + d[1];
                        if (check(nr, nc) || answer[nr][nc] != 0) continue;
                        set.add(map[nr][nc]);
                    }
                    for (int n : set) answer[r][c] += numMap.get(n);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                sb.append(answer[r][c] % 10);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
    
    private static int bfs(int sr, int sc, int num) {
        int cnt = 0;
        queue.offer(new int[]{sr, sc});
        v[sr][sc] = true;
        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            int pr = point[0];
            int pc = point[1];
            map[pr][pc] = num;
            cnt++;
            for (int[] d : dir) {
                int nr = pr + d[0];
                int nc = pc + d[1];
                if (check(nr, nc) || map[nr][nc] != 0 || v[nr][nc]) continue;
                queue.offer(new int[]{nr, nc});
                v[nr][nc] = true;
            }
        }
        return cnt;
    }
    
    private static boolean check(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= M;
    }
}