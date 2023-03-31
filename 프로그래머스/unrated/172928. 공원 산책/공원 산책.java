class Solution {
    static int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}}; // E(동) W(서) S(남) N(북)
    static final String d = "EWSN";
    
    public int[] solution(String[] park, String[] routes) {
        int[] answer = new int[2];
        int H = park.length;
        int W = park[0].length();
        char[][] map = new char[H][W];
        for (int r = 0; r < H; r++) {
            char[] tmp = park[r].toCharArray();
            for (int c = 0; c < W; c++) {
                map[r][c] = tmp[c];
                if (map[r][c] == 'S') {
                    answer[0] = r;
                    answer[1] = c;
                }
            }
        }
        for (int i = 0; i < routes.length; i++) {
            String[] tmp = routes[i].split(" ");
            int way = d.indexOf(tmp[0]);
            int n = Integer.parseInt(tmp[1]);
            boolean pass = true;
            for (int j = 1; j <= n; j++) {
                int nr = answer[0] + dir[way][0] * j;
                int nc = answer[1] + dir[way][1] * j;
                if (nr < 0 || nr >= H || nc < 0 || nc >= W || map[nr][nc] != 'O') {
                    pass = false;
                    break;
                }
            }
            if (pass) {
                map[answer[0]][answer[1]] = 'O';
                answer[0] += dir[way][0] * n;
                answer[1] += dir[way][1] * n;
                map[answer[0]][answer[1]] = 'S';
            }
        }
        return answer;
    }
}