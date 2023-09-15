import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    static int N, nextBottom, answer = 0;
    static int[][] dice;
    
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dice = new int[N + 1][6];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 6; j++) {
                dice[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < 6; i++) {
            nextBottom = getOpposite(i);
            setDice(1, dice[0][nextBottom], getMax(0, i, nextBottom));
        }
        System.out.println(answer);
    }
    
    private static void setDice(int diceNum, int postTop, int sum) {
        if (diceNum == N) {
            answer = Math.max(answer, sum);
            return;
        }
        for (int i = 0; i < 6; i++) {
            if (dice[diceNum][i] == postTop) {
                nextBottom = getOpposite(i);
                setDice(diceNum + 1, dice[diceNum][nextBottom], sum + getMax(diceNum, i, nextBottom));
                break;
            }
        }
        
    }
    
    private static int getOpposite(int idx) {
        int target = -1;
        switch (idx) {
            case 0:
                target = 5;
                break;
            case 1:
                target = 3;
                break;
            case 2:
                target = 4;
                break;
            case 3:
                target = 1;
                break;
            case 4:
                target = 2;
                break;
            case 5:
                target = 0;
                break;
        }
        return target;
    }
    
    private static int getMax(int n, int bottom, int top) {
        int result = 0;
        for (int i = 0; i < 6; i++) {
            if (i == bottom || i == top) continue;
            result = Math.max(result, dice[n][i]);
        }
        return result;
    }
}