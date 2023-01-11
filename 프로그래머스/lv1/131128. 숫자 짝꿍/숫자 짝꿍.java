class Solution {
    public String solution(String X, String Y) {
        StringBuilder answer = new StringBuilder();
        int[] xArr = new int[10];
        int[] yArr = new int[10];
        for (int i = 0; i < X.length(); i++) {
            xArr[Character.getNumericValue(X.charAt(i))]++;
        }
        for (int i = 0; i < Y.length(); i++) {
            yArr[Character.getNumericValue(Y.charAt(i))]++;
        }
        for (int i = 9; i >= 0; i--) {
            if (xArr[i] > 0 && yArr[i] > 0) {
                for (int j = 0; j < Math.min(xArr[i],yArr[i]); j++) {
                    answer.append(i);
                }
            }
        }
        if (answer.toString().equals("")) return "-1";
        else if (answer.charAt(0) == '0') return "0";
        else return answer.toString();
    }
}