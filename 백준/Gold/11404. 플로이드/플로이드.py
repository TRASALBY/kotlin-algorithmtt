import sys

input = sys.stdin.readline

n = int(input())
m = int(input())

inf = float('inf')
graph = [[inf for _ in range(n+1)] for _ in range(n+1)]
for i in range(n+1):
    graph[i][i] = 0

for _ in range(m):
    a,b,c = map(int,input().split())
    if graph[a][b] > c:
        graph[a][b] = c

for i in range(1,n+1):
    for a in range(1,n+1):
        for b in range(1,n+1):
            graph[a][b] = min(graph[a][b],graph[a][i]+graph[i][b])

for a in range(1,n+1):
    for b in range(1,n+1):
        if graph[a][b] == inf:
            graph[a][b] = 0
        print(graph[a][b],end=" ")
    print()