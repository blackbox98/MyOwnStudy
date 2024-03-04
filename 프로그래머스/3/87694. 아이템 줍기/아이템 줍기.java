import java.io.*;
import java.util.*;

class Solution {
    int N;
    int[][] map;
    int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 우 하 좌 상
    
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        N = 102;
        map = new int[N][N];
        for (int[] rec : rectangle) {
            int minX = Math.min(rec[0], rec[2]) * 2;
            int maxX = Math.max(rec[0], rec[2]) * 2;
            int minY = Math.min(rec[1], rec[3]) * 2;
            int maxY = Math.max(rec[1], rec[3]) * 2;
            for (int x = minX; x <= maxX; x++) {
                for (int y = minY; y <= maxY; y++) {
                    if (map[x][y] != 2 && (x == minX || x == maxX || y == minY || y == maxY)) map[x][y] = 1;
                    else map[x][y] = 2;
                }
            }
        }
        return bfs(characterX * 2, characterY * 2, itemX * 2, itemY * 2);
    }
    
    public int bfs(int characterX, int characterY, int itemX, int itemY) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[2]));
        pq.offer(new int[]{characterX, characterY, 0});
        boolean[][] v = new boolean[N][N];
        v[characterX][characterY] = true;
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            if (cur[0] == itemX && cur[1] == itemY) return cur[2] / 2;
            for (int[] d : dir) {
                int nr = cur[0] + d[0];
                int nc = cur[1] + d[1];
                if (check(nr, nc) || v[nr][nc] || map[nr][nc] != 1) continue;
                pq.offer(new int[]{nr, nc, cur[2] + 1});
                v[nr][nc] = true;
            }
        }
        return 0;
    }

    public boolean check(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= N;
    }
}