import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        System.out.println(bfs(a, b, c, d));
    }
    
    private static int bfs(int a, int b, int c, int d) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0, 0});
        boolean[][] v = new boolean[a + 1][b + 1];
        v[0][0] = true;
        while (!queue.isEmpty()) {
            int[] bottle = queue.poll();
            int curA = bottle[0];
            int curB = bottle[1];
            int curCnt = bottle[2];
            if (curA == c && curB == d) return curCnt;
            int nCnt = curCnt + 1;
            if (!v[a][curB]) {
                queue.offer(new int[]{a, curB, nCnt});
                v[a][curB] = true;
            }
            if (!v[curA][b]) {
                queue.offer(new int[]{curA, b, nCnt});
                v[curA][b] = true;
            }
            if (!v[0][curB]) {
                queue.offer(new int[]{0, curB, nCnt});
                v[0][curB] = true;
            }
            if (!v[curA][0]) {
                queue.offer(new int[]{curA, 0, nCnt});
                v[curA][0] = true;
            }
            int na = b - curB < curA ? curA - (b - curB) : 0;
            int nb = curB + curA > b ? b : curB + curA;
            if (!v[na][nb]) {
                queue.offer(new int[]{na, nb, nCnt});
                v[na][nb] = true;
            }
            na = curA + curB > a ? a : curA + curB;
            nb = a - curA < curB ? curB - (a - curA) : 0;
            if (!v[na][nb]) {
                queue.offer(new int[]{na, nb, nCnt});
                v[na][nb] = true;
            }
        }
        return -1;
    }
}