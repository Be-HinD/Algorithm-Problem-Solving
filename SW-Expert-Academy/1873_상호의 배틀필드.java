import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int tc, H, W, cnt;
	static char[][] map;
	static int[] dx = new int[] {-1,0,1,0}; //방향벡터
	static int[] dy = new int[] {0,1,0,-1}; //방향벡터
	static String commandline, input;
	static char command;
	static tank k9;
	static char point;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		tc = Integer.parseInt(st.nextToken());
		
		for (int t = 0; t < tc; t++) { //테스트 케이스 반복
			st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			//맵 입력
			map = new char[H][W];
			for(int i=0; i<H; i++) {
				st = new StringTokenizer(br.readLine()); //한줄씩 받고 charAt으로 한글자씩 입력
				input = st.nextToken();
				for(int j=0; j<W; j++) {
					map[i][j] = input.charAt(j);
					if(map[i][j] == '<') { //방향에 따른 객체 생성
						k9 = new tank(i,j,3);
						point = '<';
					} else if (map[i][j] == '^') { //방향에 따른 객체 생성
						k9 = new tank(i,j,0);
						point = '^';
					} else if (map[i][j] == 'v') { //방향에 따른 객체 생성
						k9 = new tank(i,j,2);
						point = 'v';
					} else if (map[i][j] == '>') { //방향에 따른 객체 생성
						k9 = new tank(i,j,1);
						point = '>';
					}
				}
			}
			//북 동 남 서
			cnt = Integer.parseInt(br.readLine()); //명령의 개수 (23)
			commandline = br.readLine(); //명령들의 집합
			int nx, ny;
			for(int i=0; i<cnt; i++) { //명령 반복
				command = commandline.charAt(i); //명령 한개씩 진행
				switch(command) { //들어온 명령에 따라
				case 'U': //up 위로
					k9.vector = 0; //벡터 재설정
					nx = k9.x + dx[0]; //다음 목표진행 좌표 입력
					ny = k9.y + dy[0]; //다음 목표진행 좌표 입력
					point = '^'; //방향에 따른 char 세팅
					map[k9.x][k9.y] = point; ////맵 상의 char 변경
					if(k9.vector == 0 || k9.vector == 2) { //맵 밖으로 NULL처리
						if(nx < 0 || nx > H-1 || ny < 0 || ny > W-1) break;
					} else {
						if(nx < 0 || nx > H-1 || ny < 0 || ny > W-1) break;
					}
					if(map[nx][ny] == '.') { //다음 진행좌표가 평지라면
						map[k9.x][k9.y] = '.'; //기존 좌표 평지로 변경
						map[nx][ny] = point; //다음좌표 char 변경
						k9.x = nx; //좌표 갱신
						k9.y = ny; //좌표 갱신
					}
					break;
				case 'D': //down 밑으로
					k9.vector = 2;
					nx = k9.x + dx[2];
					ny = k9.y + dy[2];
					point = 'v';
					map[k9.x][k9.y] = point;
					if(k9.vector == 0 || k9.vector == 2) {
						if(nx < 0 || nx > H-1 || ny < 0 || ny > W-1) break;
					} else {
						if(nx < 0 || nx > H-1 || ny < 0 || ny > W-1) break;
					}
					if(map[nx][ny] == '.') {
						map[k9.x][k9.y] = '.';
						map[nx][ny] = point;
						k9.x = nx;
						k9.y = ny;
					}
					break;
				case 'L': //Left 왼쪽
					k9.vector = 3;
					nx = k9.x + dx[3];
					ny = k9.y + dy[3];
					point = '<';
					map[k9.x][k9.y] = point;
					if(k9.vector == 0 || k9.vector == 2) {
						if(nx < 0 || nx > H-1 || ny < 0 || ny > W-1) break;
					} else {
						if(nx < 0 || nx > H-1 || ny < 0 || ny > W-1) break;
					}
					if(map[nx][ny] == '.') {
						map[k9.x][k9.y] = '.';
						map[nx][ny] = point;
						k9.x = nx;
						k9.y = ny;
					}
					break;
				case 'R': //Right 오른쪽
					k9.vector = 1;
					nx = k9.x + dx[1];
					ny = k9.y + dy[1];
					point = '>';
					map[k9.x][k9.y] = point;
					if(k9.vector == 0 || k9.vector == 2) {
						if(nx < 0 || nx > H-1 || ny < 0 || ny > W-1) break;
					} else {
						if(nx < 0 || nx > H-1 || ny < 0 || ny > W-1) break;
					}
					if(map[nx][ny] == '.') {
						map[k9.x][k9.y] = '.';
						map[nx][ny] = point;
						k9.x = nx;
						k9.y = ny;
					}
					break;
				case 'S': //포탄 발사 시
					nx = k9.x;
					ny = k9.y;
					while(true) {
						nx += dx[k9.vector]; //포탄의 진행좌표 갱신
						ny += dy[k9.vector]; //포탄의 진행좌표 갱신
						if(k9.vector == 0 || k9.vector == 2) { //포탄의 맵 밖 NULL처리
							if(nx < 0 || nx > H-1 || ny < 0 || ny > W-1) break;
						} else {
							if(nx < 0 || nx > H-1 || ny < 0 || ny > W-1) break;
						}
						
						if(map[nx][ny] == '*') { //벽돌
							map[nx][ny] = '.'; //평지가됨
							break;
						}
						if(map[nx][ny] == '#') { //강철
							break;
						}
					}
				}
			}
			System.out.print("#" + (t+1) + " "); //결과값 출력
			for(int i=0; i<H; i++) {
				for(int j=0; j<W; j++) {
					System.out.print(map[i][j]);
				}
				System.out.println();
			}
		}
	}
}

class tank { //맵 상 탱크 객체를 위한 클래스
	int x; //좌표 x
	int y; //좌표 y
	int vector; //방향벡터
	public tank(int x, int y, int vector) {
		super();
		this.x = x;
		this.y = y;
		this.vector = vector;
	}
}
