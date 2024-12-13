import java.io.*;
import java.util.*;

//BOJ_30827
class Main {
    static int N, K, res;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        /**
         * 접근법
         * 회의가 끝난 즉시 같은 회의실에서 다른 회의를 시작할 수 없다. 끝난시점+1
         * 시작시간을 기준으로 정렬
         * 반례 : 정렬기준이 곧 정답인가?
         * **/

        N = Integer.parseInt(st.nextToken()); //회의
        K = Integer.parseInt(st.nextToken()); //회의실

        int[] room = new int[K];

        int[][] arr = new int[N][2];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr, ((o1, o2) -> {
            if(o1[1] == o2[1]) return o1[0] - o2[0];
            return o1[1] - o2[1];
        }));

        for(int[] idx : arr) {
            for(int i=K-1; i>=0; i--) { //보다 제일 낮은 값
                if(room[i] < idx[0]) {
                    room[i] = idx[1];
                    res++;
                    break;
                }
            }
            Arrays.sort(room);
        }

        System.out.println(res);
    }
}