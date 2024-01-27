import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] map;
    static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 우 하 좌 상
    static List<int[]> list;
    static Queue<int[]> queue;
    static boolean[][] v;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < M; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        list = new ArrayList<>();
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (map[r][c] != 0) continue;
                for (int[] d : dir) {
                    int nr = r + d[0];
                    int nc = c + d[1];
                    if (check(nr, nc) || map[nr][nc] != 2) continue;
                    list.add(new int[]{r, c});
                    break;
                }
            }
        }
        int answer = 0;
        int size = list.size();
        queue = new LinkedList<>();
        for (int i = 0; i < size - 1; i++) {
            for (int j = i + 1; j < size; j++) {
                int[] empty1 = list.get(i);
                int[] empty2 = list.get(j);
                map[empty1[0]][empty1[1]] = 1;
                map[empty2[0]][empty2[1]] = 1;
                v = new boolean[N][M];
                int area = 0;
                for (int r = 0; r < N; r++) {
                    for (int c = 0; c < M; c++) {
                        if (v[r][c] || map[r][c] != 2) continue;
                        area += bfs(r, c);
                    }
                }
                answer = Math.max(answer, area);
                map[empty1[0]][empty1[1]] = 0;
                map[empty2[0]][empty2[1]] = 0;
            }
        }
        if (size == 1) {
            int[] empty = list.get(0);
            map[empty[0]][empty[1]] = 1;
            v = new boolean[N][M];
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < M; c++) {
                    if (v[r][c] || map[r][c] != 2) continue;
                    answer += bfs(r, c);
                }
            }
            map[empty[0]][empty[1]] = 0;
        }
        System.out.println(answer);
    }

    private static int bfs(int sr, int sc) {
        int area = 0;
        int empty = 0;
        queue.offer(new int[]{sr, sc});
        v[sr][sc] = true;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            area++;
            for (int[] d : dir) {
                int nr = cur[0] + d[0];
                int nc = cur[1] + d[1];
                if (check(nr, nc) || v[nr][nc] || map[nr][nc] == 1) continue;
                if (map[nr][nc] == 0) {
                    empty++;
                    continue;
                }
                queue.offer(new int[]{nr, nc});
                v[nr][nc] = true;
            }
        }
        return empty == 0 ? area : 0;
    }

    private static boolean check(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= M;
    }
}