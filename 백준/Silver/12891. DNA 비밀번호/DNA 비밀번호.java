import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static Set<Character> alpha_Set = new HashSet<>(Arrays.asList('A', 'C', 'G', 'T'));
    static String DNA_String;
    static int target_Size;
    static int alpha_Size = 4;
    static int[] alpha_Num = new int[alpha_Size]; // 0 : A, 1 : C, 2 : G, 3 : T
    static int[] nums = new int[alpha_Size];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int answer = 0;
        int whole_Size = Integer.parseInt(st.nextToken());
        target_Size = Integer.parseInt(st.nextToken());
        DNA_String = br.readLine();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < alpha_Num.length; i++) {
            alpha_Num[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i < target_Size; i++) {
            char c = DNA_String.charAt(i);
            if (!alpha_Set.contains(c)) continue;
            nums[getIdx(c)]++;
        }
        if (meet_Condition(0) && checkNum()) answer++;
        int removeIdx = 0;
        for (int i = target_Size; i < whole_Size; i++) {
            char remove = DNA_String.charAt(removeIdx++);
            if (alpha_Set.contains(remove)) nums[getIdx(remove)]--;
            char add = DNA_String.charAt(i);
            if (alpha_Set.contains(add)) nums[getIdx(add)]++;
            if (meet_Condition(i) && checkNum()) answer++;
        }
        System.out.println(answer);
    }

    private static int getIdx(char c) {
        int result = -1;
        switch (c) {
            case 'A':
                result = 0;
                break;
            case 'C':
                result = 1;
                break;
            case 'G':
                result = 2;
                break;
            case 'T':
                result = 3;
                break;
        }
        return result;
    }

    private static boolean meet_Condition(int start) {
        for (int i = start; i < target_Size; i++) {
            char c = DNA_String.charAt(i);
            if (!alpha_Set.contains(c)) return false;
        }
        return true;
    }

    private static boolean checkNum() {
        for (int i = 0; i < alpha_Size; i++) {
            if (alpha_Num[i] > nums[i]) return false;
        }
        return true;
    }
}