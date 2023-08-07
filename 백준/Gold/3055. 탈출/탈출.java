import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        char[][] map = new char[R][C];
        Queue<int[]> hedgehog = new LinkedList<>();
        Queue<int[]> water = new LinkedList<>();
        String s;
        for (int r = 0; r < R; r++) {
            s = br.readLine();
            for (int c = 0; c < C; c++) {
                map[r][c] = s.charAt(c);
                if (map[r][c] == 'S') hedgehog.offer(new int[]{r, c});
                else if (map[r][c] == '*') water.offer(new int[]{r, c});
            }
        }
        int answer = 0;
        boolean flag = false;
        int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 우 하 좌 상
        L:
        while (true) {
            answer++;
            Queue<int[]> newHedgehog = new LinkedList<>();
            while (!hedgehog.isEmpty()) {
                int[] point = hedgehog.poll();
                if (map[point[0]][point[1]] == '*') continue;
                for (int[] d : dir) {
                    int nr = point[0] + d[0];
                    int nc = point[1] + d[1];
                    if (nr >= 0 && nr < R && nc >= 0 && nc < C) {
                        if (map[nr][nc] == 'D') {
                            flag = true;
                            break L;
                        } else if (map[nr][nc] == '.') {
                            map[nr][nc] = 'S';
                            newHedgehog.offer(new int[]{nr, nc});
                        }
                    }
                }
            }
            Queue<int[]> newWater = new LinkedList<>();
            while (!water.isEmpty()) {
                int[] point = water.poll();
                for (int[] d : dir) {
                    int nr = point[0] + d[0];
                    int nc = point[1] + d[1];
                    if (nr >= 0 && nr < R && nc >= 0 && nc < C && (map[nr][nc] == '.' || map[nr][nc] == 'S')) {
                        map[nr][nc] = '*';
                        newWater.offer(new int[]{nr, nc});
                    }
                }
            }
            if (newHedgehog.isEmpty()) break;
            hedgehog = newHedgehog;
            water = newWater;
        }
        System.out.println(flag ? answer : "KAKTUS");
    }
}