import java.io.*;
import java.util.*;

//BOJ_1406 에디터
public class Main {
    static int N;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();


        Stack<Character> after = new Stack<>();     //커서 기준 앞 문자열
        Stack<Character> before = new Stack<>();    //커서 기준 뒤 문자열

        String init = br.readLine();

        for(int i=0; i<init.length(); i++) {
            after.push(init.charAt(i));
        }

        int N = Integer.parseInt(br.readLine());

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            char idx = st.nextToken().charAt(0);

            if(!st.hasMoreTokens()) {
                ChangeCursor(idx, after, before);
                continue;
            }

            char item = st.nextToken().charAt(0);

            after.push(item);

        }


        //결과 담기
        Stack<Character> res = new Stack<>();
        Queue<Character> q = new ArrayDeque<>();
        while(!before.isEmpty()) {
            q.offer(before.pop());
        }

        while(!after.isEmpty()) {
            res.push(after.pop());

        }

        while(!res.isEmpty()) {
            sb.append(res.pop());
        }
        while(!q.isEmpty()) {
            sb.append(q.poll());
        }

        System.out.println(sb);

    }

    private static void ChangeCursor(char idx, Stack<Character> after, Stack<Character> before) {
        if(idx == 'L') {    //왼쪽으로
            if(after.isEmpty()) return;
            before.push(after.pop());
        }
        else if(idx == 'D') {   //오른쪽으로
            if(before.isEmpty()) return;
            after.push(before.pop());
        }
        else if(idx == 'B'){
            if(after.isEmpty()) return;
            after.pop();
        }
    }
}