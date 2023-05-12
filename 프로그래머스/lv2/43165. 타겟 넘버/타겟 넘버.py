from collections import deque


def solution(numbers, target):
    answer = 0
    queue = deque()
    
    queue.append((numbers[0], 0))
    queue.append((-numbers[0],0))
    
    
    while queue:
        nowNum, depth = queue.popleft()
        if(depth < len(numbers) - 1):
            queue.append((nowNum + numbers[depth+1], depth+1))
            queue.append((nowNum - numbers[depth+1], depth+1))
        else:
            if nowNum == target:
                answer +=1
    return answer