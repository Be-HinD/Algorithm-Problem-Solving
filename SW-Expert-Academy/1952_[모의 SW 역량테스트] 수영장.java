import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Solution {
    static int Min;
    static int[] price = new int[4]; //수영장 이용 가격
    static int[] arr = new int[12]; //이용계획
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int tc = Integer.parseInt(st.nextToken());
        //입력
        for(int i = 1; i<(tc+1); i++) {
            st = new StringTokenizer(br.readLine());
            for(int k=0; k<4; k++){
                price[k] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for(int k=0; k<12; k++){
                arr[k] = Integer.parseInt(st.nextToken());
            }
            
            Min = Integer.MAX_VALUE; //최소값 비교
            dfs(0, 0); //완전탐색
            Min = price[3] < Min ? price[3] : Min; //1년치 이용가격이 더 저렴하면 갱신
            sb.append("#" + i + " " + Min +"\n");
        }
        System.out.println(sb);
    }
    private static void dfs(int idx, int sum) {
        if(idx > 11) { //기저조건 : 12월을 넘어간다면 Min값 갱신 후 종료
            Min = sum < Min ? sum : Min;
            return;
        }
        if(arr[idx] == 0) { //해당 월에 이용계획이 없다면 다음달로 이동
            dfs(idx + 1, sum);
            return;
        }
        dfs(idx +1, sum + (price[0] * arr[idx])); //1일치
        dfs(idx + 1, sum + price[1]); //한달치
        dfs(idx + 3, sum + price[2]); //세달치
    }
}
