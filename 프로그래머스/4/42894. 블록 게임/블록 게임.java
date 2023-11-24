import java.util.*;

class Solution {
    int N;
    Map<Integer, Block> map;
    int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 우 하 좌 상
    
    public class Block {
        int sr;
        int sc;
        int er;
        int ec;
        
        public Block(int sr, int sc, int er, int ec) {
            this.sr = sr;
            this.sc = sc;
            this.er = er;
            this.ec = ec;
        }
    }
    
    public int solution(int[][] board) {
        N = board.length;
        Map<Integer, Block> map = new HashMap<>();
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (board[r][c] == 0 || map.containsKey(board[r][c])) continue;
                map.put(board[r][c], getBlock(board, r, c));
            }
        }
        int answer = 0;
        int beforeAns = -1;
        Set<Integer> set;
        while (answer > beforeAns) {
            beforeAns = answer;
            set = new HashSet<>();
            L:
            for (int c = 0; c < N; c++) {
                for (int r = 0; r < N; r++) {
                    if (board[r][c] == 0) continue;
                    if (set.contains(board[r][c])) break;
                    set.add(board[r][c]);
                    Block block = map.get(board[r][c]);
                    for (int br = block.sr; br <= block.er; br++) {
                        for (int bc = block.sc; bc <= block.ec; bc++) {
                            if (board[br][bc] != 0) {
                                if (board[br][bc] != board[r][c]) continue L;
                                continue;
                            }
                            for (int nr = br - 1; nr >= 0; nr--) {
                                if (board[nr][bc] != 0) continue L;
                            }
                        }
                    }
                    for (int br = block.sr; br <= block.er; br++) {
                        for (int bc = block.sc; bc <= block.ec; bc++) {
                            board[br][bc] = 0;
                        }
                    }
                    map.remove(board[r][c]);
                    answer++;
                }
            }
        }
        return answer;
    }
    
    public Block getBlock(int[][] board, int r, int c) {
        int num = board[r][c];
        int sr = N;
        int sc = N;
        int er = -1;
        int ec = -1;
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{r, c});
        boolean[][] visit = new boolean[N][N];
        visit[r][c] = true;
        while (!q.isEmpty()) {
            int[] p = q.poll();
            sr = Math.min(sr, p[0]);
            sc = Math.min(sc, p[1]);
            er = Math.max(er, p[0]);
            ec = Math.max(ec, p[1]);
            for (int[] d : dir) {
                int nr = p[0] + d[0];
                int nc = p[1] + d[1];
                if (check(nr, nc) || board[nr][nc] != num || visit[nr][nc]) continue;
                q.offer(new int[]{nr, nc});
                visit[nr][nc] = true;
            }
        }
        return new Block(sr, sc, er, ec);
    }
    
    public boolean check(int r, int c) {
        return r < 0 || r>= N || c < 0 || c >= N;
    }
}