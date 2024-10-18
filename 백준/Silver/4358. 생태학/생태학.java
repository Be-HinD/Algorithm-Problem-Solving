import java.io.*;
import java.util.*;

//BOJ_4358
public class Main {
    static int N, L, res;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int totalCnt = 0;
        Map<String, Double> map = new TreeMap<>((o1, o2) -> {return o1.compareTo(o2);});
        String input;
        while((input = br.readLine()) != null) {
            totalCnt++;
            map.put(input, map.getOrDefault(input, (double) 0) + 1);
        }

        StringBuilder sb = new StringBuilder();

        for(String item : map.keySet()) {
            String ratio = String.format("%.4f", map.get(item) / totalCnt * 100);
            sb.append(item).append(" ").append(ratio).append("\n");
        }

        System.out.println(sb);
    }
}