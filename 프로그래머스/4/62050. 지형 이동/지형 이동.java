import java.util.*;

class Solution {
    int height, N, groupNum;
    int[] parents;
    int[][] land, map;
    int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 우 하 좌 상
    boolean[][] v;
    Queue<int[]> queue;
    PriorityQueue<Ladder> pq;
    
    public int solution(int[][] land, int height) {
        this.height = height;
        this.land = land;
        N = land.length;
        map = new int[N][N];
        groupNum = 0;
        queue = new LinkedList<>();
        v = new boolean[N][N];
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (map[r][c] == 0) {
                    groupNum++;
                    grouping(r, c);
                }
            }
        }
        findLadder();
        parents = new int[groupNum + 1];
        for (int i = 1; i <= groupNum; i++) {
            parents[i] = i;
        }
        return kruskal();
    }
    
    private void grouping(int sr, int sc) {
        queue.offer(new int[]{sr, sc});
        v[sr][sc] = true;
        map[sr][sc] = groupNum;
        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            for (int[] d : dir) {
                int nr = point[0] + d[0];
                int nc = point[1] + d[1];
                if (check(nr, nc) || v[nr][nc] || Math.abs(land[point[0]][point[1]] - land[nr][nc]) > height) continue;
                queue.offer(new int[]{nr, nc});
                v[nr][nc] = true;
                map[nr][nc] = groupNum;
            }
        }
    }
    
    private void findLadder() {
        int cur;
        pq = new PriorityQueue<>();
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                cur = map[r][c];
                for (int[] d : dir) {
                    int nr = r + d[0];
                    int nc = c + d[1];
                    if (check(nr, nc) || cur == map[nr][nc]) continue;
                    pq.offer(new Ladder(cur, map[nr][nc], Math.abs(land[r][c] - land[nr][nc])));
                }
            }
        }
    }
    
    private int kruskal() {
        int cost = 0;
        int cnt = 0;
        while (!pq.isEmpty() && cnt < groupNum - 1) {
            Ladder l = pq.poll();
            if (find(l.group1) == find(l.group2)) continue;
            union(l.group1, l.group2);
            cost += l.cost;
            cnt++;
        }
        return cost;
    }

    private void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a < b) parents[b] = a;
        else parents[a] = b;
    }

    private int find(int a) {
        if (parents[a] == a) return a;
        else return find(parents[a]);
    }
    
    private boolean check(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= N;
    }
    
    private class Ladder implements Comparable<Ladder> {
        int group1;
        int group2;
        int cost;
        
        private Ladder(int group1, int group2, int cost) {
            if (group1 > group2) {
                int tmp = group1;
                group1 = group2;
                group2 = tmp;
            }
            this.group1 = group1;
            this.group2 = group2;
            this.cost = cost;
        }
        
        @Override
        public int compareTo(Ladder o) {
            return this.cost - o.cost;
        }
    }
}