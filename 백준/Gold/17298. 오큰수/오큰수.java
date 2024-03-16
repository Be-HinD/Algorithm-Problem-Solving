import java.io.*;
import java.util.*;

//BOJ_8980 택배
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        /*
        * 접근법
        * 정렬이 안되있는 배열 기준이라 이분탐색은 불가능
        * 현재 포인터 i 기준으로 오른쪽 값들을 탐색
        * 자료구조, 스택 문제
        *
        * */

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Stack<Integer> stack = new Stack<>();

        for(int i=0; i<N; i++) {

            while(!stack.isEmpty() && arr[stack.peek()] < arr[i]) {
                arr[stack.pop()] = arr[i];
            }

            stack.push(i);
        }

        while(!stack.isEmpty()) {
            arr[stack.pop()] = -1;
        }

        for(int i=0; i<N; i++) {
            sb.append(arr[i]).append(" ");
        }

        System.out.println(sb);
    }
}