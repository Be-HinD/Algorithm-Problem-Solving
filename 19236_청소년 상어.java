import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int ans;
    static Fish[][] map;
    static Fish[] fishArr = new Fish[17]; //물고기들만 저장할 배열
    static int[] dx = new int[]{-1,-1,0,1,1,1,0,-1}; //방향벡터
    static int[] dy = new int[]{0,-1,-1,-1,0,1,1,1}; //12시부터 반시계 방향

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        map = new Fish[4][4]; //고정크기 맵

        for(int i=0; i<4; i++) { //맵 입력
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<4; j++) {
                //입력 : 물고기 번호, 방향
                int num = Integer.parseInt(st.nextToken());
                int dist = Integer.parseInt(st.nextToken()) - 1; //방향벡터 인덱스 시작을 0으로 하기위해
                Fish f = new Fish(i,j,num,dist,true); //객체 생성
                fishArr[num] = new Fish(i,j,num,dist,true);
                map[i][j] = f;
            }
        }

        //상어는 항상 0,0으로 이동
        map[0][0].life = false;
        fishArr[map[0][0].num].life = false; //물고기 배열도 같이 갱신
        ans = map[0][0].num; //0,0에서부터 못갈 경우를 위한 전처리
        move(map, 0, 0); //물고기 이동

        dfs(0, 0, map[0][0].num, map[0][0].dist);
        System.out.println(ans);
    }

    private static void dfs(int x, int y, int sum, int dist) { //상어좌표, 먹은 물고기 합, 상어방향
        Fish[][] copy = new Fish[4][4]; //copy == 현재 스택 기준 이동하기 전 맵 상태(원복할 맵)
        for(int i=0; i<4; i++) { //깊은 복사
            copy[i] = map[i].clone();
        }
        //배열 또한 원복 카피배열 생성
        Fish[] copyArr = new Fish[17];
        for(int i=1; i<17; i++) { //깊은 복사
            copyArr[i] = new Fish(fishArr[i].x, fishArr[i].y, fishArr[i].num, fishArr[i].dist, fishArr[i].life);
        }
//        copyArr = fishArr.clone(); //잘못된 깊은 복사

        for(int i=0; i<4; i++) { //상어는 최대 3칸거리만 움직일 수 있음.
            x += dx[dist];
            y += dy[dist];
            if(x<0 || y<0 || x>3 || y>3) { //기저 조건 : 상어의 진행 경로에서 더 이상 먹을 수 있는 물고기가 없을 때
                ans = Math.max(ans, sum); //결과값 갱신
                return;
            }

            Fish f = map[x][y]; //먹을 물고기의 정보
            if(f.life) { //이동할 좌표의 물고기가 살아있는 경우
                f.life = false; //물고기 냠냠
                fishArr[f.num].life = false; //배열 값 또한 변경
                move(map, x, y); //물고기 이동
                dfs(x, y, sum+f.num, f.dist); //재귀 호출
                for(int j=0; j<4; j++) { //깊은 복사 //map = copy 하는 순간 둘은 동일객체가 되어 덮어쓰기 되버림.
                    map[j] = copy[j].clone();
                }
                //배열 또한 원복
                for(int j=1; j<17; j++) { //깊은 복사 //배열도 똑같이 fishArr = copyArr 하는 순간 덮어쓰기 되버림.
                    fishArr[j] = new Fish(copyArr[j].x, copyArr[j].y, copyArr[j].num, copyArr[j].dist, copyArr[j].life);
                }
            }
        }
    }

    private static void move(Fish[][] map, int x, int y) { //맵, 상어좌표
        for(int i=1; i<fishArr.length; i++) {
            Fish f = fishArr[i];
            if(f.life) { //물고기가 살아있는 경우에만
                for(int j=0; j<8; j++) { //방향을 바꿔가며
                    int dist = (f.dist + j) % 8;
                    int nx = f.x + dx[dist];
                    int ny = f.y + dy[dist];
                    if (nx < 0 || ny < 0 || nx > 3 || ny > 3) continue;//맵 밖 전처리
                    if (nx == x && ny == y) continue; //상어위치 전처리
                    //이동 가능한 시점 //첫 시도는 물고기가 살아있거나 없거나로 전처리했는데 다시생각해보니 그럴필요없이 로직은 동일함...
                    int nextFish = map[nx][ny].num; //이동대상 물고기 번호
                    f.dist = dist; //현재 물고기의 방향 갱신
                    //SWAP 주의할 점 : 배열의 값도 변경해줘야함
                    Fish temp = new Fish(map[nx][ny].x, map[nx][ny].y, map[nx][ny].num, map[nx][ny].dist, map[nx][ny].life); //다음위치의 객체를 임시저장
                    map[nx][ny] = new Fish(f.x, f.y, f.num, f.dist, f.life); //다음위치에 현재 객체를 저장
                    fishArr[nextFish].x = f.x;
                    fishArr[nextFish].y = f.y;
                    map[f.x][f.y] = temp;
                    fishArr[i].x = nx;
                    fishArr[i].y = ny;
                    fishArr[i].dist = dist;
                    break;
                }
            }
        }
    }
}

class Fish { //좌표, 번호, 방향, 생사유무
    int x;
    int y;
    int num;
    int dist;
    boolean life;
    Fish(int x, int y, int num, int dist, boolean life) {
        this.x = x;
        this.y = y;
        this.num = num;
        this.dist = dist;
        this.life = life;
    }
}
