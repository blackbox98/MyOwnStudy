import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static final int N = 3;
    static final String SUCCESS = "123456780";
    static Map<String, Integer> map;
    static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 우 하 좌 상

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder start = new StringBuilder();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                start.append(st.nextToken());
            }
        }
        map = new HashMap<>();
        map.put(start.toString(), 0);
        System.out.println(movePuzzle(start.toString()));
    }

    private static int movePuzzle(String start) {
        Queue<String> queue = new LinkedList<>();
        queue.offer(start);
        while (!queue.isEmpty()) {
            String puzzle = queue.poll();
            int moveCnt = map.get(puzzle);
            if (puzzle.equals(SUCCESS)) return moveCnt;
            int zero = puzzle.indexOf('0');
            int zr = zero / N;
            int zc = zero % N;
            for (int[] d : dir) {
                int nr = zr + d[0];
                int nc = zc + d[1];
                if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
                String nPuzzle = moveZero(puzzle, zero, nr * 3 + nc);
                if (!map.containsKey(nPuzzle)) {
                    map.put(nPuzzle, moveCnt + 1);
                    queue.offer(nPuzzle);
                }
            }
        }
        return -1;
    }

    private static String moveZero(String puzzle, int from, int to) {
        StringBuilder nPuzzle = new StringBuilder(puzzle);
        char tmp = puzzle.charAt(to);
        nPuzzle.setCharAt(from, tmp);
        nPuzzle.setCharAt(to, '0');
        return nPuzzle.toString();
    }
}