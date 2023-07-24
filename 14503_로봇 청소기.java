import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Stream;

public class Main {
    static int max = 0;
    static int N;
    static int M;
    static int r;
    static int c;
    static int d;
    static int[][] arrNum;


    public static void main(String[] args) throws IOException {
        //북 : -1,0 동 : 0, 1 남 : 1,0 서 : 0,-1
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[][] arr = new int[][] {{-1,0},{0,1},{1,0},{0,-1}}; //북,동,남,서
        //입력
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int[][] visited = new int[N][M];

        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        arrNum = new int[N][M];
        //2차원 배열 저장
        for(int i=0; i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M;j++){
                arrNum[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        //바라볼 방향 저장임시값
        int a;
        int b;
        int check = 0; //빈 방 유무
        int stop = 0; //작동유무
        //구현
        while(stop ==0) {
            //현재 칸이 청소안된 상태면 청소
            if (arrNum[r][c] == 0 && visited[r][c] == 0) {
                max++;
                visited[r][c] = 1;
            }

            //4방향 청소유무 확인
            for (int i = 0; i < 4; i++) {
                a = r + arr[i][0];
                b = c + arr[i][1];
                //청소되지않은 빈 방을 찾은 경우
                if (arrNum[a][b] == 0 && visited[a][b] == 0) {
                    check = 1;
                    break;
                }
            }
            //청소되지않은 빈 방을 찾지 못한 경우
            if (check == 0) {
                //후진할 수 있는 경우
                if (arrNum[r + arr[(d + 2) % 4][0]][c + arr[(d + 2) % 4][1]] == 0) {
                    r += arr[(d + 2) % 4][0];
                    c += arr[(d + 2) % 4][1];
                } else { //안 될 경우
                    break;//작동정지
                }

            } else { //빈 방을 찾은 경우
                    //반시계방향 회전
                    if (d == 0) { // 북쪽일 경우 서쪽으로 보게
                        d = 3;
                    } else { // 나머지는 각각 90도회전되게끔
                        d = d % 4 - 1;
                    }
                    //전진 가능
                    if (arrNum[r + arr[d][0]][c + arr[d][1]] == 0 && visited[r + arr[d][0]][c + arr[d][1]] == 0) {
                        r += arr[d][0];
                        c += arr[d][1]; //전진
                        check = 0;
                    }

            }
        }
        System.out.println(max);

        }
}
