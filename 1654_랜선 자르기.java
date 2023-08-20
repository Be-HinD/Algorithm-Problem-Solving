import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class Main {
    static int K, N, MaxLength;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        K = Integer.parseInt(st.nextToken()); //랜선의 개수 1<=K<=10,000
        N = Integer.parseInt(st.nextToken()); //필요한 랜선의 개수 1<=N<=1,000,000
        //항상 K<=N, 랜선의 길이<=2^31-1

        //랜선 K개의 길이를 담는 배열 입력
        arr = new int[K];
        for(int i=0; i<K; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            MaxLength = Math.max(MaxLength, arr[i]); //입력 받으면서 랜선 길이 최대값 찾기
        }

        BinarySearch(MaxLength, N); //이분 탐색
    }
    //이분탐색 : x<y의 조건에서 특정값 x부터 y사이에서 원하는 값을 얻기 위한 로직
    private static void BinarySearch(int MaxLength, int key) { //MaxLength : 주어진 K개의 랜선 중 최대 길이, key : 만들 랜선의 개수
        long low = 1; //0으로 초기화 시 mid가 0이 되고 cnt를 구하는 과정에서 arr[i]를 0으로 나누게 될 수 있기에 1로 초기화
        long high = MaxLength; //들어올 값이 2^31-1을 초과할 경우가 있으므로 long Type선언
        while(low <= high) {
            final long mid = (high + low)/2; //중간 길이부터 자르기 시작
            int cnt = 0; //자른 랜선 개수
            for(int idx : arr) { //각각의 랜선을 mid값만큼 자른 개수를 cnt에 저장 **향상 for문 사용
                cnt += idx / mid;
            }
            if(cnt >= key) { //자른 랜선의 개수가 필요한 랜선의 개수 이상일 경우
                low = mid + 1;
            }
            else { //자른 랜선의 개수가 필요한 랜선의 개수 미만일 경우
                high = mid - 1;
            }
            //반례 : 필요한 랜선의 개수 key만큼 잘리는 경우가 최적해이지만 key만큼 잘리는 경우가
            //여러개 있을 수 있기 때문에 cnt == key의 경우는 비교할 수 없음.
            //또한 N개보다 많이 만드는 것도 N개를 만드는 것에 포함된다는 조건이 있으므로 유추가능.
        }
        System.out.println(high); //결과 출력
    }
}
