import java.awt.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] arr;
    static int sex, num;
    static int left, right;
    static int a;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        arr = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i =1 ;i<arr.length;i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        for(int i =0 ;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            sex = Integer.parseInt(st.nextToken());
            num = Integer.parseInt(st.nextToken());

            if(sex == 1) {//남자일 때
                for(int j=num; j<arr.length;j++) {
                    if((j%num) == 0) { //배수일때
                        change(j); //상태 변환
                    }
                }
            } else { //여자일 때
                for(int j =1; j<arr.length; j++) {
                    if(num-j > 0 && num+j < arr.length) { //null 예외처리 ***index 1부터 시작***
                        left = arr[num - j];
                        right = arr[num + j];
                        if (left == right) { //좌우대칭일 때 상태변경
                            change(num-j);
                            change(num+j);
                        }
                        else {
                            break;
                        }
                    }

                }
                change(num); //여자일 경우 마지막에 본인 index 상태변경
            }

        }
        for(int i=1; i<arr.length;i++){
            System.out.print(arr[i] + " ");
            if(i % 20 == 0) { //스위치 20개가 넘어갈 경우 라인바꿈
                System.out.println();
            }
        }
    }
    //스위치 상태 변경 메서드
    static void change(int idx) {
        if(arr[idx] == 1) {
            arr[idx] = 0;
        } else {
            arr[idx] = 1;
        }
    }
}
