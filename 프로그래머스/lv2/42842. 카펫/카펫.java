class Solution {
    static int total;
    
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        total = brown + yellow;
        int row = total;
        int column = 1;
        for (int i = (int) Math.sqrt(total); i >= 2; i--) {
            if (total % i == 0 && check(brown, i)) {
                row = total / i;
                column = i;
                break;
            }
        }
        answer[0] = row;
        answer[1] = column;
        return answer;
    }
    
    private static boolean check(int brown, int i) {
        int sum = (total / i) + i;
        boolean flag = false;
        while (true) {
            brown -= sum * 2 - 4;
            if (brown == 0) {
                flag = true;
                break;
            } else if (brown < 0) break;
            sum -= 4;
        }
        return flag;
    }
}