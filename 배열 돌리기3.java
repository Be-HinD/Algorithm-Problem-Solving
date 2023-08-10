import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class Main {
    static int N,M,num,x,y; //배열 크기 N M, 연산회수 num
    static int[][] arr, temp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        num = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        for(int i=0; i<N; i++) { //배열 입력
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        //크기 갱신을 위한 변수
        x = N;
        y = M;

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<num; i++) { //들어오는 연산종류에 따른 switch-case
            int tc = Integer.parseInt(st.nextToken());
            switch (tc) {
                case 1:
                    operation1();
                    break;
                case 2:
                    operation2();
                    break;
                case 3:
                    operation3();
                    break;
                case 4:
                    operation4();
                    break;
                case 5:
                    operation5();
                    break;
                case 6:
                    operation6();
                    break;
            }
        }

        for(int i=0;i<N;i++) {
            for(int j=0;j<M;j++) {
                sb.append(arr[i][j] + " ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    /*아래 로직에서 중요한 부분은 temp라는 임시변수를 통해 로직을 실행하고 마지막 부분에 arr의 주소참조를 변경하는식으로 백트래킹*/
    static void operation1() { //상하 반전
        temp = new int[N][M];
        int reverse_i = N-1;
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                temp[i][j] = arr[reverse_i][j];

            }
            reverse_i--;
        }
        arr = temp;
    }

    static void operation2() { //좌우반전
        temp = new int[N][M];
        int reverse_j = M-1;
        for(int i=0; i<M; i++) {
            for(int j=0; j<N; j++) {
                temp[j][i] = arr[j][reverse_j];
            }
            reverse_j--;
        }
        arr = temp;
    }

    static void operation3() { //오른쪽 90도 회전
        temp = new int[M][N];
        for(int i=0; i<M; i++) {
            for(int j=0; j<N; j++) {
                temp[i][j] = arr[(N-1)-j][i];
            }
        }
        int swap_temp = N;
        N = M;
        M = swap_temp;
        arr = temp;
    }

    static void operation4() { //왼쪽 90도 회전
        temp = new int[M][N];
        for(int i=0; i<M; i++) {
            for(int j=0; j<N; j++) {
                temp[i][j] = arr[j][(M-1)-i];
            }
        }
        int swap_temp = N;
        N = M;
        M = swap_temp;
        arr = temp;
    }

    static void operation5() { //시계방향
        temp = new int[N][M];
        for(int i=0; i<N/2; i++) { //0~2
            for(int j=0; j<M/2;j++) { //0~3
                temp[i][(M/2) + j] = arr[i][j]; //1->2  // 2 1
                temp[(N/2) + i][(M/2) + j] = arr[i][(M/2)+j]; //2->3  // 3 2
                temp[(N/2) + i][j] = arr[(N/2) + i][(M/2) + j]; //3->4  // 4 3
                temp[i][j] = arr[(N/2) + i][j]; //4->1    // 4 1
            }
        }
        arr = temp;
    }

    static void operation6() { //반시계방향
        temp = new int[N][M];
        for(int i=0; i<N/2; i++) { //0~2
            for(int j=0; j<M/2;j++) { //0~3
                temp[i][j] = arr[i][(M/2) + j]; //1->4
                temp[i][(M/2)+j] = arr[(N/2) + i][(M/2) + j]; //4->3
                temp[(N/2) + i][(M/2) + j] = arr[(N/2) + i][j]; //3->2
                temp[(N/2) + i][j] = arr[i][j]; //2->1
            }
        }
        arr = temp;
    }

}
