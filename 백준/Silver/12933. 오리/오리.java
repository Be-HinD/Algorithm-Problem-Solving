import java.io.*;
import java.util.*;

//BOJ_12933 오리
public class Main {
    static int res;
    static String idx;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st;

        idx = br.readLine();

        if(idx.charAt(0) != 'q' || idx.length() % 5 != 0) {
            System.out.println(-1);
            return;
        }
        Map<Integer, Character> map = new HashMap<>();
        map.put(1, 'q');
        map.put(2, 'u');
        map.put(3, 'a');
        map.put(4, 'c');
        map.put(5, 'k');

        boolean[] v = new boolean[idx.length()];

        for(int i=0; i<idx.length(); i++) {
            List<Character> list = new ArrayList<>();
            int p = 1;
            for(int j=i; j<idx.length(); j++) {
                char cur = idx.charAt(j);
                if(cur == map.get(p) && !v[j]) {
                    list.add(cur);
                    v[j] = true;
                    p++;
                    if(p > 5) p = 1;
                }

            }

            if(!list.isEmpty()) {
                if(list.get(list.size()-1) != map.get(5)) {
                    System.out.println(-1);
                    return;
                }
                res++;
            }


        }

        System.out.println(res == 0 ? -1 : res);
    }
}