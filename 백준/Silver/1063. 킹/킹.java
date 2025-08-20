import java.io.*;
import java.util.*;

//BOJ_1063 (심심풀이 땅콩)
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        /**
         * 하드코딩 10분컷
         * **/
        Map<Character, Integer> col = new HashMap<>();
        Map<Integer, Character> reverseCol = new HashMap<>();
        for(int i=1; i<=8; i++) {
            col.put((char) ('A'+i-1), i-1);
            reverseCol.put(i-1, (char) ('A'+i-1));
        }

        Map<String, int[]> dist = new HashMap<>();
        dist.put("R", new int[]{0,1});
        dist.put("L", new int[]{0,-1});
        dist.put("B", new int[]{1,0});
        dist.put("T", new int[]{-1,0});
        dist.put("RT", new int[]{-1,1});
        dist.put("LT", new int[]{-1,-1});
        dist.put("RB", new int[]{1,1});
        dist.put("LB", new int[]{1,-1});

        char[] king = st.nextToken().toCharArray();
        char[] rock = st.nextToken().toCharArray();
        int n = Integer.parseInt(st.nextToken());

        int[] kings = new int[2];
        int[] rocks = new int[2];
        ;
        kings[0] = 8 - (king[1] - '0'); kings[1] = col.get(king[0]);
        rocks[0] = 8 - (rock[1] - '0'); rocks[1] = col.get(rock[0]);

        for(int i=0; i<n; i++) {
            String command = br.readLine();
            int[] d = dist.get(command);

            int nx = kings[0] + d[0];
            int ny = kings[1] + d[1];
            if(nx<0 || ny<0 || nx>=8 || ny>=8) continue;

            if(nx == rocks[0] && ny == rocks[1]) {
                int rnx = rocks[0] + d[0];
                int rny = rocks[1] + d[1];
                if(rnx<0 || rny<0 || rnx>=8 || rny>=8) continue;
                rocks[0] = rnx; rocks[1] = rny;
                kings[0] = nx; kings[1] = ny;   //돌이나 킹 둘중에 하나라도 넘어가면 아예 턴아웃
            }
            else {
                kings[0] = nx; kings[1] = ny;   //돌이나 킹 둘중에 하나라도 넘어가면 아예 턴아웃
            }
        }


        System.out.print(reverseCol.get(kings[1]));
        System.out.print(8 - kings[0]);
        System.out.println();
        System.out.print(reverseCol.get(rocks[1]));
        System.out.print(8 - rocks[0]);

    }
}