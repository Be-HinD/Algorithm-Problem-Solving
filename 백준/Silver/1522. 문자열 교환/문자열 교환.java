import java.io.*;
import java.util.*;

//BOJ_1522
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] arr = br.readLine().toCharArray();

        int aCnt = 0;

        for(int i=0; i<arr.length; i++) {
            if(arr[i] == 'a') aCnt++;
        }

        int min = 10000;
        for(int i=0; i<arr.length; i++) {
            int bCnt = 0;
            for(int j=i; j<i+aCnt; j++) {
                int idx = j % arr.length;
                if(arr[idx] == 'b') bCnt++;
            }
            min = Math.min(min, bCnt);
        }

        System.out.println(min);

    }
}