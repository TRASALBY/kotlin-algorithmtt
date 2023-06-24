import sys

input = sys.stdin.readline

arr1 = input().strip()
arr2 = input().strip()

len1 = len(arr1)
len2 = len(arr2)

dp = [[0 for _ in range(len1+1)] for _ in range(len2+1)]
for i in range(1,len2+1):
    for j in range(1,len1+1):
        if arr2[i-1] == arr1[j-1]:
            dp[i][j] = dp[i-1][j-1] + 1
        else:
            dp[i][j] = max(dp[i][j-1],dp[i-1][j])

print(dp[-1][-1])