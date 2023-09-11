import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static final int limit = 100;
    static int[][] map;
    static Map<Integer, Integer> checkNumMap;
    static List<Integer> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken()) - 1;
        int c = Integer.parseInt(st.nextToken()) - 1;
        int k = Integer.parseInt(st.nextToken());
        map = new int[limit][limit];
        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(calc(r, c, k));
    }

    private static int calc(int r, int c, int k) {
        int answer = 0;
        int maxR = 3;
        int maxC = 3;
        int max, n, idx;
        int[][] tmpMap;
        while (answer <= limit) {
            if (map[r][c] == k) break;
            tmpMap = new int[limit][limit];
            max = 0;
            if (maxR >= maxC) { // R 연산
                for (int i = 0; i < maxR; i++) {
                    checkNumMap = new HashMap<>();
                    for (int j = 0; j < maxC; j++) {
                        n = map[i][j];
                        if (n == 0) continue;
                        if (checkNumMap.containsKey(n)) {
                            checkNumMap.put(n, checkNumMap.get(n) + 1);
                        } else checkNumMap.put(n, 1);
                    }
                    list = new ArrayList<>(checkNumMap.keySet());
                    list.sort(comp);
                    idx = 0;
                    for (int num : list) {
                        tmpMap[i][idx++] = num;
                        tmpMap[i][idx++] = checkNumMap.get(num);
                        if (idx == limit) {
                            idx--;
                            break;
                        }
                    }
                    max = Math.max(max, idx);
                }
                maxC = max;
            } else { // C 연산
                for (int i = 0; i < maxC; i++) {
                    checkNumMap = new HashMap<>();
                    for (int j = 0; j < maxR; j++) {
                        n = map[j][i];
                        if (n == 0) continue;
                        if (checkNumMap.containsKey(n)) {
                            checkNumMap.put(n, checkNumMap.get(n) + 1);
                        } else checkNumMap.put(n, 1);
                    }
                    list = new ArrayList<>(checkNumMap.keySet());
                    list.sort(comp);
                    idx = 0;
                    for (int num : list) {
                        tmpMap[idx++][i] = num;
                        tmpMap[idx++][i] = checkNumMap.get(num);
                        if (idx == limit) {
                            idx--;
                            break;
                        }
                    }
                    max = Math.max(max, idx);
                }
                maxR = max;
            }
            map = tmpMap;
            answer++;
        }
        return answer > limit ? -1 : answer;
    }

    private static Comparator<Integer> comp = new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            if (checkNumMap.get(o1).equals(checkNumMap.get(o2))) {
                return o1 - o2;
            } else return checkNumMap.get(o1) - checkNumMap.get(o2);
        }
    };
}