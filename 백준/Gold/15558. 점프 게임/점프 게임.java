import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static char[][] map;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new char[2][N];
        map[0] = br.readLine().toCharArray();
        map[1] = br.readLine().toCharArray();
        System.out.println(jumpGame(0));
    }
    
    private static int jumpGame(int start) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, start, -1});
        boolean[][] v = new boolean[2][N];
        v[0][0] = true;
        int[] info;
        int line, h, limit, nextLine;
        while (!queue.isEmpty()) {
            info = queue.poll();
            line = info[0];
            h = info[1];
            limit = info[2] + 1;
            nextLine = (line + 1) % 2;
            if (h >= N) return 1;
            if (h + 1 >= N || (map[line][h + 1] == '1' && !v[line][h + 1])) {
                if (h + 1 < N) v[line][h + 1] = true;
                queue.offer(new int[]{line, h + 1, limit});
            }
            if (h - 1 > limit && map[line][h - 1] == '1' && !v[line][h - 1]) {
                v[line][h - 1] = true;
                queue.offer(new int[]{line, h - 1, limit});
            }
            if (h + K >= N || (map[nextLine][h + K] == '1' && !v[nextLine][h + K])) {
                if (h + K < N) v[nextLine][h + K] = true;
                queue.offer(new int[]{nextLine, h + K, limit});
            }
        }
        return 0;
    }
}