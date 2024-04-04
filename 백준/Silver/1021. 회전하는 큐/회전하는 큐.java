import java.io.*;
import java.util.*;

//BOJ_1021 회전하는 큐
public class Main {
    static int N, M;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());   //뽑아내려고 하는 수의 개수

        LinkedList<Integer> list = new LinkedList<>();

        for(int i=1; i<=N; i++) {
            list.offer(i);
        }

        st = new StringTokenizer(br.readLine());


        int res = 0;
        for(int i=0; i<M; i++) {
            int target = Integer.parseInt(st.nextToken());

            //target을 꺼내기 위해 2번과 3번 행동중에 무얼 해야하는지 판별
            /**
             * 1 2 3 4 5 중에 3을 꺼내려면
             * 왼쪽으로 회전해야 하는지
             * 오른쪽으로 회전해야 하는지
             * 특정 index를 기준으로 왼쪽과 오른쪽의 개수를 파악하고 있어야 함.
             * 3을 기준으로는 왼쪽일 경우 2번만 회전
             * 오른쪽으로는 3번 회전
             * **/
            while(true) {
                if(list.peek().equals(target)) {
                    list.poll();
                    break;
                }
                else {
                    if(list.indexOf(target) < (double) list.size() / 2) {
                        while(list.peek() != target) {
                            list.offerLast(list.pollFirst());
                            res++;
                        }
                    }
                    else {
                        while(list.peek() != target) {
                            list.offerFirst(list.pollLast());
                            res++;
                        }
                    }
                }
            }
        }

        System.out.println(res);
    }
}