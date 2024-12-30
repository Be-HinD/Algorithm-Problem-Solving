import java.util.*;

class Solution {
    static class Node {
        Node prev, next;
        int idx;

        Node(int idx) {
            this.idx = idx;
        }
    }

    public String solution(int n, int k, String[] cmd) {
        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(i);
            if (i > 0) {
                nodes[i].prev = nodes[i - 1];
                nodes[i - 1].next = nodes[i];
            }
        }

        Node current = nodes[k];
        Stack<Node> deleted = new Stack<>();

        for (String c : cmd) {
            char operation = c.charAt(0);

            if (operation == 'D') {
                int x = Integer.parseInt(c.substring(2));
                while (x-- > 0) current = current.next;
            } else if (operation == 'U') {
                int x = Integer.parseInt(c.substring(2));
                while (x-- > 0) current = current.prev;
            } else if (operation == 'C') {
                deleted.push(current);
                if (current.prev != null) current.prev.next = current.next;
                if (current.next != null) current.next.prev = current.prev;
                current = (current.next != null) ? current.next : current.prev;
            } else if (operation == 'Z') {
                Node node = deleted.pop();
                if (node.prev != null) node.prev.next = node;
                if (node.next != null) node.next.prev = node;
            }
        }

        StringBuilder result = new StringBuilder("O".repeat(n));
        while (!deleted.isEmpty()) {
            result.setCharAt(deleted.pop().idx, 'X');
        }

        return result.toString();
    }
}
