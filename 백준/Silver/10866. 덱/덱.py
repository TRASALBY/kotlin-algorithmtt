import sys
from collections import deque

input = sys.stdin.readline

n = int(input())

q = deque()
for i in range(n):
    a = input().split()
    if a[0] == "push_back":
        q.append(int(a[1]))
    elif a[0] == "push_front":
        q.appendleft(int(a[1]))
    elif a[0] == "pop_front":
        if len(q) == 0:
            print(-1)
        else:
            print(q.popleft())
    elif a[0] == "pop_back":
        if len(q) == 0:
            print(-1)
        else:
            print(q.pop())
    elif a[0] == "size":
        print(len(q))
    elif a[0] == "empty":
        if len(q) == 0:
            print(1)
        else:
            print(0)
    elif a[0] == "front":
        if len(q) == 0:
            print(-1)
        else:
            print(q[0])
    elif a[0] == "back":
        if len(q) == 0:
            print(-1)
        else:
            print(q[-1])
     