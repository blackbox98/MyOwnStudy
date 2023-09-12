import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Main {
    static int N;
    static int[][] map;
    static List<Integer>[] listArr;
    static PriorityQueue<Seat> pq;
    static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 우 하 좌 상
    
    private static class Seat implements Comparable<Seat> {
        int r;
        int c;
        int adjCnt = 0;
        int emptyCnt = 0;
        
        private Seat(int r, int c) {
            this.r = r;
            this.c = c;
        }
        
        @Override
        public int compareTo(Seat o) {
            if (this.adjCnt == o.adjCnt) {
                if (this.emptyCnt == o.emptyCnt) {
                    if (this.r == o.r) {
                        return this.c - o.c;
                    } else return this.r - o.r;
                } else return o.emptyCnt - this.emptyCnt;
            } else return o.adjCnt - this.adjCnt;
        }
    }
    
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        listArr = new List[N * N + 1];
        StringTokenizer st;
        int n, like;
        for (int i = 0; i < N * N; i++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            listArr[n] = new ArrayList<>();
            for (int j = 0; j < 4; j++) {
                like = Integer.parseInt(st.nextToken());
                listArr[n].add(like);
            }
            seating(n);
        }
        System.out.println(getSatisfaction());
    }
    
    private static void seating(int n) {
        pq = new PriorityQueue<>();
        Seat seat;
        int num, nr, nc;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (map[r][c] != 0) continue;
                seat = new Seat(r, c);
                for (int[] d : dir) {
                    nr = r + d[0];
                    nc = c + d[1];
                    if (!check(nr, nc)) continue;
                    if (map[nr][nc] == 0) seat.emptyCnt++;
                    else if (listArr[n].contains(map[nr][nc])) seat.adjCnt++;
                }
                pq.offer(seat);
            }
        }
        if (!pq.isEmpty()) {
            Seat target = pq.poll();
            map[target.r][target.c] = n;
        }
    }
    
    private static int getSatisfaction() {
        int answer = 0;
        int n, cnt, nr, nc;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                n = map[r][c];
                cnt = 0;
                for (int[] d : dir) {
                    nr = r + d[0];
                    nc = c + d[1];
                    if (check(nr, nc) && listArr[n].contains(map[nr][nc])) cnt++;
                }
                switch (cnt) {
                    case 1:
                        answer += 1;
                        break;
                    case 2:
                        answer += 10;
                        break;
                    case 3:
                        answer += 100;
                        break;
                    case 4:
                        answer += 1000;
                        break;
                }
            }
        }
        return answer;
    }
    
    private static boolean check(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }
}