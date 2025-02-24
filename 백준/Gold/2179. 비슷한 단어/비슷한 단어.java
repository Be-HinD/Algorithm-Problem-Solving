import java.io.*;
import java.util.*;

//BOJ_2179
public class Main {
    static int n, max, firstIdx;
    static String res1, res2;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(br.readLine());

        Map<String, Integer> map = new HashMap<>();
        List<String> list = new ArrayList<>();

        firstIdx = 20055;

        for(int i=0; i<n; i++) {
            String input = br.readLine();
            char[] arr = input.toCharArray();

            list.add(input);

            StringBuilder sb = new StringBuilder();
            for(int j=0; j<arr.length; j++) {
                sb.append(arr[j]);

                if(map.containsKey(sb.toString())) {
                    //맵에 해당 접두사가 존재한다면 갱신
                    int idx = map.get(sb.toString());

                    if(max <= sb.toString().length()) {
                        if(max == sb.toString().length() && idx >= firstIdx) continue;
                        max = sb.toString().length();
                        firstIdx = idx;
                        res1 = list.get(idx);
                        res2 = list.get(i);
                    }
                }
                else {
                    map.put(sb.toString(), i);
                }
            }
        }

        System.out.println(res1);
        System.out.println(res2);
    }
}