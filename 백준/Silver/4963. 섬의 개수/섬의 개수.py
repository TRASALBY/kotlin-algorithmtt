import sys
from collections import deque

input = sys.stdin.readline

dx = [0, 0, 1, -1, 1, -1, 1, -1]
dy = [1, -1, 0, 0, 1, -1, -1, 1]

def bfs(start):
    queue = deque()
    queue.append(start)

    while queue:
        x, y = queue.popleft()
        for i in range(len(dx)):
            nx = x + dx[i]
            ny = y + dy[i]

            if 0 <= nx < w and 0 <= ny < h and board[ny][nx] == 1:
                board[ny][nx] = 0
                queue.append((nx,ny))


while True:
    w, h = map(int, input().strip().split())
    if w == 0 and h == 0:
        break
    board = [list(map(int, input().strip().split())) for _ in range(h)]
    answer = 0
    for y in range(h):
        for x in range(w):
            if board[y][x] == 1:
                bfs((x, y))
                answer += 1

    print(answer)
