import java.util.*;
class Solution {
    static int[][] loseMap, winMap;
    public int solution(int n, int[][] results) {
        int answer = 0;

        /* 승리 전적표 채우기 */
        boolean[][] map = new boolean[n + 1][n + 1];
        for (int[] i : results)
            map[i[0]][i[1]] = true;

        /* 플루이드 와샬 알고리즘을 통해 승리 채우기 */
        for (int k = 1; k <= n; ++k) { // 가운데 선수
            for (int i = 1; i <= n; ++i) { // 강한 선수
                for (int j = 1; j <= n; ++j) { // 약한 선수
                    if (map[i][k] && map[k][j]) {
                        map[i][j] = true;
                    }
                }
            }
        }

        /* 모든 전적을 알 수 있는 선수의 수 구하기 */
        for (int i = 1; i <= n; ++i) {
            boolean possible = true;
            for (int j = 1; j <= n; ++j) {
                if (i != j && !map[i][j] && !map[j][i]) {
                    possible = false;
                    break;
                }
            }
            answer = possible ? answer + 1 : answer;
        }

        return answer;
    }
}