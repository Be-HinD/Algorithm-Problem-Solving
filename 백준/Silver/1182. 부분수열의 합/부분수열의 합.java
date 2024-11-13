import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.*;

//BOJ_22115
public class Main {
    static int N, S, res;
    static int[] arr;
    static boolean[] v;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        v = new boolean[N];
        powerSet(0);

        System.out.println(res);
    }
    private static void powerSet(int cnt) { //현재까지 처리한 원소개수
        if(cnt == arr.length) { //선택된 원소의 개수가 원소개수 N에 도달했을 때
            // 부분집합 완성
            int sum = 0;
            boolean flag = true;
            for(int i=0; i<arr.length; i++) {
                if(v[i]) {
                    sum += arr[i];
                    flag = false;
                }
            }
            if(sum == S && !flag) res++;
        } else {
            v[cnt] = true; // 현재 원소를 선택했을 경우
            powerSet(cnt + 1);
            v[cnt] = false; //현재 원소를 선택하지않았을 경우
            powerSet(cnt + 1);
        }
    }
}