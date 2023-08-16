import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int M, N;
    static int[] answer;
    static int[][] map, areaMap;
    static boolean[][] v;
    static Map<Integer, Integer> cntMap;
    static int[][] dir = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}}; // 서 북 동 남

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[M][N];
        for (int r = 0; r < M; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        answer = new int[3];
        areaMap = new int[M][N];
        v = new boolean[M][N];
        cntMap = new HashMap<>();
        int num = 1;
        for (int r = 0; r < M; r++) {
            for (int c = 0; c < N; c++) {
                if (v[r][c]) continue;
                answer[0]++;
                bfs(r, c, num++);
            }
        }
        for (int r = 0; r < M; r++) {
            for (int c = 0; c < N; c++) {
                if (check(r, c + 1) && areaMap[r][c] != areaMap[r][c + 1])
                    answer[2] = Math.max(answer[2], cntMap.get(areaMap[r][c]) + cntMap.get(areaMap[r][c + 1]));
                if (check(r + 1, c) && areaMap[r][c] != areaMap[r + 1][c])
                    answer[2] = Math.max(answer[2], cntMap.get(areaMap[r][c]) + cntMap.get(areaMap[r + 1][c]));
            }
        }
        for (int ans : answer) {
            System.out.println(ans);
        }
    }

    private static void bfs(int sr, int sc, int num) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{sr, sc});
        v[sr][sc] = true;
        int cnt = 0;
        while (!queue.isEmpty()) {
            cnt++;
            int[] point = queue.poll();
            areaMap[point[0]][point[1]] = num;
            StringBuilder sb = new StringBuilder(Integer.toBinaryString(map[point[0]][point[1]]));
            sb.reverse();
            for (int i = sb.length(); i < 4; i++) {
                sb.append('0');
            }
            for (int i = 0; i < sb.length(); i++) {
                if (sb.charAt(i) == '1') continue;
                int nr = point[0] + dir[i][0];
                int nc = point[1] + dir[i][1];
                if (check(nr, nc) && !v[nr][nc]) {
                    queue.offer(new int[]{nr, nc});
                    v[nr][nc] = true;
                }
            }
        }
        cntMap.put(num, cnt);
        answer[1] = Math.max(answer[1], cnt);
    }

    private static boolean check(int r, int c) {
        return r >= 0 && r < M && c >= 0 && c < N;
    }
}