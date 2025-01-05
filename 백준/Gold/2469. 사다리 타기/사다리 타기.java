import java.io.*;
import java.util.*;

//2469
public class Main {
    static int k, n, question;
    static char[] ans;
    static char[][] map;
    static Map<Character, Integer> up, down;
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st;

        /**
         * 접근법
         * ?줄을 기준으로 위에서 내려오고, 아래에서 위로 올라간 후
         * 2칸 이상 차이가 나면 return x
         * 1칸 차이가 날 경우 왼쪽부분 인덱스에 - 처리
         * **/

        k = Integer.parseInt(br.readLine());
        n = Integer.parseInt(br.readLine());

        ans = br.readLine().toCharArray();


        map = new char[n][k];

        for(int i=0; i<n; i++) {
            String input = br.readLine();
            for(int j=0; j<k-1; j++) {
                map[i][j] = input.charAt(j);
            }
            map[i][k-1] = '*';
            if(map[i][0] == '?') {
                question = i;
            }

        }

        //위에서 아래로 배열로 관리 : [A,B,C,D] -> [2,1,4,3]
        up = new HashMap<>();
        for(int i=0; i<k; i++) {
            moveDown(i);
        }

        down = new HashMap<>();
        for(int i=0; i<ans.length; i++) {
            moveUp(ans[i], i);
        }


        char[] res = new char[k-1];
        Arrays.fill(res, '*');
        for(int i=0; i<k; i++) {
            char key = (char) ('A' + i);

            int upIdx = up.get(key);
            int downIdx = down.get(key);

            int diff = Math.abs(upIdx - downIdx);
            if(diff > 1) {
                Arrays.fill(res, 'x');
                break;
            }
            else if(diff == 1){
                int p = Math.min(upIdx, downIdx);
                res[p] = '-';
            }
        }

        StringBuilder sb = new StringBuilder();
        for(char idx : res) {
            sb.append(idx);
        }
        System.out.println(sb);
    }

    static void moveDown(int start) {

        char alpa = (char) ('A' + start);

        int x = 0;
        while(true) {
            if(x == question) break;

            if(map[x][start] == '-') start++;
            else if(start != 0 && map[x][start-1] == '-') {
                start--;
            }
            x++;
        }

        up.put(alpa, start);

    }
    static void moveUp(char alpa, int start) {

        int x = n-1;
        while(true) {
            if(x == question) break;

            if(map[x][start] == '-') start++;
            else if(start != 0 && map[x][start-1] == '-') {
                start--;
            }
            x--;
        }

        down.put(alpa, start);

    }

}