import sys
input = sys.stdin.readline

global answer
answer = ''
def quad(arr):
    global answer

    n = len(arr)//2

    arr1 = []
    for i in range(n):
        arr1.append(arr[i][:n])
    s = sum(sum(arr1,[]))

    if s == len(arr1) ** 2:
        answer += '1'
    elif s == 0:
        answer += '0'
    else:
        answer += '('
        quad(arr1)
        answer += ')'

    arr2 = []
    for i in range(n):
        arr2.append(arr[i][n:])
    s = sum(sum(arr2,[]))
    if s == len(arr2) ** 2:
        answer += '1'
    elif s == 0:
        answer += '0'
    else:
        answer += '('
        quad(arr2)
        answer += ')'

    arr3 = []
    for i in range(n,2*n):
        arr3.append(arr[i][:n])
    s = sum(sum(arr3,[]))
    if s == len(arr3) ** 2:
        answer += '1'
    elif s == 0:
        answer += '0'
    else:
        answer += '('
        quad(arr3)
        answer += ')'

    arr4 = []
    for i in range(n,2*n):
        arr4.append(arr[i][n:])
    s = sum(sum(arr4,[]))
    if s == len(arr4) ** 2:
        answer += '1'
    elif s == 0:
        answer += '0'
    else:
        answer += '('
        quad(arr4)
        answer += ')'


n = int(input())
graph = []
for i in range(n):
    graph.append(list(map(int,list(str(input().strip())))))

s = sum(sum(graph,[]))
if s == len(graph) ** 2:
    answer += '1'
elif s == 0:
    answer += '0'
else:
    answer += '('
    quad(graph)
    answer += ')'
print(answer)


