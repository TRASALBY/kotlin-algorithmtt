from collections import deque

testCase = 10

for t in range(1, testCase + 1):
    _ = input()

    queue = deque(list(map(int, input().strip().split())))
    i = 1
    while True:
        if i > 5:
            i = 1
        cur = queue.popleft()
        cur -= i
        if cur <= 0:
            cur = 0
            queue.append(cur)
            break
        queue.append(cur)
        i+=1

    print(f"#{t}", end=" ")
    print(*queue)