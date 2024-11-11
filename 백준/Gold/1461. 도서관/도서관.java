import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//BOJ_1461
public class Main {
    static int N, M, res;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        /**
         * 키워드
         * 세준이는 현재 0에 있고
         *  마구 놓은 책도 전부 0에 있다
         *  각 책들의 원래 위치가 주어질 때, 책을 모두 제자리에 놔둘 때 드는 최소 걸음 수
         *   한 걸음에 좌표 1칸씩
         *   다시 0으로 돌아올 필요는 없다
         *   한 번에 최대 M권의 책
         * **/

        // 마지막으로 가는 곳은 왼쪽 오른쪽에서 더 큰 값
        // 더 작은 방향에 대한 로직
        // 최대한 끝까지 M개를 전달
        // 최대 방향 탐색
        // 작은 방향에 대해서 maxHeap -> pq.poll() * 2,


        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        //내림차순
        PriorityQueue<Integer> left = new PriorityQueue<>(((o1, o2) -> o2 - o1));
        PriorityQueue<Integer> right = new PriorityQueue<>(((o1, o2) -> o2 - o1));

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            int idx = Integer.parseInt(st.nextToken());
            if(idx < 0) left.offer(Math.abs(idx));
            else right.offer(idx);
        }

        if(left.isEmpty()) {
            //오른쪽에 대해서만
            res += right.poll();
            for(int i=0; i<M-1; i++) {  //M-1개만큼 제거
                if (right.isEmpty()) break;
                right.poll();
            }
            while(!right.isEmpty()) {
                res += right.poll()*2;
                for(int i=0; i<M-1; i++) {  //M-1개만큼 제거
                    if (right.isEmpty()) break;
                    right.poll();
                }
            }
            System.out.println(res);
            return;
        }
        else if(right.isEmpty()){
            res += left.poll();
            for(int i=0; i<M-1; i++) {  //M-1개만큼 제거
                if (left.isEmpty()) break;
                left.poll();
            }
            while(!left.isEmpty()) {
                res += left.poll()*2;
                for(int i=0; i<M-1; i++) {  //M-1개만큼 제거
                    if (left.isEmpty()) break;
                    left.poll();
                }
            }
            System.out.println(res);
            return;
        }
        else {
            if(left.peek() > right.peek()) {
                //오른쪽 최대값이 더 작다면 오른쪽 왕복
                res += left.poll();
                for(int i=0; i<M-1; i++) {
                    if(left.isEmpty()) break;
                    left.poll();
                }

                while(!right.isEmpty()) {
                    res += right.poll() * 2;
                    for(int i=0; i<M-1; i++) {  //M-1개만큼 제거
                        if(right.isEmpty()) break;
                        right.poll();
                    }
                }
                //왼쪽은 직진
                while(!left.isEmpty()) {
                    res += left.poll() * 2;
                    for(int i=0; i<M-1; i++) {  //M-1개만큼 제거
                        if (left.isEmpty()) break;
                        left.poll();
                    }
                }
            }
            else {
                //왼쪽 최대값이 더 작다면 왼쪽 왕복
                res += right.poll();
                for(int i=0; i<M-1; i++) {
                    if(right.isEmpty()) break;
                    right.poll();
                }
                while(!left.isEmpty()) {
                    res += left.poll() * 2;
                    for(int i=0; i<M-1; i++) {  //M-1개만큼 제거
                        if(left.isEmpty()) break;
                        left.poll();
                    }
                }
                while(!right.isEmpty()) {
                    res += right.poll() * 2;
                    for(int i=0; i<M-1; i++) {  //M-1개만큼 제거
                        if (right.isEmpty()) break;
                        right.poll();
                    }
                }
            }
        }

        System.out.println(res);
    }
}