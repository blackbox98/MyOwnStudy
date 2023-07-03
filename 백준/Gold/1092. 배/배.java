import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean fail = false;
        List<Integer> cranes = new ArrayList<>();
        List<Integer> boxes = new ArrayList<>();
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            cranes.add(Integer.parseInt(st.nextToken()));
        }
        cranes.sort(Collections.reverseOrder());
        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int box = Integer.parseInt(st.nextToken());
            if (cranes.get(0) < box) {
                fail = true;
                break;
            }
            boxes.add(box);
        }
        long answer = 0;
        if (!fail) {
            boxes.sort(Collections.reverseOrder());
            while (!boxes.isEmpty()) {
                int craneIdx = 0;
                int boxIdx = 0;
                while (craneIdx < N) {
                    if (boxIdx == boxes.size() || boxes.isEmpty()) break;
                    if (cranes.get(craneIdx) >= boxes.get(boxIdx)) {
                        boxes.remove(boxIdx);
                        craneIdx++;
                    } else boxIdx++;
                }
                answer++;
            }
        } else answer = -1;
        System.out.println(answer);
    }
}