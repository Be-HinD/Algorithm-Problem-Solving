import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException{
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      int N = Integer.parseInt(st.nextToken());
      int K = Integer.parseInt(st.nextToken());
      int[] arr = new int[N];
      st = new StringTokenizer(br.readLine());
      
      for(int i=0; i<N; i++) {
        arr[i] = Integer.parseInt(st.nextToken());
      }

      for(int i=0; i<K; i++) {
        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken())-1;
        int y = Integer.parseInt(st.nextToken())-1;
        float sum = 0;
        for(int j=x; j<=y; j++) {
          sum += arr[j];
        }

        //평균 구하기
        float res = sum/((y+1)-x);
        System.out.println(String.format("%.2f", res));
      }
      
    }
}
