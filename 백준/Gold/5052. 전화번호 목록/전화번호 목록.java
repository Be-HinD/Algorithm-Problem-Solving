import java.io.*;
import java.util.*;

//BOJ_5052 전화번호 목록

public class Main {
    static class Node {
        Map<Character, Node> childNode = new HashMap<>();
        boolean endOfword;  //해당 단어 이후의 문자의 유무

        public Node() {

        }
        public Node(boolean endOfword) {
            this.endOfword = endOfword;
        }
    }
    static class TRIE {
        Node root = new Node();

        void insert(String str) {
            Node node = this.root;
            for(int i=0; i<str.length(); i++) {
                node = node.childNode.computeIfAbsent(str.charAt(i), key -> new Node(false));
            }
            node.endOfword = true;
        }

        //찾고자하는 str의 끝 문자열 이후 노드가 있는지
        boolean search(String str) {
            Node node = this.root;

            for(int i=0; i<str.length(); i++) {
                node = node.childNode.getOrDefault(str.charAt(i), null);
                if(node == null) {
                    return false;
                }
            }

            if(node.childNode.isEmpty()) {
                return true;
            }
            return false;
        }
    }
    static int T, N;
    static String[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());    //Test Case


        for(int tc=0; tc<T; tc++) {
            N = Integer.parseInt(br.readLine());

            TRIE trie = new TRIE();

            arr = new String[N];
            for(int i=0; i<N; i++) {
                String input = br.readLine();
                arr[i] = input;
                trie.insert(input);
            }

            Arrays.sort(arr);

            boolean flag = true;
            for(int i=0; i<arr.length; i++) {
                if(!trie.search(arr[i])) {
                    flag = false;
                    break;
                }
            }
            if(flag) {
                sb.append("YES").append("\n");
            }
            else {
                sb.append("NO").append("\n");
            }
        }

        System.out.println(sb);

    }
}