import sys
from collections import deque

input = sys.stdin.readline

n, m = map(int, input().split())

graph = [[] for _ in range(n + 1)]


def bfs(start, end):
    visited = [False] * (n + 1)
    visited[start] = True
    queue = deque()
    queue.append([start, 0])
    while queue:
        node, cost = queue.popleft()
        if node == end:
            return cost
        for nowNode, nowCost in graph[node]:
            if not visited[nowNode]:
                visited[nowNode] = True
                queue.append([nowNode, nowCost + cost])


for i in range(n - 1):
    n1, n2, cost = map(int, input().split())
    graph[n1].append([n2, cost])
    graph[n2].append([n1, cost])

for i in range(m):
    start, end = map(int, input().split())
    print(bfs(start, end))
