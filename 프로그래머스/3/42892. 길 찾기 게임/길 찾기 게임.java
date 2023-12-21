import java.util.*;

class Solution {
    int[][] answer;
    Node[] nodes;
    int idx;
    
    public class Node implements Comparable<Node> {
        int x;
        int y;
        int n;
        Node left;
        Node right;
        
        public Node(int x, int y, int n, Node left, Node right) {
            this.x = x;
            this.y = y;
            this.n = n;
            this.left = left;
            this.right = right;
        }
        
        @Override
        public int compareTo(Node o) {
            if (this.y == o.y) return this.x - o.x;
            else return o.y - this.y;
        }
    }
    
    public int[][] solution(int[][] nodeinfo) {
        int size = nodeinfo.length;
        answer = new int[2][size];
        nodes = new Node[size];
        for (int i = 0; i < size; i++) {
            nodes[i] = new Node(nodeinfo[i][0], nodeinfo[i][1], i + 1, null, null);
        }
        Arrays.sort(nodes);
        for (int i = 1; i < size; i++) {
            insertNode(nodes[0], nodes[i]);
        }
        idx = 0;
        preOrder(nodes[0]);
        idx = 0;
        postOrder(nodes[0]);
        return answer;
    }
    
    public void insertNode(Node parent, Node child) {
        if (parent.x > child.x) {
            if (parent.left == null) parent.left = child;
            else insertNode(parent.left, child);
        } else {
            if (parent.right == null) parent.right = child;
            else insertNode(parent.right, child);
        }
    }
    
    public void preOrder(Node node) {
        if (node != null) {
            answer[0][idx++] = node.n;
            preOrder(node.left);
            preOrder(node.right);
        }
    }
    
    public void postOrder(Node node) {
        if (node != null) {
            postOrder(node.left);
            postOrder(node.right);
            answer[1][idx++] = node.n;
        }
    }
}