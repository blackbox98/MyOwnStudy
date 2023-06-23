import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    /*
    7 0 1
    6 X 2
    5 4 3
     */
    static int[][] dir = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};
    static int N, M, K;
    static Queue<FireBall> queue = new LinkedList<>();
    static PriorityQueue<FireBall> pq = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            queue.offer(new FireBall(r, c, m, s, d));
        }
        while (K-- > 0) {
            while (!queue.isEmpty()) {
                FireBall fireBall = queue.poll();
                int nr = getCorrectLocation(fireBall.r + dir[fireBall.d][0] * fireBall.s);
                int nc = getCorrectLocation(fireBall.c + dir[fireBall.d][1] * fireBall.s);
                pq.offer(new FireBall(nr, nc, fireBall.m, fireBall.s, fireBall.d));
            }
            while (!pq.isEmpty()) {
                FireBall fireBall = pq.poll();
                int cnt = 1;
                int sumM = fireBall.m;
                int sumS = fireBall.s;
                int typeD = fireBall.d % 2;
                boolean checkDir = true;
                while (pq.peek() != null && fireBall.r == pq.peek().r && fireBall.c == pq.peek().c) {
                    FireBall sameLocationFireBall = pq.poll();
                    cnt++;
                    sumM += sameLocationFireBall.m;
                    sumS += sameLocationFireBall.s;
                    if (typeD != (sameLocationFireBall.d % 2)) checkDir = false;
                }
                if (cnt > 1) {
                    sumM /= 5;
                    if (sumM == 0) continue;
                    sumS /= cnt;
                    int startDir = 0;
                    if (!checkDir) startDir = 1;
                    for (int d = startDir; d < dir.length; d += 2) {
                        queue.offer(new FireBall(fireBall.r, fireBall.c, sumM, sumS, d));
                    }
                } else queue.offer(fireBall);
            }
        }
        int answer = 0;
        for (FireBall fireBall : queue) {
            answer += fireBall.m;
        }
        System.out.println(answer);
    }

    private static int getCorrectLocation(int location) {
        if (location < 0) location = (N + (location % N)) % N;
        else if (location >= N) location %= N;
        return location;
    }

    private static class FireBall implements Comparable<FireBall> {
        int r;
        int c;
        int m;
        int s;
        int d;

        private FireBall(int r, int c, int m, int s, int d) {
            this.r = r;
            this.c = c;
            this.m = m;
            this.s = s;
            this.d = d;
        }

        @Override
        public int compareTo(FireBall o) {
            if (this.r == o.r) return Integer.compare(this.c, o.c);
            else return Integer.compare(this.r, o.r);
        }
    }
}