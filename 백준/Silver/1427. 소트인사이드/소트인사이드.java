import java.io.*;
import java.util.*;

//BOJ_1427
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] arr = br.readLine().toCharArray();

        Arrays.sort(arr);

        StringBuilder res = new StringBuilder();
        for(int i=arr.length-1; i>=0; i--) res.append(arr[i]);

        System.out.println(res);
    }
}