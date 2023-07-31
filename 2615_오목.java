import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int[][] map;
    static int nr, nc;
    static int[] r = new int[] {1, -1, 1, 0};
    static int[] c = new int[] {1, 1, 0, 1};
    static int cnt;
    static int check;
    static int x, y;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        //0은 빈공간 1은 검은색 2는 흰색
        map = new int[20][20];
        for(int i=1; i<20;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<20; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int i=1; i<20;i++) {
            if(check == 1 || check == 2) break;
            for(int j=1; j<20; j++) {
                nr= i;
                nc = j;
                x =nr;
                y =nc;
                if(map[i][j] == 1) {
                    for(int k=0; k<5; k++) { //6번 반복 대각선 오른쪽 검은색
                        if(nr+r[0] > 19 || nr+r[0] < 0 || nc+c[0] > 19 || nc+c[0] < 0) break;
                        if(map[nr+r[0]][nc+c[0]] == 1) {
                            nr += r[0];
                            nc += c[0];
                            cnt++;
                        }
                    }
                    if(cnt == 4) {
                        if(map[i-1][j-1] > 19 && map[i-1][j-1] < 0) {
                            check = 1; break;
                        } else if(map[i-1][j-1] != 1){
                            check = 1; break;
                        }
                    }
                    nr = i;
                    nc = j;
                    cnt = 0;

                    for(int k=0; k<5; k++) { //6번 반복 대각선 위쪽 탐색
                        if(nr+r[1] > 19 || nr+r[1] < 0 || nc+c[1] > 19 || nc+c[1] < 0) break;
                        if(map[nr + r[1]][nc + c[1]] == 1) {
                            nr += r[1];
                            nc += c[1];
                            cnt++;
                        }
                    }
                    if(cnt == 4) {
                        if(map[i+1][j-1] > 19 && map[i+1][j-1] < 0) {
                            check = 1; break;
                        } else if(map[i+1][j-1] != 1){
                            check = 1; break;
                        }
                    }
                    nr = i;
                    nc = j;
                    cnt = 0;

                    for(int k=0; k<5; k++) { //6번 반복 대각선 위쪽 탐색
                        if(nr+r[2] > 19 || nr+r[2] < 0 || nc+c[2] > 19 || nc+c[2] < 0) break;
                        if(map[nr + r[2]][nc + c[2]] == 1) {
                            nr += r[2];
                            nc += c[2];
                            cnt++;
                        }
                    }
                    if(cnt == 4) {
                        if(map[i-1][j] > 19 && map[i-1][j] < 0) {
                            check = 1; break;
                        } else if(map[i-1][j] != 1){
                            check = 1; break;
                        }
                    }
                    nr = i;
                    nc = j;
                    cnt = 0;

                    for(int k=0; k<5; k++) { //6번 반복 오른쪽 흰색
                        if(nr+r[3] > 19 || nr+r[3] < 0 || nc+c[3] > 19 || nc+c[3] < 0) break;
                        if(map[nr + r[3]][nc + c[3]] == 1) {
                            nr += r[3];
                            nc += c[3];
                            cnt++;
                        }
                    }
                    if(cnt == 4) {
                        if(map[i][j-1] > 19 && map[i][j-1] < 0) {
                            check = 1; break;
                        } else if(map[i][j-1] != 1){
                            check = 1; break;
                        }
                    }
                    nr = i;
                    nc = j;
                    cnt = 0;
                }
                if(map[i][j] == 2) {
                    for(int k=0; k<5; k++) { //6번 반복 대각선 오른쪽 검은색
                        if(nr+r[0] > 19 || nr+r[0] < 0 || nc+c[0] > 19 || nc+c[0] < 0) break;
                        if(map[nr+r[0]][nc+c[0]] == 2) {
                            nr += r[0];
                            nc += c[0];
                            cnt++;
                        }
                    }
                    if(cnt == 4) {
                        if(map[i-1][j-1] > 19 && map[i-1][j-1] < 0) {
                            check = 2; break;
                        } else if(map[i-1][j-1] != 2){
                            check = 2; break;
                        }
                    }
                    nr = i;
                    nc = j;
                    cnt = 0;
                    for(int k=0; k<5; k++) { //6번 반복 대각선 왼쪽 흰색
                        if(nr+r[1] > 19 || nr+r[1] < 0 || nc+c[1] > 19 || nc+c[1] < 0) break;
                        if(map[nr + r[1]][nc + c[1]] == 2) {
                            nr += r[1];
                            nc += c[1];
                            cnt++;
                        }
                    }
                    if(cnt == 4) {
                        if(map[i+1][j-1] > 19 && map[i+1][j-1] < 0) {
                            check = 2; break;
                        } else if(map[i+1][j-1] != 2){
                            check = 2; break;
                        }
                    }
                    nr = i;
                    nc = j;
                    cnt = 0;
                    for(int k=0; k<5; k++) { //6번 반복 대각선 왼쪽 흰색
                        if(nr+r[2] > 19 || nr+r[2] < 0 || nc+c[2] > 19 || nc+c[2] < 0) break;
                        if(map[nr + r[2]][nc + c[2]] == 2) {
                            nr += r[2];
                            nc += c[2];
                            cnt++;
                        }
                    }
                    if(cnt == 4) {
                        if(map[i-1][j] > 19 && map[i-1][j] < 0) {
                            check = 2; break;
                        } else if(map[i-1][j] != 2){
                            check = 2; break;
                        }
                    }
                    nr = i;
                    nc = j;
                    cnt = 0;

                    for(int k=0; k<5; k++) { //6번 반복 오른쪽 흰색
                        if(nr+r[3] > 19 || nr+r[3] < 0 || nc+c[3] > 19 || nc+c[3] < 0) break;
                        if(map[nr + r[3]][nc + c[3]] == 2) {
                            nr += r[3];
                            nc += c[3];
                            cnt++;
                        }
                    }
                    if(cnt == 4) {
                        if(map[i][j-1] > 19 && map[i][j-1] < 0) {
                            check = 2; break;
                        } else if(map[i][j-1] != 2){
                            check = 2; break;
                        }
                    }
                    cnt = 0;
                    nr = i;
                    nc = j;
                }
                if(check == 1 || check == 2) {
                    break;
                }
            }
        }
        if(check == 1 ) {
            System.out.println("1");
            System.out.printf("%d %d", x, y);
        }
        if(check == 2) {
            System.out.println("2");
            System.out.printf("%d %d", x, y);
        }
        if(check == 0) {
            System.out.println("0");
        }
    }
}
// 노가다 완전구현 (코드 단순화 필요)
