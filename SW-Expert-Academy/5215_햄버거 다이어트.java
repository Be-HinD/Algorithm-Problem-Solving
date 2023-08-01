import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int Max = 0;
    static int[] Score;
    static int[] Cal;
    static int L;
    static int N;
    
    static void dfs(int idx, int score, int cal){
        if(idx == Score.length) { //현재 idx가 마지막 포인트일 때
            if(cal <= L){ //현재 섭취한 칼로리가 제한 칼로리 L 이상일 때
                Max = Math.max(Max, score); //Max값 비교 후 갱신
        }
            return;
        }
        dfs(idx+1, score + Score[idx], cal + Cal[idx]); //현재 idx햄버거를 먹은 경우
        dfs(idx + 1, score, cal); //현재 idx햄버거를 먹지 않은 경우
    }
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int tc = Integer.parseInt(st.nextToken());

        for(int i = 0; i<tc; i++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());

            Score = new int[N];
            Cal = new int[N];

            for(int j =0; j<N; j++){
                st = new StringTokenizer(br.readLine());
                Score[j] = Integer.parseInt(st.nextToken());
                Cal[j] = Integer.parseInt(st.nextToken());
            }
            dfs(0, 0, 0);
            System.out.println(String.format("#%d %d",i + 1,Max));
            Max = 0;
        }
    }
}
