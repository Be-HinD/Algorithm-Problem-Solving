import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

//BOJ_1644 소수의 연속합
public class Main {
    static int N, M;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(br.readLine());   //수열의 개수
//        M = Integer.parseInt(st.nextToken());   //타겟 넘버

        //N은 최소 1, 최대 4,000,000


        List<Integer> list = new ArrayList<>();
        for(int i=2; i<=N; i++) {
            //i가 소수인지 판별
            //i가 소수라면 list에 추가
            if(isPrime(i)) list.add(i);
        }
        int[] prime = new int[list.size()];
        for(int i=0; i<list.size(); i++) {
            prime[i] = list.get(i);
        }

        //prime == 소수 배열
        int l,r,sum;
        l = r = sum = 0;

        int res = 0;
        while(true) {
            if(sum >= N) {
                sum -= prime[l++];
            }
            else if(r == prime.length) break;
            else {
                //sum이 작을 경우
                sum += prime[r++];
            }
            if(sum == N) res++;
        }
        System.out.println(res);
    }

    private static boolean isPrime(int num) {
        for(int i=2; i*i<=num; i++) {
            if(num%i == 0) return false;
        }
        return true;
    }
}