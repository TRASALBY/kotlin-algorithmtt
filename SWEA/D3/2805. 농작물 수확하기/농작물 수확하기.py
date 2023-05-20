testCase = int(input())

for t in range(1, testCase + 1):
    n = int(input())

    farm = [list(map(int, list(input().strip()))) for _ in range(n)]

    answer = 0
    half = n // 2
    s = half
    e = half + 1
    for i in range(n):
        answer += sum(farm[i][s: e])
        if i < half:
            s -= 1
            e += 1
        else:
            s += 1
            e -= 1
    print(f"#{t} {answer}")
