import java.io.*;
import java.util.*;
import java.util.Map.Entry;

//BOJ_1302
class Main {
    static int N;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        TreeMap<String, Integer> map = new TreeMap<>();
        for(int i=0; i<N; i++) {
            String idx = br.readLine();
            map.put(idx, map.getOrDefault(idx, 0) + 1);
        }

        int cnt = 0; String res = "";
        for(Entry<String, Integer> idx : map.entrySet()) {
            if(idx.getValue() > cnt) {
                cnt =  idx.getValue();
                res = idx.getKey();
            }
        }

        System.out.println(res);

    }
}