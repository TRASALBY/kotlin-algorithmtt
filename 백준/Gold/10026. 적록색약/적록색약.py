import sys
from collections import deque

input = sys.stdin.readline

dx = [0, 0, -1, 1]
dy = [1, -1, 0, 0]


def bfs(queue, gragh, visited, x, y):
    queue.append([x, y])
    target = graph[y][x]

    while queue:
        x, y = queue.popleft()
        for i in range(4):
            nx = dx[i] + x
            ny = dy[i] + y

            if 0 <= nx < n and 0 <= ny < n and not visited[ny][nx] and gragh[ny][nx] == target:
                visited[ny][nx] = 1
                queue.append([nx, ny])


n = int(input())

queue = deque()
graph = []
visited = [[0] * n for _ in range(n)]
for i in range(n):
    graph.append(list(input().strip()))

answer1 = 0
for i in range(n):
    for j in range(n):
        if not visited[i][j]:
            bfs(queue, graph, visited, j, i)
            answer1 += 1

for i in range(n):
    for j in range(n):
        if graph[j][i] == 'G':
            graph[j][i] = 'R'

answer2 = 0
visited = [[0] * n for _ in range(n)]
queue.clear()
for i in range(n):
    for j in range(n):
        if not visited[i][j]:
            bfs(queue, graph, visited, j, i)
            answer2 += 1

print(answer1, answer2)