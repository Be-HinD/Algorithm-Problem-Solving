import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int[][] map = new int[9][9];
    static ArrayList<int[]> list; //빈칸의 위치를 담을 리스트
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        list = new ArrayList<>();
        for(int i=0; i<9; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<9; j++) {
                int num = Integer.parseInt(st.nextToken());
                if(num == 0) list.add(new int[]{i,j}); //빈칸 리스트 추가
                map[i][j] = num;
            }
        }
        dfs(0);
    }

    private static void dfs(int n) {
        if(n==list.size()) { //기저조건
            for(int k=0; k<9; k++) {
                for(int l=0; l<9; l++) {
                    System.out.print(map[k][l] + " ");
                }
                System.out.println();
            }
            System.exit(0);
        }

        int[] idx = list.get(n);
        int xx = (idx[0]/3) * 3;
        int yy = (idx[1]/3) * 3;

        boolean[] v = new boolean[10]; //들어갈 수 있는 숫자 탐색을 위한 체크 배열
        for(int i=xx; i<(xx+3); i++) { //3*3 칸 체크
            for(int j=yy; j<(yy+3); j++) {
                v[map[i][j]] = true;
            }
        }
        for(int i=0; i<9; i++) { //세로체크
            v[map[i][idx[1]]] = true;
        }
        for(int i=0; i<9; i++) { //가로체크
            v[map[idx[0]][i]] = true;
        }

        for(int i=1; i<10; i++) {
            if(!v[i]) { //들어갈 수 있는 숫자들에 대해서 백트래킹 및 재귀
                map[idx[0]][idx[1]] = i;
                dfs(n+1);
                map[idx[0]][idx[1]] = 0;
            }
        }
    }
}
