T = 10
# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.

for test_case in range(1, T + 1):
    answer = 0
    tc = input()
    graph = []
    for _ in range(100):
        a = list(input().strip().split())
        nowList = list(map(int, a))
        graph.append(nowList)
        answer = max(answer, sum(nowList))

    for i in range(100):
        nowSum = 0
        for j in range(100):
            nowSum += graph[j][i]
        answer = max(answer, nowSum)

    nowSum1 = 0
    nowSum2 = 0
    for i in range(100):
        nowSum1 += graph[i][i]
        nowSum2 += graph[99 - i][i]

    answer = max(answer, nowSum1, nowSum2)
    print(f"#{test_case} {answer}")
