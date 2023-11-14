import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//BOJ_1477 휴게소 세우기
public class Main {
    static int N, M, L, res;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); //현재 휴게소 개수
        M = Integer.parseInt(st.nextToken()); //더 지으려는 휴개소 개수
        L = Integer.parseInt(st.nextToken()); //고속도로 길이

        //이미 휴게소가 있는 곳에 휴게소를 또 세울 수 없고,
        //고속도로의 끝에도 휴게소를 세울 수 없다.
        arr = new int[N+1]; //+1 이유는 휴게소의 마지막과 고속도로의 끝지점의 거리도 비교해야하기 때문

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        arr[N] = L;
        //정렬
        Arrays.sort(arr);

        //이분탐색
        binarySearch();


    }

    private static void binarySearch() {
        //휴게소의 위치 범위만큼 지정
        int low = 1;
        int high = L-1;

        //M개의 휴게소를 가지고 줄일 수 있는 최소값(mid)을 상정하며 비교
        while(low <= high) {
            final int mid = low + (high-low)/2; //최소거리 (즉 출력값 상정)

            //mid 거리만큼 휴게소를 설치
            int cnt = 0; //설치된 휴게소 개수
            int dist = 0; //최초 시작점
            for(int i=0; i<arr.length; i++) {
                int diff = arr[i] - dist;
                dist = arr[i];
                if(diff > mid) {
                    cnt += (diff-1)/mid;
                    //mid == 70, diff == 140 then 100 - 170 - 240 === 170지역 한개면 충분하기 때문에 -1한 값에 mid값을 나눈 값을 증가
                }
            }

            if(cnt > M) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        System.out.println(low);
    }
}