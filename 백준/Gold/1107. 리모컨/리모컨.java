import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class Main {
    static int N, M, ans;
    static int[] arr = new int[10];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        for(int i=0; i<10; i++) {
            arr[i] = i; //버튼 개수만큼 배열 생성
        }

        if(M != 0) { //고장난 버튼이 없을 경우 예외처리
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                int idx = Integer.parseInt(st.nextToken());
                arr[idx] = -1; //고장난 버튼은 -1로 초기화
            }
        }
        ans = Math.abs(N-100); //값 초기화 : 목표값에서 -100을 뺀 값 즉, 시작번호 100에서 목표값까지의 + 혹은 - 버튼만 눌렀을 때의 결과값
        for(int i=0; i<=999999; i++) { //최대 입력값의 자리수는 10^5, 9버튼만 부서지지않을 경우의 최대값 : 999,999까지
            boolean check = false; //누르려는 버튼이 고장난지 체크할 변수
            String idx = String.valueOf(i); //누르려는 채널의 값을 String으로 변환
            for(int j=0; j<idx.length(); j++) {
                int item = idx.charAt(j) - '0';
                if(arr[item] == -1) { //누르려는 버튼들 중 고장난 버튼이 있는지 체크
                    check = true;
                    break;
                }
            }
            if(!check) {
                //결과값 비교 및 갱신 : (현재 옮긴 채널 numIdx에서 목표채널 N까지 +혹은-만 눌렀을 경우의 수) + (처음 채널을 옮길 때 필요한 버튼 클릭 수)
                ans = Math.min(ans, Math.abs(N - i) + idx.length());
            }
        }
        System.out.println(ans);
    }
}