import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] dir = {{0, 0}, {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}}; // ., ←, ↖, ↑, ↗, →, ↘, ↓, ↙

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][N];
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        List<Cloud> cloudList, newCloudList;
        cloudList = new ArrayList<>();
        cloudList.add(new Cloud(N - 2, 0));
        cloudList.add(new Cloud(N - 2, 1));
        cloudList.add(new Cloud(N - 1, 0));
        cloudList.add(new Cloud(N - 1, 1));
        int d, s;
        for (int order = 0; order < M; order++) {
            st = new StringTokenizer(br.readLine());
            d = Integer.parseInt(st.nextToken());
            s = Integer.parseInt(st.nextToken());
            for (Cloud cloud : cloudList) {
                getNextLocation(cloud, d, s);
            }
            for (Cloud cloud : cloudList) {
                map[cloud.r][cloud.c]++;
            }
            for (Cloud cloud : cloudList) {
                int cnt = 0;
                for (int i = 2; i <= 8; i += 2) {
                    int nr = cloud.r + dir[i][0];
                    int nc = cloud.c + dir[i][1];
                    if (check(nr, nc) || map[nr][nc] == 0) continue;
                    cnt++;
                }
                map[cloud.r][cloud.c] += cnt;
            }
            newCloudList = new ArrayList<>();
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if (map[r][c] < 2) continue;
                    Cloud cloud = new Cloud(r, c);
                    if (cloudList.contains(cloud)) continue;
                    newCloudList.add(new Cloud(r, c));
                    map[r][c] -= 2;
                }
            }
            cloudList = newCloudList;
        }
        int answer = 0;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                answer += map[r][c];
            }
        }
        System.out.println(answer);
    }

    private static void getNextLocation(Cloud cloud, int d, int s) {
        int r = cloud.r + dir[d][0] * s;
        int c = cloud.c + dir[d][1] * s;
        while (r < 0 || r >= N) {
            if (r >= N) r -= N;
            else r += N;
        }
        while (c < 0 || c >= N) {
            if (c >= N) c -= N;
            else c += N;
        }
        cloud.r = r;
        cloud.c = c;
    }

    private static boolean check(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= N;
    }

    private static class Cloud {
        int r;
        int c;

        private Cloud(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public boolean equals(Object obj) {
            Cloud o = (Cloud) obj;
            return this.r == o.r && this.c == o.c;
        }

        @Override
        public int hashCode() {
            return this.r * (N + 2) + this.c;
        }
    }
}