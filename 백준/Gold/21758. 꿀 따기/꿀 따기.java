import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] honeys, LToR, RToL;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        honeys = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            honeys[i] = Integer.parseInt(st.nextToken());
        }
        LToR = new int[N];
        RToL = new int[N];
        LToR[0] = honeys[0];
        RToL[N - 1] = honeys[N - 1];
        for (int i = 1; i < N; i++) {
            LToR[i] = LToR[i - 1] + honeys[i];
            RToL[N - i - 1] = RToL[N - i] + honeys[N - i - 1];
        }
        /*
        벌1 : 1
        벌2 : 2
        벌통 : 3
         */
        int answer = Math.max(Math.max(case123(), case321()), case132());
        System.out.println(answer);
    }

    private static int case123() {
        int bee1 = LToR[N - 1] - LToR[1];
        int bee2 = bee1;
        int result = bee1 + bee2;
        bee1 += honeys[1];
        for (int i = 2; i < N; i++) {
            bee2 -= honeys[i];
            result = Math.max(result, bee1 + bee2 - honeys[i]);
        }
        return result;
    }

    private static int case321() {
        int bee1 = RToL[0] - RToL[N - 2];
        int bee2 = bee1;
        int result = bee1 + bee2;
        bee1 += honeys[N - 2];
        for (int i = N - 3; i >= 0; i--) {
            bee2 -= honeys[i];
            result = Math.max(result, bee1 + bee2 - honeys[i]);
        }
        return result;
    }

    private static int case132() {
        int bee1;
        int bee2;
        int result = 0;
        for (int i = 1; i < N - 1; i++) {
            bee1 = LToR[i] - honeys[0];
            bee2 = RToL[i] - honeys[N - 1];
            result = Math.max(result, bee1 + bee2);
        }
        return result;
    }
}