import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
        
        char[] arr = br.readLine().toCharArray();
        int[] res = new int[26];    //알파벳 개수
        
        for(char idx : arr) {
            res[idx - 97]++;
        }
        
        for(int idx : res) {
            System.out.print(idx + " ");
        }
    }
}