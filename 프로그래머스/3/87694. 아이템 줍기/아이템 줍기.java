import java.util.*;
class Solution {
    static class Node {
        int x;
        int y;
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int[][] edge;
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int answer = 0;
        
        
        /*
        접근법
        탐색할 수 있는 거리들에 대해 2차원 배열에서 1로 만들기
        -> 좌표값 : 1 이상 50 이하 == [51][51]배열 생성 후
        1. 입력으로 들어오는 직사각형을 모두 1로 칠하고
        2. 1로 된 값들에 대해 4방탐색 진행 => 4방이 전부 1이라면 테두리의 안쪾.
        3. 안쪽인 부분을 전부 0으로 만들고
        4. 아이템까지의 거리를 산출
        */
        //예시로 1,1 and 7,4 직사각형이 있을 경우
        //1,1 1,2 1,3 1,4 1,5 1,6 1,7
        //1,1 2,1 3,1 4,1
        //4,1 4,2 4,3 4,4 4,5 4,6 4,7
        //4,7 3,7 2,7 1,7
        
        int[][] map = new int[101][101];  //최대 크기의 배열 선언
        edge = new int[101][101];
        List<Node> list = new ArrayList<>();
        
        //1번 로직
        for(int i=0; i<rectangle.length; i++) {
            int[] arr = rectangle[i];
            for(int j=0; j<4; j++) {
                arr[j] = arr[j] * 2;
            }
            //1,1 ~ 7,4
            //하단의 y축 ~ 상단의 y축 길이만큼 반복
            for(int j=arr[1]; j<=arr[3]; j++) {
                // j == y축 좌표
                for(int k=arr[0]; k<=arr[2]; k++) {
                    //하단 x축 ~ 상단 x축 까지 반복
                    map[j][k] = 1;
                    list.add(new Node(j,k));
                }
            }
        }
        
        //2번 로직
        //테두리인 부분을 새로운 배열 edge에 1로 채우기
        for(Node cur : list) {
            isEdge(map, cur);
        }
        

        //4번 로직
        answer = bfs(characterY * 2, characterX * 2, itemY * 2, itemX * 2);
        
        return answer/2;
    }
    static int[] dx = new int[]{1,-1,0,0};
    static int[] dy = new int[]{0,0,1,-1};
    private static int bfs(int x, int y, int qx, int qy) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{x,y,0});
        boolean[][] v = new boolean[101][101];
        v[x][y] = true;
        
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            
            if(cur[0] == qx && cur[1] == qy) return cur[2];
            for(int i=0; i<4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                if(nx<0 || ny<0 || nx>100 || ny>100 || v[nx][ny]) continue;
                if(edge[nx][ny] == 1) {
                    q.offer(new int[]{nx, ny,cur[2] + 1});
                    v[nx][ny] = true;
                }
            }
            
        }
        return -1;
    }
    
    private static void isEdge(int[][] map, Node cur) {
        int cnt = 0;
        for(int i=0; i<4; i++) {
            int nx = cur.x + dx[i];
            int ny = cur.y + dy[i];
            if(nx<0 || ny<0 || nx>100 || ny>100) break;
            if(map[nx][ny] == 1) cnt++;
        }
        
        //이어지는 부분이 연결이 안됨
        //로직 추가 : 4면이 1일 때 대각선이 0인지 체크 후 1로 변환
        int[] ddx = new int[]{1,-1,1,-1};
        int[] ddy = new int[]{-1,-1,1,1};
        //
        if(cnt == 4) {
            int zeroCnt = 0;
            for(int i=0; i<4; i++) {
                int nx = cur.x + ddx[i];
                int ny = cur.y + ddy[i];
                if(nx<0 || ny<0 || nx>=101 || ny>=101) continue;
                if(map[nx][ny] == 0) zeroCnt++;
            }
            if(zeroCnt>0) edge[cur.x][cur.y] = 1;
            return;
        }
        if(cnt < 4) edge[cur.x][cur.y] = 1;
    }
    
}