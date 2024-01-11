import java.io.*;
import java.util.*;

//BOJ_2659 십자카드 문제
public class Main {
    static int res;
    static int[] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        arr = new int[4];
        for(int i=0; i<4; i++) arr[i] = Integer.parseInt(st.nextToken());       //십자카드 입력
        check(1324);
        //시계수 계산
        String difference = "9999";
        for(int i=0; i<4; i++) {
            int idx = i;

            String num = "";
            for(int j=0; j<4; j++) {
                idx = (i+j) % 4;
                int p = arr[idx];
                num += Integer.toString(p);
            }
            int number = Integer.parseInt(num);
            if(Integer.parseInt(difference) > number) {
                difference = num;
            }
        }

        int target = Integer.parseInt(difference);
        for(int i=3; i>=0; i--) {

            arr[i] = target % 10;
            target /= 10;
        }


        int[] diff = new int[5];
        for(int i=1111; i<10000; i++) {
            int idx = i;

            boolean flag = true;
            for(int j=3; j>=0; j--) {
                diff[j] = idx % 10;
                idx /= 10;
                if(diff[j] == 0) {      //0이 나올 경우
                    flag = false;
                    break;
                }
            }
            if(!flag) continue;

            if(!check(i)) continue;

            //시계수일 경우
            String di = Integer.toString(i);
            for(int j=0; j<4; j++) {
                if(arr[j] != Integer.parseInt(String.valueOf(di.charAt(j)))) {
                    flag = false;
                    break;
                }
            }
            if(flag) {
                System.out.println(++res);
                return;
            }

            res++;
        }


    }

    private static boolean check(int num) {
        //매개변수 num이 시계수인지 체크하는 로직
        String in = Integer.toString(num);

        int[] diff = new int[4];
        for(int i=0; i<4; i++) {
            diff[i] = Integer.parseInt(String.valueOf(in.charAt(i)));
        }

        String difference = "9999";
        for(int i=0; i<4; i++) {
            int idx = i;

            String numb = "";
            for(int j=0; j<4; j++) {
                idx = (i+j) % 4;
                int p = diff[idx];
                numb += Integer.toString(p);
            }
            int number = Integer.parseInt(numb);
            if(Integer.parseInt(difference) > number) {
                difference = numb;
            }
        }

        if(difference.equals(Integer.toString(num))) return true;
        return false;
    }
}