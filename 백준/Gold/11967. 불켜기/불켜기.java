import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, answer;
    static List<Room>[][] switchList;
    static boolean[][] checkLight;
    static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 우 하 좌 상

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        switchList = new List[N + 1][N + 1];
        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= N; c++) {
                switchList[r][c] = new ArrayList<>();
            }
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            switchList[x][y].add(new Room(a, b));
        }
        answer = 1;
        checkLight = new boolean[N + 1][N + 1];
        checkLight[1][1] = true;
        while (bfs(new Room(1, 1))) ;
        System.out.println(answer);
    }

    private static boolean bfs(Room start) {
        Queue<Room> queue = new LinkedList<>();
        queue.offer(start);
        boolean[][] v = new boolean[N + 1][N + 1];
        v[start.r][start.c] = true;
        int cnt = 0;
        while (!queue.isEmpty()) {
            Room room = queue.poll();
            for (int i = 0; i < switchList[room.r][room.c].size(); i++) {
                Room target = switchList[room.r][room.c].get(i);
                if (!checkLight[target.r][target.c]) {
                    checkLight[target.r][target.c] = true;
                    cnt++;
                }
            }
            for (int[] d : dir) {
                int nr = room.r + d[0];
                int nc = room.c + d[1];
                if (nr < 0 || nr > N || nc < 0 || nc > N || v[nr][nc] || !checkLight[nr][nc]) continue;
                queue.offer(new Room(nr, nc));
                v[nr][nc] = true;
            }
        }
        answer += cnt;
        return cnt > 0;
    }

    private static class Room {
        int r;
        int c;

        private Room(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}