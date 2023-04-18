class Solution {
    public long[] solution(long[] numbers) {
        long[] answer = new long[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            long start = numbers[i];
            if (start % 2 == 0) {
                answer[i] = start + 1;
                continue;
            }
            String bits = Long.toBinaryString(start);
            int zeroIdx = bits.lastIndexOf("0");
            if (zeroIdx == -1) {
                bits = "10" + bits.substring(1);
            } else {
                bits = bits.substring(0, zeroIdx) + "10" + bits.substring(zeroIdx + 2);
            }
            answer[i] = Long.parseLong(bits, 2);
        }
        return answer;
    }
}