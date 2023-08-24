import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Solution {
    static int[] stack; //4개의 톱니바퀴 회전 여부
    static int sn = 2; //맞물린 위치
    static int ns = 6; //맞물린 위치
    static int[][] arr = new int[4][8]; //입력배열
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int tc = Integer.parseInt(st.nextToken());
        //입력
        for(int t = 1; t<(tc+1); t++) { //TestCase
            int k = Integer.parseInt(br.readLine()); //k : 회전연산 개수
            //배열 입력
            for(int q=0; q<4; q++) {
                st = new StringTokenizer(br.readLine());
                for (int w=0; w<8; w++) {
                    arr[q][w] = Integer.parseInt(st.nextToken());
                }
            }
            for(int i=0; i<k; i++) { //회전연산 개수 k만큼 반복
                st = new StringTokenizer(br.readLine());
                int num = Integer.parseInt(st.nextToken()) - 1; //회전하려는 톱니바퀴 번호
                int vector = Integer.parseInt(st.nextToken()); //회전 방향
                int originVector = vector; //기존 회전방향값 저장
                int origin = num; //원본 톱니번호 복원용 변수
                int dx = num - 1; //왼쪽 톱니
                int[] copy = arr[origin].clone(); //왼쪽 톱니 변경 이후 복원을 위한 카피
                stack = new int[4]; //회전체크 배열 초기화
                for(int j=1; j<4; j++) { //현재 톱니의 왼쪽방향 확인
                    //왼쪽방향의 톱니의 맞물린포인트와 현재톱니의 맞물린 방향 확인
                    if(dx < 0) break; //예외처리
                    if(arr[dx][sn] == arr[num][ns]) { //같은 극일 때
                        if(vector == 1) { //시계방향
                            stack[num] = 1; //해당 톱니바퀴만 회전
                        } else {
                            stack[num] = -1;
                        }
                        break;
                    }
                    //다른 극일 시 이동 및 포인터 변환
                    if(vector == 1) { //시계방향
                        stack[dx] = -1; //이전 톱니
                        stack[num] = 1; //현재톱니
                    } else { //반시계방향
                        stack[dx] = 1; //이전 톱니
                        stack[num] = -1; //현재톱니
                    }

                    //방향값 변경 및 포인터 이동
                    if(vector == 1) vector = 0;
                    else vector = 1;
                    num--;
                    dx--;
                }
                //왼쪽 변경 끝&원본 값 초기화
                vector = originVector;
                num = origin;
                dx = num + 1;
                for(int j=0; j<4; j++) { //오른쪽 톱니 체크
                    if(stack[j] == -1) {
                        arrangeReverse(arr, j);
                    }
                    if(stack[j] == 1) {
                        arrange(arr, j);
                    }
                }
                if(dx < 4) {
                    arr[origin] = copy;
                }
                stack = new int[4]; //배열 초기화
                for(int j=1; j<4; j++) { //현재 톱니의 오른쪽방향 확인
                    if(dx > 3) break; //예외처리
                    //오른쪽방향의 톱니의 맞물린포인트와 현재톱니의 맞물린 방향 확인
                    if(arr[dx][ns] == arr[num][sn]) { //같은 극일 경우 현재 톱니만 회전
                        if(vector == 1) {
                            stack[num] = 1; //현재톱니
                        } else {
                            stack[num] = -1; //현재 톱니
                        }
                        break;
                    }
                    //다른 극일 시 이동 및 포인터 변환
                    if(vector == 1) { //시계방향
                        stack[dx] = -1;
                        stack[num] = 1;
                    } else { //반시계방향
                        stack[dx] = 1;
                        stack[num] = -1;
                    }
                    //방향값 변경&포인터 이동
                    if(vector == 1) vector = 0;
                    else vector = 1;
                    num++;
                    dx++;
                }
                for(int j=0; j<4; j++) { //스택의 값을 체크하고 회전연산 실행
                    if(stack[j] == -1) {
                        arrangeReverse(arr, j);
                    }
                    if(stack[j] == 1) {
                        arrange(arr, j);
                    }
                }
            }

            int a = 1; //점수
            int ans = 0; //결과값
            for(int i=0; i<4; i++) {
                if(arr[i][0] == 1) {
                    ans += a;
                }
                a *= 2;
            }

            sb.append("#" + t + " " + ans +"\n");
        }
        System.out.println(sb);
    }

    private static void arrange(int[][] arr, int y) { //시계방향
        int[] copy = new int[8];
        int ar = 7;
        for(int i=0; i<8; i++) {
            copy[i] = arr[y][ar%8];
            ar++;
        }
        arr[y] = copy;
    }
    private static void arrangeReverse(int[][] arr, int y) { //반시계방향
        int[] copy = new int[8];
        int ar = 1;
        for(int i=0; i<8; i++) {
            copy[i] = arr[y][ar%8];
            ar++;
        }
        arr[y] = copy;
    }
}
