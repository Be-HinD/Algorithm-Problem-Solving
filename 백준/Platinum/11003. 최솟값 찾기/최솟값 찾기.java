import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Deque<Integer> deque = new LinkedList<>();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            // 현재 윈도우에 포함되지 않는 인덱스 제거
            if (!deque.isEmpty() && deque.peekFirst() < i - L + 1) {
                deque.pollFirst();
            }

            // 현재 값보다 큰 값들을 뒤에서 제거
            while (!deque.isEmpty() && arr[deque.peekLast()] > arr[i]) {
                deque.pollLast();
            }

            deque.offerLast(i);

            // 현재 윈도우의 최솟값 추가
            sb.append(arr[deque.peekFirst()]).append(" ");
        }

        System.out.println(sb.toString().trim());
    }
}
