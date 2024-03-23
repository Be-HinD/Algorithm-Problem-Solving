import java.io.*;
import java.util.*;

//BOJ_1940 주몽
public class Main {
    static int N, M;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());   //재료의 개수
        M = Integer.parseInt(br.readLine());    //타겟 넘버

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        //두 개의 조합으로 M을 생성
        Arrays.sort(arr);

        int l = 0;
        int r = arr.length-1;

        int res = 0;
        while(l<r) {
            int sum = arr[l] + arr[r];

            if(sum > M) r--;
            else if(sum < M) l++;
            else {
                res++;
                l++;
            }
        }

        System.out.println(res);

    }
}