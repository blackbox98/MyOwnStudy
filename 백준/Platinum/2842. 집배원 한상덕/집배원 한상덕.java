import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, kNum;
    static int[] start;
    static char[][] map;
    static int[][] distMap;
    static int[][] dir = {{-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}};
    /*
    0 1 2
    7 x 3
    6 5 4
    */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        kNum = 0;
        start = new int[2];
        map = new char[N][N];
        distMap = new int[N][N];
        for (int r = 0; r < N; r++) {
            map[r] = br.readLine().toCharArray();
            for (int c = 0; c < N; c++) {
                if (map[r][c] == 'K') kNum++;
                else if (map[r][c] == 'P') start = new int[]{r, c};
            }
        }
        StringTokenizer st;
        Set<Integer> set = new HashSet<>();
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                distMap[r][c] = Integer.parseInt(st.nextToken());
                set.add(distMap[r][c]);
            }
        }
        int startH = distMap[start[0]][start[1]];
        Integer[] heights = set.toArray(new Integer[]{});
        Arrays.sort(heights);
        int answer = Integer.MAX_VALUE;
        int left = 0;
        int right = 0;
        while (left <= right && right < heights.length) {
            if (heights[left] <= startH && startH <= heights[right] && bfs(heights[left], heights[right])) {
                answer = Math.min(answer, heights[right] - heights[left]);
                left++;
            } else right++;
        }
        System.out.println(answer);
    }

    private static boolean bfs(int min, int max) {
        int cntK = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(start);
        boolean[][] v = new boolean[N][N];
        v[start[0]][start[1]] = true;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (kNum == cntK) return true;
            for (int[] d : dir) {
                int nr = cur[0] + d[0];
                int nc = cur[1] + d[1];
                if (check(nr, nc) || v[nr][nc] || min > distMap[nr][nc] || distMap[nr][nc] > max) continue;
                if (map[nr][nc] == 'K') cntK++;
                queue.offer(new int[]{nr, nc});
                v[nr][nc] = true;
            }
        }
        return false;
    }

    private static boolean check(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= N;
    }
}