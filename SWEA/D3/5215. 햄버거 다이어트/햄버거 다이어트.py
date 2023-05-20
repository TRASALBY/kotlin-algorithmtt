global n
global l
global food
global maxScore

T = int(input().strip())


def dfs(depth, s, k):
    global maxScore
    if k > l:
        return
    maxScore = max(maxScore, s)
    if depth == n:
        return
    dfs(depth + 1, s + food[depth][0], k + food[depth][1])
    dfs(depth + 1, s, k)


for t in range(1, T + 1):
    n, l = map(int, input().strip().split())
    maxScore = 0
    food = []
    for i in range(n):
        s, k = map(int, input().strip().split())
        food.append((s, k))
    dfs(0, 0, 0)
    print(f"#{t} {maxScore}")