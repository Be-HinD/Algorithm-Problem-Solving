import java.io.*;

//BOJ_10799 쇠막대기
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();

        int item = 0;   //쇠막대기 개수
        int res = 0;

        for(int i=0; i<input.length(); i++) {
            char idx = input.charAt(i);

            //여는 괄호의 경우 다음 나오는게 바로 닫히는 괄호인지 아닌지를 판단
            if(idx == '(') item++;

            //닫는 괄호의 경우
            else {
                item--;
                
                if(input.charAt(i-1) == '(') {
                    res += item;
                }
                else {
                    res++;
                }
            }
        }

        System.out.println(res);
    }
}