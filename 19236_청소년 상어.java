import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int num, v, Max; //idx : 물고기 순서를 나타내는 인덱스, Max : 최종 결과값
    static Fish[][] map; //물고기 정보맵
    static List<Fish> list;
    static int[] dx = new int[] {-1, -1,0, 1, 1, 1, 0, -1};
    static int[] dy = new int[] {0, -1, -1, -1, 0, 1, 1, 1};
    static Fish[] arr; //물고기의 정보를 담을 배열
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        list = new ArrayList<>();
        map = new Fish[4][4];
        arr = new Fish[16];
        for(int i=0; i<4; i++) { //물고기의 객체를 가지는 4*4 배열 입력
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<4; j++) {
                num = Integer.parseInt(st.nextToken());
                v = Integer.parseInt(st.nextToken());
                Fish f = new Fish(i, j, num, v-1, true);
                list.add(f);
                map[i][j] = f;
            }
        }
        Collections.sort(list, new Comparator<Fish>() {
            @Override
            public int compare(Fish o1, Fish o2) {
                return o1.num - o2.num;
            }
        });
        Shark pinkpong = new Shark(0, 0, map[0][0].vector, map[0][0].num); //상어의 정보 초기화 : 0,0의 정보를 담음
        map[0][0].life = false;
        dfs(map, pinkpong);
        System.out.println(Max);
    }
    private static void dfs(Fish[][] map, Shark shark ) { //매개변수로 원본배열의 카피본, 새롭게 갱신된 상어정보
        //백트래킹을 위한 물고기 배열 카피
        Fish[][] copy = new Fish[map.length][];
        for (int i = 0; i < map.length; i++) {
            copy[i] = map[i].clone();
        }

        //물고기 이동
        //물고기 번호 순대로 기준잡고 8방탐색 및 이동(swap)
        //물고기의 객체를 리스트에서 가져오면 정보변경이 안되기 때문에 맵에서 직접가져와야함.... == 바뀜 ㅋ
        for(int i=0; i<list.size(); i++) {
            Fish fish = list.get(i);
            if(fish.life == false) continue;
            //8방탐색
            int vector = fish.vector; //입력받을 때 -1시켜서 방향벡터dx,dy랑 맞춰야했는데 여기서 -1 해줘서 인덱스에러발생
            for(int j=0; j<8; j++) {
                int nx = fish.x + dx[(vector+j) % 8]; //+j 안붙이고 dx[vector%8]로 돌려서 한참 찾았음
                int ny = fish.y + dy[(vector+j) % 8];
                //null체크
                if(nx < 0 || nx > 3 || ny < 0 || ny > 3) continue;
                //가려는 좌표에 상어가 있는지
                if(nx == shark.x && ny == shark.y) continue;
                //물고기 이동 -> 물고기 객체의 x, y 좌표 및 copy배열의 정보 교환
                if(copy[nx][ny].life == false) { //가려는 칸이 빈칸일 경우
                    fish.x = nx; //좌표 변경
                    fish.y = ny; //좌표 변경
                    copy[nx][ny] = fish; //현재 칸의 물고기만 이동
                    break; //이동 후 break를 통해 다음 번호 물고기 이동을 시켜야함!! break문 없으면 계속 이동
                } else { //가려는 칸에 다른 물고기가 있는 경우, 물고기방향은 불변인줄 알고 한참 찾음.
                    //현재 물고기 좌표 복사
                    int temp_x = fish.x;
                    int temp_y = fish.y;
                    Fish swap_fish = copy[nx][ny]; //가려는 칸의 물고기 정보
                    //현재 물고기 이동
                    fish.x = nx; //객체 좌표 변경
                    fish.y = ny; //객체 좌표 변경
                    fish.vector = (vector+j) % 8; //현재 물고기의 방향을 바뀐 값으로 갱신
                    copy[nx][ny] = fish; //맵 변경
                    swap_fish.x = temp_x; //swap_fish의 좌표정보를 기존 fish의 좌표로 변경
                    swap_fish.y = temp_y; //swap_fish의 좌표정보를 기존 fish의 좌표로 변경
                    copy[temp_x][temp_y] = swap_fish; //복사해논 정보로 swap
                    break; //이동 후 break를 통해 다음 번호 물고기 이동을 시켜야함!! break문 없으면 계속 이동
                }
            }
        }
        //상어 이동 : 현재 vector 기준으로 8방탐색 및 널 체크 및 재귀호출
        for(int i=0; i<4; i++) {
            for(int j=0; j<4; j++) {
                if(copy[i][j].life) System.out.print("1       ");
                else System.out.print("0      ");
            }
            System.out.println();
        }
        System.out.println("=================");
        int vector = shark.vector;
        int nx = shark.x;
        int ny = shark.y;
        for(int i=0; i<4; i++) {
            nx += dx[vector];
            ny += dy[vector];
            if(nx < 0 || nx > 3 || ny < 0 || ny > 3) break; //null 체크
            if(copy[nx][ny].life) { //해당 좌표의 물고기가 살아있는 경우
                copy[nx][ny].life = false;
                dfs(copy, new Shark(nx, ny, copy[nx][ny].vector, shark.eat_Fish + copy[nx][ny].num));
                copy[nx][ny].life = true; //백트래킹
            }
        }
        //기저조건 : 상어의 현재좌표로부터 이동가능한 칸이 없는 경우
        System.out.println(shark.eat_Fish);
        Max = Math.max(Max, shark.eat_Fish);
    }


}
class Shark { //상어 클래스
    int x;
    int y;
    int vector;
    int eat_Fish;
    Shark(int x, int y, int vector, int eat_Fish) {
        this.x = x;
        this.y = y;
        this.vector = vector;
        this.eat_Fish = eat_Fish;
    }
}
class Fish { //물고기 클래스
    int x;
    int y;
    int num;
    int vector;
    boolean life;
    Fish(int x, int y, int num, int vector, boolean life) {
        this.x = x;
        this.y = y;
        this.num = num;
        this.vector = vector;
        this.life = life;
    }
}
