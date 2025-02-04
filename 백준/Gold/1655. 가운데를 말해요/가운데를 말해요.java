import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
//BOJ_1655 가운데를 말해요
public class Main {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> minPq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        PriorityQueue<Integer> maxPq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });


        //maxPq는 항상 중앙값을 포함한 큐여야함
        //minPq는 중앙값보다 작은 애들을 추가

        for(int i=0; i<N; i++) {
            int idx = Integer.parseInt(br.readLine());

            if(minPq.isEmpty() && maxPq.isEmpty()) { //두 개의 큐가 모두 비어있을 경우
                maxPq.offer(idx);
            }
            else {
                if(idx <= maxPq.peek()) { //중앙값보다 같거나 작을 때
                    maxPq.offer(idx);

                }
                else {
                    minPq.offer(idx);
                }
            }

            //두 개의 큐 사이즈를 맞춰줘야함
            if(maxPq.size() < minPq.size()) {
                maxPq.offer(minPq.poll());
            }
            else if(maxPq.size() > minPq.size() + 1) {
                minPq.offer(maxPq.poll());
            }

            sb.append(maxPq.peek()).append("\n");
        }

        System.out.println(sb);
    }
}