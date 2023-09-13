import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class Main {
    static int N, ans;
    static int[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        arr = new int[N+1][2];
        for(int i=1; i<arr.length; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        for(int i =1; i<arr.length; i++) {
            int nextDay = arr[i][0]+i; //i날짜에 해당일을 했을 경우 끝나는 날짜
            if(nextDay > arr.length) continue; //퇴사일을 넘길 경우
            dfs(nextDay , arr[i][1]); //퇴사일을 넘기지 않을 경우
        }
        System.out.println(ans);
    }
    private static void dfs(int idx, int money) { //idx : 현재 날짜
        for(int i=idx; i<arr.length; i++) { //끝난 시점을 기준으로 한 개씩 선택
            int cur = arr[i][0] + i; //i일을 기준으로 끝나는 날짜
            if(cur <= arr.length) { //선택한 일이 가능한 일이라면
                dfs(cur, money + arr[i][1]); //일 선택(가지치기)
            }
        }
        ans = Math.max(ans, money); //결과값 비교 및 갱신
    }
}
