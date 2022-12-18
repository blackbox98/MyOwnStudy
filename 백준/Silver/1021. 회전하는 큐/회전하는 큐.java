import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static ArrayList<Integer> list, target;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int ans = 0;
        int N = Integer.parseInt(st.nextToken());
        list = new ArrayList<>();
        int M = Integer.parseInt(st.nextToken());
        target = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            list.add(i);
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            target.add(Integer.parseInt(st.nextToken()));
        }
        for (int n : target) {
            while (list.indexOf(n) != 0) {
                if (list.indexOf(n) <= list.size() / 2) {
                    turnLeft();
                } else {
                    turnRight();
                }
                ans++;
            }
            popFirst();
        }
        System.out.println(ans);
    }

    private static void turnLeft() {
        int tmp = list.get(0);
        list.remove(0);
        list.add(tmp);
    }

    private static void turnRight() {
        int tmp = list.get(list.size() - 1);
        list.remove(list.size() - 1);
        list.add(0, tmp);
    }

    private static void popFirst() {
        list.remove(0);
    }
}