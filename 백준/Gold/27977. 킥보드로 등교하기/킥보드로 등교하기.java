import java.io.*;
import java.util.*;

//BOJ_27977
public class Main {
    static int L, N, K;
    static List<List<Integer>> list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        /**
         * 키워드
         * 배터리 1 소모 = 1만큼 이동
         * 배터리 x -> 충전소에서 충전
         * 최대 K번 충전소 방문가능 (가득 충전한 상태로 출발)
         * 가격 == 배터리 용량
         * 접근법
         * lowerbound 접근
         * mid 용량으로 갈 수 있는지 판단 -> 최대한 갈 수 있는 충전소까지 이동 및 충전 후 다시 탐색
         * 현재위치+1 <-> mid 사이에 충전소가 있는지 여부 ->
         * 검증
         * 충전소 도착 시 용량이 0이되도 된다.
         * **/

        L = Integer.parseInt(st.nextToken()); //학교까지 거리
        N = Integer.parseInt(st.nextToken()); //충전소 개수 (최대 100,000)
        K = Integer.parseInt(st.nextToken()); //최대 방문횟수


        list = new ArrayList<>();
        for(int i=0; i<L; i++) {
            list.add(new ArrayList<>());
        }

        st = new StringTokenizer(br.readLine());
        int prev = Integer.parseInt(st.nextToken());
        list.get(0).add(prev);
        for(int i=1; i<N; i++) {
            int idx = Integer.parseInt(st.nextToken());
            list.get(prev).add(idx);
            prev = idx;
        }
        list.get(prev).add(L);


        System.out.println(lower());


    }
    static int lower() {
        int low = 0;
        int high = Integer.MAX_VALUE;
        while(low < high) {
            final int mid = low + (high - low) / 2;

            //mid용량으로 K번 방문하고 갈 수 있는지 확인
            int position = 0;
            int power = K;
            boolean flag = false;
            while(power >= 0) {
                int next = position + mid;
                if(next >= L) {
                    flag = true;
                    break;
                }

                if(power == 0) break;

                int nextEv = position;

                if(list.get(nextEv).get(0) > next) break;   //다음 주유소 X
                while(true) {   //현재위치 + mid 이전 갈 수 있는 충전소를 탐색
                    if(list.get(nextEv).get(0) <= next) {
                        nextEv = list.get(nextEv).get(0);
                        continue;
                    }
                    break;
                }

                position = nextEv; //현재위치 -> 다음 충전소
                power--; //방문횟수감소
            }
            
            if(flag) {
                high = mid;
            }
            else {
                low = mid + 1;
            }
        }

        return high;
    }

}
