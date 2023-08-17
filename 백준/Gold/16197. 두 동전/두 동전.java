import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, answer;
    static char[][] map;
    static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 우 하 좌 상
    
    public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      M = Integer.parseInt(st.nextToken());
      map = new char[N][M];
      int[][] coins = new int[2][2];
      int idx = 0;
      char[] tmp;
      for (int r = 0; r < N; r++) {
          tmp = br.readLine().toCharArray();
          for (int c = 0; c < M; c++) {
              map[r][c] = tmp[c];
              if (map[r][c] == 'o') coins[idx++] = new int[]{r, c};
          }
      }
      answer = Integer.MAX_VALUE;
      findRoad(coins, 0);
      if (answer == Integer.MAX_VALUE) answer = -1;
      System.out.println(answer);
    }
    
    private static void findRoad(int[][] coins, int moveCnt) {
        if (moveCnt >= 10 || moveCnt > answer) return;
        for (int[] d : dir) {
            int alive = 2;
            int nr1 = coins[0][0] + d[0];
            int nc1 = coins[0][1] + d[1];
            int nr2 = coins[1][0] + d[0];
            int nc2 = coins[1][1] + d[1];
            if (check(nr1, nc1)) alive -= 1;
            if (check(nr2, nc2)) alive -= 1;
            if (alive == 0) continue;
            else if (alive == 1) {
                answer = Math.min(answer, moveCnt + 1);
                return;
            }
            int[][] tmpCoins = {{nr1, nc1}, {nr2, nc2}};
            if (map[nr1][nc1] == '#') tmpCoins[0] = coins[0];
            if (map[nr2][nc2] == '#') tmpCoins[1] = coins[1];
            findRoad(tmpCoins, moveCnt + 1);
        }
    }
    
    private static boolean check(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= M;
    }
}