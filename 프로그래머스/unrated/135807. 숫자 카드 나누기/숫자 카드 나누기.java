import java.util.Arrays;

class Solution {
    public int solution(int[] arrayA, int[] arrayB) {
        Arrays.sort(arrayA);
        Arrays.sort(arrayB);
        int gcdA = arrayA[0];
        int gcdB = arrayB[0];
        int size = arrayA.length;
        for (int i = 1; i < size; i++) {
            gcdA = gcd(gcdA, arrayA[i]);
            gcdB = gcd(gcdB, arrayB[i]);
        }
        boolean flagA = true;
        boolean flagB = true;
        for (int i = 0; i < size; i++) {
            if (flagA && arrayB[i] % gcdA == 0) flagA = false;
            if (flagB && arrayA[i] % gcdB == 0) flagB = false;
            if (!flagA && !flagB) return 0;
        }
        if (flagA && flagB) return Math.max(gcdA, gcdB);
        else if (flagA) return gcdA;
        else return gcdB;
    }
    
    private static int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
}