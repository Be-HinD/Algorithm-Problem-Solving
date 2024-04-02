import java.io.*;
import java.util.*;

//BOJ_5430 AC
public class Main {
    static int T, N;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for(int tc=0; tc<T; tc++) {

            String oper = br.readLine();

            N = Integer.parseInt(br.readLine());

            ArrayDeque<Integer> ad = new ArrayDeque<>();

            String input = br.readLine();
            String clean = input.substring(1, input.length()-1);

            String[] arr = clean.split(",");

            if(arr[0] != null && !arr[0].equals("")) {
                for (int i = 0; i < arr.length; i++) {
                    ad.addLast(Integer.valueOf(arr[i]));
                }
            }


            boolean flag = true;
            boolean isError = false;
            for(int i=0; i<oper.length(); i++) {
                if(oper.charAt(i) == 'D') {
                    if(ad.isEmpty()) {
                        sb.append("error").append("\n");
                        isError = true;
                        break;
                    }
                    if(flag) {
                        ad.pollFirst();
                    }
                    else {
                        ad.pollLast();
                    }
                }
                else {
                    flag = !flag;
                }
            }

            if(isError) continue;

            sb.append("[");

            if(flag) {
                int leng = ad.size()-1;
                for (int i = 0; i < leng; i++) {
                    sb.append(ad.pollFirst()).append(",");
                }
                if(!ad.isEmpty()) {
                    sb.append(ad.pollFirst());
                }
            }
            else {
                int leng = ad.size()-1;
                for (int i = 0; i < leng; i++) {
                    sb.append(ad.pollLast()).append(",");
                }
                if(!ad.isEmpty()) {
                    sb.append(ad.pollFirst());
                }
            }
            sb.append("]").append("\n");
        }

        System.out.println(sb);


    }
}