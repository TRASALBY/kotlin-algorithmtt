import sys

input = sys.stdin.readline

n = int(input())
map = []


def printRecursion(x, y, n):
    now = map[y][x]
    global answer
    for nx in range(x, x + n):
        for ny in range(y, y + n):
            if map[ny][nx] != now:
                answer += "("
                printRecursion(x, y, n // 2)
                printRecursion(x + n // 2, y, n // 2)
                printRecursion(x, y + n // 2, n // 2)
                printRecursion(x + n // 2, y + n // 2, n // 2)
                answer += ")"
                return
    answer += now


for i in range(n):
    map.append((input().strip()))

answer = ""
printRecursion(0, 0, n)

print(answer)
