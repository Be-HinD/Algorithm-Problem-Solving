import java.io.*;
import java.util.*;

// BOJ_18808
public class Main {
    static int N, M, K, res;
    static int[][] map;
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());   // 세로
        M = Integer.parseInt(st.nextToken());   // 가로
        K = Integer.parseInt(st.nextToken());   // 스티커 개수

        /**
         * 스티커의 개수 <= 100
         * 1. 입력
         * 2. 노트북(40*40)에 붙일 수 있는 공간 탐색 -> 최대 4번
         * 구현 로직
         * 1. 붙일 수 있는 공간 탐색
         * 2. 회전 로직
         *
         * **/

        map = new int[N][M];
        for(int s=0; s<K; s++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            int[][] sticker = new int[n][m];
            for(int i=0; i<n; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<m; j++) {
                    sticker[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            boolean flag = false;

            // 기본 스티커
            outer:for(int i=0; i<N; i++) {
                for(int j=0; j<M; j++) {
                    // i, j가 시작지점
                    if(attach(i, j, sticker)) {
                        inputChange(i,j,sticker);
                        flag = true;
                        break outer;
                    }
                }
            }

            if(flag) continue;

            // 회전
            outer:for(int d=0; d<3; d++) {
                sticker = rotate(sticker);
//                printSticker(sticker);
                for(int i=0; i<N; i++) {
                    for(int j=0; j<M; j++) {
                        // i, j가 시작지점
                        if(attach(i, j, sticker)) {
                            inputChange(i,j,sticker);
                            break outer;
                        }
                    }
                }
            }
        }

//        for(int i=0; i<N; i++) {
//            System.out.println(Arrays.toString(map[i]));
//        }

        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(map[i][j] == 1) {
                    res++;
                }
            }
        }

        System.out.println(res);
    }

    static int[][] rotate(int[][] origin) {
        int[][] result = new int[origin[0].length][origin.length];

        for(int i=0; i<origin.length; i++) {
            for(int j=0; j<origin[0].length; j++) {
                result[j][origin.length-1-i] = origin[i][j];
            }
        }

        return result;
    }

    static boolean attach(int r, int c, int[][] sticker) {
        for(int x=0; x<sticker.length; x++) {
            for(int y=0; y<sticker[0].length; y++) {
                int nx = r + x;
                int ny = c + y;
                if(!isRange(nx,ny) || (sticker[x][y] == 1 && map[nx][ny] == 1)) return false;
            }
        }

        return true;
    }

    static void inputChange(int r, int c, int[][] sticker) {
        for(int i=0; i<sticker.length; i++) {
            for(int j=0; j<sticker[0].length; j++) {
                int nx = r + i;
                int ny = c + j;
                if(sticker[i][j] == 1) map[nx][ny] = 1;
            }
        }
    }

    static void printSticker(int[][] sticker) {
        for(int i=0; i<sticker.length; i++) {
            System.out.println(Arrays.toString(sticker[i]));
        }

        System.out.println("-----------------");
    }
    static boolean isRange(int x, int y) {
        if(x<0 || y<0 || x>=N || y>=M) return false;
        return true;
    }
}