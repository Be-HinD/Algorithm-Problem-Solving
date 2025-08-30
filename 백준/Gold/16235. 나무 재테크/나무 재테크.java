import java.io.*;
import java.util.*;

//BOJ_16235
public class Main {
    static class Ground {
        int idx;
        int snack;  //양분 변수명을 뭐로 해야하지?
        public Ground(int idx, int snack) {
            this.idx = idx;
            this.snack = snack;
        }
    }
    static class Tree {
        int age;
        public Tree(int age) {
            this.age = age;
        }
    }
    static int n, m, k, res;
    static int[][] A;
    static Ground[][] map;
    static List<Tree>[] treeList;
    static Map<Integer, int[]> reverseTrace;    // treeList -> map 역방향 추적을 위한 객체
    static int[] dx = new int[]{0,0,1,-1,-1,1,1,-1};
    static int[] dy = new int[]{1,-1,0,0,-1,1,-1,1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        /**
         * 조건
         * 봄 : 나이만큼 양분 -/ 나이 +1, 어린 나무부터 (나이 관리), 못먹으면 죽음
         * 여름 : 죽은 나무 -> 양분 (나무나이/2)
         * 가을 : 나이가 5의 배수인 나무 번식 -> 인접 8칸 나이 1 나무 생성
         * 겨울 : 양분 추가 (입력값)
         * 로직 --
         * arr[n][n] 배열에는 나무 리스트를 가리키는 IDX와 남은 양분을 기록할 GROUND 객체로 관리
         * 나무 리스트는 매번 정렬 이후 순차적으로 성장 혹은 삭제
         * **/

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        A = new int[n][n];
        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        map = new Ground[n][n];
        reverseTrace = new HashMap<>();

        int p = 0;
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                map[i][j] = new Ground(p, 5); //첫 양분은 5로 시작
                reverseTrace.put(p++, new int[]{i,j});
            }
        }

        listInit();

        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;   //1,1부터 시작 대응
            int y = Integer.parseInt(st.nextToken()) - 1;
            int age = Integer.parseInt(st.nextToken());
            int index = map[x][y].idx;
            treeList[index].add(new Tree(age));
        }

        for(int i=0; i<k; i++) {
            // TODO : 봄 + 여름
            springWithSummer();

            // TODO : 가을
            fall();

            // TODO : 겨울
            winter();

        }

        res = extractResult();

        System.out.println(res);

    }

    @SuppressWarnings("unchecked")  //백준 오류
    static void listInit() {
        treeList = new ArrayList[n*n];
        for(int i=0; i<n*n; i++) treeList[i] = new ArrayList<>();
    }

    static void springWithSummer() {
        // 나무리스트 전체 순회하면서 체크

        for(int i=0; i<n*n; i++) {
            List<Tree> cur = treeList[i];
            if(cur.isEmpty()) continue;

            int[] mapIdx = reverseTrace.get(i);

            //나이순 정렬
            Collections.sort(cur, new Comparator<Tree>() {
                @Override
                public int compare(Tree o1, Tree o2) {
                    return o1.age - o2.age;
                }
            });

            int plus = 0;
            for(int j=0; j<cur.size(); j++) {
                Tree t = cur.get(j);
                int snack = map[mapIdx[0]][mapIdx[1]].snack; //양분 스니펫

                if(snack < t.age) {
                    //죽어야함.
                    plus += t.age/2;
                    cur.remove(j);
                    j--;
                }
                else {
                    map[mapIdx[0]][mapIdx[1]].snack -= t.age;
                    t.age++;
                }
            }
            map[mapIdx[0]][mapIdx[1]].snack += plus;    //2단계 스킵
        }
    }

    static void fall() {
        for(int i=0; i<n*n; i++) {
            List<Tree> cur = treeList[i];
            if(cur.isEmpty()) continue;

            int[] mapIdx = reverseTrace.get(i);

            for(int j=0; j<cur.size(); j++) {
                Tree t = cur.get(j);

                if(t.age % 5 == 0) {
                    //5의 배수라면 인접 8칸 나이 1인 나무 생성
                    for(int d=0; d<8; d++) {
                        int nx = mapIdx[0] + dx[d];
                        int ny = mapIdx[1] + dy[d];
                        if(nx<0 || ny<0 || nx>=n || ny>=n) continue;

                        int p = map[nx][ny].idx;
                        treeList[p].add(new Tree(1));
                    }
                }
            }
        }
    }

    static void winter() {
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                map[i][j].snack += A[i][j];
            }
        }
    }

    static int extractResult() {
        int cnt = 0;
        for(List<Tree> list : treeList) {
            cnt += list.size();
        }

        return cnt;
    }
}