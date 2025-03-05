import java.io.*;
import java.util.*;

//BOJ_5549
public class Main {
    static int n, m, k;
    static char[][] map;
    static int[][] jMap, oMap, iMap;
    static StringBuilder res;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        k = Integer.parseInt(br.readLine());

        map = new char[n+1][];
        jMap = new int[n+1][m+1];
        oMap = new int[n+1][m+1];
        iMap = new int[n+1][m+1];

        for(int i=1; i<=n; i++) {
            map[i] = br.readLine().toCharArray();
            for(int j=0; j<m; j++) {
                if(map[i][j] == 'J') jMap[i][j+1]++;
                else if(map[i][j] == 'O') oMap[i][j+1]++;
                else iMap[i][j+1]++;
                jMap[i][j+1] += jMap[i][j] + jMap[i-1][j+1] - jMap[i-1][j];
                oMap[i][j+1] += oMap[i][j] + oMap[i-1][j+1] - oMap[i-1][j];
                iMap[i][j+1] += iMap[i][j] + iMap[i-1][j+1] - iMap[i-1][j];
            }
        }

        res = new StringBuilder();
        for(int i=0; i<k; i++) {
            st = new StringTokenizer(br.readLine());
            search(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        System.out.println(res);

    }

    private static void search(int a, int b, int c, int d) {

        int jCnt = jMap[c][d] - jMap[c][b-1] - jMap[a-1][d] + jMap[a-1][b-1];
        int oCnt = oMap[c][d] - oMap[c][b-1] - oMap[a-1][d] + oMap[a-1][b-1];
        int iCnt = iMap[c][d] - iMap[c][b-1] - iMap[a-1][d] + iMap[a-1][b-1];

        res.append(jCnt).append(" ").append(oCnt).append(" ").append(iCnt).append("\n");
    }
}