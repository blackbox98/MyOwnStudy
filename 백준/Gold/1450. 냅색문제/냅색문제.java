import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, C, idx;
    static int[] things;
    static List<Integer> beforeList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        things = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            things[i] = Integer.parseInt(st.nextToken());
        }
        beforeList = new ArrayList<>();
        List<Integer> afterList = new ArrayList<>();
        addToList(beforeList, 0, N / 2, 0);
        addToList(afterList, N / 2, N, 0);
        Collections.sort(beforeList);
        int answer = 0;
        int size = beforeList.size() - 1;
        for (int thing : afterList) {
            idx = -1;
            binarySearch(0, size, thing);
            answer += idx + 1;
        }
        System.out.println(answer);
    }

    private static void addToList(List<Integer> list, int idx, int end, int sum) {
        if (sum > C) return;
        if (idx == end) {
            list.add(sum);
            return;
        }
        addToList(list, idx + 1, end, sum + things[idx]);
        addToList(list, idx + 1, end, sum);
    }

    private static void binarySearch(int left, int right, int thing) {
        if (left > right) return;
        int mid = (left + right) / 2;
        if (beforeList.get(mid) + thing > C) binarySearch(left, mid - 1, thing);
        else {
            idx = mid;
            binarySearch(mid + 1, right, thing);
        }
    }
}