import java.io.*;
import java.util.*;

//BOJ_1300 K번째 큰 수
public class Main {
    static int N, K;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine()); //수열의 크기


        //접근법 :


        K = Integer.parseInt(br.readLine());

        System.out.println(BinarySearch(K));

    }

    /*
    * K값은 즉 구하고자 하는 값 x보다 작은 원소의 개수를 뜻함
    * 이분탐색을 통해 K보다 같거나 큰값이 처음 나오는 위치를 구하면 됨.
    * 비교하는 값은 cnt로 임의의 값 mid보다 작은 원소의 개수를 구한 후 비교 진행
    */
    private static int BinarySearch(int key) {
        int low = 1;
        int high = key;

        /* LowerBound */
        while(low < high) {
            final int mid = (low+high) / 2;

            long cnt = 0;

            for(int i=1; i<=N; i++) {       //mid값보다 작은 원소의 개수를 구하는 과정
                cnt += Math.min(mid/i,N);       //각 행에서 mid보다 작은 원소의 개수는 최대 N개를 넘어가지 않기 때문에 비교
            }

            if(cnt >= key) {        //key값보다 작은 원소가 더 많을 경우 범위를 줄여서 탐색
                high = mid;
            }
            else {                  //key값보다 작은 원소가 적다면 범위를 올려서 탐색
                low = mid + 1;
            }


        }
        return low;
    }
}