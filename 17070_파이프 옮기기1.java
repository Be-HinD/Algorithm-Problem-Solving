import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
public class Main {
    static int N;
    static int[][] arr;
    static int cnt = 0;
    static void dfs(int i, int j, int k, int l){ //0,1
//        탈출조건
        if (k == N && l ==N) { //앞부분이 도착했을 경우
            cnt++;
            return;
        }
        if(i == N || j == N || k == N || l == N) { // 장외아웃
            return;
        }
        if(arr[k][l] == 1) { // 앞부분이 막혀있을 경우
            return;
        }
        if((k-i) == 1 && (l-j) == 1) { //대각선
            if (arr[i][j + 1] == 1 || arr[k][l-1] == 1) { //4방향중에 1이 있을경우
                return;
            }
        }
        /////////////////////////
        if(i == k){ //가로
            dfs(i, j+1, k, l+1); //직진
            dfs(k,l,k +1, l+1); //대각선
        }
        else if(j == l){ //세로
            dfs(i+1,j, k+1,l); //직진
            dfs(k, l, k+1, l+1); //대각선
        }
        else{ //대각선
            dfs(k,l,k+1,l+1); //직진
            dfs(k,l,k+1,l); //아래로
            dfs(k,l, k, l+1); // 오른쪽으로
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        arr = new int[N][N];
        //2차원 배열 입력
        for(int i=0; i< N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dfs(0, 0,0,1);
        System.out.print(cnt);
    }
}
