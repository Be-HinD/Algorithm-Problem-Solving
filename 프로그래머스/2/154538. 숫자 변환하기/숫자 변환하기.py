from collections import deque


def solution(x, y, n):
    def bfs(x, y, n):
        q = deque()
        dist[x] = 0
        q.append(x)

        while q:
            x = q.popleft()
            if 0 <= x + n <= 1000000 and dist[x + n] == 0:
                dist[x + n] = dist[x] + 1
                q.append(x + n)
            if 0 <= x * 2 <= 1000000 and dist[x * 2] == 0:
                dist[x * 2] = dist[x] + 1
                q.append(x * 2)
            if 0 <= x * 3 <= 1000000 and dist[x * 3] == 0:
                dist[x * 3] = dist[x] + 1
                q.append(x * 3)

    dist = [0] * 1000001
    bfs(x,y,n)

    if x == y:
        return 0
    if dist[y] == 0:
        return -1
    return dist[y]
