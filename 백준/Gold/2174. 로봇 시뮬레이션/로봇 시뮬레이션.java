import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static String answer = "OK";
    static int A, B, N, M;
    static int[][] map;
    static List<Robot> list = new ArrayList<>();
    static Map<Character, Integer> dirMap = new HashMap<>();
    static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        map = new int[A][B];
        dirMap.put('N', 0);
        dirMap.put('E', 1);
        dirMap.put('S', 2);
        dirMap.put('W', 3);
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        list.add(new Robot(-1, -1, ' '));
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            char d = st.nextToken().charAt(0);
            list.add(new Robot(r, c, dirMap.get(d)));
            map[r][c] = i + 1;
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int robotNum = Integer.parseInt(st.nextToken());
            char order = st.nextToken().charAt(0);
            int s = Integer.parseInt(st.nextToken());
            if (!move(robotNum, order, s)) break;
        }
        System.out.println(answer);
    }

    private static boolean move(int robotNum, char order, int s) {
        boolean complete = true;
        Robot robot = list.get(robotNum);
        int r = robot.r;
        int c = robot.c;
        int d = robot.d;
        if (order == 'F') {
            for (int i = 0; i < s; i++) {
                int nr = r + dir[d][0];
                int nc = c + dir[d][1];
                if (nr >= 0 && nr < A && nc >= 0 && nc < B) {
                    if (map[nr][nc] != 0) {
                        complete = false;
                        answer = "Robot " + robotNum + " crashes into robot " + map[nr][nc];
                        break;
                    }
                    r = nr;
                    c = nc;
                } else {
                    complete = false;
                    answer = "Robot " + robotNum + " crashes into the wall";
                    break;
                }
            }
            map[robot.r][robot.c] = 0;
            map[r][c] = robotNum;
            list.get(robotNum).r = r;
            list.get(robotNum).c = c;
        } else {
            for (int i = 0; i < s % 4; i++) {
                d = getDir(order, d);
            }
            list.get(robotNum).d = d;
        }
        return complete;
    }

    private static int getDir(char order, int d) {
        switch (order) {
            case 'L':
                d--;
                if (d < 0) d = 3;
                break;
            case 'R':
                d++;
                if (d > 3) d = 0;
                break;
        }
        return d;
    }

    private static class Robot {
        int r;
        int c;
        int d;

        private Robot(int r, int c, int d) {
            this.r = r;
            this.c = c;
            this.d = d;
        }
    }
}