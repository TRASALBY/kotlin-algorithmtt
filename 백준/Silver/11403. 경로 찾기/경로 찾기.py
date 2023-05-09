import sys

input = sys.stdin.readline

n = int(input())

graph = []

for i in range(n):
    graph.append(list(map(int, input().split())))

for k in range(n):
    for i in range(n):
        for j in range(n):
            if graph[i][j] == 0 and graph[i][k] == 1 and graph[k][j]:
                graph[i][j] = 1

for i in range(n):
    print(*graph[i])
