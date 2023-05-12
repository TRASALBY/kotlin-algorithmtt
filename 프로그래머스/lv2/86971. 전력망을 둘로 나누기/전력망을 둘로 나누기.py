from collections import deque

def bfs(start, n):
    queue = deque()
    queue.append(start)
    visited = [0] * (n + 1)
    visited[start] = 1
    
    cnt = 1
    
    while queue:
        cur = queue.popleft()
        for i in range(1, n+1):
            if not visited[i] and graph[cur][i] == 1:
                visited[i] = 1
                queue.append(i)
                cnt += 1
        
    return cnt
def cutWire(last, now):
    n1, n2 = last
    n3, n4 = now
    
    graph[n1][n2] = 1
    graph[n2][n1] = 1
    graph[n3][n4] = 0
    graph[n4][n3] = 0
    
def solution(n, wires):
    global graph 
    answer = -1
    
    
    graph = [[0] * (n+1) for _ in range(n+1)]
    
    for wire in wires:
        n1, n2 = wire
        graph[n1][n2] = 1
        graph[n2][n1] = 1
        
    lastWire = wires[0]
    graph[lastWire[0]][lastWire[1]] = 0
    graph[lastWire[1]][lastWire[0]] = 0
    
    minDiff = abs(n - bfs(1, n) * 2)
    for i in range(1, len(wires)):
        nowWire = wires[i]
        cutWire(lastWire, nowWire)
        lastWire = nowWire
        
        minDiff = min(minDiff, abs(n - bfs(1, n) * 2))
        print(nowWire,  bfs(1, n))
    return minDiff