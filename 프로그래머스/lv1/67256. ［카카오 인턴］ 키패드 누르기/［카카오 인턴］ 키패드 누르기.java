class Solution {
    public String solution(int[] numbers, String hand) {
        StringBuilder sb = new StringBuilder();
        int[] leftHand = {3, 0};
        int[] rightHand = {3, 2};
        for (int n : numbers) {
            int push = 0;
            int leftDist = Math.abs(leftHand[0] - (n == 0 ? 3 : n / 3)) + Math.abs(leftHand[1] - 1);
            int rightDist = Math.abs(rightHand[0] - (n == 0 ? 3 : n / 3)) + Math.abs(rightHand[1] - 1);
            if (n == 0) {
                if (leftDist == rightDist) {
                    if (hand.equals("left")) {
                        push = -1;
                    } else {
                        push = 1;
                    }
                } else if (leftDist > rightDist) {
                    push = 1;
                } else {
                    push = -1;
                }
            }
            else if (n % 3 == 0) {
                sb.append("R");
                rightHand[0] = n / 3 - 1;
                rightHand[1] = 2;
            }
            else if (n % 3 == 1) {
                sb.append("L");
                leftHand[0] = n / 3;
                leftHand[1] = 0;
            }
            else {
                if (leftDist == rightDist) {
                    if (hand.equals("left")) {
                        push = -1;
                    } else {
                        push = 1;
                    }
                } else if (leftDist > rightDist) {
                    push = 1;
                } else {
                    push = -1;
                }
            }
            if (push == -1) {
                sb.append("L");
                leftHand[0] = n == 0 ? 3 : n / 3;
                leftHand[1] = 1;
            } else if (push == 1){
                sb.append("R");
                rightHand[0] = n == 0 ? 3 : n / 3;
                rightHand[1] = 1;
            }
        }
        return sb.toString();
    }
}