import java.io.*;
import java.util.*;

//BOJ_10799 쇠막대기
public class Main {
    static int N, M;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();

        //아직 닫히지 않은 쇠막대기들의 개수를 총합하고
        //레이저가 나올때마다 그 개수를 2분할 해서 증가
        //닫힌 레이저가 나오면 레이저의 개수를 차감

        int item = 0;   //쇠막대기 개수
        int res = 0;

        for(int i=0; i<input.length(); i++) {
            char idx = input.charAt(i);

            //여는 괄호의 경우 다음 나오는게 바로 닫히는 괄호인지 아닌지를 판단
            if(idx == '(') {


                if(input.charAt(i+1) == ')') {
                    //레이저일 경우
                    if(item != 0) {
                        res += item;
                    }

                    i++;    //다음 인덱스 건너뛰기
                }

                else {
                    item++;
                    res++;
                }
            }

            //닫는 괄호의 경우
            else {
                //무조건 쇠막대기의 끝이기 때문에 item감소
                item--;
            }
        }

        System.out.println(res);
    }
}