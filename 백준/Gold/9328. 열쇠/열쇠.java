import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static int h, w, answer;
    static char[][] map;
    static Set<Character> keys;
    static Queue<int[]> queue;
    static boolean[][] v;
    static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 우 하 좌 상
    
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine());
            h = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            map = new char[h][w];
            for (int r = 0; r < h; r++) {
                map[r] = br.readLine().toCharArray();
            }
            keys = new HashSet<>();
            String s = br.readLine();
            if (!s.equals("0")) {
                for (char key : s.toCharArray()) keys.add(key);
            }
            answer = 0;
            while (true) {
                int curAns = answer;
                int keyCnt = keys.size();
                for (int r = 0; r < h; r++) {
                    for (int c = 0; c < w; c++) {
                        if (r == 0 || r == h - 1 || c == 0 || c == w - 1) {
                            if (map[r][c] != '*') bfs(r, c);
                        }
                    }
                }
                if (curAns == answer && keyCnt == keys.size()) break;
            }
            System.out.println(answer);
        }
    }
    
    private static void bfs(int sr, int sc) {
        queue = new LinkedList<>();
        queue.offer(new int[]{sr, sc});
        v = new boolean[h][w];
        v[sr][sc] = true;
        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            int pr = point[0];
            int pc = point[1];
            if (map[pr][pc] == '$') {
                map[pr][pc] = '.';
                answer++;
            } else if ('a' <= map[pr][pc] && map[pr][pc] <= 'z') {
                keys.add(map[pr][pc]);
                map[pr][pc] = '.';
            } else if ('A' <= map[pr][pc] && map[pr][pc] <= 'Z' && keys.contains((char) (map[pr][pc] + 32))) {
                map[pr][pc] = '.';
            } else if (map[pr][pc] != '.') continue;
            for (int[] d : dir) {
                int nr = pr + d[0];
                int nc = pc + d[1];
                if (check(nr, nc) || map[nr][nc] == '*' || v[nr][nc]) continue;
                queue.offer(new int[]{nr, nc});
                v[nr][nc] = true;
            }
        }
    }
    
    private static boolean check(int r,  int c) {
        return r < 0 || r >= h || c < 0 || c >= w;
    }
}