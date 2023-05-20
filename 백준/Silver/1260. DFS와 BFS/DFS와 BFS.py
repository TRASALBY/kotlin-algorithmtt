import sys
from collections import deque

input = sys.stdin.readline

n, m, v = map(int, input().strip().split())

nodeList = [[] for _ in range(n + 1)]


def dfs(start: int):
    visited[start] = True
    print(start, end=" ")
    for node in nodeList[start]:
        if not visited[node]:
            dfs(node)


def bfs(start: int):
    queue = deque()
    queue.append(start)
    visited[start] = True
    while queue:
        cur = queue.popleft()
        print(cur, end=" ")
        for node in nodeList[cur]:
            if not visited[node]:
                visited[node] = True
                queue.append(node)


for i in range(m):
    n1, n2 = map(int, input().strip().split())
    nodeList[n1].append(n2)
    nodeList[n2].append(n1)

for i in range(1, n + 1):
    nodeList[i].sort()

visited = [False] * (n + 1)
dfs(v)
print()
visited = [False] * (n + 1)
bfs(v)
