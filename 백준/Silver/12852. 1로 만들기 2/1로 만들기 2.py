import sys

input = sys.stdin.readline

x = int(input().strip())

dp = [(0, 0)] * 1000001

for i in range(2,x+1):
    dp[i] = (dp[i - 1][0] + 1, i - 1)
    if i % 2 == 0:
        dp[i] = min(dp[i], (dp[i // 2][0] + 1, i // 2), key=lambda y: y[0])
    if i % 3 == 0:
        dp[i] = min(dp[i], (dp[i // 3][0] + 1, i // 3), key=lambda y: y[0])

now = x
nums = []
while now != 1:
    nums.append(now)
    now = dp[now][1]
nums.append(1)
print(dp[x][0])
print(*nums)
