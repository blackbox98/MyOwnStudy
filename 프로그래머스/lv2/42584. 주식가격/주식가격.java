import java.util.Stack;

class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        Stack<Price> stack = new Stack<>();
        for (int i = 0; i < prices.length; i++) {
            int price = prices[i];
            if (stack.isEmpty()) {
                stack.push(new Price(i, price));
                continue;
            }
            if (stack.peek().price <= price) {
                stack.push(new Price(i, price));
            } else {
                while (!stack.isEmpty() && stack.peek().price > price) {
                    Price p = stack.pop();
                    answer[p.idx] = i - p.idx;
                }
                stack.push(new Price(i, price));
            }
        }
        while (!stack.isEmpty()) {
            Price p = stack.pop();
            answer[p.idx] = prices.length - 1 - p.idx;
        }
        return answer;
    }
    
    private static class Price {
        int idx;
        int price;

        public Price(int idx, int price) {
            this.idx = idx;
            this.price = price;
        }
    }
}