import java.io.*;
import java.util.*;

public class Main {
    static class Room {
        char type;
        int cost;
        List<Integer> nextRoom;
        public Room(char type, int cost, List<Integer> nextRoom) {
            this.type = type;
            this.cost = cost;
            this.nextRoom = nextRoom;
        }
    }
    static int n;
    static List<Room> list;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while(true) {
            n = Integer.parseInt(br.readLine());
            if(n == 0) break;

            list = new ArrayList<>();
            for(int i=0; i<n; i++) {
                st = new StringTokenizer(br.readLine());
                String type = st.nextToken();
                int cost = Integer.parseInt(st.nextToken());

                List<Integer> nextRoom = new ArrayList<>();
                while(true) {
                    int next = Integer.parseInt(st.nextToken());
                    if(next == 0) break;
                    nextRoom.add(next);
                }
                list.add(new Room(type.charAt(0), cost, nextRoom));
            }

            System.out.println(bfs() ? "Yes" : "No");
        }
    }

    static boolean bfs() {
        Queue<int[]> q = new ArrayDeque<>();
        int[] maxMoney = new int[n+1];
        Arrays.fill(maxMoney, -1);
        
        // 1번 방 진입 처리
        Room first = list.get(0);
        int startMoney = 0;
        
        if(first.type == 'T') {
            if(first.cost > 0) return false; // 시작 불가
            startMoney = 0; // 통행료 0이면 통과
        } else if(first.type == 'L') {
            startMoney = first.cost; // 레프리콘이 채워줌
        }
        // E 타입이면 startMoney = 0 그대로
        
        q.offer(new int[]{1, startMoney});
        maxMoney[1] = startMoney;

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int curRoom = cur[0];
            int curMoney = cur[1];

            if(curRoom == n) {
                return true;
            }

            Room room = list.get(curRoom-1);

            for(int next : room.nextRoom) {
                Room nextRoom = list.get(next-1);
                int nextMoney = curMoney;
                
                // 다음 방 타입에 따라 처리
                if(nextRoom.type == 'T') {
                    // 트롤: 통행료 필요
                    if(curMoney < nextRoom.cost) continue; // 돈 부족
                    nextMoney = curMoney - nextRoom.cost; // 통행료 차감
                } else if(nextRoom.type == 'L') {
                    // 레프리콘: 최소 금액 보장
                    nextMoney = Math.max(curMoney, nextRoom.cost);
                }
                // E 타입: nextMoney = curMoney 그대로
                
                // 더 많은 돈으로 방문했던 적이 있으면 스킵
                if(maxMoney[next] >= nextMoney) continue;
                
                maxMoney[next] = nextMoney;
                q.offer(new int[]{next, nextMoney});
            }
        }

        return false;
    }
}