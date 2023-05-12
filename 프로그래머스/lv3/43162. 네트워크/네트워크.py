from collections import deque

def bfs(start, computers):
    global visited
    
    queue = deque()
    queue.append(start)
    
    while queue:
        cur = queue.popleft()
        
        for i in range(len(computers)):
            if computers[cur][i] == 1 and not visited[i]:
                queue.append(i)
                visited[i] = True


def solution(n, computers):
    global visited
    
    answer = 0
    visited = [False] * n
    
    
    for i in range(n):
        if not visited[i]:
            bfs(i, computers)
            answer +=1
    
    return answer